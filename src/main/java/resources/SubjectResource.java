package resources;

import models.Student;
import models.Subject;
import services.StudentService;
import services.SubjectService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

@Path("subjects")
@XmlRootElement(name="subjects")
public class SubjectResource {

    private SubjectService subjectService = new SubjectService();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSubjects(@DefaultValue("") @QueryParam("teacher") String lecturer) {
        return subjectService.getSubjects(lecturer);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSubject(@PathParam("id") String id) {
        return subjectService.getSubject(id);
    }

    @GET
    @Path("/{id}/grades")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSubjectGrades(@PathParam("id") String id) {
        return subjectService.getSubjectGrades(id);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postSubject(Subject entity) {
        return subjectService.postSubject(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response putSubject(@PathParam("id") String id, Subject entity) {
        return subjectService.putSubject(id, entity);
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postSubject(@PathParam("id") String id) {
        return subjectService.deleteSubject(id);
    }

}
