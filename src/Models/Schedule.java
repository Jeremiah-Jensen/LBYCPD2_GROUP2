package Models;

import java.time.LocalDate;

public class Schedule {

    String name;
    String time;
    String link;
    String status;
    String day;
    String id;

    public Schedule() {

    }

    public Schedule(String id, String name, String day, String time, String link, String status) {
        this.name = name;
        this.day = day;
        this.time = time;
        this.link = link;
        this.status = status;
    }

    public  String getId() { return id; }

    public void setId(String id) { this.id = id; }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

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
