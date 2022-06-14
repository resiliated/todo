package todo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Type {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String entitled;

    public Type(String entitled){
        this.entitled = entitled;
    }
}
