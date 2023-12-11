package br.com.michaelcruz.persistence.dto;

import br.com.michaelcruz.persistence.model.Account;
import br.com.michaelcruz.persistence.model.User;

public class AccountDto {
    Long userId;

    String tipoConta;

    double saldoAtual;

    User user;


    public AccountDto(Long userId, String tipoConta, double saldoAtual, User user) {
        this.userId = userId;
        this.tipoConta = tipoConta;
        this.saldoAtual = saldoAtual;
        this.user = user;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "userId=" + userId +
                ", tipoConta='" + tipoConta + '\'' +
                ", saldoAtual=" + saldoAtual +
                ", user=" + user +
                '}';
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(double saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
