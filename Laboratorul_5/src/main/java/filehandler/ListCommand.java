package filehandler;
import model.Catalog;

public class ListCommand implements Command{
    private ListCommand() {}

    public static void execute(Catalog catalog) {
        System.out.println(catalog.getItemList());
    }
}
