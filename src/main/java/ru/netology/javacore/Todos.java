package ru.netology.javacore;

import java.util.*;

public class Todos {
    private Set<String> tasksList = new TreeSet<>();
    private int maxCountTasks = 7;
    private Stack<Request> requestList = new Stack<>();

    public void addTask(String task) {
        if (tasksList.size() < maxCountTasks) {
            tasksList.add(task);
        }
    }

    public void removeTask(String task) {
        if (tasksList.contains(task)) {
            tasksList.remove(task);
        }
    }

    public String getAllTasks() {
        String tasks = "";
        if (!tasksList.isEmpty()) {
            Iterator<String> iterator = tasksList.iterator();
            while (iterator.hasNext()) {
                tasks = tasks + iterator.next() + " ";
            }
        } else {
            tasks = "Список задач пуст";
        }
        return tasks;
    }

    public void addLastTask(Request request) {
        requestList.add(request);
    }

    public void restoreRequest() {
        if (!requestList.isEmpty()) {
            Request lastRequest = requestList.pop();
            switch (lastRequest.getType()) {
                case "ADD":
                    tasksList.remove(lastRequest.getTask());
                    break;
                case "REMOVE":
                    tasksList.add(lastRequest.getTask());
                    break;
                default:
                    break;
            }
        }
    }
}
