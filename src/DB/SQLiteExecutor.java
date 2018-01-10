package DB;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.sql.ResultSet;


// Исполнитель команд к базе данных.
public class SQLiteExecutor implements DBExecutor
{
    // Запрос без результата.
    @Override
    public void executeUpdate(String query)
    {
        throw new NotImplementedException();
    }

    // Запрос с результатаом.
    @Override
    public ResultSet executeQuery(String query)
    {
        throw new NotImplementedException();
    }
}
