package resources;

import javax.ws.rs.ext.ParamConverter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParamConverter implements ParamConverter<Date> {
    SimpleDateFormat sdf;
    @Override
    public Date fromString(String string) {
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (string == null || string.equals(""))
            return null;
        try {
            return sdf.parse(string);
        } catch (Exception e) {
            throw new HttpException(409, "Niepoprawny format daty w zapytaniu URL.");
        }
    }

    @Override
    public String toString(Date date) {
        return "";//sdf.format(date);
    }
}