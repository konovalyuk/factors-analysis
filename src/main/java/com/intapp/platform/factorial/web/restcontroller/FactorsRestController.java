package com.intapp.platform.factorial.web.restcontroller;

import com.intapp.platform.factorial.service.FactorsService;
import com.intapp.platform.factorial.service.dto.FactorsDTOGet;
import com.intapp.platform.factorial.service.dto.FactorsDTOPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    protected FactorsRestController(FactorsService factorsService) {
        this.factorsService = factorsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(factorsService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok(factorsService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/")
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

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}/update")
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

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
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

}

