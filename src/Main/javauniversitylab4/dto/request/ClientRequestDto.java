package Main.main.java.com.ltp.javauniversitylab4.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDto {
    private String name;
    private double balance;
}
