package com.project.hotel.web;

import com.project.hotel.service.SearchRoomService;
import com.project.hotel.web.dto.ReservationFormDto;
import com.project.hotel.web.dto.RoomTypeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final SearchRoomService searchRoomService;

    @GetMapping("/customer/reserveRoom")
    public String search(@ModelAttribute("reservationFormDto") ReservationFormDto reservationFormDto,
                                                                    @RequestParam(value = "guests", required = false) Integer guests,
                                                                    @RequestParam(value = "checkIn", required = false) String checkIn,
                                                                    @RequestParam(value = "checkOut", required = false) String checkOut,
                                                                    Model model) {

        if (guests != null && checkIn != null && checkOut != null) {
            ArrayList<RoomTypeDTO> rooms = searchRoomService.findRooms(checkIn, checkOut, reservationFormDto.getGuests());
            model.addAttribute("rooms", rooms);
            model.addAttribute("period", searchRoomService.getPeriod(checkIn, checkOut).getDays());
        }
        return "/customer/searchRoom";
    }

}
