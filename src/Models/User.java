package Models;

public class User {

    String id, firstName, lastName, username, password, reenterPass, birthday, email, contactNumber, address, gender, condition, credit, name, cardnumber, expirydate, bank, network, cvv;

    public User() {

    }

    public User(String id, String firstName, String lastName, String username, String password, String reenterPass, String birthday, String email, String contactNumber, String address, String gender, String condition, String credit, String name, String cardnumber, String expirydate, String bank, String network, String cvv) {
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
        this.condition = condition;
        this.credit = credit;
        this.name = name;
        this.cardnumber = cardnumber;
        this.expirydate = expirydate;
        this.bank = bank;
        this.network = network;
        this.cvv = cvv;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) { this.cvv = cvv; }

    public void setName(String name){ this.name = name; }

    public String getName(){ return name; }
}