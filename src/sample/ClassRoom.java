package sample;

public class ClassRoom {

    private String classRoomId;
    private int ClassRoomNo;
    private boolean classRoomAvailable;
    private String classRoomType;

    public ClassRoom(String classRoomId, int classRoomNo, boolean classRoomAvailable, String classRoomType) {
        this.classRoomId = classRoomId;
        this.ClassRoomNo = classRoomNo;
        this.classRoomAvailable = classRoomAvailable;
        this.classRoomType = classRoomType;
    }

    public String getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(String classRoomId) {
        this.classRoomId = classRoomId;
    }

    public int getClassRoomNo() {
        return ClassRoomNo;
    }

    public void setClassRoomNo(int classRoomNo) {
        ClassRoomNo = classRoomNo;
    }

    public boolean isClassRoomAvailable() {
        return classRoomAvailable;
    }

    public void setClassRoomAvailable(boolean classRoomAvailable) {
        this.classRoomAvailable = classRoomAvailable;
    }

    public String getClassRoomType() {
        return classRoomType;
    }

    public void setClassRoomType(String classRoomType) {
        this.classRoomType = classRoomType;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "classRoomId='" + classRoomId + '\'' +
                ", ClassRoomNo=" + ClassRoomNo +
                ", classRoomAvailable=" + classRoomAvailable +
                ", classRoomType='" + classRoomType + '\'' +
                '}';
    }
}
