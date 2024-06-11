// https://learn.microsoft.com/ru-ru/dotnet/fsharp/language-reference/functions/
let findMin lst compareFunc =
    match lst with
    | [] -> failwith "Empty list"
    | head::tail -> 
        tail
        // |> (оператор конвереа)
        // передает значение слева от оператора как последний аргумент функции справа от него. 
        // Я передаю хвост списка в функцию List.fold. 

        |> List.fold (fun acc elem -> if compareFunc elem acc < 0 then elem else acc) head

let lst = [5; 3; -1; -2; 9]
let minNum = findMin lst (fun a b -> a - b)
printfn "Минимальное число в списке: %d" minNum
