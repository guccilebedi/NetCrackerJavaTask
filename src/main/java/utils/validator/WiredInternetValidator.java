package utils.validator;

import contracts.Contract;
import contracts.DigitalTelevision;
import contracts.WiredInternet;

public class WiredInternetValidator implements Validator {
    /*
     * validate method checks all wired internet
     * contract fields, so they will satisfy
     * necessary conditions
     *
     * @param contract - contract to validate
     * @param validation result - validation status and errors list
     * @return validation result
     */
    @Override
    public ValidationResult validate(Contract contract, ValidationResult validationResult) {
        if (contract.getClass().equals(WiredInternet.class)) {
            WiredInternet wiredInternet = (WiredInternet) contract;
            if (wiredInternet.getSpeed() <= 0) {
                if (validationResult.getStatus() != ValidationStatus.ERROR) {
                    validationResult.setStatus(ValidationStatus.RISK);
                }
                validationResult.setErrors("Invalid speed!");
            }
        }
        return validationResult;
    }
}
