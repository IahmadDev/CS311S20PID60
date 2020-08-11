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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;


public class CourseController implements Initializable {

    @FXML
    private TextField courseName;

    @FXML
    private TextField courseCode;

    @FXML
    private Tooltip courseNameTooltip;

    @FXML
    private Tooltip courseCodeTooltip;

    @FXML
    private Tooltip courseIdTooltip;

    @FXML
    private Tooltip courseLabStatusTooltip;

    @FXML
    private ComboBox<Integer> courseCreditHoursChoiceBox;

    @FXML
    private ComboBox<String> courseInstructorChoiceBox;

    @FXML
    private ComboBox<Integer> courseWHChoiceBox;

    @FXML
    private TextField courseId;

    @FXML
    private TextField labStatus;

    @FXML
    private Tooltip teachersTooltip;

    @FXML
    private Tooltip creditHoursTooltip;

    @FXML
    private Tooltip weeklyHoursTooltip;

    @FXML
    private ListView<String> courseListView;

    public ArrayList<Course> courses = new ArrayList<>();

    public static ArrayList<Course> officialCourses = new ArrayList<>();

    @FXML
    void onNextButtonPressed(ActionEvent event) throws IOException {
        int yesOrNo = 0;
        if (courses.isEmpty()) {
            yesOrNo = ConfirmDialgoe.showConfirmDialoge("You can't make schedule without courses");
        }

        for (Course c : courses) {
            System.out.println(c);
        }

        if (yesOrNo == 0) {
            writingToFile(courses);
            Parent teacher = FXMLLoader.load(getClass().getResource("dayFxml.fxml"));
            Scene teacherScene = new Scene(teacher);
            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(teacherScene);
            window.show();
        }
    }

    @FXML
    void onSaveButtonPressed(ActionEvent event) {

        if (isEmptyFields()) {
            ConfirmDialgoe.showAlertDialoge("Validate Fields","Please enter into fields");
        } else if (isEmptyChoiceBoxes()) {
            ConfirmDialgoe.showAlertDialoge("Validate choice boxes","Please select from choice boxes");
        } else {

            String name = courseName.getText().toString();
            String id = courseId.getText().toString();
            String code = courseCode.getText().toString();
            int creditHours = Integer.parseInt(courseCreditHoursChoiceBox.getValue().toString());
            String instructor = courseInstructorChoiceBox.getValue().toString();
            int weeklyLectures = Integer.parseInt(courseWHChoiceBox.getValue().toString());
            boolean lab = Boolean.parseBoolean(labStatus.getText().toString());

            Course course = new Course(name, id, code, creditHours, instructor, 0, weeklyLectures, lab);
            courses.add(course);
            courseListView.getItems().add(name);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courseInstructorChoiceBox.setItems(TeacherController.teachers);
        ObservableList<Integer> integers = FXCollections.observableArrayList();
        integers.addAll(1, 2, 3, 4, 5);
        courseCreditHoursChoiceBox.setItems(integers);
        courseWHChoiceBox.setItems(integers);

        courseName.setTooltip(courseNameTooltip);
        courseCode.setTooltip(courseCodeTooltip);
        courseId.setTooltip(courseIdTooltip);
        labStatus.setTooltip(courseLabStatusTooltip);

        courseInstructorChoiceBox.setTooltip(teachersTooltip);
        courseWHChoiceBox.setTooltip(weeklyHoursTooltip);
        courseCreditHoursChoiceBox.setTooltip(creditHoursTooltip);
    }

    public static ArrayList<Course> getOfficialCourses() {
        officialCourses = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("courses.txt"));
            scanner.useDelimiter(",");

            while (scanner.hasNextLine()) {

                String name = scanner.next();
                scanner.skip(scanner.delimiter());

                String id = scanner.next();
                scanner.skip(scanner.delimiter());

                String code = scanner.next();
                scanner.skip(scanner.delimiter());

                int creditHours = scanner.nextInt();
                scanner.skip(scanner.delimiter());

                String instructor = scanner.next();
                scanner.skip(scanner.delimiter());

                int studied = scanner.nextInt();
                scanner.skip(scanner.delimiter());

                int weeklyLectures = scanner.nextInt();
                scanner.skip(scanner.delimiter());

                boolean labStatus = Boolean.parseBoolean(scanner.nextLine());

                Course course = new Course(name, id, code, creditHours, instructor, studied, weeklyLectures, labStatus);
                officialCourses.add(course);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return officialCourses;
    }

    public void writingToFile(ArrayList<Course> courses) {
        FileWriter locFile = null;
        try {
            locFile = new FileWriter("courses.txt");
            for (Course course : courses) {
                locFile.write(course.getCourseName() + "," + course.getCourseId() + "," + course.getCourseCode() + "," +
                        course.getCourseCreditHours() + "," + course.getCourseInstructorId() + "," + course.getStudied() + "," +
                        course.getWeeklyLectures() + "," + course.getLabStatus() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("in catch block");
        } finally {
            try {
                if (locFile != null) {
                    locFile.close();
                }
            } catch (IOException e) {
                System.out.println("in finally block");
                e.printStackTrace();
            }
        }
    }

    public boolean isEmptyFields() {
        if (courseName.getText().toString().isEmpty() || courseCode.getText().toString().isEmpty()
                || courseId.getText().toString().isEmpty() || labStatus.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmptyChoiceBoxes() {
        if (courseCreditHoursChoiceBox.getValue() == null || courseWHChoiceBox.getValue() == null
                || courseInstructorChoiceBox.getValue() == null) {
            return true;
        } else {
            return false;
        }
    }

}
