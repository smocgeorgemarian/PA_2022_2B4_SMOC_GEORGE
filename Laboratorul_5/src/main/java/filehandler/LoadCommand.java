package filehandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Catalog;

import java.io.File;
import java.io.IOException;

public class LoadCommand implements Command{
    private LoadCommand() {}

    public static Catalog execute (String path)
            throws InvalidCatalogException {
        ObjectMapper objectMapper = new ObjectMapper();
        Catalog catalog;
        try {
            catalog = objectMapper.readValue(new File(path), Catalog.class);
        }
        catch (IOException e){
            throw new InvalidCatalogException(e);
        }
        return catalog;
    }
}
