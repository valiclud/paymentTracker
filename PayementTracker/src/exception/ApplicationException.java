package exception;

/**
 * ApplicationException class.
 * @author Ludvik Valicek - March 9 2016
 *
 */
public class ApplicationException extends java.lang.Exception {

    public ApplicationException(String s) {
        super(s);
    }

    public ApplicationException(Throwable c) {
        super(c);
    }
}
