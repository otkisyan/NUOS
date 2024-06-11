// Генератор чисел в указанном интервале

let generateNumbersInInterval start finish =
    let current = ref start
    let hasNext () = !current <= finish
    let getNext () =
        let value = !current
        current := value + 1
        value
    hasNext, getNext

let start = 5
let finish = 15
let hasNext, getNext = generateNumbersInInterval start finish

printfn "Generating numbers from %d to %d:" start finish
while hasNext() do
    printf "%d " (getNext())
printfn ""


