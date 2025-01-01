package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        final String SERVER_ADDRESS = "127.0.0.1";
        final int SERVER_PORT = 8080;

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())))
        {

            System.out.println("Connected to the server.");
            String userHome = System.getProperty("user.home");

            String[] predefinedPaths = {
                    userHome + "/Documents",
                    userHome + "/Desktop",
                    userHome + "/Pictures",
                    userHome + "/Music",
            };

            boolean running = true;

            while (running) {
                // Output of the proposed paths
                System.out.println("Choose a directory path:");
                for (int i = 0; i < predefinedPaths.length; i++) {
                    System.out.println((i + 1) + ": " + predefinedPaths[i]);
                }
                System.out.println("9: Exit");

                // User selection input
                System.out.print("Enter your choice (0-9): ");
                int choice = Integer.parseInt(consoleReader.readLine());
                String directoryPath;

                // Define path depending on user selection
                if (choice >= 1 && choice <= predefinedPaths.length) {
                    directoryPath = predefinedPaths[choice - 1];
                } else if (choice == 9) {
                    running = false; // Set running to false to exit the loop
                    continue; // Go to the next iteration of the loop
                } else {
                    System.out.println("Invalid choice. Please try again.");
                    continue; // Continue to the next iteration if the choice is invalid
                }

                if (directoryPath != null && !directoryPath.trim().isEmpty()) {
                    // Sending directory path to the server
                    out.println(directoryPath);
                }
                // Receiving and outputting the list of files from the server
                System.out.println("Files in the directory:");
                String responseLine;
                while ((responseLine = in.readLine()) != null && !responseLine.isEmpty()) {
                    System.out.println(responseLine);
                }
            }

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}