// Функция принимает два аргумента: список lst и функцию сравнения compareFunc.
// Внутри функции осуществляется сопоставление (match) для списка lst.
// Если список пуст ([]), генерируется исключение "Empty list".
// Если в списке только один элемент ([x]), этот элемент возвращается.
// Если в списке более одного элемента (head::tail), 
// рекурсивно вызывается minInList для хвоста списка tail.
// Результат рекурсивного вызова сохраняется в переменной minTail.

// С помощью compareFunc сравнивается голова head с минимальным элементом minTail.
// Если результат сравнения меньше 0, то head считается новым минимальным элементом и возвращается.
// В противном случае возвращается minTail, который уже содержит минимальный элемент из хвоста списка.

let rec minInList lst compareFunc =
    match lst with
    | [] -> failwith "Empty list"
    | [x] -> x
    | head::tail -> 
        let minTail = minInList tail compareFunc
        if compareFunc head minTail < 0 then head else minTail

let lst = [5; 3; -1; -2; 9]
let minNum = minInList lst (fun a b -> a - b)
printfn "Минимальное число в списке: %d" minNum
