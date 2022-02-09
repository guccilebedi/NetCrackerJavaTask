package utils.validator;

import contracts.Contract;
import contracts.DigitalTelevision;
import contracts.MobileCommunication;

public class MobileCommunicationValidator implements Validator {
    /*
     * validate method checks all mobile communication
     * contract fields, so they will satisfy
     * necessary conditions
     *
     * @param contract - contract to validate
     * @param validation result - validation status and errors list
     * @return validation result
     */
    @Override
    public ValidationResult validate(Contract contract, ValidationResult validationResult) {
        if (contract.getClass().equals(MobileCommunication.class)) {
            MobileCommunication mobileCommunication = (MobileCommunication) contract;
            if (mobileCommunication.getMinutes() <= 0) {
                if (validationResult.getStatus() != ValidationStatus.ERROR) {
                    validationResult.setStatus(ValidationStatus.RISK);
                }
                validationResult.setErrors("Invalid minutes number!");
            }
            if (mobileCommunication.getMessages() <= 0) {
                if (validationResult.getStatus() != ValidationStatus.ERROR) {
                    validationResult.setStatus(ValidationStatus.RISK);
                }
                validationResult.setErrors("Invalid messages number!");
            }
            if (mobileCommunication.getGigabytes() <= 0) {
                if (validationResult.getStatus() != ValidationStatus.ERROR) {
                    validationResult.setStatus(ValidationStatus.RISK);
                }
                validationResult.setErrors("Invalid gigabytes number!");
            }
        }
        return validationResult;
    }
}
