package org.example;

import java.time.Year;
import java.util.NoSuchElementException;

public class CarLinkedList {

    private static class CarNode {

        Car car;
        CarNode next;
        CarNode prev;

        public CarNode(Car car, CarNode next, CarNode prev) {

            this.car = car;
            this.next = next;
            this.prev = prev;

        }

        public CarNode(Car car) {

            this.car = car;
            this.next = null;
            this.prev = null;
        }
    }

    private CarNode head;
    private CarNode tail;
    private int size;

    public CarLinkedList() {

        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int getSize() {

        return this.size;
    }


    public boolean isEmpty() {

        return head == null;
    }

    /**
     * Добавляет новый объект Car в начало связанного списка.
     *
     * @param car объект Car, добавляемый в связный список.
     */
    public void addFirst(Car car) {

        CarNode newNode = new CarNode(car);

        if (isEmpty()) {

            // Если связный список пуст, устанавливаем tail на новый узел
            tail = newNode;

        } else {

            // Если head связанного списка не равен null (т.е. связанный список не пуст)
            // Устанавливаем предыдущий узел текущей head на новый узел
            head.prev = newNode;
        }

        newNode.next = head;
        head = newNode;
        size++;

    }

    /**
     * Добавляет новый объект Car в конец связанного списка.
     *
     * @param car объект Car для добавления в список.
     */
    public void addLast(Car car) {

        CarNode newNode = new CarNode(car);

        if (isEmpty()) {

            // Если связный список пуст, устанавливаем head на новый узел
            head = newNode;
        } else {

            tail.next = newNode;
        }

        // Устанавливаем предыдущий узел нового узла как старый tail
        newNode.prev = tail;
        tail = newNode;
        size++;

    }

    /**
     * Вставляет новый объект Car в список по указанному индексу.
     *
     * @param index индекс, по которому вставляется новый объект.
     * @param car   объект Car, который нужно вставить в список
     * @throws IndexOutOfBoundsException, если индекс находится вне диапазона (index < 0 || index > size)
     */
    public void insertAt(int index, Car car) {

        if (index < 0 || index > size) {

            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {

            addFirst(car);
            return;

        }

        if (index == size) {

            addLast(car);

        }

        CarNode current = head;

        for (int i = 0; i < index; i++) {

            current = current.next;

        }

        CarNode newNode = new CarNode(car, current, current.prev);

        // Следующий указатель узла перед newNode (т.е. current.prev) должен указывать на newNode,
        // а указатель prev текущего узла должен указывать на newNode.
        current.prev.next = newNode;
        current.prev = newNode;
        size++;

    }

    /**
     * Удаляет первый элемент из списка
     *
     * @throws NoSuchElementException если список пуст
     */
    public void removeFirst() {

        // Проверка пуст ли связанный список
        if (head == null) {

            throw new NoSuchElementException();
        }

        // Проверка, есть ли в списке только один узел (т.е. head.next == null).
        // Если это так, то это означает, что head является единственным узлом в списке,
        // поэтому его удаление сделает список пустым.
        // В этом случае tail устанавливается в null, чтобы отразить, что список теперь пуст.
        if (head.next == null) {

            tail = null;

        } else {

            // Если head не является единственным узлом в списке,
            // устанавливаем указатель prev второго узла в списке в null.
            // Это происходит потому, что первый узел удаляется, и новый первый узел не должен иметь указателя prev.
            head.next.prev = null;
        }

        head = head.next;
        size--;
    }

    /**
     * Удаляет последний элемент из списка
     *
     * @throws NoSuchElementException если список пуст
     */
    public void removeLast() {

        // Проверка пуст ли связанный список
        if (head == null) {

            throw new NoSuchElementException();
        }

        // Если у tail нет предыдущего узла (т.е. в списке только один узел),
        // то список устанавливается пустым путем установки head в null.
        if (tail.prev == null) {

            head = null;
        }

        // Если у tail есть предыдущий узел (т.е. в списке больше одного узла),
        // то указатель next предпоследнего узла устанавливается в null, удаляя последний узел из списка.
        else {

            tail.prev.next = null;
        }

        size--;

    }

    /**
     * Удаляет элемент в указанной позиции. Сдвигает все последующие элементы влево
     *
     * @param index индекс элемента для удаления
     * @throws IndexOutOfBoundsException если индекс вне диапазона (index < 0 || index >= size)
     */
    public void removeAt(int index) {

        if (index < 0 || index >= size) {

            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {

            removeFirst();
            return;
        }

        if (index == size - 1) {

            removeLast();
            return;
        }


        CarNode current = head;

        for (int i = 0; i < index; i++) {

            current = current.next;
        }

        // 1 <--> 2(текущий) <--> 3
        // Устанавливаем следующий указатель предыдущего узла на узел после текущего узла,
        // тем самым пропуская текущий узел и удаляя его из связного списка
        current.prev.next = current.next; // 1 --> 3

        // Устанавливаем указатель предыдущего узла следующего узла на узел перед текущим узлом
        current.next.prev = current.prev; // 1 <--> 3

        size--;

    }

    /**
     * Возвращает элемент в указанной позиции
     *
     * @param index индекс возвращаемого элемента
     * @return элемент в указанной позиции
     * @throws IndexOutOfBoundsException если индекс вне диапазона (index < 0 || index >= size)
     */
    public Car getByIndex(int index) {

        if (index < 0 || index >= size) {

            throw new IndexOutOfBoundsException();
        }

        int counter = 0;

        CarNode current = head;

        while (current != null) {

            if (counter == index) {

                return current.car;
            }

            current = current.next;
            counter++;
        }

        throw new IndexOutOfBoundsException();
    }

    /**
     * Проверяет, содержит ли список заданный объект <code>Car</code>
     *
     * @param car объект для поиска
     * @return true, если список содержит заданный объект, в противном случае false
     */
    public boolean contains(Car car) {

        CarNode current = head;

        while (current != null) {

            if (current.car.equals(car)) {

                return true;
            }

            current = current.next;
        }

        return false;
    }

    /**
     * Удаляет все элементы из списка
     */
    public void clear() {

        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Выводит все элементы списка.
     * <p>Каждый элемент будет выведен в формате, заданным его методом <code>toString()</code></p>
     */
    public void print() {

        CarNode current = head;

        while (current != null) {

            System.out.println(current.car.toString());
            current = current.next;

        }
    }

    /**
     * Возвращает список автомобилей с определенной маркой
     *
     * @param mark марка для поиска
     * @return список автомобилей с определенной маркой
     */
    public CarLinkedList getCarsOfMark(String mark) {

        CarLinkedList carsOfMark = new CarLinkedList();
        CarNode current = head;

        while (current != null) {

            if (current.car.getMark().equals(mark)) {

                carsOfMark.addLast(current.car);
            }

            current = current.next;
        }

        return carsOfMark;
    }

    /**
     * Возвращает список автомобилей с серийным номером, содержащим указанную цифру.
     *
     * @param digit цифра для поиска в серийном номере
     * @return список автомобилей с серийным номером, содержащим указанную цифру
     */
    public CarLinkedList getCarsWithSerialDigit(char digit) {

        CarLinkedList carsWithSerialDigit = new CarLinkedList();
        CarNode current = head;

        while (current != null) {

            if (current.car.getSerialNumber().indexOf(digit) != -1) {

                carsWithSerialDigit.addLast(current.car);
            }

            current = current.next;
        }

        return carsWithSerialDigit;
    }

    /**
     * Возвращает список автомобилей с объемом двигателя больше определенного и эксплуатацией меньше определенных лет
     *
     * @param engineDisplacement  объем двигателя
     * @param yearsOfExploitation лет в эксплуатации
     * @return список автомобилей с объемом двигателя больше определенного и эксплуатацией меньше определенных лет
     */
    public CarLinkedList getCarsWithDisplacementAndExploitation(double engineDisplacement, int yearsOfExploitation) {

        CarLinkedList carsWithDisplacementAndExploitation = new CarLinkedList();
        CarNode current = head;
        int currentYear = Year.now().getValue();

        while (current != null) {

            if (current.car.getEngineDisplacement() > engineDisplacement
                    && (currentYear - current.car.getYearOfManufacture()) < yearsOfExploitation) {

                carsWithDisplacementAndExploitation.addLast(current.car);
            }

            current = current.next;
        }

        return carsWithDisplacementAndExploitation;
    }
}
