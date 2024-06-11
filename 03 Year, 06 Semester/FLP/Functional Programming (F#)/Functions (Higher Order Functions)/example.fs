// List.map - https://www.youtube.com/watch?v=C3_6plCm7jc
// List.filter - https://www.youtube.com/watch?v=pHDzgp7zDR0
let originalList = [1; 2; 3; 4; 5; 6; 7; 8; 9; 10]
let F x = x * 2
let P x = x > 5

let resultList =
originalList
|> List.map (fun x -> F x)
|> List.filter (fun x -> P x)

printfn "Original list: %A" originalList
printfn "Processed list: %A" resultList