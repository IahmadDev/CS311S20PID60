package sample;

public class Teacher {

    private String teacherId;
    private String teacherName;
    private String teacherTitle;

    public Teacher(String teacherId, String teacherName, String teacherTitle) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherTitle = teacherTitle;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherTitle() {
        return teacherTitle;
    }

    public void setTeacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherTitle='" + teacherTitle + '\'' +
                '}';
    }
}
