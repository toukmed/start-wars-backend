package com.cnexia.starwarsbackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "episode_id")
    private Integer episodeId;
    private String title;
    @Column(name = "opening_crawl")
    private String openingCrawl;

    private String director;
    private String producer;
    @Column(name = "release_date")
    private String releaseDate;
}
