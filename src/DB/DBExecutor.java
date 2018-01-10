package DB;

import java.sql.ResultSet;


// Интерфейс исполнителя кодманд к базе данных.
public interface DBExecutor
{
    // Запрос без езультата.
    void executeUpdate(String query);

    // Запрос с результатом.
    ResultSet executeQuery(String query);
}
