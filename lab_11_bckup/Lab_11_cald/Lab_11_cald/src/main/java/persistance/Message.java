package persistance;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGES")
@NamedQueries({
        @NamedQuery(name = "Message.findById",
                query = "select e from Message e where e.idPerson = :id and e.isRead = false")
})
public class Message implements AbstractEntity{
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_msg"
    )
    @SequenceGenerator(
            name = "seq_msg",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "ID_PERSON", nullable = false)
    private Long idPerson;

    @Column(name = "MESSAGE", length = 200)
    private String message;

    @Column(name = "IS_READ")
    private Boolean isRead;

    public Message(Long idPerson, String message) {
        this.idPerson = idPerson;
        this.message = message;
        this.isRead = false;
    }

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

}