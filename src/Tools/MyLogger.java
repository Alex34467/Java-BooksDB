package Tools;

import java.util.logging.Level;
import java.util.logging.Logger;


// Класс для логгирования.
public class MyLogger
{
    // Данные.
    private Logger logger;
    private static MyLogger instance;


    // Геттер.
    public static MyLogger getInstance()
    {
        if (instance == null) instance = new MyLogger();
        return instance;
    }

    // Логирование.
    public void log(Level level, String message)
    {
        logger.log(level, message);
    }

    // Конструктор.
    private MyLogger()
    {
        logger = Logger.getLogger("Logger");
    }
}
