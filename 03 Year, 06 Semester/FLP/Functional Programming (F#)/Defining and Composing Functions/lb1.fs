// e = \sum_{k = 1}^{\infty}\frac{1}{k!}

// Основна ідея полягає в тому, щоб послідовно додавати члени ряду до деякої точності (похибки), 
// поки різниця між сумою і попереднім наближенням не стане менше заданої похибки.
// - factorial: Ця рекурсивна функція обчислює факторіал числа n. 
// Вона використовує базовий випадок, коли n дорівнює 0, і повертає 1.0, 
// в іншому випадку вона викликає себе з аргументом (n - 1) і помножує результат на поточне значення n.

// - sumSeriesTerm: Ця функція обчислює значення одного члена ряду. Вона викликає factorial та обертає його результат у дробовий тип.

// - sumSeriesApproximation: Це головна функція обчислення суми ряду. 
// Вона рекурсивно додає члени ряду, поки різниця між новим і попереднім наближенням не стане менше встановленої похибки. 
// Вона приймає аргументи n (поточний член ряду), approximation (поточне наближення суми) і tolerance (похибка).

// - sumSeriesAutomatic: Це допоміжна функція, яка викликає sumSeriesApproximation з початковим значенням n та approximation.
// Значення approximationTolerance встановлюється на 0.0001, що є значенням похибки, 
// яке використовується для припинення обчислень, коли досягнута необхідна точність. 

// Потім викликається sumSeriesAutomatic з цією похибкою, і результат виводиться на екран. 
// Коли сума наступного члена ряду стає менше, ніж похибка, обчислення припиняються, 
// і результат вважається достатньо точним для вказаної похибки.

// Функція для обчислення факторіалу
let rec factorial (n: int) : float =
    if n = 0 then 1.0
    else float n * factorial (n - 1)

// Функція для обчислення одного члена послідовності
let sumSeriesTerm (n: int) : float =
    1.0 / float(factorial n)

// Функція для обчислення суми
let rec sumSeriesApproximation (n: int) (approximation: float) (tolerance: float) : float =
    let term = sumSeriesTerm n
    let newApproximation = approximation + term
    if System.Math.Abs(newApproximation - approximation) < tolerance then
        newApproximation
    else
        sumSeriesApproximation (n + 1) newApproximation tolerance

// Допоміжна функція
let sumSeriesAutomatic (tolerance: float) : float =
    sumSeriesApproximation 1 1.0 tolerance

let approximationTolerance = 0.0001 // Похибка

let expressionValue : float = sumSeriesAutomatic approximationTolerance
printfn "Наближене значення числа e: %f" expressionValue