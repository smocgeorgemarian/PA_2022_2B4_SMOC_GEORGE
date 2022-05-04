package entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "CONTINENTS", schema = "STUDENT")
public class ContinentsEntity {
    @Id
    @Column(name = "ID")
    private BigInteger id;
    @Basic
    @Column(name = "NAME")
    private String name;

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
