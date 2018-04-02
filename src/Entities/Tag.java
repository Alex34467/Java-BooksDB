package Entities;

// Класс метки.
public class Tag
{
    // Данные.
    private int id;
    private String name;


    // Конструктор.
    public Tag(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    // Конструктор.
    public Tag(String name)
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
