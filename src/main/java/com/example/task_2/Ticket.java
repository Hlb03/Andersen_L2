package com.example.task_2;

import com.example.task_2.validation_annotation.NullableWarning;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class Ticket extends Identifier implements Printer {

    @NullableWarning
    private String concertHall;
    private short eventCode;
    private long unixTime;
    private boolean isPromo;
    private char stadiumSector;
    private float maxBackpackWeight;
    private BigDecimal price;

    public Ticket() {
        super(null);
        setCreationTime();
        processNullableWarningAnnotation();
    }

    public Ticket(String concertHall, short eventCode) { //as long as there is an automatic time detection, `time` param is omitted in this constructor
        super(null);
        this.concertHall = concertHall;
        this.eventCode = eventCode;

        setCreationTime();
        processNullableWarningAnnotation();
    }

    public Ticket(String id, String concertHall, short eventCode, boolean isPromo, char stadiumSector,
                  float maxBackpackWeight, BigDecimal price) {
        super(id);
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        this.price = price;

        setCreationTime();
        processNullableWarningAnnotation();
    }

    public void setUnixTime(long unixTime) { //possibility to change time
        this.unixTime = unixTime;
    }

    public void setStadiumSector(char stadiumSector) { // method to change stadium sector
        this.stadiumSector = stadiumSector;
    }

    public String getConcertHall() {
        return concertHall;
    }

    public short getEventCode() {
        return eventCode;
    }

    public long getUnixTime() {
        return unixTime;
    }

    public boolean isPromo() {
        return isPromo;
    }

    public char getStadiumSector() {
        return stadiumSector;
    }

    public float getMaxBackpackWeight() {
        return maxBackpackWeight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void shared(String phoneNumber) {
        System.out.printf("Shared by phone number %s", phoneNumber);
    }

    public void shared(String phoneNumber, String email) {
        System.out.printf("Shared by phone number %s and by email %s as well", phoneNumber, email);
    }

    @Override
    public void print() { // could be omitted, then default realization will be available
        System.out.println("Overrided implementation of print() method for Ticket class");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(super.getId(), ticket.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId());
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + super.getId() + '\'' +
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

    private void processNullableWarningAnnotation() {
        try {
            for (Field field : this.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(NullableWarning.class) && field.get(this) == null) {
                    System.out.println("Variable " + field.getName() + " is null in " + this.getClass());
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e); //could be added more informative logs
        }
    }
}
