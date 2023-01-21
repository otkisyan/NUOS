package logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import products.Product;

import java.util.ArrayList;
import java.util.List;

class LogicTest {
    Logic logic;

    @BeforeEach
    void setUp() {

        logic = new Logic();
    }

    @Test
    void addProduct() {

        Product newProduct = new Product("Test", "Testovich", 1, 2, 3);
        List<Product> listExcepted = new ArrayList<>();
        listExcepted.add(newProduct);

        List<Product> actualList = new ArrayList<>();
        //logic.addProduct(actualList);

    }

    @Test
    void removeProduct() {


    }

    @Test
    void filterByName() {

        Product newProduct1 = new Product("test1", "testovich", 1, 2, 3);
        List<Product> list = new ArrayList<>();
        list.add(newProduct1);

        List<Product> tempList;

        tempList = logic.filterByName("test1", list);

        assertEquals(tempList, list);

    }

    @Test
    void filterNotExceedPrice() {

    }

    @Test
    void filterPeriodLongerThan() {

    }
}