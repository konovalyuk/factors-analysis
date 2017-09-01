package com.intapp.platform.factorial.web.restcontroller;

import com.intapp.platform.factorial.service.FactorsService;
import com.intapp.platform.factorial.service.dto.FactorsDTOGet;
import com.intapp.platform.factorial.service.dto.FactorsDTOPost;
import com.intapp.platform.factorial.service.LinearRegressionWekaModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(factorsService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok(factorsService.findById(id));
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> createFactors(@Valid @RequestBody FactorsDTOPost factor, BindingResult errors) {
        if (errors.hasErrors()) {
            String msg = errorMessage(errors);
            return ResponseEntity.badRequest().body(msg);
        }

        FactorsDTOGet result = factorsService.create(factor);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId())
                .toUri();

        return ResponseEntity.ok(location);
    }

    @PutMapping(path = "/{id}/update")
    public ResponseEntity<?> updateFactor(
            @PathVariable String id,
            @Valid @RequestBody FactorsDTOPost factorsDTOPost, BindingResult errors) {
        if (errors.hasErrors()) {
            String msg = errorMessage(errors);
            return ResponseEntity.badRequest().body(msg);
        }
        FactorsDTOGet result = factorsService.update(id, factorsDTOPost);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> removeFactors(@PathVariable String id) {
        factorsService.delete(id);
        return ResponseEntity.ok("FactorsDTOPost has been removed.");
    }

    private String errorMessage(BindingResult errors) {
        String msg = errors.getAllErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.joining(","));
        logger.error(msg);
        return msg;
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

