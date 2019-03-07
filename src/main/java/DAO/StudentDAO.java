package DAO;

import models.Indexes;
import models.Student;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import sequence.Sequence;
import static main.Main.datastore;

import java.util.Collection;
import java.util.Date;


public class StudentDAO {
    public static StudentDAO instance;

    Indexes indexes = datastore.createQuery(Indexes.class).get();

    private StudentDAO() {
        if (indexes == null) {
            datastore.save(new Indexes());
        }
        //Student student1 = new Student("Łukasz", "Osiński", new Date());
        //Student student2 = new Student("Dominik", "Białecki", new Date());
        //Student student3 = new Student("Mateusz", "Lewandowski", new Date());
        //students.put(student1.getIndex(), student1);
        //students.put(student2.getIndex(), student2);
        //students.put(student3.getIndex(), student3);
    }

    //private Map<Integer, Student> students = new HashMap<>();

    public Student getData(int index) {
        return datastore.createQuery(Student.class).field("index").equal(index).get();
    }

    public Collection<Student> getCollection() {
        Query<Student> query = datastore.createQuery(Student.class);
        return query.asList();
    }

    public Collection<Student> getCollection(String name, String surname, Date born, Date minDate, Date maxDate) {
        Query<Student> query = datastore.createQuery(Student.class);
        if (!name.equals(""))
            query.filter("name", name);
        if (!surname.equals(""))
            query.filter("surname", surname);
        if (born != null)
            query.filter("birthDate", born);
        if (minDate != null)
            query.field("birthDate").greaterThan(minDate);
        if (maxDate != null)
            query.field("birthDate").lessThan(maxDate);
        return query.asList();
    }

    public void addData(Student student) {
        //students.put(student.getIndex(), student);
        //student.index = Sequence.generateStudentId();
        student.setIndex(getNextStudentIndex());
        datastore.save(student);
    }

    public void deleteData(int index) {
        datastore.delete(datastore.createQuery(Student.class).field("index").equal(index));
        //students.remove(id);
    }

    public void updateData(int index, Student entity) {
        Student student = datastore.createQuery(Student.class).field("index").equal(index).get();
        if(student != null) {
            student.setName(entity.getName() == null ? student.getName() : entity.getName());
            student.setSurname(entity.getSurname() == null ? student.getSurname() : entity.getSurname());
            student.setBirthDate(entity.getBirthDate() == null ? student.getBirthDate() : entity.getBirthDate());
        }
        datastore.save(student);
    }

    public static StudentDAO getInstance() {
        if(StudentDAO.instance == null) {
            StudentDAO.instance = new StudentDAO();
        }
        return StudentDAO.instance;
    }

    public int getNextStudentIndex() {
        Query<Indexes> query = datastore.createQuery(Indexes.class).limit(1);
        UpdateOperations<Indexes> updateOperation = datastore.createUpdateOperations(Indexes.class).inc("studentIndex");
        return datastore.findAndModify(query, updateOperation, false, false).getStudentIndex();
    }


}
