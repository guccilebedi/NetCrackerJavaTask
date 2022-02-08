package utils.validator;

import contracts.Contract;

import java.util.Objects;

public class ContractValidator implements Validator {
    /*
     * validate method checks all abstract
     * contract fields, so they will satisfy
     * necessary conditions
     *
     * @param contract - contract to validate
     * @param validation result - validation status and errors list
     * @return validation result
     */
    @Override
    public ValidationResult validate(Contract contract, ValidationResult validationResult) {
        if (contract.getDateStart() == null) {
            validationResult.setStatus(ValidationStatus.ERROR);
            validationResult.setErrors("Invalid contract starting date!");
        }
        if (contract.getDateEnd() == null) {
            validationResult.setStatus(ValidationStatus.ERROR);
            validationResult.setErrors("Invalid contract ending date!");
        }
        if (contract.getNumber() <= 0) {
            validationResult.setStatus(ValidationStatus.ERROR);
            validationResult.setErrors("Invalid contract number!");
        }
        if (contract.getPerson() != null) {
            if (Objects.equals(contract.getPerson().getFullName(), "") || Objects.equals(contract.getPerson().getFullName(), " ")) {
                validationResult.setStatus(ValidationStatus.ERROR);
                validationResult.setErrors("Invalid owners full name!");
            }
            if (contract.getPerson().getDateOfBirth() == null) {
                if (validationResult.getStatus() != ValidationStatus.ERROR) {
                    validationResult.setStatus(ValidationStatus.RISK);
                }
                validationResult.setErrors("Invalid owners date of birth!");
            }
            if (contract.getPerson().getSex() == null) {
                if (validationResult.getStatus() != ValidationStatus.ERROR) {
                    validationResult.setStatus(ValidationStatus.RISK);
                }
                validationResult.setErrors("Invalid owners sex!");
            }
            if (Objects.equals(contract.getPerson().getIdSeriesNumber(), "")) {
                validationResult.setStatus(ValidationStatus.ERROR);
                validationResult.setErrors("Invalid owners id series and number!");
            }
        } else {
            validationResult.setStatus(ValidationStatus.ERROR);
            validationResult.setErrors("Contract has no owner!");
        }
        return validationResult;
    }
}
