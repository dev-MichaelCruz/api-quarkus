package br.com.michaelcruz.persistence.dao;

import br.com.michaelcruz.persistence.model.Account;
import br.com.michaelcruz.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class UserDao implements Dao<User>{

    @Inject
    EntityManager entityManager;

    @Override
    public Optional<User> get(Long id) {

        User user = this.entityManager.find(User.class, id);

        Optional<User> optionalUser = Optional.of(user);
        if(optionalUser.isEmpty()){
            return Optional.empty();
        }

        return optionalUser;
    }

    @Override
    @Transactional
    public void save(User data) {
        this.entityManager.persist(data);
    }

    @Override
    public void update() {

    }
}
