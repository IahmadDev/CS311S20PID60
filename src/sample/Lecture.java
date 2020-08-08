package sample;

public class Lecture {
    private String subjectName;
    private int start;
    private int end;

    public Lecture(String subjectName, int start, int end) {
        this.subjectName = subjectName;
        this.start = start;
        this.end = end;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "subjectName='" + subjectName + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }



}
