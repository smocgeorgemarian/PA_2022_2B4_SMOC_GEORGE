package entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CONTINENTS", schema = "STUDENT")
@NamedQueries({
        @NamedQuery(name = "ContinentsEntity.findById",
                query = "select e from ContinentsEntity e where e.id = :id"),
        @NamedQuery(name = "ContinentsEntity.findByName",
                query = "select e from ContinentsEntity e where e.name = :name")
})

public class ContinentsEntity implements AbstractEntity {
    @Id
    @Column(name = "ID")
//    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private BigInteger id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @OneToMany(targetEntity = CountriesEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name="CONTINENT", referencedColumnName = "NAME")
    private List<ContinentsEntity> continentsEntities;

    public ContinentsEntity(BigInteger id, String name) {this.id = id;this.name = name;}

    public ContinentsEntity(String name) {this.name = name;}

    public ContinentsEntity() {}

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContinentsEntity that = (ContinentsEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContinentsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
