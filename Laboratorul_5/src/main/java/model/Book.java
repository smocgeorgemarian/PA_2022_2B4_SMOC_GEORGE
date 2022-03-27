package model;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonTypeName;
@JsonTypeName("book")
public class Book extends Item {
    private String description;

    public Book() {}

    public Book(String id, String title, String location, int year, String author, Map<String, Item> tags, String description) {
        super(id, title, location, year, author, tags);
        this.description = description;
    }

    public Book(String id, String title, String location, int year, String author, String description) {
        super(id, title, location, year, author);
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", year=" + year +
                ", author='" + author + '\'' +
                ", tags=" + tags +
                '}';
    }
}
