package services;

import DAO.GradeDAO;
import DAO.StudentDAO;
import DAO.SubjectDAO;
import models.Grade;
import models.Student;
import models.Subject;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GradeService {

    private GradeDAO gradeDAO = GradeDAO.getInstance();
    private StudentDAO studentDAO = StudentDAO.getInstance();
    //private SubjectDAO subjectDAO = SubjectDAO.getInstance();

    public Response getStudentGrades(String studentId, String subjectName, float minValue, float maxValue) {
        int _studentId = Integer.parseInt(studentId);
        Student student = studentDAO.getData(_studentId);
        Collection<Grade> grades = gradeDAO.getCollection(student, subjectName, maxValue, minValue);
        if(grades != null) {
            Response.ResponseBuilder responseBuilder = Response.status(200);
            return responseBuilder.entity(grades).build();
        } else {
            Response.ResponseBuilder responseBuilder = Response.status(404);
            return responseBuilder.build();
        }
    }

    public Response getStudentGrade(String studentId, String id) {
        int _studentId = Integer.parseInt(studentId);
        //int _id = Integer.parseInt(id);
        Grade grade = gradeDAO.getData(id);
        Student student = studentDAO.getData(_studentId);
        if(grade.getStudent() == student) {
            Response.ResponseBuilder responseBuilder = Response.status(200);
            return responseBuilder.entity(gradeDAO.getData(id)).build();
        } else {
            Response.ResponseBuilder responseBuilder = Response.status(404);
            return responseBuilder.build();
        }

    }

    public Response postGrade(String studentId, Grade entity) {
        Student student = studentDAO.getData(Integer.parseInt(studentId));
        entity.setStudent(student);
        if(entity.getSubject() == null || entity.getStudent() == null){
            return Response.status(404).build();
        } else if (validateGrade(entity)) {
            return Response.status(400).build();
        }
        gradeDAO.addData(entity);
        return Response.status(201).build();
    }

    public Response putGrade(String id, String studentId, Grade entity) {
        int _studentId = Integer.parseInt(studentId);
        //int _id = Integer.parseInt(id);
        Student student = studentDAO.getData(_studentId);
        if(validateGrade(entity)) {
            return Response.status(400).build();
        }
        gradeDAO.updateData(id, student, entity);
        return Response.status(200).build();
    }

    public Response deleteGrade(String id) {
        //int _id = Integer.parseInt(id);
        gradeDAO.deleteData(id);
        return Response.status(200).build();
    }

    public boolean validateGrade(Grade grade) {
        //return (grade.getValue() > 6.0 || grade.getValue() < 2.0 || grade.getValue()%0.5 != 0.0);
        return false;
    }
}
