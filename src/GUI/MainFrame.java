package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Главное окно.
public class MainFrame extends JFrame
{
    // Данные.
    private JButton filterButton;
    private JButton addButton;
    private JButton searchButton;
    private JButton editButton;
    private JButton deleteButton;


    // Конструктор.
    public MainFrame(String windowName)
    {
        // Настройка окна.
        super(windowName);
        setBounds(500, 500, 800, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Создание интерфейса.
        makeGUI();
    }

    // Создание интерфейса.
    private void makeGUI()
    {
        // Инициализация контейнера.
        Container container = getContentPane();

        // Инициализация таблицы.
        JTable booksTable = new JTable();

        // Инициализация панели кнопок.
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.LIGHT_GRAY);
        buttonsPanel.setPreferredSize(new Dimension(100, 400));
        buttonsPanel.setLayout(new GridLayout(0, 1));

        // Инициализация кнопок.
        filterButton = new JButton("Фильтр");
        addButton = new JButton("Добавить");
        searchButton = new JButton("Поиск");
        editButton = new JButton("Изменить");
        deleteButton = new JButton("Удалить");

        // Установка команд для кнопок.
        filterButton.setActionCommand("Filter");
        addButton.setActionCommand("Add");
        searchButton.setActionCommand("Search");
        editButton.setActionCommand("Edit");
        deleteButton.setActionCommand("Delete");

        // Добавление обработчиков событий для кнопок.
        ButtonClickListener buttonClickListener = new ButtonClickListener();
        filterButton.addActionListener(buttonClickListener);
        addButton.addActionListener(buttonClickListener);
        searchButton.addActionListener(buttonClickListener);
        editButton.addActionListener(buttonClickListener);
        deleteButton.addActionListener(buttonClickListener);

        // Добавление кнопок на панель.
        buttonsPanel.add(filterButton);
        buttonsPanel.add(addButton);
        buttonsPanel.add(searchButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        // Добавление остальных компоенетов.
        container.add(booksTable, BorderLayout.WEST);
        container.add(buttonsPanel, BorderLayout.EAST);
    }


    // Обработчик событий нажантия кнопок.
    private class ButtonClickListener implements ActionListener
    {
        // Обработка события.
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Получение команды.
            String command = e.getActionCommand();

            // Выбор кнопки.
            switch (command)
            {
                case "Filter":
                    break;
                case "Add":
                    break;
                case "Search":
                    break;
                case "Edit":
                    break;
                case "Delete":
                    break;
            }
        }
    }
}
