package Entities;

// Класс записи в теблице книг и меток.
public class BooksAndTagsEntity
{
    // Данные.
    private int id;
    private int bookId;
    private int tagId;


    // Конструктор.
    public BooksAndTagsEntity(int id, int bookId, int tagId)
    {
        this.id = id;
        this.bookId = bookId;
        this.tagId = tagId;
    }

    // Конструктор.
    public BooksAndTagsEntity(int bookId, int tagId)
    {
        this(-1, bookId, tagId);
    }

    // Геттеры.
    public int getId()
    {
        return id;
    }

    public int getBookId()
    {
        return bookId;
    }

    public int getTagId()
    {
        return tagId;
    }
}
