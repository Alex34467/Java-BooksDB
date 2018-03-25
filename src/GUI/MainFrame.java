package GUI;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Главное окно.
public class MainFrame extends JFrame
{
    // Данные.
    private JTable booksTable;
    private JButton filterButton;
    private JButton addButton;
    private JButton searchButton;
    private JButton editButton;
    private JButton deleteButton;


    // Конструктор.
    public MainFrame()
    {
        // Настройка окна.
        super("Books DB");
        setBounds(800, 400, 800, 400);
        setLocationRelativeTo(null);
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
        booksTable = new JTable(new MyTableModel());
        booksTable.setPreferredScrollableViewportSize(new Dimension(670, 400));
        setTableColumnsWidth(booksTable, 100, 400, 40, 80, 80);

        // Инициализация панели прокрутки.
        JScrollPane scrollPane = new JScrollPane(booksTable);

        // Инициализация панели кнопок.
        JPanel buttonsPanel = new JPanel();
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
        container.add(scrollPane, BorderLayout.WEST);
        container.add(buttonsPanel, BorderLayout.EAST);
    }

    // Установка ширины столблов таблицы.
    private void setTableColumnsWidth(JTable table, int... widths)
    {
        // Получение модели.
        TableColumnModel tableColumnModel = table.getColumnModel();

        // Установка ширины столбцов.
        for (int i = 0; i < widths.length; i++)
        {
            tableColumnModel.getColumn(i).setMaxWidth(widths[i]);
        }
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
                    BookFrame bookFrame = new BookFrame(MainFrame.this, "Add book", true);
                    bookFrame.setLocationRelativeTo(MainFrame.this);
                    bookFrame.setVisible(true);
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
