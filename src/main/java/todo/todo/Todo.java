package todo.todo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class Todo {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Integer id;

        private String content;

        private Date creation;

        private Date close;

        public Integer getId() {
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
        }
}
