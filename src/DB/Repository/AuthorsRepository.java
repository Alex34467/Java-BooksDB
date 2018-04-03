package DB.Repository;

import DB.DBExecutor;
import Entities.Author;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;


// Репозиторий авторов.
public class AuthorsRepository
{
    // Данные.
    private DBExecutor executor;


    // Конструктор.
    public AuthorsRepository(DBExecutor executor)
    {
        this.executor = executor;
    }

    // Получение всех авторов.
    public Collection<Author> getAll()
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Authors";

        // Выполнение.
        ResultSet resultSet = executor.executeQuery(query);

        // Преобразование и возврат результата.
        return getAuthorsFromResultSet(resultSet);
    }

    // Получение автора по имени.
    public Author getByName(String name)
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Authors WHERE Name = \"" + name + "\"";

        // Выполнение.
        ResultSet resultSet = executor.executeQuery(query);

        // Преобразование и возврат результата.
        return getAuthorFromResultSet(resultSet);
    }

    // Получение имен всех авторов.
    public String[] getAllNames()
    {
        // Подготовка запроса.
        String query = "SELECT Name FROM Authors";

        // Выполнение.
        ResultSet resultSet = executor.executeQuery(query);

        // Обработка результата.
        HashSet<String> set = new HashSet<>();
        try
        {
            while (resultSet.next())
            {
                set.add(resultSet.getString(1));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // Возврат результата.
        return set.toArray(new String[set.size()]);
    }

    // Получение id авторов по именам.
    public int[] getIdsByNames(String[] names)
    {
        // Подготовка запроса.
        StringBuilder builder = new StringBuilder("SELECT id FROM Authors WHERE Name IN (");

        for (int i = 0; i < names.length; i++)
        {
            builder.append("\"").append(names[i]).append("\"");

            if (i != names.length - 1)
            {
                builder.append(", ");
            }
            else
            {
                builder.append(")");
            }
        }

        String query = builder.toString();

        // Выполнение запроса.
        ResultSet resultSet = executor.executeQuery(query);

        // Получение и аозврат результата.
        return getIdsFromResultSet(resultSet, names.length);
    }

    // Добавление автора.
    public void add(Author author)
    {
        // Подготовка запроса.
        String query = "INSERT INTO Authors(Name) VALUES (\"" + author.getName() + "\")";

        // Выполнение запроса.
        executor.executeUpdate(query);
    }

    // Получение автора из ResultSet.
    private Author getAuthorFromResultSet(ResultSet resultSet)
    {
        // Данные.
        Author author = null;

        // Инициализация.
        try
        {
            // Проверка на наличие результата.
            while (resultSet.next())
            {
                author = new Author(resultSet.getInt(1), resultSet.getString(2));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // Возврат результата.
        return author;
    }

    // Получение коллекции авторов из ResultSet.
    private Collection<Author> getAuthorsFromResultSet(ResultSet resultSet)
    {
        // Данные.
        Collection<Author> authors = new HashSet<>();

        // Заполнение коллекции.
        try
        {
            while (resultSet.next())
            {
                Author author = new Author(resultSet.getInt(1), resultSet.getString(2));
                authors.add(author);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // Возврат результата.
        return authors;
    }

    // Получение массива id из ResultSet.
    private int[] getIdsFromResultSet(ResultSet resultSet, int length)
    {
        // Данные.
        int[] result = new int[length];

        // Заполнение.
        try
        {
            int i = 0;
            while (resultSet.next())
            {
                result[i] = resultSet.getInt(1);
                i++;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // Возврат результата.
        return result;
    }
}
