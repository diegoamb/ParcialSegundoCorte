package com.example.parcial2;

public class Artist {
    String name;
    String mbid;
    String url;

    public String getName() {
        return name;
    }

    public Artist(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
