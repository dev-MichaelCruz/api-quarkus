package br.com.michaelcruz.service;

import br.com.michaelcruz.persistence.dao.AccountDao;
import br.com.michaelcruz.persistence.dao.UserDao;
import br.com.michaelcruz.persistence.dto.AccountDto;
import br.com.michaelcruz.persistence.model.Account;
import br.com.michaelcruz.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

import java.util.Optional;

@ApplicationScoped
public class AccountService {

    @Inject
    AccountDao accountDao;

    @Inject
    UserDao userDao;

    public void addAccount(@Valid AccountDto accountData){
        Long userId = accountData.getUserId();

        Optional<User> user = userDao.get(userId);
        User otherUser = user.get();

        Account account = new Account();

        account.setTipoConta(accountData.getTipoConta());

        account.setSaldoAtual(accountData.getSaldoAtual());
        account.setUser(otherUser);

        this.accountDao.save(account);

    }

}
