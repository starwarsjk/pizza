package pl.tu.kielce.pizza.common.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private String city;
    private String street;
    private Integer houseNumber;
    private Integer flatNumber;

//    private String name;
//    private String lastName;
//    private String phoneNumber;
//    private String email;
//
//    private String note;
}
