package br.com.michaelcruz.util;

import br.com.michaelcruz.exception.NotFoundException;
import br.com.michaelcruz.persistence.dao.AccountDAO;
import br.com.michaelcruz.persistence.dto.AccountDTO;
import br.com.michaelcruz.persistence.dto.UserDTO;
import br.com.michaelcruz.persistence.model.Account;
import br.com.michaelcruz.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class AccountUtil {

    @Inject
    AccountDAO accountDAO;

    //Obter informações da conta
    public AccountDTO accountInfo(Long accountId) {
        Optional<Account> accountOptional = accountDAO.findById(accountId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return convertToAccountDTO(account);
        }
        throw new NotFoundException("Conta não encontrada");
    }

    //Converter de AccountDTO para Account
    public Account convertToAccount(AccountDTO accountDTO, UserDTO userDTO) {
        Account account = new Account();

        account.setType(accountDTO.getType());
        account.setBalance(accountDTO.getBalance());

        var user = new User();

        user.setId(userDTO.getId());
        account.setUser(user);

        return account;
    }

    //Converter de Account para AccountDTO
    public AccountDTO convertToAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setAccountId(account.getId());
        accountDTO.setType(account.getType());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setUserId(account.getUser().getId());

        return accountDTO;
    }
}
