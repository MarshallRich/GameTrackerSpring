package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by MacLap on 3/8/16.
 */

@Entity
public class Game {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String platform;

    @Column(nullable =false)
    String genre;

    int releaseYear;

    @ManyToOne
    User user;

    public Game() {
    }

    public Game(String name, String platform, String genre, int releaseYear, User user) {
        this.name = name;
        this.platform = platform;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.user = user;
    }
}
