package application;

import administrator.Continent;
import administrator.ContinentDAO;
import administrator.Country;
import administrator.CountryDAO;

import java.sql.SQLException;
import java.util.stream.IntStream;

public class Main {
    private static void testContinentDAO() throws SQLException {
        ContinentDAO continentDAO = new ContinentDAO();
        var continents = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> "continent" + i)
                .toArray(String[]::new);

        for (var continent: continents)
            continentDAO.create(continent);

        Continent continent = continentDAO.findByName("continent0");
        System.out.println(continent);
        continent = continentDAO.findById(2);
        System.out.println(continent);
    }

    private static void testCountriesDAO() throws SQLException {
        CountryDAO countryDAO = new CountryDAO();
        var countries = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> "country" + i)
                .toArray(String[]::new);
        var codes =  IntStream.rangeClosed(0, 3)
                .mapToObj(i -> "code" + i)
                .toArray(String[]::new);

        for (int i = 0; i < countries.length; i++)
            countryDAO.create(countries[i], codes[i], 1);

        Country country = countryDAO.findByCode("code1");
        System.out.println(country);
        country = countryDAO.findById(2);
        System.out.println(country);
        country = countryDAO.findByName("country2");
        System.out.println(country);
    }

    public static void main(String[] args) {
        try {
            testContinentDAO();
            testCountriesDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
