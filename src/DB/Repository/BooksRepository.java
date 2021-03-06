package DB.Repository;

import DB.DBExecutor;
import Entities.Book;
import java.sql.ResultSet;
import java.sql.SQLException;


// Репозиторий книг.
public class BooksRepository
{
    // Данные.
    private DBExecutor executor;


    // Конструктор.
    public BooksRepository(DBExecutor executor)
    {
        this.executor = executor;
    }

    // Получение книги по названию и году.
    public Book getByNameAndYear(String name, int year)
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Books WHERE Name = \"" + name + "\" AND Year ";
        if (year != 0)
        {
            query += "= " + year;
        }
        else
        {
            query += "IS NULL";
        }

        // Выполнение запроса.
        ResultSet resultSet = executor.executeQuery(query);

        // Получение и возврат результата.
        return getBookFromResultSet(resultSet);
    }

    // Добавление книги.
    public void add(Book book)
    {
        // Подготовка данных.
        String yearStr = (book.getYear() == 0)? "NULL" : String.valueOf(book.getYear());
        String extStr = (book.getExtensionId() == 0)? "NULL" : String.valueOf(book.getExtensionId());

        // Подготовка запроса.
        String query = "INSERT INTO Books(Name, Year, Read_State_Id, Language_id, Extension_id) VALUES(\"";
        query += book.getName() + "\", " + yearStr + ", " + book.getReadStateId() + ", " + book.getLanguageId();
        query += ", " + extStr + ")";

        // Выполнение запроса.
        executor.executeUpdate(query);
    }

    // Получение книги из ResultSet.
    private Book getBookFromResultSet(ResultSet resultSet)
    {
        // Данные.
        Book book = null;

        // Получение данных.
        try
        {
            while (resultSet.next())
            {
                // Получение двнных.
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int year = resultSet.getInt(3);
                int readStateId = resultSet.getInt(4);
                int languageId = resultSet.getInt(5);
                int extensionId = resultSet.getInt(6);

                // Сборка объекта.
                book = new Book(id, name, year, readStateId, languageId, extensionId);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // Возврат результата.
        return book;
    }
}
