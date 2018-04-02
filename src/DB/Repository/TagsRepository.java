package DB.Repository;

import DB.DBExecutor;
import Entities.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;


// Репозиторий меток.
public class TagsRepository
{
    // Данные.
    private DBExecutor executor;


    // Конструктор.
    public TagsRepository(DBExecutor executor)
    {
        this.executor = executor;
    }

    // Получение всех меток.
    public Collection<Tag> getAll()
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Categories";

        // Выполнение запроса.
        ResultSet resultSet = executor.executeQuery(query);

        // Получение и возврат результата.
        return getTagsFromResultSet(resultSet);
    }

    // Получение метки по названию.
    public Tag getByName(String name)
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Categories WHERE Name = \"" + name + "\"";

        // Выполнение.
        ResultSet resultSet = executor.executeQuery(query);

        // Получение и возврат результата.
        return getTagFromResultSet(resultSet);
    }

    // Получение названий всех меток.
    public String[] getAllNames()
    {
        // Подготовка запроса.
        String query = "SELECT Name FROM Categories";

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

    // Добавление метки.
    public void add(Tag tag)
    {
        // Подготовка запроса.
        String query = "INSERT INTO Categories(Name) VALUES(\"" + tag.getName() + "\")";

        // Выполнение запроса.
        executor.executeUpdate(query);
    }

    // Получение метки из ResultSet.
    private Tag getTagFromResultSet(ResultSet resultSet)
    {
        // Данные.
        Tag tag = null;

        // Инициализация.
        try
        {
            // Проверка на наличие результата.
            while (resultSet.next())
            {
                tag = new Tag(resultSet.getInt(1), resultSet.getString(2));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // Возврат результата.
        return tag;
    }

    // Получение меток из ResultSet.
    private Collection<Tag> getTagsFromResultSet(ResultSet resultSet)
    {
        // Данные.
        Collection<Tag> tags = new HashSet<>();

        // Добавление элементов.
        try
        {
            while (resultSet.next())
            {
                Tag tag = new Tag(resultSet.getInt(1), resultSet.getString(2));
                tags.add(tag);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // Возврат результата.
        return tags;
    }
}
