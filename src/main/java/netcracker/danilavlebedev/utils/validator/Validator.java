package netcracker.danilavlebedev.utils.validator;

import netcracker.danilavlebedev.contracts.Contract;

public interface Validator {
    /*
     * interface for validators
     */
    ValidationResult validate(Contract contract, ValidationResult validationResult);
}
