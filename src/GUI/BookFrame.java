package GUI;

import Tools.ListModelTools;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Окно добавления / редактирования книги.
public class BookFrame extends JDialog
{
    // Данные.
    private String[] bookAuthors;
    private DefaultListModel<String> authorsListModel = new DefaultListModel<>();
    private ButtonClickListener buttonClickListener;


    // Конструктор.
    public BookFrame(JFrame owner, String title, boolean modal)
    {
        super(owner, title, modal);
        setBounds(500, 500, 400, 315);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Инициализация обработчика нажатий кнопок.
        buttonClickListener = new ButtonClickListener();

        // Создание интерфейса.
        makeGUI();
    }

    // Создание интерфейса.
    private void makeGUI()
    {
        // Инициализация контейнера.
        Container container = getContentPane();

        // Инициализация панелей.
        JPanel bookNamePanel = makeBookNameGUI();
        JPanel authorsPanel = makeAuthorsGUI();
        JPanel tagsPanel = makeTagsGUI();
        JPanel additionalDataPanel = makeAdditionalDataGUI();

        // Добавление панелей.
        container.add(bookNamePanel, BorderLayout.PAGE_START);
        container.add(authorsPanel, BorderLayout.LINE_START);
        container.add(tagsPanel, BorderLayout.LINE_END);
        container.add(additionalDataPanel, BorderLayout.PAGE_END);
    }

    // Создание интерфейса названия книги.
    private JPanel makeBookNameGUI()
    {
        // Инициализация панели.
        JPanel myBookNamePanel = new JPanel();
        myBookNamePanel.setPreferredSize(new Dimension(400, 40));
        myBookNamePanel.setLayout(new BoxLayout(myBookNamePanel, BoxLayout.Y_AXIS));

        // Инициализация элементов панели.
        JLabel bookNameLabel = new JLabel("Название книги");
        bookNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField bookNameTextField = new JTextField(20);

        // Добавление элементов.
        myBookNamePanel.add(bookNameLabel);
        myBookNamePanel.add(bookNameTextField);

        // Возврат панели.
        return myBookNamePanel;
    }

    // Создание интерфейса авторов.
    private JPanel makeAuthorsGUI()
    {
        // Инициализация панели.
        JPanel myAuthorsPanel = new JPanel();
        myAuthorsPanel.setPreferredSize(new Dimension(190, 200));
        myAuthorsPanel.setLayout(new BorderLayout());

        // Инициализация метки.
        JLabel authorsLabel = new JLabel("Авторы");
        authorsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Инициализация списка авторов.
        JList<String> authorsList = new JList<>(authorsListModel);
        JScrollPane authorsScrollPane = new JScrollPane();
        authorsScrollPane.setViewportView(authorsList);

        // Инициализация панели кнопок авторов.
        JPanel authorsButtonsPanel = new JPanel();
        authorsButtonsPanel.setPreferredSize(new Dimension(200, 26));
        authorsButtonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // Инициализация кнопок действий авторов.
        JButton addAuthorButton = new JButton("Добавить");
        addAuthorButton.setActionCommand("Add author");
        addAuthorButton.addActionListener(buttonClickListener);
        JButton deleteAuthorButton = new JButton("Удалить");

        // Добавдение кнопок действий авторов на панель кнопок авторов.
        authorsButtonsPanel.add(addAuthorButton);
        authorsButtonsPanel.add(Box.createRigidArea(new Dimension(17, 0)));
        authorsButtonsPanel.add(deleteAuthorButton);

        // Добавление элементов на панель.
        myAuthorsPanel.add(authorsLabel, BorderLayout.PAGE_START);
        myAuthorsPanel.add(authorsScrollPane, BorderLayout.CENTER);
        myAuthorsPanel.add(authorsButtonsPanel, BorderLayout.PAGE_END);

        // Возврат панели.
        return myAuthorsPanel;
    }

    // Создание иетерфейса меток.
    private JPanel makeTagsGUI()
    {
        // Инициализация панели.
        JPanel myTagsPanel = new JPanel();
        myTagsPanel.setPreferredSize(new Dimension(190, 200));
        myTagsPanel.setLayout(new BorderLayout());

        // Инициализация метки.
        JLabel tagsLabel = new JLabel("Метки");
        tagsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Инициализация списка меток.
        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JList<String> tagsList = new JList<>(data);
        JScrollPane tagsScrollPane = new JScrollPane();
        tagsScrollPane.setViewportView(tagsList);

        // Инициализация панели кнопок меток.
        JPanel tagsButtonsPanel = new JPanel();
        tagsButtonsPanel.setPreferredSize(new Dimension(200, 26));
        tagsButtonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // Инициализация кнопок действий меток.
        JButton addTagButton = new JButton("Добавить");
        JButton deleteTagButton = new JButton("Удалить");

        // Добавдение кнопок действий меток на панель кнопок меток.
        tagsButtonsPanel.add(addTagButton);
        tagsButtonsPanel.add(Box.createRigidArea(new Dimension(17, 0)));
        tagsButtonsPanel.add(deleteTagButton);

        // Добавление элементов на панель.
        myTagsPanel.add(tagsLabel, BorderLayout.PAGE_START);
        myTagsPanel.add(tagsScrollPane, BorderLayout.CENTER);
        myTagsPanel.add(tagsButtonsPanel, BorderLayout.PAGE_END);

        // Возврат панели.
        return myTagsPanel;
    }

    // Создание интерфейса дополнительных данных.
    private JPanel makeAdditionalDataGUI()
    {
        // Инициализация панели.
        JPanel myAdditionalDataPanel = new JPanel();
        myAdditionalDataPanel.setBackground(Color.GREEN);
        myAdditionalDataPanel.setPreferredSize(new Dimension(400, 75));
        myAdditionalDataPanel.setLayout(new BorderLayout());

        // Инициализация центральной панели.
        JPanel centralPanel = new JPanel();
        centralPanel.setPreferredSize(new Dimension(400, 40));
        centralPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

        // Инициализация панели года.
        JPanel yearPanel = new JPanel();
        yearPanel.setPreferredSize(new Dimension(70, 40));
        yearPanel.setLayout(new BoxLayout(yearPanel, BoxLayout.Y_AXIS));

        // Инициализация элементов панели года.
        JLabel yearLabel = new JLabel("Год");
        yearLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField yearTextField = new JTextField(10);

        // Добавление элементов панели года.
        yearPanel.add(yearLabel);
        yearPanel.add(yearTextField);

        // Инициализация панели языка.
        JPanel langPanel = new JPanel();
        langPanel.setPreferredSize(new Dimension(90, 40));
        langPanel.setLayout(new BoxLayout(langPanel, BoxLayout.Y_AXIS));

        // Инициализация элементов панели языка.
        JLabel langLabel = new JLabel("Язык");
        langLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField langTextField = new JTextField(10);

        // Добавление элементов панели языка.
        langPanel.add(langLabel);
        langPanel.add(langTextField);

        // Инициализация панели чтения.
        JPanel readPanel = new JPanel();
        readPanel.setPreferredSize(new Dimension(110, 40));
        readPanel.setLayout(new BoxLayout(readPanel, BoxLayout.Y_AXIS));

        // Инициализация элементов панели чтения.
        JLabel readLabel = new JLabel("Чтение");
        readLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        String[] readStates = {"Не прочитано", "Чтение", "Прочитано"};
        JComboBox readComboBox = new JComboBox(readStates);

        // Добавление элементов панели чтения.
        readPanel.add(readLabel);
        readPanel.add(readComboBox);

        // Инициализация панели расширения файла.
        JPanel filePanel = new JPanel();
        filePanel.setPreferredSize(new Dimension(90, 40));
        filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.Y_AXIS));

        // Инициализация элементов панели расширения файла.
        JLabel fileLabel = new JLabel("Тип файла");
        fileLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField fileTextField = new JTextField(10);

        // Добавление элементов панели расширения файла.
        filePanel.add(fileLabel);
        filePanel.add(fileTextField);

        // Добавление элементов на центральную панель.
        centralPanel.add(yearPanel);
        centralPanel.add(langPanel);
        centralPanel.add(readPanel);
        centralPanel.add(filePanel);

        // Инициализация панели действий.
        JPanel actionPanel = new JPanel();
        actionPanel.setPreferredSize(new Dimension(400, 35));
        actionPanel.setLayout(new FlowLayout());

        // Инициализация элементов панели дейсвий.
        JButton okButton = new JButton("OK");
        okButton.setActionCommand("Add book");
        okButton.addActionListener(buttonClickListener);
        JButton cancelButton = new JButton("Отмена");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(buttonClickListener);

        // Добавление элементов не панель дейстаий.
        actionPanel.add(okButton);
        actionPanel.add(Box.createRigidArea(new Dimension(240, 0)));
        actionPanel.add(cancelButton);

        // Добавление панелей.
        myAdditionalDataPanel.add(centralPanel, BorderLayout.CENTER);
        myAdditionalDataPanel.add(actionPanel, BorderLayout.PAGE_END);

        // Возврат панели.
        return myAdditionalDataPanel;
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
                // Добавление автора.
                case "Add author":
                    // Открытие окна.
                    AuthorsFrame authorsFrame = new AuthorsFrame(BookFrame.this, "Add author", true);
                    String author = authorsFrame.showDialog();

                    // Проверка значения.
                    if (!author.isEmpty())
                    {
                        // Добавление автора.
                        ListModelTools.addStringInListModel(authorsListModel, author, true);
                    }
                    break;
                // Закрытие окна.
                case "Cancel":
                    BookFrame.this.dispose();
                    break;
            }
        }
    }
}
