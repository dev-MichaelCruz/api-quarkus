package br.com.michaelcruz.persistence.dao;

import br.com.michaelcruz.persistence.model.Account;

import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(Long id);

    void save(T data);

    void update();
}
