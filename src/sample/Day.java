package sample;

import java.util.ArrayList;

public class Day {

    private String dayName;
    private int startDay;
    private int endDay;
    private int breakStart;
    private int breakEnd;
    private int studiesHours;
    public ArrayList<Lecture> lectures;

    public Day(String dayName, int startDay, int endDay, int breakStart, int breakEnd, int studiesHours, ArrayList<Lecture> lectures) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.breakStart = breakStart;
        this.breakEnd = breakEnd;
        this.dayName = dayName;
        this.studiesHours = studiesHours;
        if ( lectures != null) {
            this.lectures = new ArrayList<Lecture>(lectures);
        }else{
            this.lectures =new ArrayList<Lecture>();
        }
    }

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }

    public int getStudiesHours() {
        return studiesHours;
    }

    public void setStudiesHours(int studiesHours) {
        this.studiesHours = studiesHours;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public int getBreakStart() {
        return breakStart;
    }

    public void setBreakStart(int breakStart) {
        this.breakStart = breakStart;
    }

    public int getBreakEnd() {
        return breakEnd;
    }

    public void setBreakEnd(int breakEnd) {
        this.breakEnd = breakEnd;
    }

    @Override
    public String toString() {
        return "Day{" +
                "dayName='" + dayName + '\'' +
                ", startDay=" + startDay +
                ", endDay=" + endDay +
                ", breakStart=" + breakStart +
                ", breakEnd=" + breakEnd +
                ", studiesHours=" + studiesHours +
                ", lectures=" + lectures +
                '}';
    }
}
