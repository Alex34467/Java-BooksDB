package Entities;

// Класс автора книги.
public class Author
{
    // Данные.
    private int id;
    private String name;


    // Конструктор.
    public Author(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    // Конструктор.
    public Author(String name)
    {
        this(-1, name);
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
}
