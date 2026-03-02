package io.github.tig666.challenge_customers_api.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    @NotNull(message = "El numero de identificación del cliente es requerido.")
    private Long documentNumber;
    @NotBlank(message = "El nombre del cliente es requerido.")
    private String name;
    @Email(message = "El formato del correo es inválido.")
    @NotBlank(message = "El correo del cliente es requerido.")
    private String email;
}
