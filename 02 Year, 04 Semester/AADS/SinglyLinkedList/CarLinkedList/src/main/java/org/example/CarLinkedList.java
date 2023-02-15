package org.example;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CarLinkedList {

    private static class CarNode {

        Car car;

        // Каждый элемент знает адрес следующего элемента
        CarNode next;

        public CarNode(Car car, CarNode next) {

            this.car = car;
            this.next = next;
        }

        public CarNode(Car car) {

            this.car = car;
            this.next = null;
        }

    }

    private CarNode head;
    private int size;

    public CarLinkedList() {

        this.head = null;
    }

    public int getSize() {

        return this.size;
    }

    /**
     * Добавляет элемент в начало списка
     *
     * @param car элемент для добавления
     */
    public void addFirstCar(Car car) {

        // Созданный узел становится первым узлом в связанном списке,
        // а остальные узлы следуют за ним в том порядке, в котором они были добавлены в связанный список ранее.
        CarNode newNode = new CarNode(car, head);
        head = newNode;
        size++;
    }

    /**
     * Добавляет элемент в конец списка
     *
     * @param car элемент для добавления
     */
    public void addLastCar(Car car) {

        CarNode newNode = new CarNode(car);

        // Если голова связанного списка равна null, это означает, что связанный список пуст,
        // поэтому голова устанавливается на новый узел "newNode".
        if (head == null) {

            head = newNode;
            size++;
            return;
        }

        // Если связанный список не пуст, создается новый узел "current" и устанавливается в голову связанного списка.
        CarNode current = head;

        // Цикл продолжает обход связанного списка до тех пор,
        // пока следующий узел текущего узла не станет null, что означает, что мы достигли конца связанного списка.
        while (current.next != null) {

            // Присваивая current = current.next мы перемещаем ссылку текущего узла на следующий узел в списке,
            // эффективно переходя к следующему узлу.
            current = current.next;
        }

        // Следующий узел последнего узла в связанном списке устанавливается в новый узел "newNode".
        // Таким образом, созданный узел становится последним узлом в связанном списке,
        // а все предыдущие узлы остаются в своем первоначальном порядке.
        current.next = newNode;
        size++;

    }

    /**
     * Вставляет элемент в указанную позицию в списке
     *
     * @param car   элемент для добавления
     * @param index индекс, в который должен быть вставлен элемент
     * @throws IndexOutOfBoundsException если индекс вне диапазона (index < 0 || index >= size)
     */
    public void insertCarAt(int index, Car car) {

        if (index < 0 || index >= size) {

            throw new IndexOutOfBoundsException();
        }

        // Если индекс равен 0, вызывается метод addFirstCar, чтобы добавить автомобиль в начало связанного списка.
        if (index == 0) {

            addFirstCar(car);
            return;
        }

        CarNode current = head;

        // Цикл for выполняет итерации по связанному списку,
        // перемещая ссылку текущего узла на следующий узел, пока i не станет равным index - 1.
        // Это происходит потому, что мы хотим добавить новый автомобиль после узла по адресу index - 1, а не по адресу index.
        for (int i = 0; i < index - 1; i++) {

            current = current.next;
        }

        CarNode newNode = new CarNode(car, current.next);

        // После цикла мы устанавливаем следующий узел нового автомобиля в следующий узел текущего узла.
        current.next = newNode;
        size++;
    }

    /**
     * Удаляет первый элемент из списка
     *
     * @throws NoSuchElementException если список пуст
     */
    public void removeFirstCar() {

        // Проверка пуст ли связанный список, в этом случае он просто возвращается, поскольку удалять нечего.
        if (head == null) {

            throw new NoSuchElementException();
        }

        // Устанавливает значение head в head.next, удаляя первый узел связанного списка
        // и делая второй узел новой главой связанного списка.
        head = head.next;
        size--;
    }

    /**
     * Удаляет последний элемент из списка
     *
     * @throws NoSuchElementException если список пуст
     */
    public void removeLastCar() {

        // Проверка пуст ли связанный список, в этом случае он просто возвращается, поскольку удалять нечего.
        if (head == null) {

            throw new NoSuchElementException();
        }

        // Если имеет только один узел, в этом случае он просто устанавливает значение head в null,
        // чтобы удалить весь связанный список или единственный узел, соответственно.
        if (head.next == null) {

            head = null;
            size = 0;
            return;
        }

        CarNode current = head;

        // Используется current.next.next, потому что мы хотим удалить последний узел в связанном списке.
        // Чтобы удалить последний узел, нам нужно найти предпоследний узел в связанном списке.
        while (current.next.next != null) {

            current = current.next;
        }

        // Как только мы нашли последний узел, мы устанавливаем его поле next в null,
        // что фактически удаляет последний узел из связанного списка.
        current.next = null;
        size--;

    }

    /**
     * Удаляет элемент в указанной позиции. Сдвигает все последующие элементы влево
     *
     * @param index индекс элемента для удаления
     * @throws IndexOutOfBoundsException если индекс вне диапазона (index < 0 || index >= size)
     */
    public void removeCarAt(int index) {

        if (index < 0 || index >= size) {

            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {

            removeFirstCar();
            return;
        }

        CarNode current = head;

        for (int i = 0; i < index - 1; i++) {

            current = current.next;
        }

        // После цикла current.next устанавливается в current.next.next.
        // Это пропускает удаляемый узел, соединяя узел перед ним с узлом после него.
        CarNode toRemove = current.next;
        current.next = current.next.next;
        toRemove = null;
        size--;
    }

    /**
     * Возвращает элемент в указанной позиции
     *
     * @param index индекс возвращаемого элемента
     * @return элемент в указанной позиции
     * @throws IndexOutOfBoundsException если индекс вне диапазона (index < 0 || index >= size)
     */
    public Car getCarByIndex(int index) {

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
     * Удаляет все элементы из списка
     */
    public void clear() {

        CarNode current = head;
        while (current != null) {

            CarNode previous = current;
            current = current.next;
            previous = null;
        }

        head = null;
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
    public List<Car> getCarsOfMark(String mark) {

        List<Car> carsOfMark = new ArrayList<>();
        CarNode current = head;

        while (current != null) {

            if (current.car.getMark().equals(mark)) {

                carsOfMark.add(current.car);
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
    public List<Car> getCarsWithSerialDigit(char digit) {

        List<Car> carsWithSerialDigit = new ArrayList<>();
        CarNode current = head;

        while (current != null) {

            if (current.car.getSerialNumber().indexOf(digit) != -1) {

                carsWithSerialDigit.add(current.car);
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
    public List<Car> getCarsWithDisplacementAndExploitation(double engineDisplacement, int yearsOfExploitation) {

        List<Car> carsWithDisplacementAndExploitation = new ArrayList<>();
        CarNode current = head;
        int currentYear = Year.now().getValue();

        while (current != null) {

            if (current.car.getEngineDisplacement() > engineDisplacement
                    && (currentYear - current.car.getYearOfManufacture()) < yearsOfExploitation) {

                carsWithDisplacementAndExploitation.add(current.car);
            }

            current = current.next;
        }

        return carsWithDisplacementAndExploitation;
    }
}
