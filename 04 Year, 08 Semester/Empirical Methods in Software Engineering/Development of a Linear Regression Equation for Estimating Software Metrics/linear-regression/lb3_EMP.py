import numpy as np
import matplotlib.pyplot as plt
from scipy import stats

# X – це вхідні дані (фактор), які є незалежними змінними. # трудомісткість
# Y – це залежні змінні (метрики), які є результатами або вимірюваннями, що залежать від X. час розробки 

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

alpha = 0.05
n = len(Y)

# Обчислення середніх значень
mean_X = np.mean(X)
mean_Y = np.mean(Y)

# Розрахунок коефіцієнта нахилу (b1) і вільного члена (b0)
# За формулами для однофакторної регресії
numerator = np.sum((X - mean_X) * (Y - mean_Y))
denom = np.sum((X - mean_X) ** 2)
b1 = numerator / denom
b0 = mean_Y - b1 * mean_X

print(f"Оцінка параметрів регресії: b0 = {b0:.4f},  b1 = {b1:.4f}")

# Обчислення прогнозованих значень Y_hat
Y_hat = b0 + b1 * X

# (err = Y - Y_hat) 3.16
residuals = Y - Y_hat
print(residuals.tolist())

# Розрахунок коефіцієнта детермінації R^2
SST = np.sum((Y - mean_Y) ** 2)      # загальна сума квадратів
SSR = np.sum((Y_hat - mean_Y) ** 2)    # сума квадратів регресії
SSE = np.sum((Y - Y_hat) ** 2)         # сума квадратів залишків

R2 = 1 - (SSE / SST)
print(f"Коефіцієнт детермінації R^2 = {R2:.4f}")

# Обчислення MRE (Magnitude of Relative Error) для кожного спостереження
MRE = np.abs((Y - Y_hat) / Y)
MMRE = np.mean(MRE)
print(f"Середня величина відносної похибки MMRE = {MMRE:.4f}")

# Обчислення PRED(0.25): процент спостережень з MRE ≤ 0.25
pred_threshold = 0.25
PRED = np.sum(MRE <= pred_threshold) / n * 100  # у відсотках
print(f"PRED(0.25) = {PRED:.2f}%")

# Перевірка нормальності залишків за допомогою тесту Шапіро-Вілка
stat, p_value = stats.shapiro(residuals)
print(f"Результат тесту Шапіро-Вілка: статистика = {stat:.4f}, p-значення = {p_value:.10f}")
if p_value > 0.05:
    print("Гіпотеза про нормальність розподілу випадкової величини ε не відхиляється (рівень значущості 0.05).")
else:
    print("Гіпотеза про нормальність розподілу випадкової величини ε відхиляється (рівень значущості 0.05).")

# T-тест для коефіцієнтів
s = np.sqrt(SSE / (n - 2))  
se_b1 = s / np.sqrt(denom)                   # Стандартна похибка b1
se_b0 = s * np.sqrt(1/n + mean_X**2 / denom) # Стандартна похибка b0

t_b1 = b1 / se_b1  # t-статистика для b1
t_b0 = b0 / se_b0  # t-статистика для b0

# Критичне значення t-розподілу (α=0.05, двосторонній)
t_critical = stats.t.ppf(1 - alpha / 2, df=n-2)

# F-тест
F_stat = (SSR / 1) / (SSE / (n - 2))  # F = (MSR / MSE)
F_critical = stats.f.ppf(1 - alpha, 1, n-2)  # Критичне значення F

print("")
print("Результати T-тестів:")
print(f"t-статистика для b0: {t_b0:.4f}, критичне значення: {t_critical:.4f}")
print(f"t-статистика для b1: {t_b1:.4f}, критичне значення: {t_critical:.4f}")
print("\nРезультат F-тесту:")
print(f"F-статистика: {F_stat:.4f}, критичне значення: {F_critical:.4f}")

plt.figure(figsize=(8, 6))
plt.scatter(X, Y, color='blue', label="Емпіричні дані")
# sorted_idx = np.argsort(X)
# X_sorted = X[sorted_idx]
# Y_hat_sorted = Y_hat[sorted_idx]
# plt.plot(X_sorted, Y_hat_sorted, color='red')
plt.plot(X, Y_hat, color='red', label="Лінія регресії")
plt.xlabel('X')
plt.ylabel('Y')
plt.title('Лінійна регресія для оцінювання метрик ПЗ')
plt.legend()
plt.grid(True)
plt.show()
