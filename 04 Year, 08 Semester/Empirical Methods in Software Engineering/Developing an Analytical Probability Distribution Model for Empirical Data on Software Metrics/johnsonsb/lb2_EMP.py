import numpy as np
import matplotlib.pyplot as plt
import scipy.stats as stats
from scipy.optimize import minimize
from tabulate import tabulate

# https://ru.statisticseasily.com/glossario/what-is-johnson-transformation/?utm_source=chatgpt.com
# https://www.kaggle.com/code/lexack/top-2-using-johnson-su-distribution

# Вхідні дані
data = np.array([
    6020, 3570, 2496, 191, 406, 14520, 14430, 650, 225, 183,
    1080, 2464, 1041, 528, 212, 7060, 903, 4164, 633, 665,
    544, 5732, 1238, 1586, 281, 220, 851, 2391, 780, 591,
    391, 2263, 4112, 293, 894, 246, 1440, 1200, 1050, 3193,
    763, 2620, 9296, 7800, 3625, 170, 3800, 784, 1000, 500,
    601, 440
])

# Нормалізація
# data = np.log10(data)

# Побудова гістограми
plt.hist(data, bins="sturges", density=True, alpha=0.5, edgecolor='black', color='g', label="Histogram")

mean = np.mean(data)
var = np.var(data, ddof=1)
skewness = stats.skew(data) # асиметрія
kurtosis = stats.kurtosis(data, fisher=False) + 3  # ексцес

A2 = skewness ** 2
distribution_family = "SB"

gamma, eta, loc, scale = stats.johnsonsb.fit(data)
params = stats.johnsonsb.fit(data)

# Побудова PDF Johnson SB
x_vals = np.linspace(min(data), max(data), 1000)
pdf_vals = stats.johnsonsb.pdf(x_vals, gamma, eta, loc=loc, scale=scale)
plt.plot(x_vals, pdf_vals, 'r-', label="Johnson SB PDF")

# Гістограма для χ²-тесту
bins = np.histogram(data, bins='sturges')[1]
observed_freq, _ = np.histogram(data, bins=bins)

# Розрахунок expected_freq через CDF
expected_freq = np.zeros_like(observed_freq, dtype=float)
n = len(data)
for i in range(len(bins) - 1):
    # Cumulative distribution function - Функция распределения
    # cdf_low — вероятность, что значение меньше нижней границы интервала.
    # cdf_high — вероятность, что значение меньше верхней границы.
    cdf_low = stats.johnsonsb.cdf(bins[i], gamma, eta, loc, scale)
    cdf_high = stats.johnsonsb.cdf(bins[i+1], gamma, eta, loc, scale)
    # Умножаем на n для того чтобы получить ожидаемую частоту 
    # (то есть количество наблюдений, которые попадут в этот интервал)
    expected_freq[i] = (cdf_high - cdf_low) * n

# Обчислення χ²-статистики
mask = expected_freq >= 5
chi_squared = np.sum((observed_freq[mask] - expected_freq[mask]) ** 2 / expected_freq[mask])

# Визначення ступенів свободи і критичної величини χ²
degrees_of_freedom = len(observed_freq) - 1 - 4  # Мінус 4 через параметри моделі
alpha = 0.05  # Рівень значущості

# Вивід χ²-внеску по кожному підінтервалу
table = []
for i in range(len(observed_freq)):
    if expected_freq[i] >= 5:
        chi2_i = (observed_freq[i] - expected_freq[i]) ** 2 / expected_freq[i]
    else:
        chi2_i = float('nan')  
    row = [
        f"[{bins[i]:.2f}, {bins[i+1]:.2f})",
        observed_freq[i],
        f"{expected_freq[i]:.2f}",
        f"{chi2_i:.4f}" if not np.isnan(chi2_i) else "—"
    ]
    table.append(row)

headers = ["Інтервал", "Спостережено", "Очікувано", "χ² внесок"]
print("\n" + tabulate(table, headers=headers, tablefmt="fancy_grid"))

# Критичне значення χ² для рівня значущості alpha і ступенів свободи
# Quantile function, percent-point function, inverse cumulative distribution function
chi_squared_critical = stats.chi2.ppf(1 - alpha, degrees_of_freedom)

# Оформлення графіка
plt.xlabel("data")
plt.ylabel("Density")
plt.legend()
plt.title("Johnson Distribution Approximation")
plt.show()

# Висновки
print(f"χ²-статистика: {chi_squared:.4f}")
print(f"Критичне значення χ² (α = {alpha}): {chi_squared_critical:.4f}")
if chi_squared < chi_squared_critical:
    print("Модель адекватна даним")
else:
    print("Модель неадекватна даним")

print(f"Ексцес ε: {kurtosis}")
print(f"A2: {A2}")
print(f"Обране сімейство розподілу: {distribution_family}")
print(f"Оцінені параметри: gamma={gamma:.4f}, eta={eta:.4f}, lambda(scale)={scale:.4f}, phi(loc)={loc:.4f}")
