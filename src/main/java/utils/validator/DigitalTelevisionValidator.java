package utils.validator;

import contracts.Contract;
import contracts.DigitalTelevision;

public class DigitalTelevisionValidator implements Validator {
    /*
     * validate method checks all digital television
     * contract fields, so they will satisfy
     * necessary conditions
     *
     * @param contract - contract to validate
     * @param validation result - validation status and errors list
     * @return validation result
     */
    @Override
    public ValidationResult validate(Contract contract, ValidationResult validationResult) {
        if (contract.getClass().equals(DigitalTelevision.class)) {
            DigitalTelevision digitalTelevision = (DigitalTelevision) contract;
            if (digitalTelevision.getChannelPackage() == null) {
                if (validationResult.getStatus() != ValidationStatus.ERROR) {
                    validationResult.setStatus(ValidationStatus.RISK);
                }
                validationResult.setErrors("Invalid channel package name!");
            }
        }
        return validationResult;
    }
}
