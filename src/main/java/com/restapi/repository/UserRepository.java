package com.restapi.repository;

import com.restapi.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

//    @Query("select a from Profile a where a.appUser.id = ?1")
//    Optional<AppUser> findByUserId(Long userId);
@Query("SELECT a FROM AppUser a WHERE a.id <> 2")
List<AppUser> findUsers();

}
