package com.water.uisample.model;

public class ShareData {
    private int thumb;
    private String _name;

    public ShareData() {
    }

    public ShareData(int id, String name) {
        this.thumb = id;
        this._name = name;
    }

    public int getThumb() {
        return thumb;
    }

    public String getName() {
        return _name;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public void setName(String name) {
        this._name = name;
    }
}
