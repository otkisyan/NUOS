import java.io.File;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        main.run();
    }


    public void run() {

        String separator = File.separator;
        String filePath = "userfiles" + separator + "lab42.txt";

        File file = new File(filePath);

        Reader reader = new Reader();
        Processor processor = new Processor();

        double[] oneDimensionalArray;
        double[][] twoDimensionalArray;

        // oneDimensionalArray = reader.readOneDimensionalArray(filePath);
        // twoDimensionalArray = reader.readTwoDimensionalArray(filePath);
        oneDimensionalArray = reader.readOneDimensionalArray(file);
        twoDimensionalArray = reader.readTwoDimensionalArray(file);

        processor.processArray(oneDimensionalArray);
        processor.processArray(twoDimensionalArray);


        System.out.println("Сума квадратів всіх елементів масиву: " + processor.calculate(oneDimensionalArray));
        System.out.println("Сума від'ємних елементів заштрихованої області: " + processor.calculate(twoDimensionalArray));
    }
}
