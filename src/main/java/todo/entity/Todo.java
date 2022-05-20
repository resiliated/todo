package todo.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

        private String content;

        private Date creation;

        private Date close;

        @Enumerated(EnumType.STRING)
        private State state;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "type_id", referencedColumnName = "id")
        private Type type;

        /*public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Date getCreation() {
            return creation;
        }

        public void setCreation(Date creation) {
            this.creation = creation;
        }

        public Date getClose() {
            return close;
        }

        public void setClose(Date close) {
            this.close = close;
        }*/
}
