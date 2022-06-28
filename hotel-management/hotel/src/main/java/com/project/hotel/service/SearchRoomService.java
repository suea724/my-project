package com.project.hotel.service;

import com.project.hotel.domain.customer.TypeRepository;
import com.project.hotel.web.dto.RoomTypeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class SearchRoomService {

    private final TypeRepository typeRepository;

    public ArrayList<RoomTypeDTO> findRooms(String checkIn, String checkOut, Integer guests) {

        ArrayList<RoomTypeDTO> rooms = typeRepository.findRooms(Integer.toString(guests));

        Period period = getPeriod(checkIn, checkOut);

        for (RoomTypeDTO room : rooms) {
            room.setRate(room.getRate() * period.getDays());
        }

        return rooms;
    }

    public Period getPeriod(String checkIn, String checkOut) {
        LocalDate checkInDate = LocalDate.parse(checkIn, DateTimeFormatter.ISO_DATE);
        LocalDate checkOutDate = LocalDate.parse(checkOut, DateTimeFormatter.ISO_DATE);

        Period period = Period.between(checkInDate, checkOutDate);
        return period;
    }


}
