package exceptions;
/*
This is verbatim copied from the inclass demo.
Therefore, it is represented as the work of Prof
Robinson and not mine. However, it is so simple
I am unsure of how to create a personal version.

 */

public class UnknownException extends RuntimeException {

    public UnknownException(Exception e) {
        super(e);
    }

    public UnknownException(String msg) {
        super(msg);
    }
}
