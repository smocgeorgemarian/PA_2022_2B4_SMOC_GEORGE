package application;

import administrator.*;
import graphic.MainFrame;
import model.City;
import model.Continent;
import model.Country;

import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.IntStream;

public class Main {
    private static final String CONTINENT = "continent";
    private static final String CODE = "code";
    private static final String COUNTRY = "country";

    private static final int MAX_CITIES = 245;

    private static void testContinentDAO() throws SQLException {
        ContinentDAO continentDAO = new ContinentDAO();
        var continents = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> CONTINENT + i)
                .toArray(String[]::new);

        for (var continent: continents)
            continentDAO.create(continent);
        Database.getConnection().commit();
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
        Database.getConnection().commit();

        Country country = countryDAO.findByCode(CODE + "1");
        System.out.println(country);
        country = countryDAO.findById(2);
        System.out.println(country);
        country = countryDAO.findByName(COUNTRY + "2");
        System.out.println(country);
    }

    private static void testParserTool() throws IOException {
        try {
            CSVParserTool.parseFileIntoDatabase("concap.csv");
            Database.getConnection().commit();

            CityDAO cityDAO = new CityDAO();
            City firstCity = cityDAO.findById(1);
            City secondCity = cityDAO.findByName("Funafuti");
            System.out.println("First city: " + firstCity + "\nSecond city: " + secondCity);
            System.out.println("Distance between these cities: "
                    + DistanceCalculator.computeDistance(firstCity, secondCity) + "km");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testGraphicInterface() {
        CityDAO cityDAO = new CityDAO();
        var cityList = IntStream.range(1, MAX_CITIES)
                .mapToObj(id -> {
                    try {
                        return cityDAO.findById(id);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).toList();

        new MainFrame(1000, 1600, cityList).setVisible(true);
    }

    public static void main(String[] args) {
        try {
            testContinentDAO();
            testCountriesDAO();
            testParserTool();
            testGraphicInterface();
            Database.closeConnection();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
