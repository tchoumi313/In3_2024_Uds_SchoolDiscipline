package spring.learn.spring.exceptions;


public class ValgoException extends Exception {
    private String code;
    private String message;

    public ValgoException(String message) {
        super(message);
    }

    public ValgoException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
