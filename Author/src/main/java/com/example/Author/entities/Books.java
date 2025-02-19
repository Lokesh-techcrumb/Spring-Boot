package com.example.Author.entities;

import jakarta.persistence.*;

@Entity
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    private int publication_year;

    public Books() {}

    public Books(String title, int publication_year) {
        this.title = title;
        this.publication_year = publication_year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }
}
