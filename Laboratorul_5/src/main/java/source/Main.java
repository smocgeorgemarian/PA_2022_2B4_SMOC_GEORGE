package source;

import com.github.javafaker.Faker;
import exceptions.InvalidCatalogException;
import exceptions.TemplateProcessCustomException;
import filehandler.*;
import model.Article;
import model.Book;
import model.Catalog;
import java.util.Date;
import java.util.Calendar;

import java.io.IOException;

public class Main {
    private static final String CATALOG_PATH = "./test_data/Catalog.json";
    private static final Faker faker = new Faker();
    private void testCreateSave() {
        Catalog catalog = new Catalog("MyRefs");
        var book = new Book("knuth67", "The Art of Computer Programming",
                "./test_data/Book.txt", faker.date().between(new Date(1600, Calendar.FEBRUARY,1),
                        new Date(2022, Calendar.APRIL,3)).toString(), "Ionel Teodoreanu", "descriere");

        var article = new Article("java17", "The Java Language Specification",
                "https://docs.oracle.com/javase/specs/jls/se17/html/index.html",
                faker.date().between(new Date(1600, Calendar.FEBRUARY,1), new Date(2022, Calendar.APRIL,3)).toString(),
                "Ionel Teodoreanu", 100);

        catalog.add(book);
        catalog.add(article);
        try {
            CatalogUtil.save(catalog, CATALOG_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testLoadView() {
        Catalog catalog;
        try {
            catalog = LoadCommand.execute(CATALOG_PATH);
            System.out.println(catalog);
        } catch (InvalidCatalogException e) {
            e.printStackTrace();
            return;
        }
        try {
            ViewCommand.execute(catalog.getItemList().get(0));
            ViewCommand.execute(catalog.getItemList().get(1));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testReport() {
        try {
            Catalog catalog = LoadCommand.execute(CATALOG_PATH);
            System.out.println(catalog);
            ReportCommand.execute(catalog);
        } catch (InvalidCatalogException | TemplateProcessCustomException e) {
            e.printStackTrace();
        }
    }

    private void testInfo() {
        try {
            Catalog catalog = LoadCommand.execute(CATALOG_PATH);
            for (var item : catalog.getItemList())
                InfoCommand.execute(item.getLocation());
        } catch (InvalidCatalogException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
        app.testReport();
//        app.testInfo();
    }
}
