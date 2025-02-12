package com.knight.SchoolManagement.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_monthly_fee")
public class MonthlyFee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "fee_id")
    private UUID id;
    private Double value;
    private LocalDate issuance;
    private LocalDate expiresOn;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private final Double lateFee = 10.0;

    public MonthlyFee(){}

    //Constructor for generate test objects
    public MonthlyFee(Double value, LocalDate issuance, User user) {
        this.value = value;
        this.issuance = issuance;
        this.user = user;
        expiresOn = issuance.plusDays(7);
    }
    //Constructor that is used in other cases
    @JsonCreator
    public MonthlyFee(Double value/*, User user*/) {
        //this.id = id;
        this.value = value;
        issuance = LocalDate.now();
        //this.user = user;
        expiresOn = issuance.plusDays(7);
    }

    public Double valueToPay(){
        Double valueToPay = value;
        if(LocalDate.now().isAfter(expiresOn)){
            valueToPay = valueToPay + (lateFee * ChronoUnit.DAYS.between(expiresOn, LocalDate.now()));
        }
        return valueToPay;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }

    public Double getLateFee() {
        return lateFee;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDate getIssuance() {
        return issuance;
    }

    public void setIssuance(LocalDate issuance) {
        this.issuance = issuance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyFee that = (MonthlyFee) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
