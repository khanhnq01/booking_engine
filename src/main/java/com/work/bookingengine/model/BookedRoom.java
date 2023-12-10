package com.work.bookingengine.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @Column(name = "check_in_date")
    private LocalDate checkInDate;
    @Column(name = "check_out_date")
    private LocalDate checkOutDate;
    @Column(name = "guest_name")
    private String guestName;
    @Column(name = "guest_email")
    private String guestEmail;
    @Column(name = "adults")
    private int NumberOfAdults;
    @Column(name = "children")
    private int NumberOfChildren;
    @Column(name = "total_guest")
    private int totalNumberOfGuest;
    @Setter
    @Column(name = "confirmation_code")
    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public void calculateTotalNumberOfGuest() {
        this.totalNumberOfGuest = this.NumberOfAdults + this.NumberOfChildren;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        NumberOfAdults = numberOfAdults;
        calculateTotalNumberOfGuest();
    }

    public void setNumberOfChildren(int numberOfChildren) {
        NumberOfChildren = numberOfChildren;
        calculateTotalNumberOfGuest();
    }

}
