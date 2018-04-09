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
    private String photo;
    private String biograph;
    private String eliminated;
    private String smsMessage;
    private String smsNumber;

    public ContestantModel(String key, String name, String city, String photo, String biograph, String eliminated, String sms_message, String sms_number) {
        this.key = key;
        this.name = name;
        this.city = city;
        this.eliminated = eliminated;
        this.photo = photo;
        this.biograph = biograph;
        this.smsMessage = sms_message;
        this.smsNumber = sms_number;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getEliminated() {
        return eliminated;
    }

    public String getPhoto() {
        return photo;
    }

    public String getBiograph() {
        return biograph;
    }

    public String getSmsMessage() {
        return smsMessage;
    }

    public String getSmsNumber() {
        return smsNumber;
    }
}
