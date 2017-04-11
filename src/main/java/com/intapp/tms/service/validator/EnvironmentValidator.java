package com.intapp.tms.service.validator;

import com.intapp.tms.service.dto.EnvironmentDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by maksymk on 4/10/2017.
 */
public class EnvironmentValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return EnvironmentDTO.class.equals(aClass);
    }

    //    TODO: perform additional checks

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        EnvironmentDTO environmentDTO = (EnvironmentDTO) o;

    }

}
