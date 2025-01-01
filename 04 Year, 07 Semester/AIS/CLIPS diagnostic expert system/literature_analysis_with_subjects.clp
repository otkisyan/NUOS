; Факти про наявність літератури для кожного факультету та предмету
(deftemplate Library
    (slot institution)
    (slot subject)
    (slot textbooks)
    (slot manuals)
    (slot reference_books))

; Факти про потреби інститутів у літературі для кожного предмету
(deftemplate Needs
    (slot institution)
    (slot subject)
    (slot textbooks)
    (slot manuals)
    (slot reference_books))  
    
; Факти про фінансування
(deftemplate Funding
    (slot available-budget))

; Факти про наявність літератури для кожного факультету та предмету
(deffacts available-literature
    (Library (institution "Computer Science") (subject "Algorithms") (textbooks 50) (manuals 25) (reference_books 20))
    (Library (institution "Computer Science") (subject "Data Structures") (textbooks 30) (manuals 15) (reference_books 10))
    (Library (institution "Mechanics") (subject "Thermodynamics") (textbooks 40) (manuals 15) (reference_books 10))
    (Library (institution "Mechanics") (subject "Fluid Mechanics") (textbooks 25) (manuals 10) (reference_books 5))
    (Library (institution "Economics") (subject "Macroeconomics") (textbooks 60) (manuals 25) (reference_books 20))
    (Library (institution "Economics") (subject "Microeconomics") (textbooks 50) (manuals 20) (reference_books 15)))

; Факти про потреби інститутів у літературі для кожного предмету
(deffacts needed-literature
    (Needs (institution "Computer Science") (subject "Algorithms") (textbooks 50) (manuals 25) (reference_books 20))
    (Needs (institution "Computer Science") (subject "Data Structures") (textbooks 40) (manuals 20) (reference_books 15))
    (Needs (institution "Mechanics") (subject "Thermodynamics") (textbooks 50) (manuals 20) (reference_books 15))
    (Needs (institution "Mechanics") (subject "Fluid Mechanics") (textbooks 30) (manuals 15) (reference_books 10))
    (Needs (institution "Economics") (subject "Macroeconomics") (textbooks 70) (manuals 30) (reference_books 25))
    (Needs (institution "Economics") (subject "Microeconomics") (textbooks 60) (manuals 25) (reference_books 20)))

(deffacts funding-info
    (Funding (available-budget 10000)))
    
; Правило для перевірки достатності фінансування
(defrule check-financing
    (Funding (available-budget ?budget))
    (test (< ?budget 5000))
    =>
    (printout t "Фінансування недостатнє для закупівлі літератури." crlf))

; Правила для перевірки забезпеченості літературою за предметами
(defrule check-textbook-sufficiency
    ?library <- (Library (institution ?inst) (subject ?subj) (textbooks ?available))
    ?need <- (Needs (institution ?inst) (subject ?subj) (textbooks ?required))
    (test (< ?available ?required))
    =>
    (printout t "Недостатньо підручників для предмету " ?subj " на факультеті " ?inst "." crlf))

(defrule check-manual-sufficiency
    ?library <- (Library (institution ?inst) (subject ?subj) (manuals ?available))
    ?need <- (Needs (institution ?inst) (subject ?subj) (manuals ?required))
    (test (< ?available ?required))
    =>
    (printout t "Недостатньо методичних посібників для предмету " ?subj " на факультеті " ?inst "." crlf))

(defrule check-reference-book-sufficiency
    ?library <- (Library (institution ?inst) (subject ?subj) (reference_books ?available))
    ?need <- (Needs (institution ?inst) (subject ?subj) (reference_books ?required))
    (test (< ?available ?required))
    =>
    (printout t "Недостатньо довідників для предмету " ?subj " на факультеті " ?inst "." crlf))

; Правило для перевірки повної забезпеченості літературою
(defrule sufficient-literature
    ?library <- (Library (institution ?inst) (subject ?subj) (textbooks ?t-avail) (manuals ?m-avail) (reference_books ?r-avail))
    ?need <- (Needs (institution ?inst) (subject ?subj) (textbooks ?t-need) (manuals ?m-need) (reference_books ?r-need))
    (test (>= ?t-avail ?t-need))
    (test (>= ?m-avail ?m-need))
    (test (>= ?r-avail ?r-need))
    =>
    (printout t "Предмет " ?subj " на факультеті " ?inst " повністю забезпечений літературою." crlf))
    
 ; Правило обчислення відсотка забезпеченості літературою за предметами
(defrule calculate-percentage-sufficiency
    ?library <- (Library (institution ?inst) (subject ?subj)
                         (textbooks ?t-avail)
                         (manuals ?m-avail)
                         (reference_books ?r-avail))
    ?need <- (Needs (institution ?inst) (subject ?subj)
                    (textbooks ?t-need)
                    (manuals ?m-need)
                    (reference_books ?r-need))
    (test (and (> ?t-need 0) (> ?m-need 0) (> ?r-need 0)))  ; Перевірка на те що need більше 0
    =>
    
    ; Розрахунок відсотків для кожного типу літератури
    (bind ?textbook-percentage (/ (* ?t-avail 100) ?t-need))
    (bind ?manual-percentage (/ (* ?m-avail 100) ?m-need))
    (bind ?reference-percentage (/ (* ?r-avail 100) ?r-need))
    
    ; Розрахунок загального відсотку по предмету
    (bind ?overall-percentage (/ (+ ?textbook-percentage ?manual-percentage ?reference-percentage) 3))

    ; Вивід результатів на екран
    (printout t "Факультет: " ?inst ", Предмет: " ?subj crlf)
    (printout t "Процент забезпеченості підручниками: " ?textbook-percentage "%" crlf)
    (printout t "Процент забезпеченості методичними посібниками: " ?manual-percentage "%" crlf)
    (printout t "Процент забезпеченості довідниками: " ?reference-percentage "%" crlf)
    (printout t "Загальний процент забезпеченості: " ?overall-percentage "%" crlf crlf))
