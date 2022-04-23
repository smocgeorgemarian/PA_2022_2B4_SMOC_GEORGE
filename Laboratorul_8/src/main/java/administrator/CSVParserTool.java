package administrator;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;

public class CSVParserTool {
    private CSVParserTool() {}

    public static void parseFile(String file) throws IOException {
        File csvData = new File(file);
        CSVParser parser = CSVParser.parse(file, CSVFormat.DEFAULT);
        for (CSVRecord csvRecord : parser) {
            for (int index = 0; index < csvRecord.size(); index++)
                System.out.println(csvRecord.get(index));
        }

    }
}
