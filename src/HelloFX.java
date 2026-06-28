package src;

import java.util.List;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class HelloFX extends Application {
  private Person p;
  private Label label;
  private TextField nameField;
  private TextField todoField;
  private Label todoLabel;

  public static void main(String[] args) {
    HelloFX.launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Person editor");

    p = new Person("person", 17);
    label = new Label("Person details: " + p);

    nameField = new TextField();
    nameField.setPromptText("Enter new name");
    Button changeNameBtn = new Button("Change name");
    changeNameBtn.setOnAction(e -> updateName());

    Button increaseAgeBtn = new Button("Increase age");
    increaseAgeBtn.setOnAction(e -> {
      p.increaseAge();
      updateLabel();
    });

    Button decreaseAgeBtn = new Button("Decrease age");
    decreaseAgeBtn.setOnAction(e -> {
      p.decreaseAge();
      updateLabel();
    });

    int horizontalPadding = 5;
    HBox nameRow = new HBox(horizontalPadding, nameField, changeNameBtn);
    nameRow.setAlignment(Pos.CENTER);

    HBox ageRow = new HBox(horizontalPadding, increaseAgeBtn, decreaseAgeBtn);
    ageRow.setAlignment(Pos.CENTER);

    todoField = new TextField();
    todoField.setPromptText("Enter task");
    Button addTodoButton = new Button("Add to-do");
    addTodoButton.setOnAction(e -> {
      updateTodo();
    });

    HBox taskRow = new HBox(horizontalPadding, todoField, addTodoButton);
    taskRow.setAlignment(Pos.CENTER);

    int verticalPadding = 5;
    VBox root = new VBox(verticalPadding, label, nameRow, ageRow, taskRow);
    root.setAlignment(Pos.CENTER);

    Scene scene = new Scene(root, 300, 300);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void updateLabel() {
    label.setText("Person details: " + p);
  }

  // private void updateTodoLabel() {
  // for (ToDo l : this.p.todos){
  // l
  // }
  // }

  private void updateName() {
    String newName = nameField.getText();
    if (!newName.trim().isEmpty()) {
      p.setName(newName);
      updateLabel();
      nameField.clear();
    }

  }

  private void updateTodo() {
    String newTask = todoField.getText();
    ToDo newToDo = new ToDo(newTask);
    p.addTodo(newToDo);
    updateLabel();
    todoField.clear();
  }
}

class Person {
  String name;
  int age;
  List<ToDo> todos;

  Person(String name, int age) {
    this.name = name;
    this.age = age;
    this.todos = new ArrayList<>();

  }

  void addTodo(ToDo task) {
    this.todos.add(task);
  }

  void increaseAge() {
    this.age++;
  }

  void decreaseAge() {
    this.age--;
  }

  void setName(String name) {
    this.name = name;
  }

  public String toString() {
    String str = this.name + ", aged" + this.age;
    for (ToDo t : this.todos) {
      str += "\n- " + t.task;
    }
    return str;
  }
}

class ToDo {
  String task;

  ToDo(String task) {
    this.task = task;
  }

  public String toString() {
    return "task: " + this.task;
  }
}
