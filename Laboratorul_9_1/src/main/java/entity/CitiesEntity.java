package entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "CITIES", schema = "STUDENT")
@NamedQueries({
        @NamedQuery(name = "CitiesEntities.findById",
                query = "select e from CitiesEntity e where e.id = :id"),
        @NamedQuery(name = "CitiesEntities.findByName",
                query = "select e from CitiesEntity e where e.name = :name")
        })

public class CitiesEntity implements AbstractEntity{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private BigInteger id;
    @Basic
    @Column(name = "COUNTRY")
    private String country;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "CAPITAL")
    private Boolean capital;
    @Basic
    @Column(name = "LATITUDE")
    private Double latitude;
    @Basic
    @Column(name = "LONGITUDE")
    private Double longitude;

    @Basic
    @Column(name = "POPULATION")
    private BigInteger population;

    public CitiesEntity() {}

    public CitiesEntity(BigInteger id, String name) {
        this.id = id;
        this.name = name;
    }

    public CitiesEntity(BigInteger id, String country, String name, Boolean capital, Double latitude, Double longitude, BigInteger population) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCapital() {
        return capital;
    }

    public void setCapital(Boolean capital) {
        this.capital = capital;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CitiesEntity that = (CitiesEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(country, that.country)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(capital, that.capital)) return false;
        if (!Objects.equals(latitude, that.latitude)) return false;
        return Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (capital != null ? capital.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CitiesEntity{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", capital=" + capital +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
