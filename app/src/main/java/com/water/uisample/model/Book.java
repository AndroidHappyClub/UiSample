package com.water.uisample.model;

public class Book extends BaseItem {
    private String _author;

    public Book() {
    }

    public Book(int thumb, String name, String author) {
        super(thumb, name);
        _author = author;
    }

    public String getAuthor() {
        return _author;
    }

    public void setAuthor(String author) {
        this._author = author;
    }
}
