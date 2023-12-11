package br.com.michaelcruz.rest;

import br.com.michaelcruz.persistence.dto.AccountDto;
import br.com.michaelcruz.service.AccountService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/accounts")
public class AccountController {

    @Inject
    AccountService accountService;

    @POST
    public Response addAccount(AccountDto accountData){
        this.accountService.addAccount(accountData);
        System.out.println(accountData);
        return Response.status(Response.Status.CREATED).entity("Conta criada com sucesso").build();
    }
}
