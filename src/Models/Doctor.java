package Models;

public class Doctor {

    String day, path, time, id, firstName, lastName, username, password, reenterPass, birthday, email, contactNumber, address, gender, subspecialty;

    public Doctor() {

    }

    public Doctor(String day, String time) {
        this.day = day;
        this.time = time;
    }

    public Doctor(String path, String firstName, String lastName, String username, String password, String reenterPass, String birthday, String email, String contactNumber, String address, String gender, String subspecialty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.reenterPass = reenterPass;
        this.birthday = birthday;
        this.email = email;
        this.contactNumber = contactNumber;
        this.address = address;
        this.gender = gender;
        this.subspecialty = subspecialty;
        this.path = path;
    }

    public String getDay() { return day; }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() { return time; }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReenterPass() {
        return reenterPass;
    }

    public void setReenterPass(String reenterPass) {
        this.reenterPass = reenterPass;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSubspecialty() {
        return subspecialty;
    }

    public void setSubspecialty(String consultation) {
        this.subspecialty = consultation;
    }

    public String getPicture() {
        return path;
    }

    public void setPicture(String path) {
        this.path = path;
    }
}