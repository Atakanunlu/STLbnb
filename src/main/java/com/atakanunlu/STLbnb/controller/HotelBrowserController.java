package com.atakanunlu.STLbnb.controller;

import com.atakanunlu.STLbnb.dto.HotelDto;
import com.atakanunlu.STLbnb.dto.HotelInfoDto;
import com.atakanunlu.STLbnb.dto.HotelSearchRequest;
import com.atakanunlu.STLbnb.service.HotelService;
import com.atakanunlu.STLbnb.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowserController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelDto>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest){

        Page<HotelDto> page= inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);

    }

    @GetMapping("{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId){

        HotelInfoDto infoDto = hotelService.getHotelInfoById(hotelId);
        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
    }

}
