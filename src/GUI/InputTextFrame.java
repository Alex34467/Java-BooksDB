package GUI;

import Tools.ListModelTools;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


// Окно для ввода текста.
public class InputTextFrame extends JDialog
{
    // Данные.
    private boolean okExit = false;
    private String result;
    private String[] values;
    private List<String> restrictedValues;
    private DefaultListModel<String> textListModel;

    // Обработчики событий.
    private ButtonClickListener buttonClickListener;
    private MyDocumentListener documentListener;
    private MyListSelectionListener listSelectionListener;

    // Элементы.
    private JTextField textField;
    private JList<String> textList;


    // Конструктор.
    public InputTextFrame(JDialog owner, String title, String[] values, String[] restrictedValues,  boolean modal)
    {
        // Инициализация окна.
        super(owner, title, modal);
        setBounds(500, 500, 220, 250);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Инициализация массивов значений.
        this.values = values;
        this.restrictedValues = Arrays.asList(restrictedValues);

        // Инициализация модели списка.
        textListModel = ListModelTools.createListModelFromStringArray(values);
        ListModelTools.sortListModel(textListModel);

        // Инициализация обработчиков.
        buttonClickListener = new ButtonClickListener();
        documentListener = new MyDocumentListener();
        listSelectionListener = new MyListSelectionListener();

        // Создание интерфейса.
        makeGUI();
    }

    // Отображение.
    public String showDialog()
    {
        // Показ окна.
        setVisible(true);

        // Проверка на закрытие.
        if (!okExit) return "";

        // Воврат введенного текста.
        return result;
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
        textList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        textList.getSelectionModel().addListSelectionListener(listSelectionListener);
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
        buttonsPanel.add(Box.createRigidArea(new Dimension(75, 0)));
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
                    // Проверка значения.
                    if (restrictedValues.contains(result))
                    {
                        textField.setBackground(new Color(255,102,102));
                    }
                    else
                    {
                        // Завершение.
                        okExit = true;
                        InputTextFrame.this.dispose();
                    }
                    break;
                case "Cancel":
                    InputTextFrame.this.dispose();
                    break;
            }
        }
    }

    // Обработчик событий текстового документа.
    private class MyDocumentListener implements DocumentListener
    {
        // Добавление текста.
        @Override
        public void insertUpdate(DocumentEvent e)
        {
            updateText();
        }

        // Удаление текста.
        @Override
        public void removeUpdate(DocumentEvent e)
        {
            updateText();
        }

        // Изменение текста.
        @Override
        public void changedUpdate(DocumentEvent e)
        {

        }

        // Изменение текста.
        private void updateText()
        {
            textField.setBackground(Color.WHITE);
            result = textField.getText();
            ListModelTools.filterListModelByText(textListModel, textField.getText(), values, true);
        }
    }

    // Обработчик событий списка.
    private class MyListSelectionListener implements ListSelectionListener
    {
        // Выбор элемента.
        @Override
        public void valueChanged(ListSelectionEvent e)
        {
            // Получение модели выбора.
            ListSelectionModel model = (ListSelectionModel)e.getSource();

            if (!model.isSelectionEmpty())
            {
                // Получение элемента.
                result = textList.getSelectedValue();
            }
        }
    }
}
