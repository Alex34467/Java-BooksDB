package DB.Repository;

import DB.DBExecutor;
import Entities.BooksAndAuthorsEntity;


// Репозиторий книг и авторов.
public class BookAndAuthorsRepository
{
    // Данные.
    private DBExecutor executor;


    // Конструктор.
    public BookAndAuthorsRepository(DBExecutor executor)
    {
        this.executor = executor;
    }

    // Добавление записи.
    public void add(BooksAndAuthorsEntity entity)
    {
        // Подготовка запроса.
        String query = "INSERT INTO BooksAndAuthors(Book_Id, Author_Id) VALUES(" + entity.getBookId() + ", " + entity.getAuthorId() + ")";

        // Выполнение запроса.
        executor.executeUpdate(query);
    }
}
