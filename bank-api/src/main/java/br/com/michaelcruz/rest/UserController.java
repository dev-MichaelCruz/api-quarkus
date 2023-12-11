package br.com.michaelcruz.rest;


import br.com.michaelcruz.persistence.dto.UserDto;
import br.com.michaelcruz.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/users")
public class UserController {

    @Inject
    UserService userService;

    @POST
    public Response addUSer(UserDto userDto){
        this.userService.addUser(userDto);

        return Response.status(Response.Status.CREATED).entity("Criado com sucesso").build();
    }

}
