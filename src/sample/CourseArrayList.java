package sample;

import java.util.ArrayList;

public class CourseArrayList {
    private ArrayList<Course> courses;

    public CourseArrayList(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
