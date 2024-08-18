package dirOne;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InputForm {
    private Task task;
    public static boolean updateBoolArray(boolean[] checkboxesColumns, boolean newVar) { if(checkboxesColumns.length >= 3) { checkboxesColumns[1] = newVar;} return newVar;}
    public Task display() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("New data");

        GridPane powerGrid = new GridPane();
        powerGrid.setPadding(new Insets(10, 10, 10, 10));
        powerGrid.setVgap(8);
        powerGrid.setHgap(10);
        powerGrid.setAlignment(Pos.CENTER);
        //nr
        Label nrLabel = new Label("Number: ");
        GridPane.setConstraints(nrLabel,0,0);
        TextField nrInput = new TextField();
        GridPane.setConstraints(nrInput,1,0);
        //uwagi
        Label notesLabel = new Label("Notes: ");
        GridPane.setConstraints(notesLabel, 0, 1);
        TextField notesInput = new TextField();
        GridPane.setConstraints(notesInput, 1, 1);
        //checkbox
        CheckBox checkboxOption = new CheckBox("Mark as done?");
        GridPane.setConstraints(checkboxOption,1,2);
        boolean[] checkboxesColumn = {false, true, false};
        //przycisk!:>
        Button submitter = new Button("Add the data");
        GridPane.setConstraints(submitter,1,3);
        submitter.setOnAction(e -> { String nr = nrInput.getText(); String notes = notesInput.getText(); boolean check = checkboxOption.isSelected();
        if (!nr.isEmpty()) { task = new Task(nr, updateBoolArray(checkboxesColumn, check), notes); popup.close();}
        });


        powerGrid.getChildren().addAll(nrLabel, nrInput, notesLabel, notesInput, submitter, checkboxOption);

        Scene scene = new Scene(powerGrid, 320, 180);
        popup.setScene(scene);
        popup.showAndWait();

        return task;
        //return new Task(inputName.getText(), inputCheckbox.isSelected(), inputName.getText());
    }
}
