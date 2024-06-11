// Инвертировать список

let rec invertListAcc lst acc =
    match lst with
    | [] -> acc
    | head::tail -> invertListAcc tail (head::acc)

let invertList lst =
    invertListAcc lst []

let myList = [1; 5; 8; 4; 2]
let invertedList = invertList myList
printfn "Original list: %A" myList
printfn "Inverted list: %A" invertedList
