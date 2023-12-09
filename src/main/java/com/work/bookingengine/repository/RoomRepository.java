package com.work.bookingengine.repository;

import com.work.bookingengine.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
