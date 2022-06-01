package todo.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
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

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "type_id", referencedColumnName = "id")
        private Type type;
}
