// Вставить элемент в отсортированный список

let rec insertIntoSorted x lst =
    match lst with
    | [] -> [x]
    | head::tail -> if x <= head then x::lst else head::(insertIntoSorted x tail)

let sortedList = [1; 2; 3; 4; 5]
let insertedList = insertIntoSorted 6 sortedList
printfn "%A" insertedList
