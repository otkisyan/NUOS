parent(john, alice).
parent(john, bob).
parent(mary, alice).
parent(mary, bob).

man(john).
man(bob).
woman(mary).
woman(alice).

family(X, Y, Child) :- 
    parent(X, Child),
    parent(Y, Child),
    X \= Y,
    man(X),
    woman(Y).

children_of_family(X, Y, Children) :-
    setof(Child, family(X, Y, Child), Children).

family_structure(Man, Wife, Children, Family_Structure) :-
    children_of_family(Man, Wife, Children),
    Family_Structure =..[family, Man, Wife, Children].

