import numpy as np
import matplotlib.pyplot as plt
import scipy.stats as stats

def grubbs_test(data, alpha=0.05):
    # Создаем копию данных, чтобы не менять исходный список
    data = np.array(data)
    original_indices = list(range(len(data)))  # Индексы исходного списка
    outliers_indices = []  # Список индексов выбросов в исходном списке
    
    while True:
        mean = np.mean(data)
        std_dev = np.std(data)
        
        max_value = np.max(data)
        min_value = np.min(data)
        
        G_max = (max_value - mean) / std_dev
        G_min = (mean - min_value) / std_dev
        
        # Критическое значение
        t_critical = stats.t.ppf(alpha / (2 * len(data)), len(data) - 2)
        # delta_z
        critical_value_two_sided = ((len(data) - 1) / np.sqrt(len(data))) * np.sqrt((t_critical**2) / (len(data) - 2 + t_critical**2))

        # Проверяем на выбросы
        if G_max > critical_value_two_sided:
            # Индекс максимального значения в исходном списке
            outlier_index = np.argmax(data)
            outliers_indices.append(original_indices[outlier_index])
            data = np.delete(data, outlier_index)
            original_indices.pop(outlier_index)  # Удаляем индекс из списка оригинальных индексов
        elif G_min > critical_value_two_sided:
            # Индекс минимального значения в исходном списке
            outlier_index = np.argmin(data)
            outliers_indices.append(original_indices[outlier_index])
            data = np.delete(data, outlier_index)
            original_indices.pop(outlier_index)  # Удаляем индекс из списка оригинальных индексов
        else:
            break  # Выход из цикла, если выбросов больше нет

    return outliers_indices

# Дані з першого стовпця
data = np.array([
    6020, 3570, 2496, 191, 406, 14520, 14430, 650, 225, 183,
    1080, 2464, 1041, 528, 212, 7060, 903, 4164, 633, 665,
    544, 5732, 1238, 1586, 281, 220, 851, 2391, 780, 591,
    391, 2263, 4112, 293, 894, 246, 1440, 1200, 1050, 3193,
    763, 2620, 9296, 7800, 3625, 170, 3800, 784, 1000, 500,
    601, 440
])
n = len(data)
print(n)
# data = np.log10(data)
# Когда мы применяем десятичный логарифм, он сжимает разброс значений, 
# особенно если данные имеют большой розмах (разброс между минимальным и максимальным значением). 
# Из-за этого межквартильный размах (IQR) становится шире в логарифмическом масштабе что снижает вероятность обнаружения выбросов по правилу 1.5 * IQR.
# До логарифмирования данные были сильно разбросаны, и метод 1.5 * IQR находил выбросы среди значений, которые далеко отходили от основной массы данных.
# После логарифмирования разброс значений уменьшился, и теперь даже большие числа, которые раньше считались выбросами, находятся ближе к основному кластеру.
# 1. Обчислення вибіркового середнього
mean_value = np.mean(data)
print(f"Mean: {mean_value}")

# 2. Обчислення вибіркової дисперсії та стандартного відхилення
variance_value = np.var(data, ddof=1)
std_dev_value = np.sqrt(variance_value);  # std_dev_value2 = np.std(data, ddof=1)
print(f"Variance: {variance_value}")
print(f"Std Deviation: {std_dev_value}")

# 3. Перевірка нормальності розподілу за допомогою критерію Шапіро-Уїлка
shapiro_test = stats.shapiro(data)
shapiro_p_value = shapiro_test.pvalue
print(f"Shapiro: {shapiro_test}")

if shapiro_p_value < 0.05:
    print("Data set doesn't have a normal distribution")
    alpha = 0.05
    
    # Перетворення за log10 (ψ(x))
    data_log = np.log10(data)
    std_dev_log = np.std(data_log, ddof=1)

    # Обчислення вибіркового середнього
    mean_log = np.log10(mean_value)
    
    # Обчислення ∆z за критерієм Граббса
    n = len(data_log)
    t_critical = stats.t.ppf(1 - alpha / (2 * n), n - 2)
    delta_z = std_dev_log * ((n - 1) / np.sqrt(n)) * np.sqrt((t_critical**2) / (n - 2 + t_critical**2))

    # Визначення меж інтервалу у лог-шкалі
    lower_bound = mean_log - delta_z
    upper_bound = mean_log + delta_z
    interval_log = [mean_log - delta_z, mean_log + delta_z]

    # Зворотне перетворення (ψ⁻¹)
    lower_bound_original = 10**lower_bound
    upper_bound_original = 10**upper_bound
    interval_original = [lower_bound_original, upper_bound_original]
    outliers_non_gauss = data[(data < lower_bound_original) | (data > upper_bound_original)]
    
    print(f"Цензуровані межі: {interval_original}")
    print(f"Викиди: {outliers_non_gauss}")
    
    conf_interval = stats.t.interval(confidence=0.95, df=len(data)-1, 
                                     loc=mean_log, 
                                     scale=std_dev_log/np.sqrt(len(data)))
    conf_interval_original = (10**conf_interval[0], 10**conf_interval[1])
    print(f"Confidence interval (original scale): {conf_interval_original}")
    # ------------------
    
    # n = len(data)
    # mu4 = np.mean((data - mean_value)**4)
    
    # # Обчислення ексцесу
    # epsilon = mu4 / (std_dev_value**4)
    
    # # Обчислення ∆x за формулою (1.3)
    # delta_x = (1.55 + 0.8 * np.sqrt(epsilon - 11 * np.log10(n/10))) * std_dev_value

    # # Визначення меж цензурування
    # lower_bound_non_gauss = mean_value - delta_x
    # upper_bound_non_gauss = mean_value + delta_x
    
    # # Знаходження викидів
    # outliers_non_gauss = data[(data < lower_bound_non_gauss) | (data > upper_bound_non_gauss)]
    
    # print(f"Kurtosis: {epsilon:.2f}")
    # print(f"∆x: {delta_x:.2f}")
    # print(f"Interval censoring: [{lower_bound_non_gauss:.2f}, {upper_bound_non_gauss:.2f}]")
    # print(f"Outliers: {outliers_non_gauss}")
else:
    print("Data set has a normal distribution")

# 4. Побудова гістограми та накладання нормального розподілу
plt.figure(figsize=(10, 5))
plt.hist(data, bins='sturges', density=True, alpha=0.6, color='g', edgecolor='black', label="Гістограма")

# Накладання кривої нормального розподілу
xmin, xmax = plt.xlim()
x = np.linspace(xmin, xmax, 100)
p = stats.norm.pdf(x, mean_value, std_dev_value)
plt.plot(x, p, 'k', linewidth=2, label="Нормальний розподіл")

plt.xlabel("Значення")
plt.ylabel("Щільність ймовірності")
plt.title("Гістограма даних та нормальний розподіл")
plt.legend()
plt.show()

# 5. Визначення 95%-го довірчого інтервалу для середнього значення
# df – это количество независимых значений, которые могут изменяться при расчете статистической характеристики.
# Если у нас выборка из n наблюдений, то df = n - 1.
# loc определяет, где расположен центр распределения, относительно которого строится доверительный интервал.
# scale – это масштабный параметр (scale parameter), который обычно интерпретируется как стандартное отклонение
# conf_interval = stats.t.interval(confidence=0.95, df=len(data)-1, loc=mean_value, scale=std_dev_value/np.sqrt(len(data)))
# print(f"Confidence interval:  {conf_interval}")

# 6. Виявлення викидів за правилом 1.5*IQR
Q1, Q3 = np.percentile(data, [25, 75])

# np.percentile(data, [25, 75]): Цей рядок обчислює 25-й і 75-й персентилі масиву data.
# 25-й персентиль (Q1) - це значення, нижче якого лежить 25% усіх елементів масиву.
# 75-й персентиль (Q3) - це значення, нижче якого лежить 75% усіх елементів масиву.

IQR = Q3 - Q1
lower_bound = Q1 - 1.5 * IQR
upper_bound = Q3 + 1.5 * IQR
outliers_iqr = data[(data < lower_bound) | (data > upper_bound)]
print(f"Outliers IQR: {outliers_iqr}")

outliers_grubbs = data[grubbs_test(data)]
print(f"Outliers Grubbs: {outliers_grubbs}")