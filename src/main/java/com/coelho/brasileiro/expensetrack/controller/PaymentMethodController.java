package com.coelho.brasileiro.expensetrack.controller;


import com.coelho.brasileiro.expensetrack.constants.Params;
import com.coelho.brasileiro.expensetrack.input.PaymentMethodInput;
import com.coelho.brasileiro.expensetrack.service.PaymentMethodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("api/v1/payment-methods")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PaymentMethodInput paymentMethodInput) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.paymentMethodService.create(paymentMethodInput));
    }

    @PutMapping(path = "/{id:^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$}")
    public ResponseEntity<?> update(@RequestBody  PaymentMethodInput paymentMethodInput, @PathVariable("id") String id) {
        PaymentMethodInput input = new PaymentMethodInput(
                id,
                paymentMethodInput.getName(),
                paymentMethodInput.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(this.paymentMethodService.update(input));
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(value = Params.NAME, required = false) String name,
                                               @RequestParam(value = Params.DESCRIPTION, required = false) String description,
                                               @RequestParam(value = "pageNo", defaultValue = "0", required = false) String pageNo,
                                               @RequestParam(value = "pageSize", defaultValue = "10", required = false) String pageSize,
                                               @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
                                               @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        Map<String, String> params = new HashMap<>();
        params.put(Params.NAME, name);
        params.put(Params.DESCRIPTION, description);
        params.put(Params.NO_PAGE, pageNo);
        params.put(Params.PAGE_SIZE, pageSize);
        params.put(Params.SORT_BY, sortBy);
        params.put(Params.SORT_DIR, sortDir);
        return ResponseEntity.ok(this.paymentMethodService.findAll(params));
    }

    @DeleteMapping(path = "/{id:^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        PaymentMethodInput input = new PaymentMethodInput(id, null, null);
        this.paymentMethodService.delete(input);
        return ResponseEntity.ok().build();
    }


}
