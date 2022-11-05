package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private final int port;
    private final Todos taskManager;
    private final Gson gson;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.taskManager = todos;
        gson = new Gson();
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                System.out.println("Starting server at " + port + "...");
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    System.out.println("New connection accepted");
                    Request newTask = gson.fromJson(in.readLine(), Request.class);
                    if (newTask.getType().equals("ADD") || newTask.getType().equals("REMOVE")) {
                        taskManager.addLastTask(newTask);
                    }
                    switch (newTask.getType()) {
                        case "ADD":
                            taskManager.addTask(newTask.getTask());
                            break;
                        case "REMOVE":
                            taskManager.removeTask(newTask.getTask());
                            break;
                        case "RESTORE":
                            taskManager.restoreRequest();
                            break;
                        default:
                            break;
                    }
                    out.println(taskManager.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("server cannot start");
            e.printStackTrace();
        }
    }
}
