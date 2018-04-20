package DB.Repository;

import DB.DBExecutor;
import Entities.BooksViewEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// Репозиторий представления книг.
public class BooksViewRepository
{
    // Данные.
    private DBExecutor executor;


    // Конструктор.
    public BooksViewRepository(DBExecutor executor)
    {
        this.executor = executor;
    }

    // Получение всех записей.
    public List<BooksViewEntity> getAll()
    {
        // Подсчет строк.
        int rows = getRowsCount();

        // Подготовка запроса.
        String query = "SELECT * FROM BooksView";

        // Выполнение запроса.
        ResultSet resultSet = executor.executeQuery(query);

        // Обработка и возврат результата.
        return getFromResultSet(resultSet, rows);
    }

    // Получение коллекции BooksViewEntity мз ResultSet.
    private List<BooksViewEntity> getFromResultSet(ResultSet resultSet, int rowsCount)
    {
        // Коллекция.
        List<BooksViewEntity> books = new ArrayList<>();

        // Получение данных.
        try
        {
            // Данные подсчета.
            int i = 0;

            // Данные книги.
            String authors = "";
            String name = "";
            int year = 0;
            String language = "";
            String readState = "";

            // Обход строк.
            while (resultSet.next())
            {
                // Сравнение.
                if (resultSet.getString(2).equals(name) && resultSet.getInt(3) == year)
                {
                    // Добавление автора.
                    authors += resultSet.getString(1) + ", ";
                }
                else
                {
                    if (i != 0)
                    {
                        int u = 0;

                        // Сборка объекта.
                        authors = authors.substring(0, authors.length() - 2);
                        BooksViewEntity booksViewEntity = new BooksViewEntity(authors, name, year, language, readState);
                        authors = "";

                        // Добавление в коллекцию.
                        books.add(booksViewEntity);
                    }

                    // Присваивание.
                    authors = resultSet.getString(1) + ", ";
                    name = resultSet.getString(2);
                    year = resultSet.getInt(3);
                    language = resultSet.getString(4);
                    readState = resultSet.getString(5);
                }

                // Проверка на последнюю строку.
                if (i == rowsCount - 1)
                {
                    // Сборка объекта.
                    authors = authors.substring(0, authors.length() - 2);
                    BooksViewEntity booksViewEntity = new BooksViewEntity(authors, name, year, language, readState);
                    authors = "";

                    // Добавление в коллекцию.
                    books.add(booksViewEntity);
                }

                // Подсчет строки.
                i++;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // Возврат результата.
        return books;
    }

    // Получение количетва строк.
    private int getRowsCount()
    {
        // Подготовка запроса.
        String query = "SELECT COUNT(*) FROM BooksView";

        // Выполнение запроса.
        ResultSet resultSet = executor.executeQuery(query);

        // Получение результата.
        try
        {
            return resultSet.getInt(1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
}
