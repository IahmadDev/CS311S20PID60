package sample;

import java.util.ArrayList;


public class CollisionLecture {

    private ClassRoom ofClassRoom;
    private ClassRoom withClassRoom;
    private Day day;
    public ArrayList<Lecture> lectures;

    public CollisionLecture(ClassRoom ofClassRoom, ClassRoom withClassRoom, Day day, ArrayList<Lecture> lectures) {
        this.ofClassRoom = ofClassRoom;
        this.withClassRoom = withClassRoom;
        this.day = day;
        this.lectures = lectures;
        if ( lectures != null) {
            this.lectures = new ArrayList<Lecture>(lectures);
        }else{
            this.lectures =new ArrayList<Lecture>();
        }
    }

    public ClassRoom getOfClassRoom() {
        return ofClassRoom;
    }

    public void setOfClassRoom(ClassRoom ofClassRoom) {
        this.ofClassRoom = ofClassRoom;
    }

    public ClassRoom getWithClassRoom() {
        return withClassRoom;
    }

    public void setWithClassRoom(ClassRoom withClassRoom) {
        this.withClassRoom = withClassRoom;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }

    @Override
    public String toString() {
        return "CollisionLecture{" +
                "ofClassRoom=" + ofClassRoom.getClassRoomId() +
                ", withClassRoom=" + withClassRoom.getClassRoomId() +
                ", day=" + day.getDayName() +
                ", lectures=" + lectures +
                '}';
    }
}
