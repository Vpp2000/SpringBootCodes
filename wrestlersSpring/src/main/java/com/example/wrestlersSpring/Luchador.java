package com.example.wrestlersSpring;

public class Luchador {

    private long id;
    private String name;
    private boolean champion;

    protected Luchador(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChampion() {
        return champion;
    }

    public void setChampion(boolean champion) {
        this.champion = champion;
    }
}
