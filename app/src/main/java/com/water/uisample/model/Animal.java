package com.water.uisample.model;

public class Animal {
    private int _thumb;
    private String _name;

    public Animal() {
    }

    public Animal(int thumb, String name) {
        this._thumb = thumb;
        this._name = name;
    }

    public int getThumb() {
        return _thumb;
    }

    public String getName() {
        return _name;
    }

    public void setThumb(int thumb) {
        this._thumb = thumb;
    }

    public void setName(String name) {
        this._name = name;
    }
}