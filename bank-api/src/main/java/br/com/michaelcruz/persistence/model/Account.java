package br.com.michaelcruz.persistence.model;

import jakarta.persistence.*;


@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String tipoConta;

    @Column
    double saldoAtual;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

//    public enum EnumAccount {
//
//        CONTA_CORRENTE("Conta Corrente"),
//        CONTA_POUPANCA("Conta Poupança");
//
//        private String descricao;
//
//        EnumAccount(String descricao) {
//            this.descricao = descricao;
//        }
//    }
}



