package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

public class ScheduleController implements Initializable {

    @FXML
    private TableView<Data> tableView;

    @FXML
    private TableColumn<Data, String> daysColumn;

    @FXML
    private TableColumn<Data, String> lecture1Col;

    @FXML
    private TableColumn<Data, String> lecture2Col;

    @FXML
    private TableColumn<Data, String> lecture3Col;

    @FXML
    private TableColumn<Data, String> lecture4Col;

    @FXML
    private TableColumn<Data, String> breakCol;

    @FXML
    private TableColumn<Data, String> lecture5Col;

    @FXML
    private TableColumn<Data, String> lecture6Col;

    @FXML
    private TableColumn<Data, String> lecture7Col;

    @FXML
    private ComboBox<String> comboBoxForClassrooms;

    @FXML
    private Tooltip scComboBoxTooltip;

    private ArrayList<Course> dailyCourses;

    public ArrayList<Course> courses = new ArrayList<>();

    private ArrayList<Day> days = new ArrayList<>();

    private ArrayList<Lecture> lectures;

    private ArrayList<ClassRoom> classRooms = new ArrayList<>();

    private Map<Day, ArrayList<Course>> routine;

    public Map<ClassRoom, Map<Day, ArrayList<Course>>> schedule = new HashMap<>();

    private Map<String, ArrayList<Lecture>> settingLectures = new HashMap<>();

    private ObservableList<Data> datas = FXCollections.observableArrayList();

    @FXML
    void onButton(ActionEvent event) {
        tableView.getItems().clear();

        printDataForClassRoom(getClassRoom(comboBoxForClassrooms.getValue().toString()));
        settingOfData();
        displayData();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initial();
        setComboBox();

        // scheduling
        makeSchedule();
        System.out.println("\n\n");
        removeCollision();

        int k = 0;
        while (collisionLectures().size() != 0) {
            System.out.println("\n\n\n" + " Please wait, we're making a new schedule for the " + k++ + "th time\n\n\n");
            makeSchedule();
            removeCollision();
        }

        if (collisionLectures().size() != 0) {
            for (CollisionLecture l : collisionLectures()) {
                System.out.println(l);
            }
        } else {
            System.out.println("Collisions are removed\n");
        }

        printData();
    }

    public void displayData() {
        daysColumn.setCellValueFactory(new PropertyValueFactory<Data, String>("dayName"));
        lecture1Col.setCellValueFactory(new PropertyValueFactory<Data, String>("lecture1"));
        lecture2Col.setCellValueFactory(new PropertyValueFactory<Data, String>("lecture2"));
        lecture3Col.setCellValueFactory(new PropertyValueFactory<Data, String>("lecture3"));
        lecture4Col.setCellValueFactory(new PropertyValueFactory<Data, String>("lecture4"));
        lecture5Col.setCellValueFactory(new PropertyValueFactory<Data, String>("lecture5"));
        lecture6Col.setCellValueFactory(new PropertyValueFactory<Data, String>("lecture6"));
        lecture7Col.setCellValueFactory(new PropertyValueFactory<Data, String>("lecture7"));
        breakCol.setCellValueFactory(new PropertyValueFactory<Data, String>("dayBreak"));

        tableView.setItems(datas);
    }

    public void makeSchedule() {

        for (ClassRoom currentClassRoom : classRooms) {
            routine = new HashMap<>();
            for (Day currentDay : days) {
                dailyCourses = new ArrayList<>();
                lectures = new ArrayList<>();
                Collections.shuffle(courses);
                for (Course currentCourse : courses) {
                    if ((currentCourse.getCourseCreditHours() >= 1)
                            && currentCourse.getStudied() < currentCourse.getWeeklyLectures() && !currentCourse.getLabStatus()) {
                        // for subjects having greater than 2 credit hours
                        if (currentDay.getStudiesHours() != 12) {
                            int startLectureAt = currentDay.getStudiesHours();
                            currentDay.setStudiesHours(startLectureAt + 1);
                            routine.put(currentDay, dailyCourses);
                            schedule.put(currentClassRoom, routine);
                            currentCourse.setStudied(currentCourse.getStudied() + 1);
                            Lecture lecture = new Lecture(currentCourse.getCourseName(), startLectureAt, startLectureAt + 1);
                            lectures.add(lecture);
                            dailyCourses.add(currentCourse);
                        } else {
                            // for break hour
                            int breakstart = currentDay.getStudiesHours();
                            currentDay.setStudiesHours(breakstart + 1);
                            Lecture lecture = new Lecture("Break", breakstart, breakstart + 1);
                            lectures.add(lecture);
                        }
                    } else if ((currentCourse.getCourseCreditHours() == 1 && currentCourse.getWeeklyLectures() == 3) && (
                            (currentDay.getBreakStart() - currentDay.getStudiesHours() >= 3) ||
                                    (currentDay.getStudiesHours() > currentDay.getBreakEnd() && currentDay.getEndDay() - currentDay.getStudiesHours() >= 3))
                            && (currentCourse.getLabStatus())
                    ) {
                        // for labs having 3 consecutive lectures

                        currentCourse.setLabStatus(true);
                        int startLabAt = currentDay.getStudiesHours();
                        currentDay.setStudiesHours(startLabAt + 3);
                        dailyCourses.add(currentCourse);

                        Lecture lecture = new Lecture(currentCourse.getCourseName(), startLabAt, startLabAt + 3);
                        lectures.add(lecture);

                        routine.put(currentDay, dailyCourses);
                        schedule.put(currentClassRoom, routine);
                    }
                    currentDay.setLectures(lectures);
                }
            }
            days.clear();
            courses.clear();
            initial();
        }
    }

    public void printData() {
        for (ClassRoom classRoom : schedule.keySet()) {
            System.out.print(classRoom.getClassRoomId());
            Map<Day, ArrayList<Course>> map = schedule.get(classRoom);
            for (Day day : map.keySet()) {
                System.out.print("\n" + day.getDayName() + " ->>>>> ");
                ArrayList<Lecture> lectures = day.getLectures();
                for (Lecture lecture : lectures) {
                    System.out.print(lecture.getSubjectName() + "\t");
                }
            }
            System.out.println("\n\n");
        }
    }

    public void printDataForClassRoom(ClassRoom classRoom) {
        Map<Day, ArrayList<Course>> map = schedule.get(classRoom);
        for (Day day : map.keySet()) {
            System.out.println("\n" + day.getDayName() + " ->>>>> ");
            ArrayList<Lecture> lectures = day.getLectures();
            for (Lecture lecture : lectures) {
                System.out.println(lecture.getSubjectName() + "\t");
            }

            ArrayList<Lecture> ls = new ArrayList<>();
            for (int i = 0; i < lectures.size(); i++) {
                Lecture lecture = lectures.get(i);
                int s = lecture.getEnd() - lecture.getStart();
                while (s != 0) {
                    ls.add(lecture);
                    s--;
                }
            }
            settingLectures.put(day.getDayName(), ls);
        }
        System.out.println("\n\n");
    }

    public void settingOfData() {

        for (String dayName : settingLectures.keySet()) {
            System.out.print("\n" + dayName + "-----");
            for (Lecture lecture : settingLectures.get(dayName)) {
                System.out.print(lecture.getSubjectName() + "\t");
            }
            ArrayList<Lecture> ls = settingLectures.get(dayName);
            Data data = new Data();
            for (int i = 0; i < ls.size(); i++) {
                data.setDayName(dayName);
                if (i == 0) {
                    data.setLecture1(ls.get(i).getSubjectName());
                } else if (i == 1) {
                    data.setLecture2(ls.get(i).getSubjectName());
                } else if (i == 2) {
                    data.setLecture3(ls.get(i).getSubjectName());
                } else if (i == 3) {
                    data.setLecture4(ls.get(i).getSubjectName());
                } else if (i == 4) {
                    data.setDayBreak(ls.get(i).getSubjectName());
                } else if (i == 5) {
                    data.setLecture5(ls.get(i).getSubjectName());
                } else if (i == 6) {
                    data.setLecture6(ls.get(i).getSubjectName());
                } else if (i == 7) {
                    data.setLecture7(ls.get(i).getSubjectName());
                } else if (i == 8) {
                    data.setLecture2(ls.get(i).getSubjectName());
                }
            }
            datas.add(data);
        }
        for (Data d : datas) {
            System.out.println(d);
        }
    }

    public ArrayList<CollisionLecture> collisionLectures() {
        ArrayList<CollisionLecture> collisionLectures = new ArrayList<>();
        for (int i = 0; i < classRooms.size(); i++) {
            ClassRoom prevClassRoom = classRooms.get(i);
            Map<Day, ArrayList<Course>> prevMap = schedule.get(prevClassRoom);
            for (int j = i; j < classRooms.size(); j++) {
                ClassRoom nextClassRoom = classRooms.get(j);
                Map<Day, ArrayList<Course>> nextMap = schedule.get(nextClassRoom);
                if (i != j) {
                    for (Day prevDay : prevMap.keySet()) {
                        for (Lecture prevLecture : prevDay.getLectures()) {
                            for (Day nextDay : nextMap.keySet()) {
                                for (Lecture nextLecture : nextDay.getLectures()) {
                                    ArrayList<Lecture> lectures = new ArrayList<>();
                                    if (prevLecture.getSubjectName().equals(nextLecture.getSubjectName()) && prevLecture.getStart() == nextLecture.getStart() && prevLecture.getEnd() == nextLecture.getEnd() && !prevLecture.getSubjectName().equals("Break") && !nextLecture.getSubjectName().equals("Break") && prevDay.getDayName().equals(nextDay.getDayName())) {
                                        lectures.add(prevLecture);
                                        CollisionLecture collisionLecture = new CollisionLecture(prevClassRoom, nextClassRoom, prevDay, lectures);
                                        collisionLectures.add(collisionLecture);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return collisionLectures;
    }

    public void removeCollision() {

        int j = 0;
        boolean done = false;

        ArrayList<CollisionLecture> collisions = collisionLectures();
        ClassRoom classRoom;

        for (CollisionLecture lecture : collisions) {
            System.out.println("Returning back " + ++j);

            if (lecture.getDay().getStudiesHours() == lecture.getDay().getEndDay()) {
                classRoom = lecture.getOfClassRoom();
                System.out.println("of classRoom");
            } else {
                classRoom = lecture.getWithClassRoom();
                System.out.println("with classRoom");
            }

            Map<Day, ArrayList<Course>> tempMap = schedule.get(classRoom);
            for (Day dayy : tempMap.keySet()) {
                Day day = lecture.getDay();
                if (dayy.getDayName().equals(day.getDayName())) {
                    done = false;
                    Lecture lecture1 = null;
                    ArrayList<Lecture> collisionLectures = lecture.getLectures();
                    ArrayList<Lecture> dayLectures = day.getLectures();

                    for (Lecture dLec : dayLectures) {
                        for (Lecture cLec : collisionLectures) {
                            if (dLec.getSubjectName().equals(cLec.getSubjectName()) && dLec.getStart() == cLec.getStart()) {
                                if (day.getStudiesHours() < day.getBreakStart() && day.getBreakStart() - day.getStudiesHours() >= 1 && dLec.getEnd() - dLec.getStart() == 1) {
                                    day.setStudiesHours(day.getStudiesHours() + 1);
                                    lecture1 = new Lecture(dLec.getSubjectName(), day.getStudiesHours(), day.getStudiesHours() + 1);
                                    dLec.setSubjectName("Free");
                                    System.out.println("Done 1.1 ");
                                    done = true;
                                    break;
                                } else if (day.getStudiesHours() >= day.getBreakEnd() && day.getEndDay() - day.getStudiesHours() >= 1 && dLec.getEnd() - dLec.getStart() == 1) {
                                    day.setStudiesHours(day.getStudiesHours() + 1);
                                    lecture1 = new Lecture(dLec.getSubjectName(), day.getStudiesHours(), day.getStudiesHours() + 1);
                                    dLec.setSubjectName("Free");
                                    System.out.println("Done 1.2 ");
                                    done = true;
                                    break;
                                } else if (dLec.getEnd() - dLec.getStart() > 1) {
                                    System.out.println("Entering for lab" + day.getStudiesHours() + " , " + day.getBreakStart() + " , " + day.getBreakEnd());
                                    if (day.getStudiesHours() >= day.getBreakEnd() && day.getEndDay() - day.getStudiesHours() >= 3) {
                                        lecture1 = new Lecture(dLec.getSubjectName(), day.getStudiesHours(), day.getStudiesHours() + 3);
                                        dLec.setSubjectName("Free");
                                        System.out.println("Done 1.3.1 " + day.getStudiesHours() + " , " + day.getBreakEnd());
                                        done = true;
                                        break;
                                    } else if (day.getBreakStart() >= day.getStudiesHours() && day.getBreakStart() - day.getStudiesHours() >= 3) {
                                        lecture1 = new Lecture(dLec.getSubjectName(), day.getStudiesHours(), day.getStudiesHours() + 3);
                                        dLec.setSubjectName("Free");
                                        System.out.println("Done 1.3.2 " + day.getStudiesHours() + " , " + day.getBreakEnd());
                                        done = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (done) {
                        dayLectures.add(lecture1);
                        System.out.println("Done 2");
                    }
                }
            }
        }

        System.out.println(done);

    }

    public void initial() {
        courses = new ArrayList<>();
        days = new ArrayList<>();

        courses = CourseController.getOfficialCourses();
        days = DayController.getOfficialDays();
        classRooms = ClassroomController.getOfficialClassRooms();
    }

    public void setComboBox() {
        comboBoxForClassrooms.setTooltip(scComboBoxTooltip);
        for (ClassRoom classRoom : ClassroomController.officalClassRooms) {
            comboBoxForClassrooms.getItems().add(classRoom.getClassRoomId());
        }
    }

    public ClassRoom getClassRoom(String id) {
        for (ClassRoom classRoom : ClassroomController.officalClassRooms) {
            if (classRoom.getClassRoomId().equals(id)) {
                return classRoom;
            }
        }
        return null;
    }

}
