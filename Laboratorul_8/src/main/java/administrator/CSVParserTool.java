package administrator;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class CSVParserTool {
    private CSVParserTool() {}

    public static void parseFileIntoDatabase(String file) throws IOException, SQLException {
        File csvData = new File(file);
        CityDAO cityDAO = new CityDAO();
        CSVParser parser = CSVParser.parse(csvData, StandardCharsets.US_ASCII, CSVFormat.EXCEL.withHeader());
        for (CSVRecord csvRecord : parser) {
            String country = csvRecord.get(0);
            String name = csvRecord.get(1);
            double latitude = Double.parseDouble(csvRecord.get(2));
            double longitude = Double.parseDouble(csvRecord.get(3));
            cityDAO.create(country, name, true, latitude, longitude);
        }
    }
}
