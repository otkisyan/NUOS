package part1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Main prog = new Main();
        prog.run();

    }

    public void run() {

        Executor executor = new Executor();
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        int wordLength = scanner.nextInt();

        System.out.println(wordLength);
        System.out.println(text);

        text = executor.modifyString(wordLength, text);

        System.out.println(text);
    }

}
