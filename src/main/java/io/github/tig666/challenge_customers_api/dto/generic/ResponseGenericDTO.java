package io.github.tig666.challenge_customers_api.dto.generic;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseGenericDTO {
    private StatusDTO status;
    private Object responseData;

    public ResponseGenericDTO(int statusCode, String message) {
        this.status = new StatusDTO(statusCode, message);
    }
}
