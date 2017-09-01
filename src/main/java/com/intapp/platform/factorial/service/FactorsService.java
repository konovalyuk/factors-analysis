package com.intapp.platform.factorial.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intapp.platform.factorial.service.converter.DtoConverter;
import com.intapp.platform.factorial.service.dto.WekaModelInputDTO;
import com.intapp.platform.factorial.service.factorial.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.intapp.platform.factorial.service.factorial.MatrixLogic.*;


/**
 * Created by maksymk on 4/6/2017.
 */
@Service
public class FactorsService {

    private static Logger logger = LoggerFactory.getLogger(FactorsService.class);

    private final DtoConverter dtoConverter;
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Instantiates a new Factors service.
     *
     * @param dtoConverter       the dto converter
     */
    public FactorsService(DtoConverter dtoConverter) {
        this.dtoConverter = dtoConverter;
    }

    /**
     * Create Factors dto.
     *
     * @return the Factors dto
     */
    public Double evaluate(WekaModelInputDTO wekaModelInputDTO) {
        System.out.println("   !!!  " + wekaModelInputDTO);
        int n = 5;
        int m = 7;
        Matrix inputMatrix = MatrixFactory.createMatrix(n, m);
        com.intapp.platform.factorial.service.factorial.Vector inputVector = MatrixFactory.createVector(n);
        System.out.println(" inputVector : " + inputVector);
        System.out.println(" inputMatrix : " + inputMatrix);

        Matrix step = transposeMatrix(inputMatrix);
        System.out.println("step 1 transposeMatrix: " + step);
        step = multiplyMatrix(step,inputMatrix);
        System.out.println("step 2 multiplyMatrix: " + step);
        step = invert(step);
        System.out.println("step 3 invert: " + step);
        step = multiplyMatrix(step,transposeMatrix(inputMatrix));
        System.out.println("step 4 : " + step);
        com.intapp.platform.factorial.service.factorial.Vector resultVector = multiplyMatrixOnVector(step,inputVector);
        System.out.println(" result : " + resultVector);

        Double result = resultVector.getElement(0)*wekaModelInputDTO.getAmlRiskScore()
                + resultVector.getElement(1)*wekaModelInputDTO.getEstimatedFeeScore()
                +resultVector.getElement(2)*wekaModelInputDTO.getConflictRiskScore()
                +resultVector.getElement(3)*wekaModelInputDTO.getFinanceRiskScore();

        System.out.println("   result:   " + result);
        return result;
    }

}
