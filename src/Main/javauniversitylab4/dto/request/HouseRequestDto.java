package Main.main.java.com.ltp.javauniversitylab4.dto.request;

import Main.main.java.com.ltp.javauniversitylab4.utils.HouseCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseRequestDto {
    private Long hotelId;
    private HouseCondition houseCondition;
    private int personInHouse;
}
