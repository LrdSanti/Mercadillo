package com.mall.mercadillo.repository;

import com.mall.mercadillo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<User, Long> {

    Optional<User> findOneByEmail(String email);
}
