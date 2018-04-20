package DB;

import Tools.MyLogger;
import org.sqlite.JDBC;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.sql.*;
import java.util.logging.Level;


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
            long start = System.nanoTime();
            MyLogger.getInstance().log(Level.INFO, "Executing: " + query);

            statement.executeUpdate(query);

            float elapsed = (System.nanoTime() - start) / 1000000;
            String elapsedStr = String.format("%.2f", elapsed);
            MyLogger.getInstance().log(Level.INFO, "Executed for " + elapsedStr + " ms");
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
        try
        {
            // Выполнение.
            long start = System.nanoTime();
            MyLogger.getInstance().log(Level.INFO, "Executing: " + query);

            ResultSet resultSet = statement.executeQuery(query);

            float elapsed = ((float)(System.nanoTime() - start)) / 1000000;
            String elapsedStr = String.format("%.2f", elapsed);
            MyLogger.getInstance().log(Level.INFO, "Executed for " + elapsedStr + " ms");

            // Возврат результата.
            return resultSet;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
