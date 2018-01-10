package DB;


// Класс, управляющий БД.
public class DBService
{
    // Данные.
    private static DBService instance;


    // Геттер.
    public static DBService getInstance()
    {
        if (instance == null) instance = new DBService();
        return instance;
    }

    // Конструктор.
    private DBService()
    {

    }
}
