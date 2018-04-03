package Entities;

// Представление записи в таблице книг и авторов.
public class BooksAndAuthorsEntity
{
    // Данные.
    private int id;
    private int bookId;
    private int authorId;


    // Конструктор.
    public BooksAndAuthorsEntity(int id,  int bookId, int authorId)
    {
        this.id = id;
        this.authorId = authorId;
        this.bookId = bookId;
    }

    // Конструктор.
    public BooksAndAuthorsEntity(int bookId, int authorId)
    {
        this(-1, bookId, authorId);
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

    public int getAuthorId()
    {
        return authorId;
    }
}
