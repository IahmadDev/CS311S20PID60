package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {

    @FXML
    private TextField instructorName;

    @FXML
    private TextField instructorTitle;

    @FXML
    private TextField instructorId;

    @FXML
    private Tooltip instructorNameTooltip;

    @FXML
    private Tooltip instructorIdTooltip;

    @FXML
    private Tooltip instructorTitleTooltip;

    @FXML
    private ListView<String> instructorListView;

    public static ArrayList<Teacher> officialTeachers;
    public static ObservableList<String> teachers = FXCollections.observableArrayList();
    Teacher teacher = null;

    @FXML
    void onNextButtonPressed(ActionEvent event) throws IOException {
        int yesOrNo = 0;
        if (officialTeachers.isEmpty()) {
            yesOrNo = ConfirmDialgoe.showConfirmDialoge("You cant make schedule without teachers");
        }

        if (yesOrNo == 0) {
            Parent dayParent = FXMLLoader.load(getClass().getResource("courseControllerFxml.fxml"));
            Scene dayScene = new Scene(dayParent);
            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(dayScene);
            window.show();
        }
    }

    @FXML
    void onSaveButtonPressed(ActionEvent event) {
        if (isEmptyFields()) {
            ConfirmDialgoe.showAlertDialoge("Validate Fields","Please enter into fields");
        } else {
            String name = instructorName.getText().toString();
            String id = instructorId.getText().toString();
            String title = instructorTitle.getText().toString();

            teacher = new Teacher(id, name, title);
            officialTeachers.add(teacher);
            instructorListView.getItems().add(name);
            teachers.add(name);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        officialTeachers = new ArrayList<>();
        instructorName.setTooltip(instructorNameTooltip);
        instructorId.setTooltip(instructorIdTooltip);
        instructorTitle.setTooltip(instructorTitleTooltip);
    }

    public boolean isEmptyFields() {
        if (instructorName.getText().toString().isEmpty() || instructorId.getText().toString().isEmpty()
                || instructorTitle.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

}
