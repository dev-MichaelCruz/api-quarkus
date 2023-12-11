package br.com.michaelcruz.service;

import br.com.michaelcruz.persistence.dao.UserDao;
import br.com.michaelcruz.persistence.dto.UserDto;
import br.com.michaelcruz.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@ApplicationScoped
public class UserService {

    @Inject
    UserDao userDao;

    public void addUser(@Valid UserDto userData) {
        User user = new User();

        user.setNome(userData.getNome());
        user.setIdade(userData.getIdade());
        user.setTelefone(userData.getTelefone());
        user.setEndereco(userData.getEndereco());

        this.userDao.save(user);
    }
}
