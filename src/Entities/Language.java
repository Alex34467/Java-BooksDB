package Entities;

// Класс языка.
public class Language
{
    // Данные.
    private int id;
    private String name;


    // Конструктор.
    public Language(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    // Конструктор.
    public Language(String name)
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
