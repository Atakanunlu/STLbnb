package com.atakanunlu.STLbnb.controller;

import com.atakanunlu.STLbnb.dto.HotelDto;
import com.atakanunlu.STLbnb.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDto> createNewHotel(@RequestBody HotelDto hotelDto) {
        log.info("Yeni otel oluşturuluyor. name: {}", hotelDto.getName());
        HotelDto createdHotel = hotelService.createNewHotel(hotelDto);
        log.info("Otel oluşturuldu. ID: {}, name: {}", createdHotel.getId(), createdHotel.getName());
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long hotelId) {
        log.debug("Otel çağırılıyor. ID: {}", hotelId);
        HotelDto hotelDto = hotelService.getHotelById(hotelId);
        log.debug("Otel bulundu. ID: {}", hotelId);
        return ResponseEntity.ok(hotelDto);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelDto> updateHotelById(@RequestBody HotelDto hotelDto,
                                                    @PathVariable Long hotelId){

        log.info("Otel çağırılıyor. ID: {}",hotelId);
        HotelDto hotelDto1 = hotelService.updateHotelById(hotelDto,hotelId);

        log.debug("Otel güncellendi. ID: {}",hotelId);
        return ResponseEntity.ok(hotelDto1);

    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotelById(@PathVariable Long hotelId){
        hotelService.deleteHotelById(hotelId);

        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/{hotelId}/active")
    public ResponseEntity<Void> activateHotel(@PathVariable Long hotelId){
        hotelService.activateHotel(hotelId);
        return ResponseEntity.noContent().build();
    }
}