package todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {
    String message = "Unauthorized";
    boolean unauthorized = true;
}
