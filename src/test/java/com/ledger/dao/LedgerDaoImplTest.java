package com.ledger.dao;

import com.ledger.dao.impl.LedgerDaoImpl;
import com.ledger.exception.LoanNotFoundException;
import com.ledger.model.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.ledger.MockGenerator.getLoan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LedgerDaoImplTest {

    Map<String, Map<String, Loan>> map;
    private LedgerDao ledgerDao;

    @BeforeEach
    void setup(){
        map = new HashMap<>();
        ledgerDao = new LedgerDaoImpl(map);
    }

    @Test
    public void testSave_successfully(){
        Loan loan = getLoan();
        ledgerDao.saveLoan(loan);
        assertEquals(map.size(), 1);
        assertEquals(map.get(loan.getBorrowerName()).size(),1);
    }

    @Test
    public void testGet_successfully(){
        Loan loan = getLoan();
        ledgerDao.saveLoan(loan);
        Loan actual = ledgerDao.getLoan(loan.getBankName(), loan.getBorrowerName());
        assertEquals(loan, actual);
    }

    @Test
    public void testGet_NotFound(){
        Loan loan = getLoan();
        assertThrows(LoanNotFoundException.class,()->ledgerDao.getLoan(loan.getBankName(), loan.getBorrowerName()));
    }
}
