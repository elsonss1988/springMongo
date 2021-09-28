package com.example.springmongo.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class Comment implements Serializable {
    public static final Long serialVersionUID=1L;

    private String id;
    private String text;
    private Instant date;

    public Comment(){}

    public Comment(String id, String text, Instant date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
