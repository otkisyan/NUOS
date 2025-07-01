import numpy as np
from scipy.stats import shapiro
import matplotlib.pyplot as plt

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
print(f"Оцінка параметрів регресії: b0 = {b0:.4f},  b1 = {b1:.4f}")

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
plt.figure(figsize=(12, 5))

# Графік для нормалізованих даних
plt.subplot(1, 2, 1)
plt.scatter(Z_X, Z_Y, label="Дані", alpha=0.7)
plt.plot(Z_X, Z_Y_hat, color="red", label="Лінійна регресія")
plt.xlabel("log10(X)")
plt.ylabel("log10(Y)")
plt.title("Лінійна модель для нормалізованих даних")
plt.legend()

sorted_indices = np.argsort(X)
X_sorted = X[sorted_indices]
Y_hat_sorted = Y_hat_nonlinear[sorted_indices]
# Графік для нелінійної моделі
plt.subplot(1, 2, 2)
plt.scatter(X, Y, label="Дані", alpha=0.7)

plt.plot(X_sorted, Y_hat_sorted, color="red", label="Нелінійний прогноз")
plt.xlabel("X")
plt.ylabel("Y")
plt.title("Нелінійна модель")
plt.legend()

plt.tight_layout()
plt.show()