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
    private char stadiumSector;
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

    public Ticket(String id, String concertHall, short eventCode, boolean isPromo, char stadiumSector,
                  float maxBackpackWeight,BigDecimal price) {
        this.id = id;
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        this.price = price;

        setCreationTime();
    }

    private void setCreationTime() {
        this.unixTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public char getStadiumSector() {
        return stadiumSector;
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
}
