let generateNumbersInRange startNum endNum =
    let x = ref startNum
    seq {
        while !x <= endNum do
            yield !x
            x := !x + 1
    }

// Пример использования:
let generator = generateNumbersInRange 1 10
generator |> Seq.iter (printfn "%d")
