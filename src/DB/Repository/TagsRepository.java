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
        String query = "SELECT * FROM Tags";

        // Выполнение запроса.
        ResultSet resultSet = executor.executeQuery(query);

        // Получение и возврат результата.
        return getTagsFromResultSet(resultSet);
    }

    // Получение метки по названию.
    public Tag getByName(String name)
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Tags WHERE Name = \"" + name + "\"";

        // Выполнение.
        ResultSet resultSet = executor.executeQuery(query);

        // Получение и возврат результата.
        return getTagFromResultSet(resultSet);
    }

    // Получение названий всех меток.
    public String[] getAllNames()
    {
        // Подготовка запроса.
        String query = "SELECT Name FROM Tags";

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

    // Получение id меток по названиям.
    public int[] getIdsByNames(String[] names)
    {
        // Подготовка запроса.
        StringBuilder builder = new StringBuilder("SELECT id FROM Tags WHERE Name IN (");

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

    // Добавление метки.
    public void add(Tag tag)
    {
        // Подготовка запроса.
        String query = "INSERT INTO Tags(Name) VALUES(\"" + tag.getName() + "\")";

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
