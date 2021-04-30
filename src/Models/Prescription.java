package Models;

public class Prescription {

    String prescriptionDate, medicine, dailyDosage, duration, specialInstructions, name, appointmentId;

    public Prescription() {

    }

    public Prescription(String name, String prescriptionDate, String medicine, String dailyDosage, String duration, String specialInstructions, String appointmentId) {
        this.prescriptionDate = prescriptionDate;
        this.medicine = medicine;
        this.dailyDosage = dailyDosage;
        this.duration = duration;
        this.specialInstructions = specialInstructions;
        this.name = name;
        this.appointmentId = appointmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String prescriptionDate) {
        this.appointmentId = appointmentId;
    }

    public String getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(String prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDailyDosage() {
        return dailyDosage;
    }

    public void setDailyDosage(String dailyDosage) {
        this.dailyDosage = dailyDosage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }
}