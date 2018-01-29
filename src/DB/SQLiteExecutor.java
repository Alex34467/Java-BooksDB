package DB;

import org.sqlite.JDBC;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.sql.*;


// Исполнитель команд к базе данных.
public class SQLiteExecutor implements DBExecutor
{
    // Данные.
    private Connection connection;
    private Statement statement;


    // Подключение к базе данных.
    @Override
    public void connect(String url)
    {
        try
        {
            DriverManager.registerDriver(new JDBC());
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    // Запрос без результата.
    @Override
    public void executeUpdate(String query)
    {
        try
        {
            statement.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    // Запрос с результатаом.
    @Override
    public ResultSet executeQuery(String query)
    {
        throw new NotImplementedException();
    }
}
