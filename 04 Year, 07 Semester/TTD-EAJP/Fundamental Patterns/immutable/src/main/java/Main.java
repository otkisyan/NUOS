import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> hobbies = new ArrayList<>(Arrays.asList("Reading", "Traveling", "Swimming"));
        ImmutablePerson person = new ImmutablePerson("Alice", 30, hobbies);

        System.out.println(person.getName());   // Alice
        System.out.println(person.getAge());    // 30
        System.out.println(person.getHobbies()); // [Reading, Traveling, Swimming]

        // Попробуем изменить список после создания объекта
        hobbies.add("Cooking"); // Это изменение не повлияет на объект person

        System.out.println(person.getHobbies()); // [Reading, Traveling, Swimming]
    }
}
