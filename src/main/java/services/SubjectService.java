package services;

import DAO.GradeDAO;
import DAO.StudentDAO;
import DAO.SubjectDAO;
import models.Grade;
import models.Student;
import models.Subject;

import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

public class SubjectService {
    private SubjectDAO subjectDAO = SubjectDAO.getInstance();
    private GradeDAO gradeDAO = GradeDAO.getInstance();

    public Response getSubject(String id) {
        //int ID = Integer.parseInt(id);
        Response.ResponseBuilder responseBuilder = Response.status(200);
        return responseBuilder.entity(subjectDAO.getData(id)).build();
    }

    public Response getSubjects(String lecturer) {
        Collection<Subject> subjects = subjectDAO.getCollection(lecturer);
        Response.ResponseBuilder responseBuilder = Response.status(200);
        return responseBuilder.entity(subjects).build();
    }

    public Response getSubjectGrades(String subjectId) {
        //int _studentId = Integer.parseInt(subjectId);
        Subject subject = subjectDAO.getData(subjectId);
        Collection<Grade> grades = gradeDAO.getCollection(subject);
        if(grades != null) {
            Response.ResponseBuilder responseBuilder = Response.status(200);
            return responseBuilder.entity(grades).build();
        } else {
            Response.ResponseBuilder responseBuilder = Response.status(404);
            return responseBuilder.build();
        }
    }

    public Response postSubject(Subject entity) {
        subjectDAO.addData(entity);
        return Response.status(201).build();
    }

    public Response putSubject(String id, Subject entity) {
        //int _id = Integer.parseInt(id);
        subjectDAO.updateData(id, entity);
        return Response.status(200).build();
    }

    public Response deleteSubject(String id) {
        //int _id = Integer.parseInt(id);
        gradeDAO.deleteData(subjectDAO.getData(id));
        subjectDAO.deleteData(id);
        return Response.status(200).build();
    }
}
