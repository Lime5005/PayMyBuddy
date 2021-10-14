package com.lime.paymybuddy.model.dto;

import com.lime.paymybuddy.model.Account;

import java.math.BigDecimal;

public class TransactionDto {
    private Account fromAccount;
    private String toEmail;
    private BigDecimal amount;
    private String description;

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
