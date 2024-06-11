% Свояк (чоловік сестри)
% The husband of your sister.

parent(tetiana, olha).
parent(tetiana, bohdan).
parent(mykola, olha).
parent(mykola, bohdan).

parent(maria, alex).
parent(maria, daria).
parent(mykhailo, alex).
parent(mykhailo, daria).

parent(olha, aristarh).
parent(alex, aristarh).

woman(tetiana).
woman(maria).
woman(olha).
woman(daria).

man(mykola).
man(bohdan).
man(mykhailo).
man(aristarh).
man(alex).

sister(X, Y) :-
    parent(W, X),
    parent(W, Y),
    woman(X),
    X \= Y.
   
husband(X, Y) :- 
    parent(X, Z), 
    parent(Y, Z), 
    man(X), 
    woman(Y), 
    X \== Y.

brother_in_law(X, Y) :-
    husband(X, Z),
    sister(Z, Y), 
    X \== Y,
    !.