package com.coelho.brasileiro.expensetrack.controller;


import com.coelho.brasileiro.expensetrack.constants.Params;
import com.coelho.brasileiro.expensetrack.input.BudgetInput;
import com.coelho.brasileiro.expensetrack.input.CategoryInput;
import com.coelho.brasileiro.expensetrack.service.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/budgets")
public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<?> createBudget(@RequestBody BudgetInput budgetInput) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.budgetService.create(budgetInput));
    }

    @GetMapping
    public ResponseEntity<?> getBudgets(@RequestParam(value = Params.START_DATE) String startDate,
                                        @RequestParam(value = Params.END_DATE, required = false) String endDate,
                                        @RequestParam(value = "pageNo", defaultValue = "0", required = false) String pageNo,
                                        @RequestParam(value = "pageSize", defaultValue = "10", required = false) String pageSize,
                                        @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
                                        @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        Map<String, String> params = new HashMap<>();
        params.put(Params.START_DATE, startDate);
        params.put(Params.END_DATE, endDate);
        params.put(Params.NO_PAGE, pageNo);
        params.put(Params.PAGE_SIZE, pageSize);
        params.put(Params.SORT_BY, sortBy);
        params.put(Params.SORT_DIR, sortDir);

        return ResponseEntity.ok(this.budgetService.getBudgets(params));

    }

    @PutMapping(path = "/{id:^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$}")
    public ResponseEntity<?> updateBudget(@RequestBody BudgetInput budgetInput, @PathVariable("id") String id) {
        BudgetInput input = new BudgetInput(id,
                budgetInput.getFrequency(),
                budgetInput.getEndDate(),
                budgetInput.getStartDate(),
                budgetInput.getAmount(),
                budgetInput.getName(),
                budgetInput.getNotes(),
                budgetInput.getCategoryId());
        return ResponseEntity.status(HttpStatus.OK).body(this.budgetService.update(input));
    }

    @DeleteMapping(path = "/{id:^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$}")
    public ResponseEntity<?> delete(@PathVariable("id") String id, @RequestHeader("recurringDelete") boolean recurringDelete) {
        BudgetInput budgetInput = BudgetInput.builder().id(id).build();
        budgetInput.addHeader("recurringDelete", recurringDelete);
        this.budgetService.delete(budgetInput);
        return ResponseEntity.ok().build();
    }
}
