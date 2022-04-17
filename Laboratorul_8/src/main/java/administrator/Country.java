package administrator;

public class Country {
    private int id;
    private String name;
    private String code;
    private int continent;

    public Country(int id, String name, String code, int continent) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.continent = continent;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", continent=" + continent +
                '}';
    }
}
