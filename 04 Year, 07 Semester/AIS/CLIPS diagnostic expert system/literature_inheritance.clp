(defclass Book
   (is-a USER)
   (slot institution)
   (slot subject)     
   (slot quantity))    

(defclass Textbook
   (is-a Book)
   (slot difficulty))

(defclass Manual
   (is-a Book)
   (slot purpose))
        
(defclass ReferenceBook
   (is-a Book))
 
; Створення екземплярів для літератури
(definstances available-literature
   (Textbook-1 of Textbook 
      (institution "Computer Science") 
      (subject "Algorithms") 
      (quantity 50) 
      (difficulty "Advanced")) 

   (Textbook-2 of Textbook 
      (institution "Computer Science") 
      (subject "Data Structures") 
      (quantity 30) 
      (difficulty "Intermediate")) 

   (Textbook-3 of Textbook 
      (institution "Mechanics") 
      (subject "Thermodynamics") 
      (quantity 40) 
      (difficulty "Advanced")) 

   (Textbook-4 of Textbook 
      (institution "Mechanics") 
      (subject "Fluid Mechanics") 
      (quantity 25) 
      (difficulty "Intermediate")) 

   (Textbook-5 of Textbook 
      (institution "Economics") 
      (subject "Macroeconomics") 
      (quantity 60) 
      (difficulty "Advanced")) 

   (Textbook-6 of Textbook 
      (institution "Economics") 
      (subject "Microeconomics") 
      (quantity 50) 
      (difficulty "Intermediate")) 

   (Manual-1 of Manual 
      (institution "Computer Science") 
      (subject "Algorithms") 
      (quantity 25) 
      (purpose "Laboratory work guide")) 

   (Manual-2 of Manual 
      (institution "Computer Science") 
      (subject "Data Structures") 
      (quantity 15) 
      (purpose "Lecture notes")) 

   (Manual-3 of Manual 
      (institution "Mechanics") 
      (subject "Thermodynamics") 
      (quantity 15) 
      (purpose "Laboratory work guide")) 

   (Manual-4 of Manual 
      (institution "Mechanics") 
      (subject "Fluid Mechanics") 
      (quantity 10) 
      (purpose "Lecture notes")) 

   (Manual-5 of Manual 
      (institution "Economics") 
      (subject "Macroeconomics") 
      (quantity 25) 
      (purpose "Lecture notes")) 

   (Manual-6 of Manual 
      (institution "Economics") 
      (subject "Microeconomics") 
      (quantity 20) 
      (purpose "Laboratory work guide")) 

   (ReferenceBook-1 of ReferenceBook 
      (institution "Computer Science") 
      (subject "Algorithms") 
      (quantity 20)) 

   (ReferenceBook-2 of ReferenceBook 
      (institution "Computer Science") 
      (subject "Data Structures") 
      (quantity 10)) 

   (ReferenceBook-3 of ReferenceBook 
      (institution "Mechanics") 
      (subject "Thermodynamics") 
      (quantity 10)) 

   (ReferenceBook-4 of ReferenceBook 
      (institution "Mechanics") 
      (subject "Fluid Mechanics") 
      (quantity 5)) 

   (ReferenceBook-5 of ReferenceBook 
      (institution "Economics") 
      (subject "Macroeconomics") 
      (quantity 20)) 

   (ReferenceBook-6 of ReferenceBook 
      (institution "Economics") 
      (subject "Microeconomics") 
      (quantity 15))
)


(deftemplate Needs
    (slot institution)
    (slot subject)
    (slot textbooks)
    (slot manuals)
    (slot reference_books))  
    
; Факти про фінансування
(deftemplate Funding
    (slot available-budget))

; Створення екземплярів для потреб інститутів у літературі
(deffacts needed-literature
    (Needs (institution "Computer Science") (subject "Algorithms") (textbooks 50) (manuals 25) (reference_books 20))
    (Needs (institution "Computer Science") (subject "Data Structures") (textbooks 40) (manuals 20) (reference_books 15))
    (Needs (institution "Mechanics") (subject "Thermodynamics") (textbooks 50) (manuals 20) (reference_books 15))
    (Needs (institution "Mechanics") (subject "Fluid Mechanics") (textbooks 30) (manuals 15) (reference_books 10))
    (Needs (institution "Economics") (subject "Macroeconomics") (textbooks 70) (manuals 30) (reference_books 25))
    (Needs (institution "Economics") (subject "Microeconomics") (textbooks 60) (manuals 25) (reference_books 20)))

; Правила для перевірки забезпеченості літературою

(deffacts funding-info
    (Funding (available-budget 10000)))
    
; Правило для перевірки достатності фінансування
(defrule check-financing
    (Funding (available-budget ?budget))
    (test (< ?budget 5000))
    =>
    (printout t "Фінансування недостатнє для закупівлі літератури." crlf))

; Правило для проверки достаточности финансирования
(defrule check-financing
    (Funding (available-budget ?budget))
    (test (< ?budget 5000))
    =>
    (printout t "Фінансування недостатнє для закупівлі літератури." crlf))

; Правило для проверки обеспеченности подручниками
(defrule check-textbook-sufficiency
    ?book <- (object (is-a Textbook) (institution ?inst) (subject ?subj) (quantity ?available))
    ?need <- (Needs (institution ?inst) (subject ?subj) (textbooks ?required))
    (test (< ?available ?required))
    =>
    (printout t "Недостатньо підручників для предмету " ?subj " на факультеті " ?inst "." crlf))

; Правило для проверки обеспеченности методическими пособниками
(defrule check-manual-sufficiency
    ?book <- (object (is-a Manual) (institution ?inst) (subject ?subj) (quantity ?available))
    ?need <- (Needs (institution ?inst) (subject ?subj) (manuals ?required))
    (test (< ?available ?required))
    =>
    (printout t "Недостатньо методичних посібників для предмету " ?subj " на факультеті " ?inst "." crlf))

; Правило для проверки обеспеченности довідниками
(defrule check-reference-book-sufficiency
    ?book <- (object (is-a ReferenceBook) (institution ?inst) (subject ?subj) (quantity ?available))
    ?need <- (Needs (institution ?inst) (subject ?subj) (reference_books ?required))
    (test (< ?available ?required))
    =>
    (printout t "Недостатньо довідників для предмету " ?subj " на факультеті " ?inst "." crlf))

; Правило для проверки полной обеспеченности литературой
(defrule sufficient-literature
    ?book-textbook <- (object (is-a Textbook) (institution ?inst) (subject ?subj) (quantity ?t-avail))
    ?book-manual <- (object (is-a Manual) (institution ?inst) (subject ?subj) (quantity ?m-avail))
    ?book-reference <- (object (is-a ReferenceBook) (institution ?inst) (subject ?subj) (quantity ?r-avail))
    ?need <- (Needs (institution ?inst) (subject ?subj)
                    (textbooks ?t-need)
                    (manuals ?m-need)
                    (reference_books ?r-need))
    (test (>= ?t-avail ?t-need))
    (test (>= ?m-avail ?m-need))
    (test (>= ?r-avail ?r-need))
    =>
    (printout t "Предмет " ?subj " на факультеті " ?inst " повністю забезпечений літературою." crlf))

; Правило для расчета процента обеспеченности литературой
(defrule calculate-percentage-sufficiency
    ?book-textbook <- (object (is-a Textbook) (institution ?inst) (subject ?subj) (quantity ?t-avail))
    ?book-manual <- (object (is-a Manual) (institution ?inst) (subject ?subj) (quantity ?m-avail))
    ?book-reference <- (object (is-a ReferenceBook) (institution ?inst) (subject ?subj) (quantity ?r-avail))
    ?need <- (Needs (institution ?inst) (subject ?subj)
                    (textbooks ?t-need)
                    (manuals ?m-need)
                    (reference_books ?r-need))
    (test (and (> ?t-need 0) (> ?m-need 0) (> ?r-need 0)))  ; Проверка, что need больше 0
    =>
    ; Расчет процентов для каждого типа литературы
    (bind ?textbook-percentage (/ (* ?t-avail 100) ?t-need))
    (bind ?manual-percentage (/ (* ?m-avail 100) ?m-need))
    (bind ?reference-percentage (/ (* ?r-avail 100) ?r-need))

    ; Расчет общего процента по предмету
    (bind ?overall-percentage (/ (+ ?textbook-percentage ?manual-percentage ?reference-percentage) 3))

    ; Вывод результатов на экран
    (printout t "Факультет: " ?inst ", Предмет: " ?subj crlf)
    (printout t "Процент забезпеченості підручниками: " ?textbook-percentage "%" crlf)
    (printout t "Процент забезпеченості методичними посібниками: " ?manual-percentage "%" crlf)
    (printout t "Процент забезпеченості довідниками: " ?reference-percentage "%" crlf)
    (printout t "Загальний процент забезпеченості: " ?overall-percentage "%" crlf crlf))