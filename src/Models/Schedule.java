package Models;

public class Schedule {

    String name, day, time, link;

    public Schedule() {

    }
    public Schedule(String name, String day, String time, String link) {
        this.name = name;
        this.day = day;
        this.time = time;
        this.link = link;
    }

    public String getLink() { return link; }

    public void setLink(String link) { this.link = link; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}