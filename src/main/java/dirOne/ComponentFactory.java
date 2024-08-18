package dirOne;

import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.File;

import javafx.collections.ObservableList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;

import static dirOne.FileRead.loadFromFile;
import static dirOne.FileRead.saveToFile;

public class ComponentFactory {
    private String currentUser;

    public String getCurrentUser() {
        return currentUser;
    }

    public HBox createHbox(Stage primaryStage, TableView<Task> tableView, ObservableList<String> users, String... buttonNames) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #900009;");

        ComboBox<String> userSelection = new ComboBox<>(users);
        userSelection.setPromptText("Choose a user");
        userSelection.setOnAction(e -> currentUser = userSelection.getValue());

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File file = fileChooser.showSaveDialog(primaryStage);

            if (file != null) {
                saveToFile(tableView, file.getAbsolutePath());
            }
        });
        Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File file = fileChooser.showOpenDialog(primaryStage);

            if (file != null) {
                loadFromFile(tableView, file);
            }
        });
        Button deleteButton = new Button("Delete selected row");
        deleteButton.setOnAction(e -> {
            Task selectedTask = tableView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                tableView.getItems().remove(selectedTask);
            }
        });
        hbox.getChildren().add(loadButton);
        hbox.getChildren().add(saveButton);
        hbox.getChildren().add(deleteButton);
        hbox.getChildren().add(userSelection);

        return hbox;
    }
    public static TableColumn<Task, String> createUserColumn() {
        TableColumn<Task, String> userColumn = new TableColumn<>("User");
        userColumn.setCellValueFactory(cellData -> cellData.getValue().userProperty());
        userColumn.setEditable(false); // Make this column non-editable!

        return userColumn; }
    public <Task> TableView<Task> createTable(ObservableList<Task> data, String[] nameColumn, String[] nameProperty, boolean[] checkboxCol) {
        TableView<Task> tableView = new TableView<>();


        for (int i = 0; i < nameColumn.length; i++) {
            TableColumn<Task, ?> column;

            if (checkboxCol[i]) {
                TableColumn<Task, Boolean> checkboxColumn = new TableColumn<>(nameColumn[i]);
                checkboxColumn.setCellValueFactory(new PropertyValueFactory<>(nameProperty[i]));
                checkboxColumn.setCellFactory(col -> new CheckBoxTableCell<>());
                checkboxColumn.setEditable(true);
                column = checkboxColumn;
            } else {
                TableColumn<Task, String> dataColumn = new TableColumn<>(nameColumn[i]);
                dataColumn.setCellValueFactory(new PropertyValueFactory<>(nameProperty[i]));
                dataColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                dataColumn.setEditable(true);
                column = dataColumn;
            }
            tableView.getColumns().add(column);
            //tableView.setItems(data);

        }

        tableView.setItems(data);
        tableView.setEditable(true);
        return tableView;

    }
    private static class CheckBoxTableCell<S> extends TableCell<S, Boolean> {
        private final CheckBox checkbox;

        CheckBoxTableCell() {
            this.checkbox = new CheckBox();
            this.checkbox.setOnAction(e -> { if (getTableRow() != null && getTableRow().getItem() != null) {
                ObservableValue<Boolean> observableValue = getTableColumn().getCellObservableValue(getIndex());
                if (observableValue instanceof BooleanProperty) {
                    BooleanProperty booleanProp = (BooleanProperty) observableValue;
                    booleanProp.set(checkbox.isSelected());
                } else if (observableValue instanceof ReadOnlyObjectWrapper) {
                    ReadOnlyObjectWrapper<Boolean> wrapper = (ReadOnlyObjectWrapper<Boolean>) observableValue;
                    wrapper.set(checkbox.isSelected());
                } }
            });
        }
        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                checkbox.setSelected(item != null && item);
                setGraphic(checkbox);
                
            }
        }
    }
}