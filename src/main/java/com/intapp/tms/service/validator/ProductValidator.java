package com.intapp.tms.service.validator;

import com.intapp.tms.service.dto.EnvironmentDTO;
import com.intapp.tms.service.dto.ProductDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by maksymk on 4/10/2017.
 */
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductDTO.class.equals(aClass);
    }

//    TODO: perform additional checks

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        ProductDTO productDTO = (ProductDTO) o;

    }
}
