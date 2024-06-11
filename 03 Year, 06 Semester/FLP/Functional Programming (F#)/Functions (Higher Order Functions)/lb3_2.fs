// List.fold - https://www.youtube.com/watch?v=ucYQyIq6UOM
let findMin lst compareFunc =
    match lst with
    | [] -> failwith "Empty list"
    // Используем List.fold (функционал модуля List) для выполнения вычислений над списком.
    // Первым аргументом передается функция (folder function)
    // которая принимает:
    // аккумулятор acc - это значение, которое передается по мере выполнения вычислений (состояние). 
    // элемент elem - текущий элемент списка
    // 
    // Второй аргумент - начальное значение аккумулятора, в моем случае head (голова списка).
    // Третий аргумент - входной список, я беру tail (хвост списка)
    // Проходимся списком сравнивая элементы и записывая меньшее в аккумулятор
    | head::tail -> List.fold (fun acc elem -> if compareFunc elem acc < 0 then elem else acc) head tail

let lst = [5; 3; -1; -2; 9]
let minNum = findMin lst (fun a b -> a - b)
printfn "Минимальное число в списке: %d" minNum
