package com.work.bookingengine.controller;

import com.work.bookingengine.model.BookedRoom;
import com.work.bookingengine.model.Room;
import com.work.bookingengine.response.RoomResponse;
import com.work.bookingengine.service.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final IRoomService roomService;

    @PostMapping("/add/new-room")
    public ResponseEntity<RoomResponse> addNewRoom(@RequestParam("photo") MultipartFile photo,
                                                   @RequestParam("roomType") String roomType,
                                                   @RequestParam("roomPrice") BigDecimal roomPrice) throws SQLException, IOException {

        Room saveRoom = roomService.addNewRoom(photo, roomType, roomPrice);
        RoomResponse response = new RoomResponse(
                saveRoom.getId(),
                saveRoom.getRoomType(),
                saveRoom.getRoomPrice());
        return ResponseEntity.ok(response);
    }

    @GetMapping("room/types")
    public List<String> getRoomType() {
        return roomService.getAllRoomTypes();
    }
    public ResponseEntity<List<RoomResponse>> getAllRooms() throws SQLException {
        List<Room> rooms = roomService.getAllRooms();
        List<RoomResponse> roomResponses = new ArrayList<>();
        for (Room room : rooms){
            byte[] photoByte = roomService.getRoomPhotoByRoomId(room.getId());
            if (photoByte != null && photoByte.length >0){
                String base64Photo = Base64.getEncoder().encodeToString(photoByte);
                RoomResponse roomResponse = getRoomResponse(room);
                roomResponse.setPhoto(base64Photo);
                roomResponses.add(roomResponse);
            }
        }
        return ResponseEntity.ok(roomResponses);
    }

    private RoomResponse getRoomResponse(Room room) {
        List<BookedRoom> bookedRoomList = getAllBookingsByRoomId(room.getId());
    }

    private List<BookedRoom> getAllBookingsByRoomId(Long id) {

    }
}
