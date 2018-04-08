package com.solvits.indonesianidol.model;
import java.io.Serializable;

/**
 * Created by qushay on 06/02/18.
 */
@SuppressWarnings("serial")
public class ContestantModel implements Serializable {
    private String key;
    private String name;
    private String city;
    private String eliminated;
    private String photo;
    private String biograph;

    public ContestantModel(String key, String name, String eliminated, String photo) {
        this.key = key;
        this.name = name;
        this.eliminated = eliminated;
        this.photo = photo;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getEliminated() {
        return eliminated;
    }

    public String getPhoto() {
        return photo;
    }

}
