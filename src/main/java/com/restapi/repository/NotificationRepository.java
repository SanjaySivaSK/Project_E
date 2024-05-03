package com.restapi.repository;

import com.restapi.model.Book;
import com.restapi.model.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Notifications n SET n.request = false WHERE n.id = ?1")
    void    clear(Long id);

    @Query("SELECT n FROM Notifications n WHERE n.request = true")
    List<Notifications> notifications();

    @Modifying
    @Transactional
    @Query("UPDATE Notifications n SET n.renewal = true WHERE n.id = ?1")
    void renewal(Long id);



    @Query("SELECT n FROM Notifications n WHERE n.renewal = true")
    List<Notifications> renewalBook();

    @Modifying
    @Transactional
    @Query("UPDATE Notifications n SET n.renewal = false WHERE n.id = ?1")
    void    remove(Long id);


    @Transactional
    @Modifying
    @Query("UPDATE Notifications n SET n.decline = true, n.request = false, n.message = :msg WHERE n.id = :id")
    void decline(@Param("id") Long id, @Param("msg") String msg);


    @Query("SELECT n FROM Notifications n WHERE n.appUser.id = :userId AND n.decline = true")
    List<Notifications> declined (@ Param("userId")Long UserId);
    @Modifying
    @Transactional
    @Query("UPDATE Notifications n SET n.decline = false WHERE n.id = ?1")
    void accept(Long id);

    @Query("SELECT n FROM Notifications n WHERE n.message IS NULL")
    List<Notifications> adminSuccessBook();
    @Query("SELECT n FROM Notifications n WHERE n.message IS NOT NULL")
    List<Notifications> adminDeclinedBook();

}

