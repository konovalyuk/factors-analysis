package com.intapp.platform.factorial.web.restcontroller;

import com.intapp.platform.factorial.service.FactorsService;
import com.intapp.platform.factorial.service.LinearRegressionWekaModel;
import com.intapp.platform.factorial.service.dto.WekaModelInputDTO;
import com.intapp.platform.factorial.service.dto.WekaModelOutputDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/factors")
public class FactorsRestController {

    private static Logger logger = LoggerFactory.getLogger(FactorsRestController.class);

    protected final FactorsService factorsService;

    @Autowired
    private LinearRegressionWekaModel wekaModel;

    protected FactorsRestController(FactorsService factorsService) {
        this.factorsService = factorsService;
    }

    @PostMapping(value = "/evaluate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createFactors(@RequestBody WekaModelInputDTO factor) {
        Double result = factorsService.evaluate(factor);
        WekaModelOutputDTO wekaModelOutputDTO = new WekaModelOutputDTO();
        wekaModelOutputDTO.setResult(result);

        return ResponseEntity.ok(wekaModelOutputDTO);
    }

//    @RequestMapping(method = RequestMethod.GET, path ="/weka/model")
//    public ResponseEntity<?> buildModel() {
//        {
////            LinearRegressionWekaModel model = null;
////            try {
////                model = new LinearRegressionWekaModel();
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//            String modelInfo = wekaModel.createdModelInfo();
//            return ResponseEntity.ok(modelInfo);
//        }
//    }

}

