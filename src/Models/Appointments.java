package Models;

public class Appointments {
    String Sched;
    String Child;
    String id;
    String User;
    String Doctor;
    String Time;
    String Date;
    String PreQuest;
    String FollowUp;
    String Appointment;
    String Prescription;
    String Link;
    String Payment;
    String PainQ1;
    String Status;
    String FeelingQ;
    String ReasonQ;
    String PainScale;
    String Q1;
    String Q2;
    String Q3;
    String Q4;
    String Q5;
    String Q6;
    String Q7;
    String Q8;
    String Q9;
    String Q10;
    String FeelingQ2;
    String SideEffectsQ;
    String PainScaleQ2;
    String PainQ2;

    public Appointments(){

    }

    public Appointments(String child, String user, String doctor, String sched, String preQuest, String followUp, String appointment, String prescription, String link, String payment, String painQ1, String status, String feelingQ, String reasonQ, String painScale, String q1, String q2, String q3, String q4, String q5, String q6, String q7, String q8, String q9, String q10, String feelingQ2, String sideEffectsQ, String painScaleQ2, String painQ2) {
        this.User = user;
        this.Child = child;
        this.Sched = sched;
        this.Doctor = doctor;
        this.PreQuest = preQuest;
        this.FollowUp = followUp;
        this.Appointment = appointment;
        this.Prescription = prescription;
        this.Link = link;
        this.Payment = payment;
        this.PainQ1 = painQ1;
        this.Status = status;
        this.FeelingQ = feelingQ;
        this.ReasonQ = reasonQ;
        this.PainScale = painScale;
        this.Q1 = q1;
        this.Q2 = q2;
        this.Q3 = q3;
        this.Q4 = q4;
        this.Q5 = q5;
        this.Q6 = q6;
        this.Q7 = q7;
        this.Q8 = q8;
        this.Q9 = q9;
        this.Q10 = q10;
        this.FeelingQ2 = feelingQ2;
        this.SideEffectsQ = sideEffectsQ;
        this.PainScaleQ2 = painScaleQ2;
        this.PainQ2 = painQ2;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

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

    public void setDate(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setTime(String date) { Date = date;
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

    public String getStatus(){ return Status; }

    public void setStatus(String status){ Status = status; }

    public String getPainQ1() {
        return PainQ1;
    }

    public void setPainQ1(String painQ1) {
        this.PainQ1 = painQ1;
    }

    public String getFeelingQ(){return FeelingQ;}

    public void setFeelingQ(String feelingQ){this.FeelingQ = feelingQ;}

    public String getReasonQ(){return ReasonQ;}

    public void setReasonQ(String reasonQ){this.ReasonQ = reasonQ;}

    public String getPainScale() { return PainScale;}

    public void setPainScale(String painScale) { PainScale = painScale;}

    public String getQ1() { return Q1;}

    public void setQ1(String q1) { Q1 = q1;}

    public String getQ2() {
        return Q2;
    }

    public void setQ2(String q2) {
        Q2 = q2;
    }

    public String getQ3() {
        return Q3;
    }

    public void setQ3(String q3) {
        Q3 = q3;
    }

    public String getQ4() {
        return Q4;
    }

    public void setQ4(String q4) {
        Q4 = q4;
    }


    public String getQ5() {
        return Q5;
    }

    public void setQ5(String q5) {
        Q5 = q5;
    }
    public String getQ6() {
        return Q6;
    }

    public void setQ6(String q6) {
        Q6 = q6;
    }

    public String getQ7() {
        return Q7;
    }

    public void setQ7(String q7) {
        Q7 = q7;
    }

    public String getQ8() {
        return Q8;
    }

    public void setQ8(String q8) {
        Q8 = q8;
    }

    public String getQ9() {
        return Q9;
    }

    public void setQ9(String q9) {
        Q9 = q9;
    }

    public String getQ10() {
        return Q10;
    }

    public void setQ10(String q10) {
        Q10 = q10;
    }

    public String getFeelingQ2() {
        return FeelingQ2;
    }

    public void setFeelingQ2(String feelingQ2) {
        FeelingQ2 = feelingQ2;
    }

    public String getSideEffectsQ() {
        return SideEffectsQ;
    }

    public void setSideEffectsQ(String sideEffectsQ) {
        SideEffectsQ = sideEffectsQ;
    }

    public String getPainScaleQ2() {
        return PainScaleQ2;
    }

    public void setPainScaleQ2(String painScaleQ2) {
        PainScaleQ2 = painScaleQ2;
    }

    public String getPainQ2() {
        return PainQ2;
    }

    public void setPainQ2(String painQ2) {
        PainQ2 = painQ2;
    }

    public String getSched() {
        return Sched;
    }

    public void setChild(String child) {
        Child = child;
    }

    public String getChild() {
        return Child;
    }

    public void setSched(String child) { Sched = child;
    }


}