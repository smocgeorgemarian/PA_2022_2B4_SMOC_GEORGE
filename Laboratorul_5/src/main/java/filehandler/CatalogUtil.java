package filehandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.InvalidCatalogException;
import model.*;

import java.awt.*;
import java.io.*;
import java.net.URI;

public class CatalogUtil {
    private static final String WEB_ADDR = "http";
    private CatalogUtil() {}
    public static void save (Catalog catalog, String path)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), catalog);
    }

    public static Catalog load(String path)
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

    public static void view (Item item) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        var location = item.getLocation();
        if (location.contains(WEB_ADDR))
            desktop.browse(URI.create(location));
        else
            desktop.open(new File(location));
    }
}

