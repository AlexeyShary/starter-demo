package t1.openschool.sampleservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto for creating new user")
public class UserNewDto {
    @NotBlank(message = "Name must be not blank")
    private String name;
    @Positive(message = "Age must be positive")
    private Integer age;
}
