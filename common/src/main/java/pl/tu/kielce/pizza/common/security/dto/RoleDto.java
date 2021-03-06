package pl.tu.kielce.pizza.common.security.dto;


import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Long id;

    private String role;

    private boolean selected;
}
