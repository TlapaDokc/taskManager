package ru.netology.javacore;

public class Request {
    private String type;
    private String task;

    public Request() {
    }

    public Request(String type, String task) {
        this.type = type;
        this.task = task;
    }

    public String getType() {
        return type;
    }

    public String getTask() {
        return task;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
