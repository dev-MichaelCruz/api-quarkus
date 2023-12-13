package br.com.michaelcruz.rest;

import br.com.michaelcruz.persistence.dto.AccountDTO;
import br.com.michaelcruz.persistence.model.Account;
import br.com.michaelcruz.service.AccountService;
import br.com.michaelcruz.util.AccountUtil;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/v1/accounts")
public class AccountController {

    @Inject
    AccountService accountService;



    @POST
    public Response createAccount(AccountDTO accountDTO) {
        this.accountService.createAccount(accountDTO);
        System.out.println(accountDTO);
        return Response.status(Response.Status.CREATED).entity("Conta criada com sucesso").build();
    }

    @Path("/deposit/{accountId}")
    @PATCH
    public Response depositToAccount(@PathParam("accountId") Long accountId, double amount) {
        this.accountService.depositToAccount(accountId, amount);
        return Response.status(Response.Status.OK).entity("Depósito realizado com sucesso").build();
    }

    @Path("/withdraw/{accounId}")
    @PATCH
    public Response withdraw(@PathParam("accountId") Long accountId, double amount) {
        this.accountService.withdraw(accountId, amount);
        return Response.status(Response.Status.OK).entity("Saque realizado com sucesso").build();
    }

    @GET
    public Response listAccounts() {
        List<Account> accounts = this.accountService.listAllAccounts();
        return Response.status(Response.Status.OK).entity(accounts).build();
    }

}
