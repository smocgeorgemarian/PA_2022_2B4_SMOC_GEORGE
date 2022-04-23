package application;

import administrator.CSVParserTool;
import model.Continent;
import administrator.ContinentDAO;
import model.Country;
import administrator.CountryDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.IntStream;

public class Main {
    private static final String CONTINENT = "continent";
    private static final String CODE = "code";
    private static final String COUNTRY = "country";


    private static void testContinentDAO() throws SQLException {
        ContinentDAO continentDAO = new ContinentDAO();
        var continents = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> CONTINENT + i)
                .toArray(String[]::new);

        for (var continent: continents)
            continentDAO.create(continent);

        Continent continent = continentDAO.findByName(CONTINENT + "0");
        System.out.println(continent);
        continent = continentDAO.findById(2);
        System.out.println(continent);
    }

    private static void testCountriesDAO() throws SQLException {
        CountryDAO countryDAO = new CountryDAO();
        var countries = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> COUNTRY + i)
                .toArray(String[]::new);
        var codes =  IntStream.rangeClosed(0, 3)
                .mapToObj(i -> CODE + i)
                .toArray(String[]::new);

        for (int i = 0; i < countries.length; i++)
            countryDAO.create(countries[i], codes[i], CONTINENT + i);

        Country country = countryDAO.findByCode(CODE + "1");
        System.out.println(country);
        country = countryDAO.findById(2);
        System.out.println(country);
        country = countryDAO.findByName(COUNTRY + "2");
        System.out.println(country);
    }

    private static void testParserTool() throws IOException {
        CSVParserTool.parseFile("concap.csv");
    }

    public static void main(String[] args) {
        try {
            testContinentDAO();
            testCountriesDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            testParserTool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
