package DB.Repository;

import DB.DBExecutor;
import Entities.BooksAndTagsEntity;


// Репозиторий записей о книгах и метках.
public class BooksAndTagsRepository
{
    // Данные.
    private DBExecutor executor;


    // Конструктор.
    public BooksAndTagsRepository(DBExecutor executor)
    {
        this.executor = executor;
    }

    // Добавление записи.
    public void add(BooksAndTagsEntity entity)
    {
        // Подготовка запроса.
        String query = "INSERT INTO BooksAndTags(Book_Id, Tag_Id) VALUES(" + entity.getBookId() + ", " + entity.getTagId() + ")";

        // Выполнение запроса.
        executor.executeUpdate(query);
    }
}
