package Main.main.java.com.ltp.javauniversitylab4.controller;

import com.ltp.javauniversitylab4.dto.request.HotelRequestDto;
import com.ltp.javauniversitylab4.dto.response.HotelResponseDto;
import com.ltp.javauniversitylab4.model.Hotel;
import com.ltp.javauniversitylab4.model.Reserve;
import com.ltp.javauniversitylab4.service.HotelService;
import com.ltp.javauniversitylab4.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Month;
import java.util.List;

@RestController
public class HotelController {
    @Autowired
    private final HotelService hotelService;

    public HotelController(final HotelService hotelService) {
        this.hotelService = hotelService;
    }


    @PostMapping("/hotel")
    public ResponseEntity<String> addHotel(@RequestBody final HotelRequestDto hotelRequestDto) {
        hotelService.addOrUpdateHotel(hotelRequestDto);
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }

    @GetMapping("hotel/{id}")
    public ResponseEntity<HotelResponseDto> findHotelById(@PathVariable final Long id) {
        final Hotel hotel = hotelService.findHotelById(id);
        final HotelResponseDto hotelResponseDto = HotelHelper.convertToResponseDto(hotel);
        if (hotel.getHotelId() != -1) {
            return new ResponseEntity<>(hotelResponseDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/hotel")
    public ResponseEntity<String> updateHotel(@RequestBody final HotelRequestDto hotelRequestDto) {
        hotelService.addOrUpdateHotel(hotelRequestDto);
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("hotel/{id}")
    public ResponseEntity<String> deleteHotelById(@PathVariable final Long id) {
        hotelService.deleteHotelById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/hotels")
    public ResponseEntity<List<HotelResponseDto>> findAllHotels() {
        final List<Hotel> hotels = hotelService.findAllHotels();

        final List<HotelResponseDto> hotelResponseDtos = hotels.stream()
                .map(HotelHelper::convertToResponseDto)
                .toList();

        if (!hotels.isEmpty()) {
            return new ResponseEntity<>(hotelResponseDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/hotel/income")
    public ResponseEntity<Double> printIncome() {
        final double income = hotelService.findALlHouses().stream()
                .mapToDouble(
                        house -> {
                            final Reserve reserve = hotelService.findReserveByHouse(house);
                            if (reserve == null) {
                                return 0;
                            }
                            final Month reservationMonth = reserve.getDate().getMonth();
                            return HouseHelper.priceOfHouse(house, reservationMonth);
                        }
                )
                .sum();
        return new ResponseEntity<>(income, HttpStatus.OK);
    }

    @GetMapping("/hotel/expense")
    public ResponseEntity<Double> printExpense() {
        final double expense = hotelService.findALlHouses().stream()
                .mapToDouble(
                        house -> {
                            final Reserve reserve = hotelService.findReserveByHouse(house);
                            if (reserve == null) {
                                return 0;
                            }
                            final Month reservationMonth = reserve.getDate().getMonth();

                            return HouseHelper.expenseOfHouse(house, reservationMonth);

                        }
                )
                .sum();
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @GetMapping("/hotel/amenities")
    public ResponseEntity<List<HotelAmenitie>> printHotelAmenities() {
        final List<HotelAmenitie> hotelAmenities = HotelAmenitie.getAmenities();
        return new ResponseEntity<>(hotelAmenities, HttpStatus.OK);
    }


}
