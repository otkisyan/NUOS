% Факти про батьків
parent(john, sarah).
parent(john, emily).
parent(lisa, sarah).
parent(lisa, emily).

parent(sarah, michael).
parent(sarah, anna).
parent(emily, alice).

% Факти про сестер
sister(sarah, emily).
sister(emily, sarah).

% Визначення відношення "тітка"
aunt(Aunt, NieceNephew) :-
    parent(Parent, NieceNephew),  % Пошук батьків
    sister(Aunt, Parent).         % Перевірка, що "тітка" - це сестра батька чи матері

