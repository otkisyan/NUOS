import numpy as np
import matplotlib.pyplot as plt
from scipy import stats

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

# X = np.log10(X)
# Y = np.log10(Y)

n = len(Y)
mean_X = np.mean(X)
mean_Y = np.mean(Y)
numerator = np.sum((X - mean_X) * (Y - mean_Y))
denom = np.sum((X - mean_X) ** 2)
b1 = numerator / denom
b0 = mean_Y - b1 * mean_X
Y_hat = b0 + b1 * X
SSE = np.sum((Y - Y_hat) ** 2)
SST = np.sum((Y - mean_Y) ** 2)
R2 = 1 - (SSE / SST)

alpha = 0.95
S_Y = np.sqrt(SSE / (n - 2))          # Стандартна помилка регресії
S_xx = np.sum((X - mean_X)**2)        # Сума квадратів відхилень X
t_critical = stats.t.ppf((1 - alpha)/2, n-2)  # Критичне значення t-розподілу

# Розрахунок довірчих інтервалів та інтервалів передбачення
confidence_intervals = []
prediction_intervals = []

for xi in X:
    # Довірчий інтервал
    se_mean = S_Y * np.sqrt(1/n + (xi - mean_X)**2 / S_xx)
    ci = t_critical * se_mean
    confidence_intervals.append(ci)
    
    # Інтервал передбачення
    se_pred = S_Y * np.sqrt(1 + 1/n + (xi - mean_X)**2 / S_xx)
    pi = t_critical * se_pred
    prediction_intervals.append(pi)

confidence_intervals = np.array(confidence_intervals)
prediction_intervals = np.array(prediction_intervals)

plt.figure(figsize=(12, 7))

sorted_idx = np.argsort(X)
X_sorted = X[sorted_idx]
Y_hat_sorted = Y_hat[sorted_idx]
ci_sorted = confidence_intervals[sorted_idx]
pi_sorted = prediction_intervals[sorted_idx]

# Межі довірчого інтервалу
ci_lower = Y_hat_sorted - ci_sorted
ci_upper = Y_hat_sorted + ci_sorted

# Межі інтервалу передбачення
pi_lower = Y_hat_sorted - pi_sorted
pi_upper = Y_hat_sorted + pi_sorted

# print(ci_lower)
# print(ci_upper)
# print(pi_lower)
# print(pi_upper)

# Емпіричні дані
plt.scatter(X, Y, alpha=0.6, label='Емпіричні дані')

# Лінія регресії
plt.plot(X_sorted, Y_hat_sorted, color='red', linewidth=2, label='Лінія регресії')

# Довірчі інтервали
plt.fill_between(X_sorted, ci_lower, ci_upper, 
                 color='orange', alpha=0.3, label=f'{int(alpha * 100)}% Довірчий інтервал')

# Інтервали передбачення
plt.fill_between(X_sorted, pi_lower, pi_upper, 
                 color='green', alpha=0.2, label=f'{int(alpha * 100)}% Інтервал передбачення')


plt.xlabel('Фактор (X)', fontsize=12)
plt.ylabel('Метрика (Y)', fontsize=12)
plt.title('Лінійна регресія\n', fontsize=14)
plt.legend()
plt.grid(linestyle='--', alpha=0.7)
plt.tight_layout()

plt.show()