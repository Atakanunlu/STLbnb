package com.atakanunlu.STLbnb.service.Impl;

import com.atakanunlu.STLbnb.dto.RoomDto;
import com.atakanunlu.STLbnb.entity.Hotel;
import com.atakanunlu.STLbnb.entity.Room;
import com.atakanunlu.STLbnb.exception.ResourceNotFoundException;
import com.atakanunlu.STLbnb.repository.HotelRepository;
import com.atakanunlu.STLbnb.repository.RoomRepository;
import com.atakanunlu.STLbnb.service.InventoryService;
import com.atakanunlu.STLbnb.service.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;

    @Override
    public RoomDto createNewRoom(Long hotelId,RoomDto roomDto) {
        log.info("Otelin ID sine göre yeni oda oluşturuluyor. ID: {}",hotelId);

        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel bulunmadı. ID: "+hotelId));

        Room room = modelMapper.map(roomDto,Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);

        //aktifse bu if bloguna gir
        if (hotel.getActive()){
            inventoryService.initializeRoomForAYear(room);
        }

        return modelMapper.map(room,RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {

        log.info("Tüm odaları otelin ID sine göre getir. ID: {}",hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel bulunamadı. ID: " +hotelId));

        return hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDto.class))
                .collect(Collectors.toList());


    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Odayı ID sine göre getir. ID: {}",roomId);

        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Oda bulunamadı. ID: " + roomId));
        return modelMapper.map(room,RoomDto.class);

    }

    @Transactional
    @Override
    public void deleteRoomById(Long roomId) {
        log.info("ID sine göre oda siliniyor. ID: {}",roomId);

        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Oda bulunamadı. ID: " + roomId));

        inventoryService.deleteAllInventories(room);

        roomRepository.deleteById(roomId);

    }
}
