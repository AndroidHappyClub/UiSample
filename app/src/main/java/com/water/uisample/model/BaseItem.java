package com.water.uisample.model;

public class BaseItem {
    private int _id;
    private int _thumb;
    private String _name;

    public BaseItem() {
    }

    public BaseItem(String name) {
        _name = name;
    }

    public BaseItem(int thumb, String name) {
        _thumb = thumb;
        _name = name;
    }

    public BaseItem(int id, int thumb, String name) {
        _id = id;
        _thumb = thumb;
        _name = name;
    }

    public int getId() {
        return _id;
    }

    public int getThumb() {
        return _thumb;
    }

    public String getName() {
        return _name;
    }

    public void setId(int id) {
        _id = id;
    }

    public void setThumb(int thumb) {
        this._thumb = thumb;
    }

    public void setName(String name) {
        this._name = name;
    }
}

