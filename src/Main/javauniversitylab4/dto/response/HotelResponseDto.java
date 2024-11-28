package Main.main.java.com.ltp.javauniversitylab4.dto.response;

import Main.main.java.com.ltp.javauniversitylab4.utils.HotelAmenitie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponseDto {
    private Long hotelId;
    private String hotelName;
    private List<HotelAmenitie> hotelAmenities = new ArrayList<>(HotelAmenitie.getAmenities());
}
