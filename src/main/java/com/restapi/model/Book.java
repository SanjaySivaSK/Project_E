package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String book;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name ="author_id",referencedColumnName = "id")
    private Author author;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName="id")
    private  AppUser appUser;

    @ManyToOne
    @JoinColumn(name="category_id",referencedColumnName = "id")
    private Category category;



    private int  Stock;


    private String photo;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Notifications> notificationsList = new ArrayList<>();

//    private boolean request=false;
     private  boolean deleteFlag;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


}