package Models;

public class Children {
    String id;
    String firstname;
    String lastname;
    String parentID;
    String parentname;
    String birthday;
    String conditions;
    String picture;

    public Children() {

    }

    public Children(String id, String firstname, String lastname, String parentID, String birthday, String conditions, String parentname, String picture) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.parentID = parentID;
        this.birthday = birthday;
        this.conditions = conditions;
        this.parentname = parentname;
        this.picture = picture;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
