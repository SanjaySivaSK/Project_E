package com.restapi.repository;

import com.restapi.model.Notifications;
import com.restapi.model.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory,Long> {
    @Query("Select u from UserHistory u INNER JOIN u.appUser a WHERE a.id=?1 AND u.returned = false")
    List<UserHistory>   findByUserId(Long userId);

    @Query("SELECT u FROM UserHistory u WHERE u.returned = true")
    List<UserHistory> returnedBook();


    @Modifying
    @Transactional
    @Query("UPDATE UserHistory u SET u.returned = true, u.fineAmount = ?2 WHERE u.id = ?1")
    void returnBook(Long id, Long fineAmount);

    @Modifying
    @Transactional
    @Query("UPDATE UserHistory u SET u.renewal = true WHERE u.id = ?1")
    void renewal(Long id);

    @Query("SELECT u FROM  UserHistory u WHERE u.renewal = true")
    List<UserHistory> renewalBook();

    @Modifying
    @Transactional
    @Query("UPDATE UserHistory u SET u.renewal = false WHERE u.id = ?1")
    void    remove(Long id);

//    @Modifying
//    @Transactional
//    @Query("UPDATE UserHistory u SET u.decline = true WHERE u.id = ?1")
//    void decline(Long id);
}