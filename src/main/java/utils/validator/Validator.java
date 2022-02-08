package utils.validator;

import contracts.Contract;

public interface Validator {
    /*
     * interface for validators
     */
    ValidationResult validate(Contract contract, ValidationResult validationResult);
}
