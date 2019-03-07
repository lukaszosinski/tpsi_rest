package models;

import Adapters.ObjectIdJaxbAdapter;
import DAO.SubjectDAO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.bson.types.ObjectId;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import resources.GradeResource;
import resources.StudentResource;
import resources.SubjectResource;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@Entity(value = "grades", noClassnameStored = true)
@XmlRootElement(name="student")
public class Grade {
    private static int ID = 0;
    public Grade() {
        //this.id = ++ID;
        this.date = new Date();
    }

    public Grade(
            float value,
            Subject subject,
            Student student)
    {
        this.value = value;
        this.subject = subject;
        this.student = student;
        //this.id = ++ID;
        this.date = new Date();
    }

    @Id
    @XmlJavaTypeAdapter(ObjectIdJaxbAdapter.class)
    ObjectId id;
    public ObjectId getId() { return id; }
    //public void setId(int id) { this.id = id; }


    private float value;
    public float getValue() { return value; }
    public void setValue(float value) { this.value = value; }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Reference
    public Subject subject;
    public Subject getSubject() {
        return subject;
    }
    public void setSubject(String id) {
        //int _id = Integer.parseInt(id);
        Subject subject = SubjectDAO.getInstance().getData(id);
        this.subject = subject;
    }

    @Reference
    private Student student;
    @XmlTransient
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    @XmlTransient
    public int getStudentId() {return student.getIndex();}
    public ObjectId getSubjectId() {return subject.getId();}

    @InjectLinks({
            @InjectLink(
                    rel = "self",
                    resource = GradeResource.class,
                    method = "getStudentGrade",
                    bindings={@Binding(name="StudentId", value="${instance.studentId}"), @Binding(name="id", value="${instance.id}")}
            ),
            @InjectLink(
                    rel = "parent",
                    resource = GradeResource.class,
                    method = "getGrade",
                    bindings={@Binding(name="StudentId", value="${instance.studentId}"), @Binding(name="id", value="")}
            ),
            @InjectLink(
                    rel = "subject",
                    resource = SubjectResource.class,
                    method = "getSubject",
                    bindings={@Binding(name="id", value="${instance.subjectId}")}
            ),
            @InjectLink(
                    rel = "student",
                    resource = StudentResource.class,
                    method = "getStudent",
                    bindings={@Binding(name="id", value="${instance.studentId}")}
            )
    })
    @XmlElementWrapper(name="links")
    @XmlElement(name="link")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    List<Link> links;
}
