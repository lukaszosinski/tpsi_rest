package DAO;

import models.Grade;
import models.Student;
import models.Subject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import java.util.*;

import static main.Main.datastore;


public class GradeDAO {
    private static GradeDAO instance;

    //private Map<Integer, Grade> grades = new HashMap<>();

    public Grade getData(String id) {
        return datastore.createQuery(Grade.class).field("id").equal(new ObjectId(id)).get();
    }

    //public Collection<Grade> getCollection() {return grades.values();}

    public Collection<Grade> getCollection(Student student, String subjectName, float minValue, float maxValue) {
        Query<Grade> query = datastore.createQuery(Grade.class).field("student").equal(student);
        if (!subjectName.equals("")) {
            query.field("subject").in(datastore.createQuery(Subject.class).filter("name", subjectName));
        }
        query.field("value").lessThan(maxValue).field("value").greaterThan(minValue);
        return query.asList();
    }

    public Collection<Grade> getCollection(Subject subject) {
        Query<Grade> query = datastore.createQuery(Grade.class).field("subject").equal(subject);
        return subject == null ? null : query.asList();
    }

    public void addData(Grade grade) {
        datastore.save(grade);
    }

    public void updateData(String id, Student student, Grade entity) {
        Grade grade = datastore.createQuery(Grade.class).field("id").equal(new ObjectId(id)).get();
        if(grade.getStudent().getIndex() == student.getIndex()) {
            grade.setValue(entity.getValue() == 0 ? grade.getValue() : entity.getValue());
            grade.subject = entity.getSubject() == null ? grade.getSubject() : entity.getSubject();
        }
        datastore.save(grade);
    }

    public void deleteData(String id) {
        datastore.delete(datastore.createQuery(Grade.class).field("id").equal(new ObjectId(id)));
    }

    public void deleteData(Student student) {
        datastore.delete(datastore.createQuery(Grade.class).field("student").equal(student));
    }

    public void deleteData(Subject subject) {
        datastore.delete(datastore.createQuery(Grade.class).field("subject").equal(subject));
    }

    public static GradeDAO getInstance() {
        if(GradeDAO.instance == null) {
            GradeDAO.instance = new GradeDAO();
        }
        return GradeDAO.instance;
    }
}


