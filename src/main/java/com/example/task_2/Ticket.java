package com.example.task_2;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

//There was nothing mentioned about getters + setters, so they are not present
public class Ticket {

    private String id;
    private String concertHall;
    private short eventCode;
    private long unixTime;
    private boolean isPromo;
    private String stadiumSector;
    private float maxBackpackWeight;
    private BigDecimal price;

    public Ticket() {
        setCreationTime();
    }

    public Ticket(String concertHall, short eventCode) { //as long as there is an automatic time detection, `time` param is omitted in this constructor
        this.concertHall = concertHall;
        this.eventCode = eventCode;

        setCreationTime();
    }

    public Ticket(String id, String concertHall, short eventCode, boolean isPromo, String stadiumSector,
                  float maxBackpackWeight,BigDecimal price) {
        validateInputParams(id, concertHall, eventCode, stadiumSector); //validate basic task params
        this.id = id;
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        this.price = price;

        setCreationTime();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", unixTime=" + unixTime +
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", maxBackpackWeight=" + maxBackpackWeight +
                ", price=" + price +
                '}';
    }

    private void setCreationTime() {
        this.unixTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    private void validateInputParams(String id, String concertHall, short eventCode, String stadiumSector) {
        if (id.length() > 4)
            throw new IllegalArgumentException("Id param should contain less than 4 symbols/chars");
        if (concertHall.length() > 10)
            throw new IllegalArgumentException("Concert hall name should contain 10 chars maximum");
        if (eventCode > 999)
            throw new IllegalArgumentException("Even conde should contain only 3 digits");
        if (!stadiumSector.matches("[A-C]"))
            throw new IllegalArgumentException("Stadium sector name should be named from 'A' to 'C'");
    }
}
