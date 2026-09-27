package com.bank.loan.web;

import com.bank.loan.api.CreateLoanRequest;
import com.bank.loan.domain.LoanApplicationRecord;
import com.bank.loan.service.LoanApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanApplicationService service;
    public LoanController(LoanApplicationService service) { this.service = service; }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public LoanApplicationRecord create(@Valid @RequestBody CreateLoanRequest request) { return service.create(request); }
    @GetMapping public List<LoanApplicationRecord> list() { return service.list(); }
    @GetMapping("/{id}") public LoanApplicationRecord get(@PathVariable Long id) { return service.get(id); }
    @PostMapping("/{id}/submit") public LoanApplicationRecord submit(@PathVariable Long id) { return service.submit(id); }
}
