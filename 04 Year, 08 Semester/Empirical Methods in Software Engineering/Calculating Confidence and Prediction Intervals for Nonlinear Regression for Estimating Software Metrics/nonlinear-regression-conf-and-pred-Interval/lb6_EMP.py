import numpy as np
from scipy.stats import shapiro
import matplotlib.pyplot as plt
import scipy.stats as stats

X = np.array([
    6020, 3570, 2496, 191, 406, 14520, 14430, 650, 225, 183,
    1080, 2464, 1041, 528, 212, 7060, 903, 4164, 633, 665,
    544, 5732, 1238, 1586, 281, 220, 851, 2391, 780, 591,
    391, 2263, 4112, 293, 894, 246, 1440, 1200, 1050, 3193,
    763, 2620, 9296, 7800, 3625, 170, 3800, 784, 1000, 500,
    601, 440
])

Y = np.array([
    4, 12, 6.4, 7, 8, 19, 15, 6, 10, 5,
    3, 14, 7, 5, 5, 12, 16, 9, 9, 13,
    6, 8, 13, 6, 3, 2, 3.5, 7, 7, 8,
    3, 11, 6, 2, 19, 8, 8, 16.5, 10, 11,
    18, 8, 14, 22, 30, 3, 9, 7, 7, 4,
    10, 6
])

Z_Y = np.log10(Y)
Z_X = np.log10(X)
n = len(Z_Y)
mean_Zx = np.mean(Z_X)
mean_Zy = np.mean(Z_Y)

# Обчислення коефіцієнтів b1 та b0 за формулами
numerator = np.sum((Z_X - mean_Zx) * (Z_Y - mean_Zy))
denominator = np.sum((Z_X - mean_Zx) ** 2)
b1 = numerator / denominator
b0 = mean_Zy - b1 * mean_Zx

print(f"Лінійна модель для нормалізованих даних log10: Z_Y = {b0:.4f} + {b1:.4f} * Z_X")
Z_Y_hat = b0 + b1 * Z_X
residuals = Z_Y - Z_Y_hat

# Тест Шапіро-Вілка
stat, p_value = shapiro(residuals)
print(f"Тест Шапіро-Вілка: p-значення = {p_value:.4f}")
if p_value > 0.05:
    print("ε нормально розподілені (p > 0.05)")
else:
    print("ε не нормально розподілені (p ≤ 0.05)")

# Y_hat_nonlinear = 10**(b0 + b1 * np.log10(X))
# степенева (степенная) регресія (power-law regression).
Y_hat_nonlinear = 10**b0 * X**b1

print(f"Нелінійна модель: Y = {10**b0:.4f} * X^b1")

# Коефіцієнт детермінації R²
SST = np.sum((Y - np.mean(Y))**2)
SSE = np.sum((Y - Y_hat_nonlinear)**2)
R2 = 1 - (SSE / SST)
print(f"R²: {R2:.4f}")

# MMRE та PRED(0.25)
# Magnitude of Relative Error
MRE = np.abs((Y - Y_hat_nonlinear) / Y)
MMRE = np.mean(MRE)
PRED_25 = np.sum(MRE <= 0.25) / n * 100
print(f"MMRE: {MMRE:.4f}")
print(f"PRED(0.25): {PRED_25:.2f}%")
alpha = 0.05  # рівень значущості
t_crit = stats.t.ppf(1 - alpha / 2, df=n-2)

# Стандартна помилка для передбачення
SE_regression = np.sqrt(np.sum((Z_Y - Z_Y_hat)**2) / (n - 2))
mean_ZX = np.mean(Z_X)
SE_confidence = SE_regression * np.sqrt(1/n + (Z_X - mean_ZX)**2 / np.sum((Z_X - mean_ZX)**2))
SE_prediction = SE_regression * np.sqrt(1 + 1/n + (Z_X - mean_ZX)**2 / np.sum((Z_X - mean_ZX)**2))

# Межі довірчого інтервалу (для середнього значення)
conf_lower = Z_Y_hat - t_crit * SE_confidence
conf_upper = Z_Y_hat + t_crit * SE_confidence

# Межі інтервалу передбачення (для нових значень)
pred_lower = Z_Y_hat - t_crit * SE_prediction
pred_upper = Z_Y_hat + t_crit * SE_prediction

# Переведення назад з логарифмічної шкали
Y_conf_lower = 10 ** conf_lower
Y_conf_upper = 10 ** conf_upper
Y_pred_lower = 10 ** pred_lower
Y_pred_upper = 10 ** pred_upper

sorted_indices = np.argsort(X)
X_sorted = X[sorted_indices]
Y_hat_sorted = Y_hat_nonlinear[sorted_indices]
# print(Y_conf_lower[sorted_indices])
# print(Y_conf_upper[sorted_indices])
# print(Y_pred_lower[sorted_indices])
# print(Y_pred_upper[sorted_indices])

# Графік для нелінійної моделі
plt.figure(figsize=(16, 9)) 
# plt.yscale("log")
plt.scatter(X, Y, label="Дані", alpha=0.7)
plt.plot(X_sorted, Y_hat_sorted, color="red", label="Нелінійна регресія")
plt.fill_between(X_sorted, Y_conf_lower[sorted_indices], Y_conf_upper[sorted_indices],
                 color="green", alpha=0.3, label="Довірчий інтервал")
plt.fill_between(X_sorted, Y_pred_lower[sorted_indices], Y_pred_upper[sorted_indices],
                 color="orange", alpha=0.2, label="Інтервал передбачення")
plt.xlabel("X")
plt.ylabel("Y")
plt.title("Нелінійна модель")
plt.legend()

plt.tight_layout()
plt.show()