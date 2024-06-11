let generateNumbersInRange startNum endNum =
    // Use the ref function to create a new reference cell with an initial value. 
    // You can then change the underlying value because it is mutable. 
    // A reference cell holds an actual value; it is not just an address.
    let x = ref (startNum - 1)
    fun () ->
        if !x < endNum then
            x := !x + 1
            Some !x
        else
            None



// Пример использования:
let generator = generateNumbersInRange 1 10
printfn "%A" (generator()) // Вывод: Some 1
printfn "%A" (generator()) // Вывод: Some 2
printfn "%A" (generator()) // Вывод: Some 3
printfn "%A" (generator()) // Вывод: Some 4
printfn "%A" (generator()) // Вывод: Some 5
printfn "%A" (generator()) // Вывод: Some 6
printfn "%A" (generator()) // Вывод: Some 7
printfn "%A" (generator()) // Вывод: Some 8
printfn "%A" (generator()) // Вывод: Some 9
printfn "%A" (generator()) // Вывод: Some 10
printfn "%A" (generator()) // Вывод: None