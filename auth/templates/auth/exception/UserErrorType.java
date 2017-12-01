package <%=packageName%>.auth.exception;


public class UserErrorType {

    private String errorMessage;

    public UserErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
