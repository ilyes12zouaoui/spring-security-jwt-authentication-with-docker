package com.ilyeszouaoui.jwtauthentication.state;

import com.ilyeszouaoui.jwtauthentication.state.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> getByEmailIgnoreCase(String email);
}
