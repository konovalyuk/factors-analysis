package com.intapp.platform.factorial.web.restcontroller;

import com.intapp.platform.factorial.service.LinearRegressionWekaModel;
import com.intapp.platform.factorial.service.converter.WekaModelDTOConverter;
import com.intapp.platform.factorial.service.dto.WekaModelInfoDTO;
import com.intapp.platform.factorial.service.dto.WekaModelInputDTO;
import com.intapp.platform.factorial.service.dto.WekaModelOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import weka.core.DenseInstance;


@RestController
@RequestMapping("api/weka")
public class WekaRestController {

    @Autowired
    LinearRegressionWekaModel wekaModel;

    @Autowired
    WekaModelDTOConverter wekaModelDTOConverter;

   // @RequestMapping(method = RequestMethod.GET, path = "/model")
    @GetMapping(value = "/model",
                        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buildModel() {
        {
//
            String modelInfo = wekaModel.createdModelInfo();
            WekaModelInfoDTO wekaModelInfoDTO = new WekaModelInfoDTO();
            wekaModelInfoDTO.setModelInfo(modelInfo);
            return ResponseEntity.ok(wekaModelInfoDTO);
        }
    }

    @PostMapping(value = "/evaluate",
            consumes = "application/json",
            headers = "Accept=application/vnd.intapp+json;version=1",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> evaluate(@RequestBody WekaModelInputDTO data) {
        {
            DenseInstance denseInstance = wekaModelDTOConverter.convertInput(data);
            Double result = null;
            try {
                result = wekaModel.clasifyInstance(denseInstance);
            } catch (Exception e) {
                e.printStackTrace();
            }

            WekaModelOutputDTO wekaModelOutputDTO = new WekaModelOutputDTO();
            wekaModelOutputDTO.setResult(result);

            return ResponseEntity.ok(wekaModelOutputDTO);
        }
    }

}

