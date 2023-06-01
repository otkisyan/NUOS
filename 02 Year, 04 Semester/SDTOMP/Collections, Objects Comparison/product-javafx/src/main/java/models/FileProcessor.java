package models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    private final String fileName = "products";
    private final String fileExtension = ".json";
    private final String directory = "userfiles/";
    private final String filePath = directory + fileName + fileExtension;

    public void checkFileExists() {

        File file = new File(filePath);

        if (!file.exists()) {

            createFile();
        }
    }

    public void createFile() {

        File file = new File(filePath);

        try {

            file.createNewFile();

        } catch (IOException ex) {

            ex.printStackTrace();
        }

    }

    public void writeFile(List<Product> list) {

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(filePath), list);

        } catch (IOException ex) {

            ex.printStackTrace();
        }

    }

    public List<Product> readFile() {

        File file = new File(filePath);
        List<Product> list = new ArrayList<>();

        if (file.length() == 0) {

            return list;
        }

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            list = objectMapper.readValue(new File(filePath), new TypeReference<>() {
            });

        } catch (IOException err) {

            err.printStackTrace();
        }

        return list;


    }


}
