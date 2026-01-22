package com.atakanunlu.STLbnb.controller;

import com.atakanunlu.STLbnb.dto.RoomDto;
import com.atakanunlu.STLbnb.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomAdminController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDto> createNewRoom(@RequestBody RoomDto roomDto,
                                                 @PathVariable Long hotelId){

        RoomDto room = roomService.createNewRoom(hotelId,roomDto);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRoomsInHotel(@PathVariable Long hotelId){

        List<RoomDto> roomDtos = roomService.getAllRoomsInHotel(hotelId);
        return ResponseEntity.ok(roomDtos);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long hotelId,
                                               @PathVariable Long roomId){

        return ResponseEntity.ok(roomService.getRoomById(roomId));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> getDeleteById(@PathVariable Long roomId){

        roomService.deleteRoomById(roomId);
        return ResponseEntity.noContent().build();
    }
}
