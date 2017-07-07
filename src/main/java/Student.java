import java.util.ArrayList;
import java.util.List;

public class Student {

    private int rollNo;
    private String firstName;
    private String lastName;
    private List<String> subjects = new ArrayList<>();

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

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

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "RollNo: "+rollNo+"\nName: "+firstName+" "+lastName+"\nSubjects: "+subjects+"\n\n";
    }
}
