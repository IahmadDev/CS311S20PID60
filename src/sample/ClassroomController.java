package sample;

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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClassroomController implements Initializable {

    @FXML
    private TextField classroomId;

    @FXML
    private TextField classroomNo;

    @FXML
    private TextField classroomType;

    @FXML
    private TextField classroomAvailable;

    @FXML
    private Tooltip crIdTooltip;

    @FXML
    private Tooltip crNoTooltip;

    @FXML
    private Tooltip crTypeTooltip;

    @FXML
    private Tooltip crAvailableTooltip;

    @FXML
    private ListView<String> classRoomsListView;

    public static ArrayList<ClassRoom> officalClassRooms = new ArrayList<>();

    ClassRoom classRoom = null;

    @FXML
    void onNextButtonPressed(ActionEvent event) throws IOException {
        int yesOrNo = 0;
        if (officalClassRooms.isEmpty()) {
            yesOrNo = ConfirmDialgoe.showConfirmDialoge("You can't make schedule without class rooms");
        }

        System.out.println("Displaying classrooms");
        for (ClassRoom classRoom : officalClassRooms) {
            System.out.println(classRoom);
        }
        System.out.println();

        if (yesOrNo == 0) {
            Parent course = FXMLLoader.load(getClass().getResource("teacherFxml.fxml"));
            Scene courseScene = new Scene(course);
            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(courseScene);
            window.show();
        }
    }

    @FXML
    void onSaveButtonPressed(ActionEvent event) {

        if (isEmptyFields()) {
            showAlertDialoge();
        } else {
            String classId = classroomId.getText().toString();
            String classType = classroomType.getText().toString();
            String classNo = classroomNo.getText().toString();
            int classNumber = Integer.parseInt(classNo);
            String classAvail = classroomAvailable.getText().toString();
            boolean classAvailable = Boolean.parseBoolean(classAvail);

            classRoom = new ClassRoom(classId, classNumber, classAvailable, classType);
            officalClassRooms.add(classRoom);
            classRoomsListView.getItems().add(classRoom.getClassRoomId());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        classroomId.setTooltip(crIdTooltip);
        classroomNo.setTooltip(crNoTooltip);
        classroomType.setTooltip(crTypeTooltip);
        classroomAvailable.setTooltip(crAvailableTooltip);
    }

    public static ArrayList<ClassRoom> getOfficialClassRooms() {
        return officalClassRooms;
    }

    public boolean isEmptyFields() {
        if (classroomNo.getText().toString().isEmpty() || classroomId.getText().toString().isEmpty()
                || classroomType.getText().toString().isEmpty() || classroomAvailable.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private void showAlertDialoge() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validate Fields");
        alert.setHeaderText(null);
        alert.setContentText("Please enter into fields");
        alert.showAndWait();
    }

}
