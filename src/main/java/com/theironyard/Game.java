package com.theironyard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public Game() {
    }

    public Game(String name, String platform, String genre, int releaseYear) {
        this.name = name;
        this.platform = platform;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }
}
