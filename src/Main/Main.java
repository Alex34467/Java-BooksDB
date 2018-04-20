package Main;


import GUI.MainFrame;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;


// Основной класс.
public class Main
{
    // Точка входа в приложение.
    public static void main(String[] args)
    {
        // Настройка логгера.
        try
        {
            InputStream stream = Main.class.getResourceAsStream("/logging.properties");
            LogManager.getLogManager().readConfiguration(stream);
        }
        catch (IOException e)
        {
            System.out.println("Cannot load logging configuration: " + e.toString());
        }

        // Запуск окна.
        new MainFrame().setVisible(true);
    }
}
