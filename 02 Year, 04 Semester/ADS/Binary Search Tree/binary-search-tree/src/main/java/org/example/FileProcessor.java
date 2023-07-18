package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileProcessor {

    public Tree readTree(String filePath) {

        Tree tree;

        try (Scanner in = new Scanner(new File(filePath))) {

            int first = in.nextInt();
            tree = new Tree(first);

            while(in.hasNextInt()){

                tree.insert(tree, in.nextInt());

            }

        } catch (IOException err) {

            return null;
        }

        return tree;
    }
}
