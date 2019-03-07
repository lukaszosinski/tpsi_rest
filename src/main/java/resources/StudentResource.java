package resources;

import models.Student;
import services.StudentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@Path("students")
@XmlRootElement(name="students")
public class StudentResource {

    private StudentService studentService = new StudentService();

    //@GET
    //@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    //public Response getStudents() {
    //    return studentService.getStudents();
    //}

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStudent(@PathParam("id") String id) {
        return studentService.getStudent(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,})
    public Response getStudents(
           @DefaultValue("") @QueryParam("name") String name,
           @DefaultValue("") @QueryParam("surname") String surname,
           @QueryParam("born") Date born,
           @QueryParam("after") Date minDate,
           @QueryParam("before") Date maxDate
    ) {
        return studentService.getStudents(name, surname, born, minDate, maxDate);
    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postStudent(Student entity) {
        return studentService.postStudent(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response putStudent(@PathParam("id") String id, Student entity) {
        return studentService.putStudent(id, entity);
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteStudent(@PathParam("id") String id) {
        return studentService.deleteStudent(id);
    }

}
