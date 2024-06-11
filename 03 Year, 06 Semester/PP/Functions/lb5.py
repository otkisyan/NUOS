# Даны три списка cписков, состоящих из 5 элементов, каждый из которых содержит 4 целых числа. 
# Выведите тот, в котором больше элементов вида [0, 0, 0, 0] (если таких списков несколько, выведите их все).

def find_max_zero_lists(lists):
    counts = {i: sum(1 for sub_list in list if sub_list == [0, 0, 0, 0]) for i, list in enumerate(lists)}
    max_count = max(counts.values())
    result = [lists[i] for i, count in counts.items() if count == max_count]
    return result

def main():
    lists = [
    [[0, 0, 0, 0], [1, 2, 3, 4], [0, 0, 0, 0], [0, 0, 1, 1], [0, 0, 0, 0]],
    [[0, 0, 1, 1], [0, 0, 0, 0], [7, 8, 9, 10], [0, 0, 0, 0], [0, 0, 0, 0]],
    [[1, 2, 32, 2], [0, 0, 0, 0], [20, 24, 25, 0], [0, 1, 68, 0], [0, 0, 0, 0]]
    ]
    
    max_zero_lists = find_max_zero_lists(lists);
    print("Список(и) з найбільшою кількістю елементів [0, 0, 0, 0]:")
    for list in max_zero_lists:
        print(list)

if __name__ == "__main__":
    main()
