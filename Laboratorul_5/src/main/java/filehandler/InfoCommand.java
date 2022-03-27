package filehandler;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InfoCommand implements Command {
    private InfoCommand() {}

    public static void execute(String filename) {
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ParseContext context = new ParseContext();

        try {
            parser.parse(inputstream, handler, metadata, context);
        } catch (IOException | SAXException | TikaException e ) {
            e.printStackTrace();
        }

        System.out.println(handler);
        String[] metadataNames = metadata.names();
        for(String name : metadataNames) {
            System.out.println(name + ": " + metadata.get(name));
        }
    }
}
