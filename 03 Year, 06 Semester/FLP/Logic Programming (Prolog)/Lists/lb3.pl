% Студенти, які отримують стипендію за результатами сесії 
% Students who receive a scholarship based on the results of the session 
%
% https://cs.union.edu/~striegnk/learn-prolog-now/html/node96.html
% https://cs.union.edu/~striegnk/learn-prolog-now/html/node97.html
% https://cs.union.edu/~striegnk/learn-prolog-now/html/node98.html#subsec.l11.setof
% 
% Определение фактов оценок студентов
student(3151, chernova, [math/85, oop/90, english/88, database/76]).
student(3152, ivanov, [math/95, oop/85, english/92, database/88]).
student(3153, petrov, [math/80, oop/80, english/85, database/90]).
student(3154, sokolov, [math/82, oop/78, english/86, database/84]).

% Правило для определения студентов, получающих стипендию
eligible_for_scholarship(Group, Fam) :-
    student(Group, Fam, Grades), % Получаем оценки для данного студента
    all_passed(Grades). % Проверяем, все ли оценки выше или равны 80

% Правило для проверки, все ли оценки студента выше или равны 80
all_passed([]). % Базовый случай: если список оценок пустой, значит все оценки выше 80
all_passed([_/Grade|Grades]) :- % Проверяем оценку
    Grade >= 80, % Если оценка выше или равна 80,
    all_passed(Grades). % Проверяем остальные оценки

% Правило для поиска студентов, получающих стипендию, и записи их в список
find_scholarship_students(List) :-
    findall((Group, Fam), eligible_for_scholarship(Group, Fam), List).

find_scholarship_students_print(List) :-
    findall((Group, Fam), eligible_for_scholarship(Group, Fam), List),
    printList(List, 0).

printList([],K):- 
    write('Всього: '),
    write(K),
    write(' студентів').

printList([H|T],K):- 
    N is K+1, 
    write(N), 
    write('.'), 
    write(H),
	nl,
    printList(T,N).
