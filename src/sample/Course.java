package sample;

public class Course {

    private String courseName;
    private String courseId;
    private String courseCode;
    private int courseCreditHours;
    private String courseInstructorId;
    private int studied;
    private int weeklyLectures;
    private Boolean labStatus;

    public Course(String courseName, String courseId, String courseCode, int courseCreditHours, String courseInstructorId, int studied, int weeklyLectures, boolean labStatus) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseCreditHours = courseCreditHours;
        this.courseInstructorId = courseInstructorId;
        this.studied = studied;
        this.weeklyLectures = weeklyLectures;
        this.labStatus = labStatus;
    }

    public Boolean getLabStatus() {
        return labStatus;
    }

    public void setLabStatus(Boolean labStatus) {
        this.labStatus = labStatus;
    }

    public int getWeeklyLectures() {
        return weeklyLectures;
    }

    public void setWeeklyLectures(int weeklyLectures) {
        this.weeklyLectures = weeklyLectures;
    }

    public int getStudied() {
        return studied;
    }

    public void setStudied(int studied) {
        this.studied = studied;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCourseCreditHours() {
        return courseCreditHours;
    }

    public void setCourseCreditHours(int courseCreditHours) {
        this.courseCreditHours = courseCreditHours;
    }

    public String getCourseInstructorId() {
        return courseInstructorId;
    }

    public void setCourseInstructorId(String courseInstructorId) {
        this.courseInstructorId = courseInstructorId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", courseCreditHours=" + courseCreditHours +
                ", courseInstructorId='" + courseInstructorId + '\'' +
                ", studied=" + studied +
                ", weeklyLectures=" + weeklyLectures +
                ", labStatus=" + labStatus +
                '}';
    }
}
