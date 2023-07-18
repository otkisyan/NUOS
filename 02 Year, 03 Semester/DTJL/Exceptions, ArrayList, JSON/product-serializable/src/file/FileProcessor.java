package file;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import products.Product;

public class FileProcessor {

    private final String fileName = "products";
    private final String fileExtension = ".json";
    private final String directory = "userfiles/";
    private final String filePath = directory + fileName + fileExtension;

    public void CheckFileExists() {

        File file = new File(filePath);

        if (!file.exists()) {

            CreateFile();
        }
    }

    public void CreateFile() {

        File file = new File(filePath);

        try {

            file.createNewFile();

        } catch (IOException ex) {

            ex.printStackTrace();
        }

    }

    public void WriteFile(List<Product> list) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {

            Gson gson = new Gson();
            String json = gson.toJson(list);
            oos.writeObject(json);

        } catch (IOException ex) {

            ex.printStackTrace();
        }

       /* try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(filePath), list);

        } catch (IOException ex) {

            ex.printStackTrace();
        }*/


       /* File file = new File(filePath);

        try {

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

            oos.writeInt(list.size());

            for (Product p : list) {

                oos.writeObject(p);
            }

            // oos.writeObject(list);

            oos.close();

        } catch (IOException ex) {

            ex.printStackTrace();
        }*/
    }

    public List<Product> ReadFile(List<Product> list) {

        File file = new File(filePath);

        if (file.length() == 0) {

            return list;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {

            Gson gson = new Gson();
            String object = (String) ois.readObject();

            /*
             * TypeToken - представляет общий тип T. Java еще не предоставляет способ представления общих типов,
             * поэтому этот класс делает это. Заставляет клиентов создавать подкласс этого класса,
             * что позволяет получить информацию о типе даже во время выполнения.
             */

            return gson.fromJson(object, new TypeToken<List<Product>>() {
            }.getType());

        } catch (ClassNotFoundException | IOException e) {

            throw new RuntimeException(e);

        }

       /* try{

            ObjectMapper objectMapper = new ObjectMapper();
            list = objectMapper.readValue(new File(filePath), new TypeReference<List<Product>>() {
            });

        }
        catch (IOException err){

            err.printStackTrace();
        }

        return list;*/


/*
        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            int size = ois.readInt();

            if (size == 0) {

                return list;
            }

            for (int i = 0; i < size; i++) {

                Product p = (Product) ois.readObject();
                list.add(p);
            }

            ois.close();

        } catch (IOException ex) {

            ex.printStackTrace();

        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);
        }*/

    }


}
