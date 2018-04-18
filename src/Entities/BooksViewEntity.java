package Entities;

// Представление строки представления книг в виде объекта.
public class BooksViewEntity
{
    // Данные.
    private String authors;
    private String name;
    private int year;
    private String language;
    private String readState;


    // Конструктор.
    public BooksViewEntity(String authors, String name, int year, String language, String readState)
    {
        this.authors = authors;
        this.name = name;
        this.year = year;
        this.language = language;
        this.readState = readState;
    }

    // Геттеры.
    public String getAuthors()
    {
        return authors;
    }

    public String getName()
    {
        return name;
    }

    public int getYear()
    {
        return year;
    }

    public String getLanguage()
    {
        return language;
    }

    public String getReadState()
    {
        return readState;
    }
}
