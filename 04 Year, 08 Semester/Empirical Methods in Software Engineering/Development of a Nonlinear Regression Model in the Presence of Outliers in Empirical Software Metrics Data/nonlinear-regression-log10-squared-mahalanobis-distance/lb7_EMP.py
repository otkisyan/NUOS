import numpy as np
from scipy.stats import chi2, t
import matplotlib.pyplot as plt
import pandas as pd

X_orig = np.array([
    6020, 3570, 2496, 191, 406, 14520, 14430, 650, 225, 183,
    1080, 2464, 1041, 528, 212, 7060, 903, 4164, 633, 665,
    544, 5732, 1238, 1586, 281, 220, 851, 2391, 780, 591,
    391, 2263, 4112, 293, 894, 246, 1440, 1200, 1050, 3193,
    763, 2620, 9296, 7800, 3625, 170, 3800, 784, 1000, 500,
    601, 440
])
Y_orig = np.array([
    4, 12, 6.4, 7, 8, 19, 15, 6, 10, 5,
    3, 14, 7, 5, 5, 12, 16, 9, 9, 13,
    6, 8, 13, 6, 3, 2, 3.5, 7, 7, 8,
    3, 11, 6, 2, 19, 8, 8, 16.5, 10, 11,
    18, 8, 14, 22, 30, 3, 9, 7, 7, 4,
    10, 6
])

# Копії для роботи
X, Y = X_orig.copy(), Y_orig.copy()

# Параметри
alpha_chi2 = 0.005
threshold = chi2.ppf(1 - alpha_chi2, df=2)
print(f"Chi2: {threshold}")

def mahalanobis_squared(x, mean, inv_cov):
    delta = x - mean
    return delta.T @ inv_cov @ delta

# Ітеративне видалення викидів
iteration = 0
while True:
    Z_X = np.log10(X)
    Z_Y = np.log10(Y)
    data_norm = np.column_stack((Z_X, Z_Y))
    mean_vec = np.mean(data_norm, axis=0)
    cov_mat = np.cov(data_norm, rowvar=False)
    inv_cov_mat = np.linalg.inv(cov_mat)

    m_dist_squared = np.array([mahalanobis_squared(x, mean_vec, inv_cov_mat) for x in data_norm])
    outlier_indices = np.where(m_dist_squared > threshold)[0]

    df = pd.DataFrame({
        'X': X,
        'Y': Y,
        'mahalanobis_sq': m_dist_squared
    })
    print(df)
    print(f"Ітерація {iteration}: знайдено {len(outlier_indices)} викидів")
    if len(outlier_indices) == 0:
        break

    print("Координати викидів (X, Y):")
    for idx in outlier_indices:
        print(f"({X[idx]}, {Y[idx]})")
    print(f"Значення викидів: {Y[outlier_indices]}")
    # Видалення викидів
    X = np.delete(X, outlier_indices)
    Y = np.delete(Y, outlier_indices)
    iteration += 1

while True:
    
    Z_X = np.log10(X)
    Z_Y = np.log10(Y)

    mean_Zx = np.mean(Z_X)
    mean_Zy = np.mean(Z_Y)
    b1 = np.sum((Z_X - mean_Zx) * (Z_Y - mean_Zy)) / np.sum((Z_X - mean_Zx) ** 2)
    b0 = mean_Zy - b1 * mean_Zx

    Z_Y_hat = b0 + b1 * Z_X
    print("\nЛінійна модель:")
    print(f"Z_Y = {b0:.4f} + {b1:.4f} * Z_X")
    print("\nНелінійна модель:")
    print(f"Y = {10**b0:.4f} * X^{b1:.4f}")

    Y_hat_nonlinear = 10**b0 * X**b1
    SST = np.sum((Y - np.mean(Y))**2)
    SSE = np.sum((Y - Y_hat_nonlinear)**2)
    R2 = 1 - SSE / SST
    MRE = np.abs((Y - Y_hat_nonlinear) / Y)
    MMRE = np.mean(MRE)
    # Считает процент точных предсказаний, у которых относительная ошибка не превышает 25%.
    # MRE <= 0.25 логическое сравнение, которое возвращает массив True/False, 
    # где True — те предсказания, ошибка которых не превышает 25%.
    PRED_25 = np.sum(MRE <= 0.25) / len(Y) * 100

    print(f"\nR²: {R2:.4f}")
    print(f"MMRE: {MMRE:.4f}")
    print(f"PRED(0.25): {PRED_25:.2f}%")

    # Побудова довірчого інтервалу та інтервалу передбачення
    n = len(Z_Y)
    # m = 2 - размерность пространства признаков (две переменные: log10(X) и log10(Y)).
    m = 2
    t_crit = t.ppf(1 - 0.05/2, df=n - m)
    SE_regression = np.sqrt(np.sum((Z_Y - Z_Y_hat)**2) / (n - 2))
    SE_confidence = SE_regression * np.sqrt(1/n + (Z_X - np.mean(Z_X))**2 / np.sum((Z_X - np.mean(Z_X))**2))
    SE_prediction = SE_regression * np.sqrt(1 + 1/n + (Z_X - np.mean(Z_X))**2 / np.sum((Z_X - np.mean(Z_X))**2))

    conf_lower = Z_Y_hat - t_crit * SE_confidence
    conf_upper = Z_Y_hat + t_crit * SE_confidence
    pred_lower = Z_Y_hat - t_crit * SE_prediction
    pred_upper = Z_Y_hat + t_crit * SE_prediction

    Y_conf_lower = 10**conf_lower
    Y_conf_upper = 10**conf_upper
    Y_pred_lower = 10**pred_lower
    Y_pred_upper = 10**pred_upper

    outlier_indices = np.where((Y < Y_pred_lower) | (Y > Y_pred_upper))[0]
    print(f"Ітерація {iteration}: Знайдено {len(outlier_indices)} викидів за межами інтервалу передбачення нелінійної регресії")
    if len(outlier_indices) == 0:
        break
    
    for idx in outlier_indices:
        print(f"Викид: X = {X[idx]}, Y = {Y[idx]}")

    # Видалення викидів
    X = np.delete(X, outlier_indices)
    Y = np.delete(Y, outlier_indices)
    iteration += 1

# Графік
sorted_idx = np.argsort(X)
X_sorted = X[sorted_idx]
Y_hat_sorted = Y_hat_nonlinear[sorted_idx]

plt.figure(figsize=(16, 9))
plt.scatter(X, Y, label="Очищені дані", color="blue", alpha=0.7)
plt.plot(X_sorted, Y_hat_sorted, color="red", label="Модель")
plt.fill_between(X_sorted, Y_conf_lower[sorted_idx], Y_conf_upper[sorted_idx], color="green", alpha=0.3, label="Довірчий інтервал")
plt.fill_between(X_sorted, Y_pred_lower[sorted_idx], Y_pred_upper[sorted_idx], color="orange", alpha=0.2, label="Інтервал передбачення")
plt.xlabel("X")
plt.ylabel("Y")
plt.title("Нелінійна модель після видалення викидів")
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.show()
