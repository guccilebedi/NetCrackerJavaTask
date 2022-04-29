package netcracker.danilavlebedev.utils.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    /*
     * @param status - enum status
     * @param errors - list of string errors
     */
    private ValidationStatus status = ValidationStatus.OK;
    private List<String> errors = new ArrayList<>();

    public ValidationResult() {

    }

    public ValidationStatus getStatus() {
        return status;
    }

    public void setStatus(ValidationStatus status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(String error) {
        this.errors.add(error);
    }
}
