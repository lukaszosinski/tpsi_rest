package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.bson.types.ObjectId;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.mongodb.morphia.annotations.*;
import resources.GradeResource;
import resources.HttpException;
import resources.StudentResource;
import sequence.Sequence;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Entity(value = "students", noClassnameStored = true)
@Indexes(
        @Index(
                fields = @Field("index"),
                options = @IndexOptions(unique = true)
        )
)
@XmlRootElement(name="student")
public class Student {
    private static int ID = 0;

    public Student() {

        //this.index = Sequence.generateStudentId();
    }

    public Student(
            String name,
            String surname,
            Date birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        //this.index = ++ID;
    }

    @Id
    @XmlTransient
    private ObjectId id;
    @XmlTransient
    public ObjectId getId() { return this.id; }


    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String surname;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;
    public String getBirthDate() {
        return sdf.format(birthDate);
    }
    public void setBirthDate(String birthDate) throws HttpException {
        try {
            this.birthDate = sdf.parse(birthDate);
        } catch (ParseException e) {
            this.birthDate = null;
        }
    }

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @InjectLinks({
            @InjectLink(rel = "self",
                    resource = StudentResource.class,
                    method = "getStudent",
                    bindings={@Binding(name="id", value="${instance.index}")}),
            @InjectLink(rel = "parent",
                    resource = StudentResource.class),
            @InjectLink(rel = "grades",
                    resource = GradeResource.class,
                    bindings={@Binding(name="StudentId", value="${instance.index}")})
    })
    @XmlElement(name="link")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    List<Link> links;
}