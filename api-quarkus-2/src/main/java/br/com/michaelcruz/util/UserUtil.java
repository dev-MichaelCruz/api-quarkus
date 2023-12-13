package br.com.michaelcruz.util;

import br.com.michaelcruz.exception.NotFoundException;
import br.com.michaelcruz.persistence.dao.UserDAO;
import br.com.michaelcruz.persistence.dto.UserDTO;
import br.com.michaelcruz.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class UserUtil {
    @Inject
    UserDAO userDAO;

    //Obter informações do usuário
    public UserDTO getUserDetails(Long userId) {
        Optional<User> userOptional = userDAO.findById(userId);

        if (userOptional != null && userOptional.isPresent()) {
            User user = userOptional.get();
            return convertToUserDTO(user);
        }
        throw new NotFoundException("Usuário não encontrado");
    }

    //Converter DTO para User
    public User convertToUser(UserDTO userDTO){
        User user = new User();

        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());

        return user;
    }

    //Converter User para DTO
    public UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setAge(user.getAge());
        userDTO.setName(user.getName());
        userDTO.setPhone(user.getPhone());
        userDTO.setAddress(user.getAddress());

        return userDTO;
    }

}
