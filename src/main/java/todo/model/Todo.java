package todo.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Integer id;
        private String title;
        private String content;

        @CreatedDate
        private Date creation = new Date();
        private Date close;

        @Enumerated(EnumType.STRING)
        private State state = State.TODO;
        private Integer typeId;
        private Integer userId;
        private boolean deleted = false;
}
