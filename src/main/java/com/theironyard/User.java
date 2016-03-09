package com.theironyard;

import javax.persistence.*;

/**
 * Created by MacLap on 3/9/16.
 */

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false, unique = true)
    String name;



    int releaseYear;


    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}
