package br.com.michaelcruz.persistence.dao;

import br.com.michaelcruz.persistence.model.Account;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class AccountDao implements Dao<Account>{

    @Inject
    EntityManager entityManager;

    @Override
    public Optional<Account> get(Long id) {

        try{
            var query = entityManager.createQuery("SELECT a FROM Account a WHERE id = :id", Account.class);
            return Optional.of(query.setParameter("id", id).getSingleResult());
        } catch (Exception ex){
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void save(Account data) {
        this.entityManager.persist(data);
    }

    @Override
    public void update() {

    }
}
