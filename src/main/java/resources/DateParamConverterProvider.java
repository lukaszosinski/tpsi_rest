package resources;

import resources.DateParamConverter;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Mateusz on 15.09.2018.
 */
@Provider
public class DateParamConverterProvider implements ParamConverterProvider {
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType,
                                              Annotation[] annotations) {
        if (rawType == Date.class) {
            return (ParamConverter<T>) new DateParamConverter();
        }
        return null;
    }
}