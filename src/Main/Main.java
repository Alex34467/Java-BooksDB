package Main;

import GUI.MainFrame;


// Основной класс.
public class Main
{
    // Точка входа в приложение.
    public static void main(String[] args)
    {
        // Запуск окна.
        MainFrame frame = new MainFrame("Books DB");
        frame.setVisible(true);
    }
}
