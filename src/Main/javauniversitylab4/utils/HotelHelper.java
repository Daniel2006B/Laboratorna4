package Main.main.java.com.ltp.javauniversitylab4.utils;

import com.ltp.javauniversitylab4.dto.response.HotelResponseDto;
import com.ltp.javauniversitylab4.model.Hotel;
import java.util.List;

public class HotelHelper {
    public static HotelResponseDto convertToResponseDto(Hotel hotel){
        final Long hotelId = hotel.getHotelId();
        final String hotelName = hotel.getHotelName();
        final List<HotelAmenitie> hotelAmenities = hotel.getHotelAmenities();
        return  new HotelResponseDto(hotelId,hotelName,hotelAmenities);
    }
}
