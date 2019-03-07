package sequence;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import static main.Main.datastore;

@Entity("sequences")
public class Sequence {
    static int id = 7;

    @Id
    String table;
    public int value;
    public static synchronized int generateStudentId() {
        return generateId("students");
    }
    private static int generateId(String tableName) {
     //   Query<Sequence> query = datastore.createQuery(Sequence.class).filter("table =", tableName);
      //  int v = query.asList().get(0).value;
      //  UpdateOperations<Sequence> ops = datastore.createUpdateOperations(Sequence.class).inc("value");
      //  datastore.findAndModify(query, ops);
      //  return v;
        return ++id;
    }
}