package com.example.sippahealth;

public class ToDoItem {

    private String toDoContent;
    private String toDoActivity;

    public ToDoItem() {
        this.toDoContent = "";
        this.toDoActivity = "";
    }

    public ToDoItem(String toDoContent, String toDoActivity) {
        this.toDoContent = toDoContent;
        this.toDoActivity = toDoActivity;
    }

    public String getToDoContent() { return toDoContent; }
    public String getToDoActivity() { return toDoActivity; }
}
