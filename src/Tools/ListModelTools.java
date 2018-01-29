package Tools;

import javax.swing.*;
import java.util.Collections;
import java.util.List;


// Вспомогательный класс.
public class ListModelTools
{
    // Создание модели из массива.
    public static DefaultListModel<String> createJListModelFromStringArray(String[] data)
    {
        // Создание модели.
        DefaultListModel<String> model = new DefaultListModel<>();

        // Заполнение модели.
        for (String author : data)
        {
            model.addElement(author);
        }

        // Возврат модели.
        return model;
    }

    // Добавление строки в модель.
    public static void addStringInListModel(DefaultListModel<String> model, String value, boolean sort)
    {
        // Добавление без сортировки.
        if (sort)
        {
            // Преобразование в список.
            List<String> list = Collections.list(model.elements());

            // Добавление элемента.
            list.add(value);

            // Сортировка.
            Collections.sort(list);

            // Очистка модели.
            model.clear();

            // Добавление элементов.
            for (String str : list)
            {
                model.addElement(str);
            }
        }
        else
        {
            model.addElement(value);
        }
    }

    // Фильтрация модели.
    public static void filterByText(DefaultListModel<String> model, String text, String[] defaultValues, boolean sort)
    {
        // Проверка.
        if (defaultValues == null) return;

        // Фильтрация.
        for (String value : defaultValues)
        {
            if (!value.contains(text))
            {
                if (model.contains(value))
                {
                    model.removeElement(value);
                }
            }
            else
            {
                if (!model.contains(value))
                {
                    model.addElement(value);
                }
            }
        }

        // Сортировка.
        if (sort)
        {
            sortListModel(model);
        }
    }

    // Сортировка модели.
    public static void sortListModel(DefaultListModel<String> model)
    {
        // Преобразование в список.
        List<String> list = Collections.list(model.elements());

        // Сортировка.
        Collections.sort(list);

        // Очистка модели.
        model.clear();

        // Добавление элементов.
        for (String str : list)
        {
            model.addElement(str);
        }
    }
}
