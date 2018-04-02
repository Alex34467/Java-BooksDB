package Entities;

// Класс расширения файла.
public class Extension
{
    // Данные.
    private int id;
    private String name;

    // Конструктор.
    public Extension(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    // Конструктор.
    public Extension(String name)
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
