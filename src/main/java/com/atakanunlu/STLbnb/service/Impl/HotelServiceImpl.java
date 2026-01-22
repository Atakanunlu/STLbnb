package com.atakanunlu.STLbnb.service.Impl;

import com.atakanunlu.STLbnb.dto.HotelDto;
import com.atakanunlu.STLbnb.entity.Hotel;
import com.atakanunlu.STLbnb.entity.Room;
import com.atakanunlu.STLbnb.exception.ResourceNotFoundException;
import com.atakanunlu.STLbnb.repository.HotelRepository;
import com.atakanunlu.STLbnb.service.HotelService;
import com.atakanunlu.STLbnb.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("İsme göre hotel oluşturuluyor. name: {}", hotelDto.getName());

        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);
        Hotel savedEntity = hotelRepository.save(hotel);

        log.info("Otel oluşturuldu. ID: {}, Name: {}", savedEntity.getId(), savedEntity.getName());

        return modelMapper.map(savedEntity, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("ID'sine göre otel çağırılıyor. ID: {}", id);

        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("ID'ye göre otel bulunamadı. ID: {}", id);
                    return new ResourceNotFoundException("ID'ye göre otel bulunamadı. ID: " + id);
                });

        log.info("Çağırılan otelin ID'si: {}, Name: {}", hotel.getId(), hotel.getName());
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(HotelDto hotelDto, Long hotelId) {
        log.info("ID ye göre otel güncelleniyor. ID: {}",hotelId);

        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("ID ye gçre otel bulunamadı. ID: "+ hotelId));

        modelMapper.map(hotelDto,hotel);
        hotel.setId(hotelId);
        hotel = hotelRepository.save(hotel);

        return modelMapper.map(hotel, HotelDto.class);


    }

    @Override
    @Transactional
    public void deleteHotelById(Long id) {

        Hotel hotel = hotelRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Hotel bulunamadı. ID: "+id));

        hotelRepository.deleteById(id);
        for (Room room: hotel.getRooms()){
            inventoryService.deleteFutureInventories(room);
        }

    }

    @Override
    @Transactional
    public void activateHotel(Long hotelId) {

        log.info("ID'sine göre otel aktif ediliyor ID: {}", hotelId);

        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> {
                    log.error("ID'ye göre otel bulunamadı. ID: {}",hotelId);
                    return new ResourceNotFoundException("ID'ye göre otel bulunamadı. ID: " + hotelId);
                });
        hotel.setActive(true);

        // bi kere çalışsın
        for (Room room: hotel.getRooms()){
            inventoryService.initializeRoomForAYear(room);
        }

    }


}