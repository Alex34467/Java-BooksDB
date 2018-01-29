package Main;

import DB.DBService;
import GUI.MainFrame;


// Основной класс.
public class Main
{
    // Точка входа в приложение.
    public static void main(String[] args)
    {
        // Подключение к БД.
        DBService.getInstance();

        // Запуск окна.
        new MainFrame().setVisible(true);
    }
}
