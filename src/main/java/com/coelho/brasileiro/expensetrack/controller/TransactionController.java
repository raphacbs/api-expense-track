package com.coelho.brasileiro.expensetrack.controller;

import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("api/v1/transactions")
public class TransactionController {

    private final TransactionService  transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping

    public ResponseEntity<?> createTransaction(@RequestBody TransactionInput input) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.transactionService.saveTransaction(input));
    }
}
