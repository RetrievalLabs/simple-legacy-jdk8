package com.bank.loan.repository;

import com.bank.loan.domain.LoanApplicationRecord;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LoanApplicationRepository {
    private final ConcurrentHashMap<Long, LoanApplicationRecord> records = new ConcurrentHashMap<Long, LoanApplicationRecord>();
    private final AtomicLong ids = new AtomicLong();

    public Long nextId() { return ids.incrementAndGet(); }

    public LoanApplicationRecord save(LoanApplicationRecord record) {
        if (record.getId() == null) record.setId(nextId());
        records.put(record.getId(), record);
        return record;
    }
    public Optional<LoanApplicationRecord> find(Long id) { return Optional.ofNullable(records.get(id)); }
    public List<LoanApplicationRecord> findAll() {
        List<LoanApplicationRecord> result = new ArrayList<LoanApplicationRecord>(records.values());
        result.sort(Comparator.comparing(LoanApplicationRecord::getId));
        return result;
    }
}
