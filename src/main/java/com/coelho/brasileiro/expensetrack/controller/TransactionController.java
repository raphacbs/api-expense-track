package com.coelho.brasileiro.expensetrack.controller;

import com.coelho.brasileiro.expensetrack.dto.TransactionDto;
import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.model.Transaction;
import com.coelho.brasileiro.expensetrack.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("api/v1/transactions")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Transações",
        description = "Endpoints relacionados à gestão de transações")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Cria uma nova transação.
     *
     * @param input Dados da transação a serem criados.
     * @return ResponseEntity contendo a transação criada e o status HTTP 201 (CREATED).
     */
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201",
            description = "Transação criada com sucesso",
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TransactionDto.class))})
    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionInput input) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.transactionService.saveTransaction(input));
    }

    /**
     * Obtém transações filtradas por período (startDate e endDate) e opcionalmente por categoryId e budgetId.
     *
     * @param startDate Data de início do período.
     * @param endDate   Data de fim do período.
     * @param categoryId ID da categoria para filtrar (opcional).
     * @param budgetId   ID do orçamento para filtrar (opcional).
     * @return ResponseEntity contendo a lista de transações filtradas e o status HTTP 200 (OK).
     */

    @GetMapping("/filter")
    public ResponseEntity<List<Transaction>> getTransactionsByPeriodAndFilters(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) UUID budgetId) {

        List<Transaction> filteredTransactions = transactionService.getTransactionsByPeriodAndFilters(
                startDate, endDate, categoryId, budgetId);

        return ResponseEntity.ok().build();
    }

    /**
     * Realiza o pagamento de uma transação.
     *
     * @param transactionId ID da transação a ser paga.
     * @return ResponseEntity indicando o sucesso do pagamento e o status HTTP apropriado.
     */
    @PostMapping("/{transactionId}/pay")
    public ResponseEntity<String> payTransaction(@PathVariable UUID transactionId) {
        // Lógica de pagamento da transação
        boolean paymentSuccess = transactionService.payTransaction(transactionId);

        if (paymentSuccess) {
            return ResponseEntity.ok("Pagamento realizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar o pagamento.");
        }
    }

    /**
     * Edita uma transação existente.
     *
     * @param transactionId ID da transação a ser editada.
     * @param input         Dados atualizados da transação.
     * @return ResponseEntity contendo a transação editada e o status HTTP 200 (OK).
     */
    @PutMapping("/{transactionId}")
    public ResponseEntity<?> editTransaction(@PathVariable UUID transactionId, @RequestBody TransactionInput input) {
        Transaction editedTransaction = transactionService.editTransaction(transactionId, input);

        if (editedTransaction != null) {
            return ResponseEntity.ok(editedTransaction);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transação não encontrada.");
        }
    }

    /**
     * Exclui uma transação existente.
     *
     * @param transactionId ID da transação a ser excluída.
     * @return ResponseEntity indicando o sucesso da exclusão e o status HTTP apropriado.
     */
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable UUID transactionId) {
        boolean deletionSuccess = transactionService.deleteTransaction(transactionId);

        if (deletionSuccess) {
            return ResponseEntity.ok("Transação excluída com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transação não encontrada para exclusão.");
        }
    }
}
