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

parent(sofia, eva).
parent(pavlo, eva).

parent(eva, yuriy).
parent(aristarh, yuriy).

parent(katerina, danil).
parent(vsevolod, danil).

parent(daria, amina).
parent(danil, amina).


woman(tetiana).
woman(maria).
woman(olha).
woman(daria).
woman(sofia).
woman(eva).
woman(katerina).
woman(amina).

man(mykola).
man(bohdan).
man(mykhailo).
man(aristarh).
man(alex).
man(pavlo).
man(yuriy).
man(danil).
man(vsevolod).

% из за того что у одного "ребенка" двое родителей в результате получаем дубликаты
sister(X, Y) :-
    parent(W, X),
    parent(W, Y),
    woman(X),
    X \== Y.

commonparent(X, Y) :-
    parent(P, X),
    !,
    parent(P, Y),
    X \== Y.

sisterunique(X, Y) :-
    woman(X),
    commonparent(X, Y).


% так как у одного родителя может быть несколько "детей" в результате получаем дубликаты
husband(X, Y) :- 
    man(X), 
    woman(Y), 
    parent(X, Z), 
    parent(Y, Z), 
    X \== Y.

commonchildren(X, Y) :-
    parent(X, Z),
    !,
    parent(Y, Z),
    X \== Y.

husbandunique(X, Y) :- 
    man(X), 
    woman(Y), 
    commonchildren(X, Y),
    X \== Y.

% в результате можем получим дубликаты, так как получим дубликат из husband если
% у одних родителей несколько детей, и получим дубликат из sister так как мы находим сестру
% по общим родителям, а родителей двое
brother_in_law(X, Y) :-
    husband(X, Z),
    sister(Z, Y), 
    X \== Y.

brother_in_law_unique(X, Y) :-
    husbandunique(X, Z),
    sisterunique(Z, Y),
    X \== Y.

brother_in_law_unique_all(X, Y) :-
    brother_in_law_unique(X,Y),
	write(X),
    write(" is brother in law of "),
    write(Y),
    nl,
    fail.

brother_in_law_unique2(X, Y) :-
    setof((X, Y), brother_in_law(X, Y), Results),
    member((X, Y), Results).