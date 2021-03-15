import java.util.Objects;

public class Student {
    ///////////////////////////////////////////////
    // properties
    //////////////////////////////////////////////
    int ID;
    String name;
    String major;

    ///////////////////////////////////////////////
    // constructor
    //////////////////////////////////////////////
    Student(int id, String name, String major) {
        this.ID = id;
        this.name = name;
        this.major = major;
    }

    ///////////////////////////////////////////////
    // object oriented paradigm
    //////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return ID == student.ID && name.equals(student.name) && major.equals(student.major);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, major);
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                '}';
    }

    ///////////////////////////////////////////////
    // public getters and setters
    //////////////////////////////////////////////
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
