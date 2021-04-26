package Models;

public class Appointments {
    String id;
    String User;
    String Doctor;
    String Time;
    String PreQuest;
    String FollowUp;
    String Appointment;
    String Prescription;
    String Link;
    String Payment;
    String painQ1;

    public Appointments(){

    }

    public Appointments(String id, String user, String doctor, String time, String preQuest, String followUp, String appointment, String prescription, String link, String payment, String painQ1) {
        this.id = id;
        this.User = user;
        this.Doctor = doctor;
        this.Time = time;
        this.PreQuest = preQuest;
        this.FollowUp = followUp;
        this.Appointment = appointment;
        this.Prescription = prescription;
        this. Link = link;
        this.Payment = payment;
        this.painQ1 = painQ1;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getDoctor() {
        return Doctor;
    }

    public void setDoctor(String doctor) {
        Doctor = doctor;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getPreQuest() {
        return PreQuest;
    }

    public void setPreQuest(String preQuest) {
        PreQuest = preQuest;
    }

    public String getFollowUp() {
        return FollowUp;
    }

    public void setFollowUp(String followUp) {
        FollowUp = followUp;
    }

    public String getAppointment() {
        return Appointment;
    }

    public void setAppointment(String appointment) {
        Appointment = appointment;
    }

    public String getPrescription() {
        return Prescription;
    }

    public void setPrescription(String prescription) {
        Prescription = prescription;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getPayment() {
        return Payment;
    }

    public void setPayment(String payment) {
        Payment = payment;
    }

    public String getPainQ1() {
        return painQ1;
    }

    public void setPainQ1(String painQ1) {
        this.painQ1 = painQ1;
    }


}
