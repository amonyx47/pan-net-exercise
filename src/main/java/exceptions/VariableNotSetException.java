package exceptions;

public class VariableNotSetException extends Exception {

    public VariableNotSetException(String message){
        super(message);
    }

    public VariableNotSetException(String message, Throwable throwable){
        super(message, throwable);
    }

}
