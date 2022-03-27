package model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonTypeName;
@JsonTypeName("article")
public class Article extends Item{
    private int noDownloads;

    public Article() {}

    public Article(String id, String title, String location, int year, String author, Map<String, Item> tags, int noDownloads) {
        super(id, title, location, year, author, tags);
        this.noDownloads = noDownloads;
    }

    public Article(String id, String title, String location, int year, String author, int noDownloads) {
        super(id, title, location, year, author);
        this.noDownloads = noDownloads;
    }

    public void setNoDownloads(int noDownloads) {
        this.noDownloads = noDownloads;
    }

    public int getNoDownloads() {
        return noDownloads;
    }

    @Override
    public String toString() {
        return "Article{" +
                "noDownloads=" + noDownloads +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", year=" + year +
                ", author='" + author + '\'' +
                ", tags=" + tags +
                '}';
    }
}
