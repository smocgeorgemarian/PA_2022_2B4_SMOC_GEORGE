package filehandler;

import freemarker.template.*;
import model.Catalog;
import model.Item;

import java.awt.*;
import java.io.*;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import exceptions.*;

public class ReportCommand {
    private static final String OUTPUT = "output.html";
    private ReportCommand() {}
    public static void execute(Catalog catalog) throws TemplateProcessCustomException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setIncompatibleImprovements(Configuration.VERSION_2_3_31);
        configuration.setClassForTemplateLoading(ReportCommand.class, "/");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setLocale(Locale.ENGLISH);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> input = new HashMap<>();
        input.put("title", "Catalog " + catalog.getName());
        List<Item> systems = catalog.getItemList();
        input.put("systems", systems);

        try {
            Template template = configuration.getTemplate("template.ftl");
            Writer consoleWriter = new OutputStreamWriter(System.out);
            template.process(input, consoleWriter);
            Writer fileWriter = new FileWriter(OUTPUT);
            template.process(input, fileWriter);
            fileWriter.close();
            fileWriter = new FileWriter(OUTPUT);
            template.process(input, fileWriter);
            fileWriter.close();
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(OUTPUT));
        }
        catch (IOException | TemplateException e) {
            throw new TemplateProcessCustomException(e);
        }
    }
}
