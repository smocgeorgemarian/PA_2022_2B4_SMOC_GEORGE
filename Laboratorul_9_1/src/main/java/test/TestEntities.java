package test;

import entity.CitiesEntity;
import entity.ContinentsEntity;
import entity.CountriesEntity;
import manager.CitiesRepository;
import manager.ContinentsRepository;
import manager.CountriesRepository;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.stream.IntStream;

public class TestEntities {
    private static final String CONTINENT = "continent";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String DELTA_TIME = "Delta time: ";
    private static final int NO_GENERATED = 1000;

    private TestEntities() {}

    public static void testContinentsEntity(EntityManager em) {
        var continentsEntities = IntStream.rangeClosed(0, NO_GENERATED)
                .mapToObj(i -> new ContinentsEntity(BigInteger.valueOf(i), CONTINENT + i))
                .toArray(ContinentsEntity[]::new);

        ContinentsRepository continentsRepository = new ContinentsRepository(em);
        long timeStart = System.nanoTime();
        // bulk insert
        for (var continentsEntity: continentsEntities)
            continentsRepository.persist(continentsEntity);
        // single find
        continentsRepository.begin();
        ContinentsEntity c = continentsRepository.findById(BigInteger.valueOf(0));
        c.setName("Africa");
        continentsRepository.commit();

        long timeEnd = System.nanoTime();
        TestTimeLogger.setDataPrintableIntoFile(DELTA_TIME + (timeEnd - timeStart));
    }

    public static void testCountriesEntity(EntityManager em) {
        var countriesEntities = IntStream.rangeClosed(0, NO_GENERATED)
                .mapToObj(i -> new CountriesEntity(BigInteger.valueOf(i), COUNTRY + i, "code", CONTINENT + i))
                .toArray(CountriesEntity[]::new);

        CountriesRepository countriesRepository = new CountriesRepository(em);
        long timeStart = System.nanoTime();
        // bulk insert
        for (var countriesEntity: countriesEntities)
            countriesRepository.persist(countriesEntity);
        // single find
        countriesRepository.begin();
        CountriesEntity c = countriesRepository.findByName("country100").get(0);
        c.setName("Romania");
        countriesRepository.commit();

        long timeEnd = System.nanoTime();
        TestTimeLogger.setDataPrintableIntoFile(DELTA_TIME + (timeEnd - timeStart));
    }

    public static void testCitiesEntity(EntityManager em) {
        var citiesEntities = IntStream.rangeClosed(0, NO_GENERATED)
                .mapToObj(i -> new CitiesEntity(BigInteger.valueOf(i), COUNTRY + i, CITY + i, Boolean.TRUE, (double) (i % 60), (double) (i % 60), BigInteger.valueOf(i * 10L)))
                .toArray(CitiesEntity[]::new);

        CitiesRepository citiesRepository = new CitiesRepository(em);
        long timeStart = System.nanoTime();
        // bulk isert
        for (var citiesEntity: citiesEntities)
            citiesRepository.persist(citiesEntity);
        // single find
        CitiesEntity c = citiesRepository.findByName("city0").get(0);
        citiesRepository.begin();
        c.setName("Iasi");
        citiesRepository.commit();

        long timeEnd = System.nanoTime();
        TestTimeLogger.setDataPrintableIntoFile(DELTA_TIME + (timeEnd - timeStart));
    }
}
