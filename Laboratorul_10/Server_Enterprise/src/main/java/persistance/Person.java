package persistance;

import javax.persistence.*;

@Entity
@Table(name = "PERSONS")
@NamedQueries({
        @NamedQuery(name = "Person.findById",
                query = "select e from Person e where e.id = :id"),
        @NamedQuery(name = "Person.findByName",
                query = "select e from Person e where e.name = :name"),
        @NamedQuery(name = "Person.findCount",
                query = "select COUNT(e) from Person e")
})
public class Person implements AbstractEntity{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_post"
    )
    @SequenceGenerator(
                name = "seq_post"
    )
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "LOG")
    private Boolean log;

    public Person() {}

    public Person(String name, Boolean log) {
        this.name = name;
        this.log = log;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getLog() {
        return log;
    }

    public void setLog(Boolean log) {
        this.log = log;
    }

}