% Імовірний діагноз за вказанми симптомами
% Probable diagnosis according to the symptoms

:- dynamic patient_symptom/2.
:- dynamic patient_diagnosis/2.

symptom(fever).
symptom(cough).
symptom(rash).
symptom(headache).
symptom(fatigue).
symptom(sore_throat).
symptom(nausea).
symptom(vomiting).

diagnosis(cold, [fever, cough, sore_throat]).
diagnosis(measles, [fever, rash]).
diagnosis(influenza, [fever, cough, headache, fatigue]).
diagnosis(stomach_flu, [nausea, vomiting, fatigue]).
diagnosis(tonsillitis, [fever, sore_throat]).
diagnosis(allergy, [rash, fatigue]).

% patient_diagnosis(max, [measles]).
% patient_diagnosis(alex, [allergy]).
% patient_diagnosis(bob, [allergy]).
% patient_diagnosis(john, [cold, tonsillitis]).

% Получаем симптомы пациента
get_symptoms(Name) :-
    write('Enter patient symptoms one by one, and end with "done". '), write(':'), nl,
    get_symptoms_loop(Name).

get_symptoms_loop(Name) :-
    write('Symptom: '),
    read(Symptom),
    % Будем считывать симптомы пациента, пока пользователь не введет "done" это будет означать конец ввода
    (Symptom == done -> true ; assert(patient_symptom(Name, Symptom)), get_symptoms_loop(Name)).

% Ищем возможные диагнозы пациента на основе симптомов (patient_symptom/2)
% и запоминаем их используя remember_diagnosis
% Если подходящих диагнозов не нашлось, тогда выведем сообщение об этом и не будем запоминать пустой список
probable_diagnoses(Name) :-
    findall(Diagnosis, (diagnosis(Diagnosis, Symptoms), all_symptoms_present(Name, Symptoms)), Diagnoses),
    (   Diagnoses = []
    ->  write('No probable diagnoses found for '), write(Name), nl
    ;   write('Probable diagnoses for '), write(Name), write(': '), write(Diagnoses), nl,
        remember_diagnosis(Name, Diagnoses)
    ).

% Используя рекурсию будем двигаться вглубь списка симптомов и проверять 
% имеет ли пациент определенный симптом, если дошли до момента
% когда список пуст значит пациент имеет все симптомы
all_symptoms_present(_, []).
all_symptoms_present(Name, [Symptom|Rest]) :-
    patient_symptom(Name, Symptom),
    all_symptoms_present(Name, Rest).

% Проверяем сущестовование файла и если он существует считываем диагнозы пациентов
loadbz:-exists_file('diagnoses.bz'), read_patient_diagnoses.

% Записываем симптомы, диагнозы, диагнозы пациентов в файл
savebz:-
    tell('diagnoses.bz'),
    listing(symptom/1),
    listing(diagnosis/2), 
    listing(patient_diagnosis/2),
    told.

% Предикат для запоминания диагнозов, исключаем дубликаты
% Ищем если существует уже такой факт, если нет то тогда добавляем новый.
remember_diagnosis(Name, Diagnosis):-patient_diagnosis(Name, Diagnosis),!.
remember_diagnosis(Name, Diagnosis):-assert(patient_diagnosis(Name, Diagnosis)).

% Считываем диагнозы пациентов из файла
read_patient_diagnoses:-see('diagnoses.bz'),repeat,read(R),process(R),!,seen.

process(end_of_file):-!,true.
% X=..[F|_] = [patient_diagnosis, xxx, yyy].
% Будем считывать только patient_diagnosis, так как все остальные факты статические
process(X):-write(X),X=..[F|_],F=patient_diagnosis,assert(X),!,write(' yes!'),nl,false.
process(_):-write(' no!'),nl,false.

% Главное правило для начала работы
run_diagnosis :-
    write('Enter patient name: '),
    read(Name),
    retractall(patient_symptom(Name, _)), % Удалем старые симптомы пациента
    get_symptoms(Name),
    probable_diagnoses(Name).

% Получение списка всех диагнозов пациентов в формате "пациент/диагнозы"
diagnoses(X,Y):- var(X), var(Y), findall(Name/Diagnosis, patient_diagnosis(Name, Diagnosis), X),!.

% Поиск пациентов у которых есть определеный диагноз
diagnoses(X,Y):- 
    var(X), 
    nonvar(Y), 
    findall((Name), (patient_diagnosis(Name, Diagnosis), member(Y, Diagnosis)), X),!.

% Поиск диагнозов определенного пациента
diagnoses(X,Y):- 
    nonvar(X), 
    var(Y), 
    findall(Diagnosis, patient_diagnosis(X, Diagnosis), Y),!.

% Проверка имеет ли пациент определенные диагнозы
diagnoses(Patient, Diagnoses) :-
    patient_diagnosis(Patient, PatientDiagnoses),
    % true если элементы из Diagnoses также принадлежат PatientDiagnoses
    % например: 
    % patient_diagnosis(john, [cold, tonsillitis]).
    % ?- diagnoses(john, [cold]). -> true
	% ?- diagnoses(john, [cold, tonsillitis]). -> true
	% ?- diagnoses(john, [cold, allergy]). -> false
    
    subset(Diagnoses, PatientDiagnoses),
    write('Yes'), nl.
