package DB.Repository;

import DB.DBExecutor;
import Entities.Extension;
import java.sql.ResultSet;
import java.sql.SQLException;


// Репозиторий расширений файлов.
public class ExtensionsRepository
{
    // Данные.
    private DBExecutor executor;


    // Конструктор.
    public ExtensionsRepository(DBExecutor executor)
    {
        this.executor = executor;
    }

    // Получение расширения по имени.
    public Extension getByName(String name)
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Extensions WHERE Name = \"" + name + "\"";

        // Выполнение запроса.
        ResultSet resultSet = executor.executeQuery(query);

        // Получение и возврат результата.
        return getExtensionFromResultSet(resultSet);
    }

    // Добавление расширения.
    public void add(Extension extension)
    {
        // Подготовка запроса.
        String query = "INSERT INTO Extensions(Name) VALUES(\"" + extension.getName() + "\")";

        // Выполнение запроса.
        executor.executeUpdate(query);
    }

    // Получение расширения из ResultSet.
    private Extension getExtensionFromResultSet(ResultSet resultSet)
    {
        // Данные.
        Extension extension = null;

        // Инициализация.
        try
        {
            // Проверка на наличие результата.
            while (resultSet.next())
            {
                extension = new Extension(resultSet.getInt(1), resultSet.getString(2));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // Возврат результата.
        return extension;
    }
}
