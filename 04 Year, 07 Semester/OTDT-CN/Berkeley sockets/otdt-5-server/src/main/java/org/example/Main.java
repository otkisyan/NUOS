package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// https://stackoverflow.com/questions/28929643/understanding-how-bufferedreader-works-in-java
// https://stackoverflow.com/questions/4638974/what-is-the-buffer-size-in-bufferedreader
// After the connection is established, the server displays a list of files in the specified directory
public class Main {
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        // Creates a thread pool that creates new threads as needed,
        // but will reuse previously constructed threads when they are available.
        // If no existing thread is available, a new thread will be created and added to the pool.
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server started. Waiting for client...");

            while (true) {
                // Listens for a connection to be made to this socket and accepts it.
                // The method blocks until a connection is made.
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected " + clientSocket.getRemoteSocketAddress());

                // Tasks are small units of code that could be executed in parallel.
                // The threads (in a thread pool) are what execute them.
                // You can think of the threads like workers and the tasks like jobs.
                // Jobs can be done in parallel, and workers can work in parallel. Workers work on jobs.
                threadPool.submit(() -> handleClient(clientSocket));
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private void handleClient(Socket clientSocket) {
        try (
                // InputStreamReader converts the raw byte stream (InputStream) from the socket
                // into a character stream (text). Since network data is typically transmitted as bytes.
                // BufferedReader is a wrapper for reading characters with buffering.
                // It reads data on a line-by-line basis, providing methods such as readLine(),
                // which blocks execution if there is no data to read.
                // Without buffering, each invocation of read() or readLine() could cause bytes to be read,
                // converted into characters, and then returned, which can be very inefficient.
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String clientAddress = clientSocket.getInetAddress().getHostAddress();
            int clientPort = clientSocket.getPort();
            String directoryPath;

            // Read directory path from client
            // readLine returns only null, if eof is reached (= socked is closed)
            // and returns a String if a '\n' is read.
            while ((directoryPath = in.readLine()) != null) {
                System.out.println("Request from " + clientAddress + ":" + clientPort + " - directory path: " + directoryPath);

                // Check if path exists and is a directory
                Path path = Paths.get(directoryPath);
                if (Files.exists(path) && Files.isDirectory(path)) {
                    StringBuilder fileList = new StringBuilder();
                    // DirectoryStream - an object to iterate over the entries in a directory.
                    // A directory stream allows for the convenient use
                    // of the for-each construct to iterate over a directory.
                    try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                        for (Path entry : stream) {
                            boolean isDirectory = Files.isDirectory(entry);
                            fileList.append(entry.getFileName().toString())
                                    .append(isDirectory ? " [Directory]" : " [File]")
                                    .append("\n");
                        }
                    } catch (IOException e) {
                        fileList.append("Error reading directory contents.");
                    }

                    out.println(fileList);
                } else {
                    out.println("Invalid directory path.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Client: " + clientSocket.getRemoteSocketAddress() + " disconnected.");
            } catch (IOException e) {
                System.out.println("Error while closing client socket: " + e.getMessage());
            }
        }
    }
}
