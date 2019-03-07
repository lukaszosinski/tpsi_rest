package services;

import DAO.GradeDAO;
import DAO.StudentDAO;
import DAO.SubjectDAO;
import models.Grade;
import models.Student;
import models.Subject;
import sequence.Sequence;

import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class StudentService {
    private StudentDAO studentDAO = StudentDAO.getInstance();
    private GradeDAO gradeDAO = GradeDAO.getInstance();
    private SubjectDAO subjectDAO = SubjectDAO.getInstance();

    public Response getStudent(String id) {
        int ID = Integer.parseInt(id);
        Response.ResponseBuilder responseBuilder = Response.status(200);
        return responseBuilder.entity(studentDAO.getData(ID)).build();
    }

    public Response getStudents() {
        Response.ResponseBuilder responseBuilder = Response.status(200);
        return responseBuilder.entity(studentDAO.getCollection()).build();
    }

    public Response getStudents(String name, String surname, Date born, Date minDate, Date maxDate) {
        Response.ResponseBuilder responseBuilder = Response.status(200);
        Collection<Student> students = studentDAO.getCollection(name, surname, born, minDate, maxDate);
        return responseBuilder.entity(students).build();
    }

    public Response postStudent(Student entity) {
        studentDAO.addData(entity);
        return Response.status(201).build();
    }

    public Response putStudent(String id, Student entity) {
        int _id = Integer.parseInt(id);
        studentDAO.updateData(_id, entity);
        return Response.status(200).build();
    }

    public Response deleteStudent(String id) {
        int _id = Integer.parseInt(id);
        gradeDAO.deleteData(studentDAO.getData(_id));
        studentDAO.deleteData(_id);
        return Response.status(202).build();
    }
}
