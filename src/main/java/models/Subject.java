package models;



import Adapters.ObjectIdJaxbAdapter;
import org.bson.types.ObjectId;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import resources.GradeResource;
import resources.SubjectResource;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

@Entity(value = "courses", noClassnameStored = true)
@XmlRootElement(name="subject")
public class Subject {
    static int ID = 0;
    public Subject() {
        //this.id = ++ID;
    }

    public Subject(String name, String lecturer) {
        this.name = name;
        this.lecturer = lecturer;
        //this.id = ++ID;
    }

    @XmlElement
    @Id
    @XmlJavaTypeAdapter(ObjectIdJaxbAdapter.class)
    ObjectId id;
    public ObjectId getId() {
        return id;
    }
    //public void setID(int id) {this.id = id;}


    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    private String lecturer;
    public String getLecturer() {
        return lecturer;
    }
    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    @InjectLinks({
            @InjectLink(rel = "self",
                    resource = SubjectResource.class,
                    method = "getSubject",
                    bindings={@Binding(name="id", value="${instance.id}")}),
            @InjectLink(rel = "parent",
                    resource = SubjectResource.class)

    })
    @XmlElement(name="link")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    List<Link> links;

}

