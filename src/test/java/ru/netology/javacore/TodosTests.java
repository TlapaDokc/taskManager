package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TodosTests {
    Todos taskManagerTest = new Todos();

    @Test
    @DisplayName("Тестирование методов addTask и getAllTasks на корректный результат")
    void addTaskTest() {
        taskManagerTest.addTask("здА");
        taskManagerTest.addTask("здВ");
        taskManagerTest.addTask("здГ");
        taskManagerTest.addTask("здБ");
        taskManagerTest.addTask("здД");
        taskManagerTest.addTask("здЕ");
        taskManagerTest.addTask("здЖ");
        taskManagerTest.addTask("ЗдЗ");
        String expectedResult = "здА здБ здВ здГ здД здЕ здЖ ";
        Assertions.assertEquals(expectedResult, taskManagerTest.getAllTasks());
    }

    @Test
    @DisplayName("Тестирование методов removeTask и getAllTasks на корректный результат")
    void removeTaskTest() {
        addTaskTest();
        taskManagerTest.removeTask("здД");
        taskManagerTest.removeTask("здБ");
        taskManagerTest.removeTask("здЕ");
        String expectedResult = "здА здВ здГ здЖ ";
        Assertions.assertEquals(expectedResult, taskManagerTest.getAllTasks());
    }

    @Test
    @DisplayName("Тестирование метода restoreRequest на корректный результат")
    void restoreRequestTest() {
        taskManagerTest.addTask("Первая");
        Request addFirst = new Request("ADD", "Первая");
        taskManagerTest.addLastTask(addFirst);

        taskManagerTest.addTask("Вторая");
        Request addSecond = new Request("ADD", "Вторая");
        taskManagerTest.addLastTask(addSecond);

        taskManagerTest.removeTask("Первая");
        Request removeFirst = new Request("REMOVE", "Первая");
        taskManagerTest.addLastTask(removeFirst);

        taskManagerTest.addTask("Третья");
        Request addThird = new Request("ADD", "Третья");
        taskManagerTest.addLastTask(addThird);

        taskManagerTest.restoreRequest();
        taskManagerTest.restoreRequest();
        Assertions.assertEquals("Вторая Первая ", taskManagerTest.getAllTasks());
    }
}
