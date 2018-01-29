package DB;

import java.sql.ResultSet;


// Интерфейс исполнителя кодманд к базе данных.
public interface DBExecutor
{
    // Подключение к БД.
    void connect(String url);

    // Запрос без езультата.
    void executeUpdate(String query);

    // Запрос с результатом.
    ResultSet executeQuery(String query);
}
