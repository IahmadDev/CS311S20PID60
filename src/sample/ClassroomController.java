package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    private ListView<ClassRoom> classRoomsListView;

    public static ArrayList<ClassRoom> officalClassRooms = new ArrayList<>();

    ClassRoom classRoom = null;

    @FXML
    void onNextButtonPressed(ActionEvent event) throws IOException {

        System.out.println("Displaying classrooms");
        for(ClassRoom classRoom : officalClassRooms){
            System.out.println(classRoom);
        }
        System.out.println();

        Parent course  = FXMLLoader.load(getClass().getResource("courseControllerFxml.fxml"));
        Scene courseScene = new Scene(course);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(courseScene);
        window.show();
    }

    @FXML
    void onSaveButtonPressed(ActionEvent event) {
        String classId = classroomId.getText().toString();
        String classType = classroomType.getText().toString();
        String classNo = classroomNo.getText().toString();
        int classNumber = Integer.parseInt(classNo);
        String classAvail = classroomAvailable.getText().toString();
        boolean classAvailable = Boolean.parseBoolean(classAvail);

        classRoom = new ClassRoom(classId,classNumber,classAvailable,classType);
        officalClassRooms.add(classRoom);

        //        // ClassRooms
//        ClassRoom N1 = new ClassRoom("N1", 1, true, "Lecture");
//        ClassRoom N2 = new ClassRoom("N2", 2, true, "Lecture");
//        ClassRoom N3 = new ClassRoom("N3", 3, true, "Lecture");
//
//        officalClassRooms.add(N1);
//        officalClassRooms.add(N2);
//        officalClassRooms.add(N3);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {    }

    public static ArrayList<ClassRoom> getOfficialClassRooms(){
        return officalClassRooms;
    }

}
