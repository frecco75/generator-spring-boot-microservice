package <%=packageName%>.auth.exception;

/**
 * @author Prasenjit Karmakar
 */
public enum UserErrorMessages {
	DUPLICATE_USER("User already exists"),
    CANDIDATE_NOT_FOUND("Candidate not found"),
    POSITION_NOT_FOUND("Position not found"),
    ASSESSMENT_BAD_STATUS("Invalid status to view details"),
    ASSESSMENT_NOT_FOUND("Assessment not found"),
    OOPS_ERROR("Oops! Something went wrong"),
    EMAIL_ERROR("Error sending email"),
    INVALID_EMAIL_TEMPLATE_ID("Email template id is invalid"),
    TEMPLATE_ID_EXISTS("Template id already exists"),
    OOPS_COULD_NOT_PROCESS("Request could not be processed");

    private String message;

    UserErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
