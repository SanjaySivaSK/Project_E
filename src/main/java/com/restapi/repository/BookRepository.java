package com.restapi.repository;

import com.restapi.model.Book;
import com.restapi.model.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.deleteFlag = true WHERE b.id = ?1")
    void  DeleteById(Long id);

    @Query("SELECT b FROM Book b WHERE b.deleteFlag = false")
    List<Book> FindAll();

//

}