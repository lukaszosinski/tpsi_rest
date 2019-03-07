package DAO;

import models.Student;
import models.Subject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import java.util.Collection;


import static main.Main.datastore;


public class SubjectDAO {
    public static SubjectDAO instance;

    private SubjectDAO() {
        //Subject subject1 = new Subject("Geografia", "Zbigniew Zygora");
        //Subject subject2 = new Subject("Matematyka", "Małgorzata Zygora");
        //subjects.put(subject1.getId(), subject1);
        //subjects.put(subject2.getId(), subject2);
    }

    //private Map<Integer, Subject> subjects = new HashMap<>();

    public Subject getData(String id) {
       return datastore.createQuery(Subject.class).field("id").equal(new ObjectId(id)).get();
    }

    public Collection<Subject> getCollection(String lecturer) {
        Query<Subject> query = datastore.createQuery(Subject.class);
        if (!lecturer.equals(""))
            query.filter("lecturer", lecturer);
        return query.asList();
    }

    public void addData(Subject subject) {
        datastore.save(subject);
    }

    public void updateData(String id, Subject entity) {
        Subject subject = datastore.createQuery(Subject.class).field("id").equal(new ObjectId(id)).get();
        if(subject != null) {
            subject.setName(entity.getName() == null ? subject.getName() : entity.getName());
            subject.setLecturer(entity.getLecturer() == null ? subject.getLecturer() : entity.getLecturer());
        }
        datastore.save(subject);
    }

    public void deleteData(String id) {
        datastore.delete(datastore.createQuery(Subject.class).field("id").equal(new ObjectId(id)));
    }

    public static SubjectDAO getInstance() {
        if(SubjectDAO.instance == null) {
            SubjectDAO.instance = new SubjectDAO();
        }
        return SubjectDAO.instance;
    }
}
