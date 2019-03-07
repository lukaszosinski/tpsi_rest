package Adapters;

import org.bson.types.ObjectId;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;

public class ObjectIdJaxbAdapter extends XmlAdapter<String, ObjectId> {
    @Override
    public ObjectId unmarshal(String v) throws Exception {
        if (v == null)
            return null;
        try {
            return new ObjectId(v);
        } catch (Exception e) {
            return new ObjectId(new Date(0));
        }
    }
    @Override
    public String marshal(ObjectId v) throws Exception {
        if (v == null)
            return null;
        return v.toString();
    }
}