package GUI;

import Tools.ListModelTools;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Окно для ввода текста.
public class InputTextFrame extends JDialog
{
    // Данные.
    private String[] data;
    private DefaultListModel<String> textListModel = new DefaultListModel<>();

    // Обработчики событий.
    private ButtonClickListener buttonClickListener;
    private MyDocumentListener documentListener;

    // Элементы.
    private JTextField textField;
    private JList<String> textList;


    // Конструктор.
    public InputTextFrame(JDialog owner, String title, String[] values, boolean modal)
    {
        // Инициализация окна.
        super(owner, title, modal);
        setBounds(500, 500, 150, 250);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Инициализация списка значений.
        data = values;

        // Инициализация обработчиков.
        buttonClickListener = new ButtonClickListener();
        documentListener = new MyDocumentListener();

        // Создание интерфейса.
        makeGUI();
    }

    // Отображение.
    public String showDialog()
    {
        // Показ окна.
        setVisible(true);

        // Воврат введенного текста.
        return textField.getText();
    }

    // Создание интерфейса.
    private void makeGUI()
    {
        // Инициализация контейнера.
        Container container = getContentPane();

        // Инициализация поля ввода.
        textField = new JTextField(20);
        textField.getDocument().addDocumentListener(documentListener);

        // Инициализация списка.
        textList = new JList<>(textListModel);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textList);

        // Инициализация панели кнопок.
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(150, 26));
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // Инициализация кнопок действий.
        JButton okButton = new JButton("OK");
        okButton.setActionCommand("Add");
        okButton.addActionListener(buttonClickListener);
        JButton cancelButton = new JButton("Отмена");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(buttonClickListener);

        // Добавдение кнопок действий на панель кнопок.
        buttonsPanel.add(okButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonsPanel.add(cancelButton);

        // Добавление элементов.
        container.add(textField, BorderLayout.PAGE_START);
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(buttonsPanel, BorderLayout.PAGE_END);
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
                    InputTextFrame.this.dispose();
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
            ListModelTools.filterListModelByText(textListModel, textField.getText(), data, true);
        }

        // Удаление текста.
        @Override
        public void removeUpdate(DocumentEvent e)
        {
            ListModelTools.filterListModelByText(textListModel, textField.getText(), data, true);
        }

        // Изменение текста.
        @Override
        public void changedUpdate(DocumentEvent e)
        {

        }
    }
}
