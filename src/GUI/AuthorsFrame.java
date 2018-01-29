package GUI;

import Tools.ListModelTools;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Окно добавления авторов.
public class AuthorsFrame extends JDialog
{
    // Данные.
    private String[] authors;
    private DefaultListModel<String> authorsListModel = new DefaultListModel<>();
    private ButtonClickListener buttonClickListener;
    private MyDocumentListener documentListener;

    // Элементы.
    private JTextField authorTextField;
    private JList<String> authorsList;


    // Конструктор.
    public AuthorsFrame(JDialog owner, String title, boolean modal)
    {
        super(owner, title, modal);
        setBounds(500, 500, 150, 250);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Инициализация обработчиков.
        buttonClickListener = new ButtonClickListener();
        documentListener = new MyDocumentListener();

        // Создание интерфейса.
        makeGUI();
    }

    // Отображение.
    public String showDialog()
    {
        setVisible(true);

        return authorTextField.getText();
    }

    // Создание интерфейса.
    private void makeGUI()
    {
        // Инициализация контейнера.
        Container container = getContentPane();

        // Инициализация поля ввода авторов.
        authorTextField = new JTextField(20);
        authorTextField.getDocument().addDocumentListener(documentListener);

        // Инициализация списка авторов.
        authorsList = new JList<>(authorsListModel);
        JScrollPane authorsScrollPane = new JScrollPane();
        authorsScrollPane.setViewportView(authorsList);

        // Инициализация панели кнопок авторов.
        JPanel authorsButtonsPanel = new JPanel();
        authorsButtonsPanel.setPreferredSize(new Dimension(150, 26));
        authorsButtonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // Инициализация кнопок действий авторов.
        JButton addAuthorButton = new JButton("OK");
        addAuthorButton.setActionCommand("Add");
        addAuthorButton.addActionListener(buttonClickListener);
        JButton deleteAuthorButton = new JButton("Отмена");
        deleteAuthorButton.setActionCommand("Cancel");
        deleteAuthorButton.addActionListener(buttonClickListener);

        // Добавдение кнопок действий авторов на панель кнопок авторов.
        authorsButtonsPanel.add(addAuthorButton);
        authorsButtonsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        authorsButtonsPanel.add(deleteAuthorButton);

        // Добавление элементов.
        container.add(authorTextField, BorderLayout.PAGE_START);
        container.add(authorsScrollPane, BorderLayout.CENTER);
        container.add(authorsButtonsPanel, BorderLayout.PAGE_END);
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
                case "Add":
                case "Cancel":
                    AuthorsFrame.this.dispose();
                    break;
            }
        }
    }

    // Обработчик текстового документа.
    private class MyDocumentListener implements DocumentListener
    {
        // Добавление текста.
        @Override
        public void insertUpdate(DocumentEvent e)
        {
            ListModelTools.filterByText(authorsListModel, authorTextField.getText(), authors, true);
        }

        // Удаление текста.
        @Override
        public void removeUpdate(DocumentEvent e)
        {
            ListModelTools.filterByText(authorsListModel, authorTextField.getText(), authors, true);
        }

        // Изменение текста.
        @Override
        public void changedUpdate(DocumentEvent e)
        {

        }
    }
}
