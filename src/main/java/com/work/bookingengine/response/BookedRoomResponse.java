package com.work.bookingengine.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoomResponse {
    private Long bookingId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String guestName;
    private String guestEmail;
    private int NumberOfAdults;
    private int NumberOfChilren;
    private int totalNumberOfGuest;
    private String bookingConfirmationCode;
    private RoomResponse room;

    public BookedRoomResponse(Long bookingId, LocalDate checkInDate,
                              LocalDate checkOutDate, String bookingConfirmationCode) {
        this.bookingId = bookingId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
