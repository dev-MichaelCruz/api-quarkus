package br.com.michaelcruz.service;

import br.com.michaelcruz.exception.BadRequestException;
import br.com.michaelcruz.exception.NotFoundException;
import br.com.michaelcruz.persistence.dao.AccountDAO;
import br.com.michaelcruz.persistence.model.AccountType;
import br.com.michaelcruz.util.AccountUtil;
import br.com.michaelcruz.persistence.dto.AccountDTO;
import br.com.michaelcruz.persistence.dto.UserDTO;
import br.com.michaelcruz.persistence.model.Account;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AccountService {
    @Inject
    AccountDAO accountDAO;

    @Inject
    UserService userService;

    @Inject
    AccountUtil accountUtil;


    public void createAccount(@Valid AccountDTO accountDTO) {
        UserDTO userDTO = userService.getUserDetails(accountDTO.getUserId());
        Account account = accountUtil.convertToAccount(accountDTO, userDTO);

        accountDAO.save(account);
    }

    public void depositToAccount(@Valid Long accountId, double amount){
        AccountDTO accountDTO = accountUtil.accountInfo(accountId);
        UserDTO userDTO = userService.getUserDetails(accountId);

        if(accountDTO == null){
            throw new NotFoundException("Conta não encontrada");
        }

        if(accountDTO.getType() == AccountType.CONTA_POUPANCA){
            accountDTO.setBalance(accountDTO.getBalance() + amount + 0.5);
        } else {
            accountDTO.setBalance(accountDTO.getBalance() + amount);
        }

        Account account = accountUtil.convertToAccount(accountDTO, userDTO);

        this.accountDAO.update(account);
//        Optional<Account> accountOptional = accountDAO.findById(accountId);
//
//        Account account = accountOptional.get();
//        Double newBalance = account.getBalance() + amount;
//
//        if(account.getType() == AccountType.CONTA_POUPANCA){
//            account.setBalance(newBalance + 0.5);
//        } else {
//            account.setBalance(newBalance);
//        }
//
//        this.accountDAO.update(account);



//
//        Account account = accountDAO.getById(accountId);
//
//        double newBalance = account.getBalance() + amount;
//
//        account.setBalance(newBalance);



//        Optional<Account> optionalAccount = accountDAO.findById(accountId);
//
//        if(optionalAccount != null && optionalAccount.isPresent()){
//            Account account = optionalAccount.get();
//
//            if (amount > 0){
//                double newBalance = account.getBalance() + amount;
//
//                if(account.getType() == AccountType.CONTA_POUPANCA){
//                    newBalance += 0.5;
//                }
//
//                account.setBalance(newBalance);
//                accountDAO.save(account);
//            } else {
//                throw new BadRequestException("Valor do depósito precisa ser maior que zero");
//            }
//        } else {
//            throw new NotFoundException("Conta informada não encontrada");
//        }
    }

    public void withdraw(Long accountId, Double amount) {
        Optional<Account> optionalAccount = accountDAO.findById(accountId);

        if(optionalAccount != null && optionalAccount.isPresent()){
            Account account = optionalAccount.get();

            if(amount > 0 && account.getBalance() > amount) {
                double newBalance = account.getBalance() - amount;
                account.setBalance(newBalance);

                accountDAO.save(account);

//                Account updatedAccount = accountDAO.save(account);
//                return  accountUtil.convertToAccountDTO((updatedAccount));
            } else {
                throw new BadRequestException("Valor do saque indisponpivel a conta");
            }
        } else {
            throw new NotFoundException("Conta informada não encontrada");
        }
    }

    public List<Account> listAllAccounts(){
        return this.accountDAO.getAll();
    }


}
