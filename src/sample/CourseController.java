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
    private TextField courseCreditHours;

    @FXML
    private TextField courseInstructorName;

    @FXML
    private TextField weeklyHours;

    @FXML
    private TextField courseId;

    @FXML
    private TextField labStatus;

    @FXML
    private ListView<Course> courseListView;

    public ArrayList<Course> courses = new ArrayList<>();

    public static ArrayList<Course> officialCourses = new ArrayList<>();

    @FXML
    void onNextButtonPressed(ActionEvent event) throws IOException {

        for(Course c: courses){
            System.out.println(c);
        }
        writingToFile(courses);

        Parent teacher = FXMLLoader.load(getClass().getResource("teacherFxml.fxml"));
        Scene teacherScene = new Scene(teacher);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(teacherScene);
        window.show();
    }

    @FXML
    void onSaveButtonPressed(ActionEvent event) {
        String name = courseName.getText().toString();
        String id = courseId.getText().toString();
        String code = courseCode.getText().toString();
        int creditHours = Integer.parseInt(courseCreditHours.getText().toString());
        String instructor = courseInstructorName.getText().toString();
        int weeklyLectures = Integer.parseInt(weeklyHours.getText().toString());
        boolean lab = Boolean.parseBoolean(labStatus.getText().toString());

        Course course = new Course(name,id,code,creditHours,instructor,0,weeklyLectures,lab);
        courses.add(course);
////         courses
//        Course AOA = new Course("AOA", "CS131", "CS131", 3, "Samyan", 0, 3, false);
//        Course DBS = new Course("DBS", "CS132", "CS132", 3, "Atif", 0, 3, false);
//        Course MVC = new Course("MVC", "CS133", "CS133", 3, "Rubina", 0, 3, false);
//        Course OS = new Course("OS", "CS134", "CS134", 3, "Sadaf", 0, 3, false);
//        Course TAF = new Course("TAF", "CS135", "CS135", 3, "Tauqeer", 0, 3, false);
//        Course OS_Lab = new Course("OS_Lab", "CS136", "CS136", 1, "Mahira", 0, 3, false);
//        Course DBS_Lab = new Course("DBS_Lab", "CS137", "CS137", 1, "Laeeq", 0, 3, false);
//        //   Course course = new Course("AOA","CS131","CS131",3,"Sir");
//
//        courses.add(AOA);
//        courses.add(DBS);
//        courses.add(MVC);
//        courses.add(OS);
//        courses.add(TAF);
//        courses.add(OS_Lab);
//        courses.add(DBS_Lab);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {    }

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
}
