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

public class DayController implements Initializable {

    @FXML
    private TextField dayName;

    @FXML
    private TextField dayEndAt;

    @FXML
    private TextField dayBreakStartAt;

    @FXML
    private TextField dayBreakEndAt;

    @FXML
    private TextField dayStartAt;

    @FXML
    private ListView<Day> daysListView;

    public ArrayList<Day> Days =  new ArrayList<>();

    public static ArrayList<Day> officialDays =  new ArrayList<>();

    @FXML
    void onGenerateButtonPressed(ActionEvent event) throws IOException {
        for(Day day : Days){
            System.out.println(day);
        }

        writeToFile(Days);

        Parent classRoom = FXMLLoader.load(getClass().getResource("scheduleControllerFxml.fxml"));
        Scene classRoomScene = new Scene(classRoom);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(classRoomScene);
        window.show();
    }

    @FXML
    void onSaveButtonPressed(ActionEvent event) {
         String name = dayName.getText().toString();
         int startDay = Integer.parseInt(dayStartAt.getText().toString());
         int endDay = Integer.parseInt(dayEndAt.getText().toString());
         int breakStart = Integer.parseInt(dayBreakStartAt.getText().toString());
         int breakEnd = Integer.parseInt(dayBreakEndAt.getText().toString());

         Day day = new Day(name,startDay,endDay,breakStart,breakEnd,endDay-startDay,null);
         Days.add(day);

////         Days
//        Day Monday = new Day("Monday", 8, 16, 12, 13, 8, null);
//        Day Tuesday = new Day("Tuesday", 8, 16, 12, 13, 8, null);
//        Day Wednesday = new Day("Wednesday", 8, 16, 12, 13, 8, null);
//        Day Thursday = new Day("Thursday", 8, 16, 12, 13, 8, null);
//        Day Friday = new Day("Friday", 8, 16, 12, 13, 8, null);
//
//        Days.add(Monday);
//        Days.add(Tuesday);
//        Days.add(Wednesday);
//        Days.add(Thursday);
//        Days.add(Friday);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public static ArrayList<Day> getOfficialDays() {
        officialDays = new ArrayList<>();

        Scanner scanner = null;

        try {
            scanner = new Scanner(new FileReader("days.txt"));
            scanner.useDelimiter(",");

            while (scanner.hasNextLine()){
                String dayName = scanner.next();
                scanner.skip(scanner.delimiter());

                int startDay = scanner.nextInt();
                scanner.skip(scanner.delimiter());

                int endDay = scanner.nextInt();
                scanner.skip(scanner.delimiter());

                int breakStart = scanner.nextInt();
                scanner.skip(scanner.delimiter());

                int breakEnd = scanner.nextInt();
                scanner.skip(scanner.delimiter());

                int studiesHours = Integer.parseInt(scanner.nextLine());

                Day day = new Day(dayName,startDay,endDay,breakStart,breakEnd,studiesHours,null);
                officialDays.add(day);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return officialDays;
    }

    public void writeToFile(ArrayList<Day> days){
        FileWriter locFile = null;
        try {
            locFile = new FileWriter("days.txt");
            for(Day day : days){
                locFile.write(day.getDayName() + "," + day.getStartDay() + "," + day.getEndDay() + "," + day.getBreakStart()
                        + "," + day.getBreakEnd() + "," + day.getStudiesHours() + "\n");
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
