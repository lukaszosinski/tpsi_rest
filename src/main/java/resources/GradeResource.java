package resources;

import models.Grade;
import services.GradeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Path("students/{StudentId}/grades")
@XmlRootElement(name="grades")
public class GradeResource {
    @XmlElement(name="grade")

    private GradeService gradeService = new GradeService();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStudentGrades(
            @PathParam("StudentId") String studentId,
            @DefaultValue("") @QueryParam("subject") String subject,
            @DefaultValue("6") @QueryParam("lessThan") float maxValue,
            @DefaultValue("1") @QueryParam("greaterThan") float minValue
            ) {
        return gradeService.getStudentGrades(studentId, subject, maxValue, minValue);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getStudentGrade(@PathParam("StudentId") String studentId, @PathParam("id") String id) {
        return gradeService.getStudentGrade(studentId, id);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postGrade(@PathParam("StudentId") String studentId, Grade entity) {
        return gradeService.postGrade(studentId, entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response putGrade(@PathParam("id") String id, @PathParam("StudentId") String studentId, Grade entity) {
        return gradeService.putGrade(id, studentId, entity);
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postStudent(@PathParam("id") String id) {
        return gradeService.deleteGrade(id);
    }

}
