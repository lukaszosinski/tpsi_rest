package resources;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by Mateusz on 11.09.2018.
 */
public class HttpException extends WebApplicationException {
    public HttpException(int status) {
        this(status, "");
    }
    public HttpException(int status, String message) {
        super(message, (Throwable)null, Response.status(status).entity(message).build());
    }
}
