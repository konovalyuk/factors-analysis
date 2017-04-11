package com.intapp.tms.web.restcontroller;

import com.intapp.tms.service.AddressService;
import com.intapp.tms.service.dto.AddressDTO;
import com.intapp.tms.service.validator.AddressValidator;
import com.intapp.tms.service.validator.EnvironmentValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * The type Address rest controller.
 */
@RestController
@RequestMapping("api/{version}/tenants/{tenantId}/address")
public class AddressRestController{

    private final AddressService addressService;

    /**
     * Instantiates a new Address rest controller.
     *
     * @param addressService the address service
     */
    protected AddressRestController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Init binder.
     *
     * @param binder the binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new AddressValidator());
    }

    /**
     * Find address response entity.
     *
     * @param tenantId the tenant id
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<?> findAddress(@PathVariable String tenantId) {
        return ResponseEntity.ok(addressService.findAddress(tenantId));
    }

    /**
     * Add address response entity.
     *
     * @param tenantId the tenant id
     * @param address  the address
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.POST, path = "/")
    public ResponseEntity<?> addAddress(@PathVariable String tenantId,@Valid @RequestBody AddressDTO address) {
        addressService.updateAddress(tenantId, address);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Update address response entity.
     *
     * @param tenantId the tenant id
     * @param address  the address
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/")
    public ResponseEntity<?> updateAddress(@PathVariable String tenantId,@Valid @RequestBody AddressDTO address) {
        addressService.updateAddress(tenantId, address);

        return ResponseEntity.ok().build();
    }

    /**
     * Remove address response entity.
     *
     * @param tenantId the tenant id
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/")
    public ResponseEntity<?> removeAddress(@PathVariable String tenantId) {
        addressService.updateAddress(tenantId, new AddressDTO());

        return ResponseEntity.ok().build();
    }
}
