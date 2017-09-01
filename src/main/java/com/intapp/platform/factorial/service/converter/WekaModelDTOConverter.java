package com.intapp.platform.factorial.service.converter;

import com.intapp.platform.factorial.service.dto.WekaModelInputDTO;
import org.springframework.stereotype.Service;
import weka.core.DenseInstance;

@Service
public class WekaModelDTOConverter {
    
    public DenseInstance convertInput (WekaModelInputDTO inputDTO){
        DenseInstance denseInstance = new DenseInstance(14);
        
//        Integer[] arrDataRaw = {-1000,1,3,1,2,1,2,1,1,0,0,1,0,1};
//        for (int itr=0; itr < arrDataRaw.length-1;itr++){
//            testInstance2.setValue(itr,arrDataRaw[itr]);
//        }

        denseInstance.setValue(0, -1000);
        denseInstance.setValue(1, inputDTO.getClientType());
        denseInstance.setValue(2, inputDTO.getEstimatedFeeScore());
        denseInstance.setValue(3, inputDTO.getAmlRiskScore());
        denseInstance.setValue(4, inputDTO.getFinanceRiskScore());
        denseInstance.setValue(5, inputDTO.getConflictRiskScore());
        denseInstance.setValue(6, inputDTO.getIsContingent());
        denseInstance.setValue(7, inputDTO.getIsHighRiskCountry());
        denseInstance.setValue(8, inputDTO.getIsBribery());
        denseInstance.setValue(9, inputDTO.getIsMilitaryActivities());
        denseInstance.setValue(10, inputDTO.getIsGrant());
        denseInstance.setValue(11, inputDTO.getIsCompeteticeDecision());
        denseInstance.setValue(12, inputDTO.getIsFederal());
        denseInstance.setValue(13, inputDTO.getIsFeeArrangementContingent());

        return denseInstance;
        
            
    }
}
