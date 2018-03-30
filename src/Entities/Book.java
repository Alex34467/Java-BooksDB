package Entities;


// Класс книги.
public class Book
{
    // Данные.
    private int id;
    private String name;
    private int year;
    private int readStateId;
    private int languageId;
    private int extensionId;


    // Конструктор.
    public Book (int id, String name, int year, int readStateId, int languageId, int extensionId)
    {
        this.id = id;
        this.name = name;
        this.year = year;
        this.readStateId = readStateId;
        this.languageId = languageId;
        this.extensionId = extensionId;
    }

    // Геттеры.
    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getYear()
    {
        return year;
    }

    public int getReadStateId()
    {
        return readStateId;
    }

    public int getLanguageId()
    {
        return languageId;
    }

    public int getExtensionId()
    {
        return extensionId;
    }
}
