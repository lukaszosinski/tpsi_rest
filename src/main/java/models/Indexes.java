package models;
import org.mongodb.morphia.annotations.Entity;

@Entity

public class Indexes {

    public Indexes() {

    }

    private int studentIndex;

    private int markId;

    public int getStudentIndex() {
        return studentIndex;
    }

    public void setStudentIndex(int studentIndex) {
        this.studentIndex = studentIndex;
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }
}