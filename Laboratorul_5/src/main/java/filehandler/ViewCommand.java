package filehandler;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import exceptions.ViewCustomException;
import model.Item;
import exceptions.*;

public class ViewCommand implements Command {
    private static final String WEB_ADDR = "http";

    private ViewCommand() {}

    public static void execute(Item item)
            throws ViewCustomException {
        Desktop desktop = Desktop.getDesktop();
        var location = item.getLocation();
        try {
            if (location.contains(WEB_ADDR))
                desktop.browse(URI.create(location));
            else
                desktop.open(new File(location));
        } catch (IOException e) {
            throw new ViewCustomException(e);
        }
    }
}
