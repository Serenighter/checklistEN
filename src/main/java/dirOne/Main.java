package dirOne;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ViewController viewController = new ViewController();
        ComponentFactory componentFactory = new ComponentFactory();

        System.out.println(System.getProperty("javafx.version"));

        Scene scene = new Scene(viewController.getRoot(), 1280,720); //(viewController.getRoot()!!!) & window size
        stage.setTitle("Checklist App"); //title
        stage.setScene(scene);
        stage.show();

        ObservableList<String> users = FXCollections.observableArrayList("User 1", "User 2", "User 3");
        ObservableList<Task> data = FXCollections.observableArrayList(
                new Task("1",true,"data")

        );
        //data!
        String[] nameColumn = {"Number", "Status", "Notes"}; //naming risk
        String[] nameProperty = {"nr", "check", "notes"};
        boolean[] checkboxesColumns = {false, true, false}; //!?
        TableView<Task> tableView = componentFactory.createTable(data, nameColumn, nameProperty, checkboxesColumns);
        tableView.getColumns().add(ComponentFactory.createUserColumn()); // ---
        viewController.addComponentToCenter(tableView);

        tableView.getColumns().forEach(e -> e.setReorderable(false));

        HBox topButton = componentFactory.createHbox(stage, tableView, users, "Save");
        viewController.addComponentToTop(topButton);

        Button addingButton = new Button("Add a table");
        addingButton.setOnAction(e -> {InputForm taskInput = new InputForm();
        Task newtask = taskInput.display();
        if (newtask != null) { newtask.setUser(componentFactory.getCurrentUser());;data.add(newtask); }
        });
        viewController.addComponentToBottom(addingButton);


    }
}