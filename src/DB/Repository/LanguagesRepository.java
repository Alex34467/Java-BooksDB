package DB.Repository;

import DB.DBExecutor;
import Entities.Language;
import java.sql.ResultSet;
import java.sql.SQLException;


// Репозиторий языков.
public class LanguagesRepository
{
    // Данные.
    private DBExecutor executor;


    // Конструктор.
    public LanguagesRepository(DBExecutor executor)
    {
        this.executor = executor;
    }

    // Получение языка по названию.
    public Language getByName(String name)
    {
        // Подготовка запроса.
        String query = "SELECT * FROM Languages WHERE Name = \"" + name + "\"";

        // Выполнение запроса.
        ResultSet resultSet = executor.executeQuery(query);

        // Получение и возврат результата.
        return getLanguageFromResultSet(resultSet);
    }

    // Добавление языка.
    public void add(Language language)
    {
        // Подготовка запроса.
        String query = "INSERT INTO Languages(Name) VALUES(\"" + language.getName() + "\")";

        // Выполнение запроса.
        executor.executeUpdate(query);
    }

    // Получение языка из ResultSet.
    private Language getLanguageFromResultSet(ResultSet resultSet)
    {
        // Данные.
        Language language = null;

        // Инициализация.
        try
        {
            // Проверка на наличие результата.
            while (resultSet.next())
            {
                language = new Language(resultSet.getInt(1), resultSet.getString(2));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // Возврат результата.
        return language;
    }
}
