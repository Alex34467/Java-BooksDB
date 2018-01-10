package GUI;

import javax.swing.*;
import java.awt.*;


// Главное окно.
public class MainFrame extends JFrame
{
    // Конструктор.
    public MainFrame(String windowname)
    {
        // Настройка окна.
        super(windowname);
        setBounds(500, 500, 300, 200);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
