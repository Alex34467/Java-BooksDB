package GUI;

import DB.DBService;
import Entities.BooksAndAuthorsEntity;
import Entities.BooksAndTagsEntity;
import Tools.DBEntitiesTools;
import Tools.ListModelTools;
import Tools.ValidationTools;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Окно добавления / редактирования книги.
public class BookFrame extends JDialog
{
    // Поля книги.
    private JTextField bookNameTextField;
    private JTextField yearTextField;
    private JTextField langTextField;
    private JTextField fileTextField;

    // Списки.
    private JList<String> authorsList;
    private JList<String> tagsList;
    private JComboBox<String> readComboBox;

    // Кнопки удаления.
    private JButton deleteAuthorButton;
    private JButton deleteTagButton;

    // Модели списков.
    private DefaultListModel<String> authorsListModel;
    private DefaultListModel<String> tagsListModel;

    // Обработчики событий.
    private ButtonClickListener buttonClickListener;
    private MyListSelectionListener authorsListSelectionListener;
    private MyListSelectionListener tagsListSelectionListener;


    // Конструктор.
    public BookFrame(JFrame owner, String title, boolean modal)
    {
        // Иниуиализация окна.
        super(owner, title, modal);
        setBounds(500, 500, 400, 315);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Инициализация списков.
        authorsListModel = new DefaultListModel<>();
        tagsListModel = new DefaultListModel<>();

        // Инициализация обработчиков.
        buttonClickListener = new ButtonClickListener();
        authorsListSelectionListener = new MyListSelectionListener("Authors");
        tagsListSelectionListener = new MyListSelectionListener("Tags");

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
        bookNameTextField = new JTextField(20);

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
        authorsList = new JList<>(authorsListModel);
        authorsList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        authorsList.getSelectionModel().addListSelectionListener(authorsListSelectionListener);
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
        deleteAuthorButton = new JButton("Удалить");
        deleteAuthorButton.setActionCommand("Delete author");
        deleteAuthorButton.addActionListener(buttonClickListener);
        deleteAuthorButton.setEnabled(false);

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
        tagsList = new JList<>(tagsListModel);
        tagsList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        tagsList.getSelectionModel().addListSelectionListener(tagsListSelectionListener);
        JScrollPane tagsScrollPane = new JScrollPane();
        tagsScrollPane.setViewportView(tagsList);

        // Инициализация панели кнопок меток.
        JPanel tagsButtonsPanel = new JPanel();
        tagsButtonsPanel.setPreferredSize(new Dimension(200, 26));
        tagsButtonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // Инициализация кнопок действий меток.
        JButton addTagButton = new JButton("Добавить");
        addTagButton.setActionCommand("Add tag");
        addTagButton.addActionListener(buttonClickListener);
        deleteTagButton = new JButton("Удалить");
        deleteTagButton.setActionCommand("Delete tag");
        deleteTagButton.addActionListener(buttonClickListener);
        deleteTagButton.setEnabled(false);

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
        yearTextField = new JTextField(10);

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
        langTextField = new JTextField(10);

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
        readComboBox = new JComboBox(readStates);

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
        fileTextField = new JTextField(10);

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

    // Проверка введенных данных о книге.
    private boolean validateBookForm()
    {
        // Данные.
        boolean result = true;
        Color errorColor = new Color(255,102,102);

        // Сброс цвета.
        bookNameTextField.setBackground(Color.WHITE);
        authorsList.setBackground(Color.WHITE);
        tagsList.setBackground(Color.WHITE);
        yearTextField.setBackground(Color.WHITE);
        langTextField.setBackground(Color.WHITE);

        // Проверка названия.
        if (bookNameTextField.getText().isEmpty())
        {
            bookNameTextField.setBackground(errorColor);
            result = false;
        }

        // Проверка авторов.
        if (authorsListModel.isEmpty())
        {
            authorsList.setBackground(errorColor);
            result = false;
        }

        // Проверка меток.
        if (tagsListModel.isEmpty())
        {
            tagsList.setBackground(errorColor);
            result = false;
        }

        // Проверка года.
        if (!yearTextField.getText().isEmpty())
        {
            if (!ValidationTools.validateYear(yearTextField.getText()))
            {
                yearTextField.setBackground(errorColor);
                result = false;
            }
        }

        // Проверка языка.
        if (langTextField.getText().isEmpty() || !langTextField.getText().matches("[a-zA-Zа-яА-Я]+"))
        {
            langTextField.setBackground(errorColor);
            result = false;
        }

        // Возврат результата.
        return result;
    }

    // Обработка данных о книге.
    private boolean proceedBookFrameData()
    {
        // Получение данных.
        int year = 0;
        if (!yearTextField.getText().isEmpty())
        {
            year = Integer.parseInt(yearTextField.getText());
        }

        // Обработка данных книги.
        boolean result = DBEntitiesTools.addBook(bookNameTextField.getText(), year, readComboBox.getSelectedIndex() + 1, langTextField.getText(), fileTextField.getText());
        if(result)
        {
            // Обработка авторов.
            String[] authorsNames = ListModelTools.getElementsFromListModel(authorsListModel);
            DBEntitiesTools.addAuthors(authorsNames);

            // Обработка меток.
            String[] tagsNames = ListModelTools.getElementsFromListModel(tagsListModel);
            DBEntitiesTools.addTags(tagsNames);

            // Получение id элементов.
            int bookId = DBService.getInstance().getBookByNameAndYear(bookNameTextField.getText(), year).getId();
            int[] authorsIds = DBService.getInstance().getAuthorsIdsByNames(authorsNames);
            int[] tagsIds = DBService.getInstance().getTagsIdsByNames(tagsNames);

            // Добавление записей в таблицу книг и авторов.
            for (int authorId : authorsIds)
            {
                // Сборка объекта.
                BooksAndAuthorsEntity entity = new BooksAndAuthorsEntity(bookId, authorId);

                // Добавление в БД.
                DBService.getInstance().addBookAndAuthor(entity);
            }

            // Добавление записей в таблицу книг и меток.
            for (int tagId : tagsIds)
            {
                // Сборка объекта.
                BooksAndTagsEntity entity = new BooksAndTagsEntity(bookId, tagId);

                // Добавление в БД.
                DBService.getInstance().addBookAndTag(entity);
            }

            // Завершение.
            return true;
        }
        else
        {
            // Книга уже существует.
            Color existColor = new Color(255, 255, 153);
            bookNameTextField.setBackground(existColor);
            yearTextField.setBackground(existColor);
            return false;
        }
    }

    // Добавление автора в список.
    private void addAuthor()
    {
        // Перекрасшивание списка.
        authorsList.setBackground(Color.WHITE);

        // Получение данных.
        String[] authorsNames = DBService.getInstance().getAllAuthorsNames();
        String[] restrictedAuthors = ListModelTools.getElementsFromListModel(authorsListModel);

        // Открытие окна.
        InputTextFrame authorsFrame = new InputTextFrame(BookFrame.this, "Add author", authorsNames, restrictedAuthors, true);
        authorsFrame.setLocationRelativeTo(BookFrame.this);

        // Получение результата.
        String author = authorsFrame.showDialog();

        // Проверка значения.
        if (author != null && !author.isEmpty())
        {
            // Добавление автора.
            ListModelTools.addStringInListModel(authorsListModel, author, true);
        }
    }

    // Добавление тега в список.
    private void addTag()
    {
        // Получение данных.
        String[] tagsNames = DBService.getInstance().getAllTagsNames();
        String[] restrictedTags = ListModelTools.getElementsFromListModel(tagsListModel);

        // Открытие окна.
        InputTextFrame tagsFrame = new InputTextFrame(BookFrame.this, "Add tag", tagsNames, restrictedTags, true);
        tagsFrame.setLocationRelativeTo(BookFrame.this);

        // Получение результата.
        String tag = tagsFrame.showDialog();

        // Проверка значения.
        if (tag != null && !tag.isEmpty() )
        {
            // Добавление автора.
            ListModelTools.addStringInListModel(tagsListModel, tag, true);
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
                // Добавление автора.
                case "Add author":
                    addAuthor();
                    break;

                // Удаление автора из списка.
                case "Delete author":
                    // Получение значения.
                    String selectedAuthor = authorsList.getSelectedValue();

                    // Удаление.
                    authorsListModel.removeElement(selectedAuthor);
                    break;

                // Добавление метки.
                case "Add tag":
                    addTag();
                    break;

                // Удаление метки из списка.
                case "Delete tag":
                    // Получение значения.
                    String selectedTag = tagsList.getSelectedValue();

                    // Удаление.
                    tagsListModel.removeElement(selectedTag);
                    break;

                // Добавление книги.
                // FIXME: Реализовать.
                case "Add book":
                    // Проверка данных.
                    if (validateBookForm())
                    {
                        // Обработка данных.
                        if(proceedBookFrameData())
                        {
                            // Закрытие окна.
                            BookFrame.this.dispose();
                        }
                    }
                    break;

                // Закрытие окна.
                case "Cancel":
                    BookFrame.this.dispose();
                    break;
            }
        }
    }

    // Обработчик событий списков.
    private class MyListSelectionListener implements ListSelectionListener
    {
        // Данные.
        private String label;


        // Конструктор.
        public MyListSelectionListener(String label)
        {
            this.label = label;
        }

        // Изменние значения.
        @Override
        public void valueChanged(ListSelectionEvent e)
        {
            // Получение модели.
            ListSelectionModel listSelectionModel = (ListSelectionModel)e.getSource();

            // Выбор списка.
            if (label.equals("Authors"))
            {
                // Получение значения.
                if (listSelectionModel.isSelectionEmpty())
                {
                    deleteAuthorButton.setEnabled(false);
                }
                else
                {
                    deleteAuthorButton.setEnabled(true);
                }
            }
            else
            {
                // Получение значения.
                if (listSelectionModel.isSelectionEmpty())
                {
                    deleteTagButton.setEnabled(false);
                }
                else
                {
                    deleteTagButton.setEnabled(true);
                }
            }
        }
    }
}
