    package com.restapi.model;

    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import javax.persistence.*;

    @Entity
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Notifications {
        @Id
        @GeneratedValue
        private Long id;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private AppUser appUser;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "book_id", referencedColumnName = "id")
        private Book book;


        private boolean renewal =false;

        private boolean request=false;

        private boolean decline=false;

        private String message;

    }