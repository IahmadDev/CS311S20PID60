package sample;

public class Data {
    private String dayName;
    private String lecture1;
    private String lecture2;
    private String lecture3;
    private String lecture4;
    private String dayBreak;
    private String lecture5;
    private String lecture6;
    private String lecture7;

    public Data(String dayName, String lecture1, String lecture2, String lecture3, String lecture4, String dayBreak, String lecture5, String lecture6, String lecture7) {
        this.dayName = dayName;
        this.lecture1 = lecture1;
        this.lecture2 = lecture2;
        this.lecture3 = lecture3;
        this.lecture4 = lecture4;
        this.dayBreak = dayBreak;
        this.lecture5 = lecture5;
        this.lecture6 = lecture6;
        this.lecture7 = lecture7;
    }

    public Data() {
    }

    @Override
    public String toString() {
        return "Data{" +
                "dayName='" + dayName + '\'' +
                ", lecture1='" + lecture1 + '\'' +
                ", lecture2='" + lecture2 + '\'' +
                ", lecture3='" + lecture3 + '\'' +
                ", lecture4='" + lecture4 + '\'' +
                ", dayBreak='" + dayBreak + '\'' +
                ", lecture5='" + lecture5 + '\'' +
                ", lecture6='" + lecture6 + '\'' +
                ", lecture7='" + lecture7 + '\'' +
                '}';
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getLecture1() {
        return lecture1;
    }

    public void setLecture1(String lecture1) {
        this.lecture1 = lecture1;
    }

    public String getLecture2() {
        return lecture2;
    }

    public void setLecture2(String lecture2) {
        this.lecture2 = lecture2;
    }

    public String getLecture3() {
        return lecture3;
    }

    public void setLecture3(String lecture3) {
        this.lecture3 = lecture3;
    }

    public String getLecture4() {
        return lecture4;
    }

    public void setLecture4(String lecture4) {
        this.lecture4 = lecture4;
    }

    public String getDayBreak() {
        return dayBreak;
    }

    public void setDayBreak(String dayBreak) {
        this.dayBreak = dayBreak;
    }

    public String getLecture5() {
        return lecture5;
    }

    public void setLecture5(String lecture5) {
        this.lecture5 = lecture5;
    }

    public String getLecture6() {
        return lecture6;
    }

    public void setLecture6(String lecture6) {
        this.lecture6 = lecture6;
    }

    public String getLecture7() {
        return lecture7;
    }

    public void setLecture7(String lecture7) {
        this.lecture7 = lecture7;
    }
}
