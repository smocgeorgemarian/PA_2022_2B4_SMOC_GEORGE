package entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "COUNTRIES", schema = "STUDENT")
@NamedQueries({
        @NamedQuery(name = "CountriesEntities.findById",
                query = "select e from CountriesEntity e where e.id = :id"),
        @NamedQuery(name = "CountriesEntities.findByName",
                query = "select e from CountriesEntity e where e.name = :name")
})
public class CountriesEntity implements AbstractEntity {
    @Id
    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Basic
    private BigInteger id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "CODE")
    private String code;
    @Basic
    @Column(name = "CONTINENT")
    private String continent;
    @OneToMany(targetEntity = CitiesEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name="COUNTRY", referencedColumnName = "NAME")
    private List<CitiesEntity> citiesEntityList;

    public CountriesEntity() {
    }

    public CountriesEntity(BigInteger id, String name) {
        this.id = id;
        this.name = name;
    }

    public CountriesEntity(BigInteger id, String name, String code, String continent) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.continent = continent;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountriesEntity that = (CountriesEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(code, that.code)) return false;
        return Objects.equals(continent, that.continent);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (continent != null ? continent.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CountriesEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", continent='" + continent + '\'' +
                '}';
    }
}
