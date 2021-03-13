package com.xdu.dzsb.repository;

import com.xdu.dzsb.model.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findAppUserByUserName(String userName);

}
