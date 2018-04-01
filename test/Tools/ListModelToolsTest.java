package Tools;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;


// Класс для тестирования класса ListModelTools.
class ListModelToolsTest
{
    // Проверка создания ListModel из пустого массива строк.
    @Test
    void testCreateListModelFromStringArrayWithEmptyStringArray()
    {
        // Подготовка.
        String[] data = new String[0];

        // Выполнение.
        DefaultListModel<String> model = ListModelTools.createListModelFromStringArray(data);
        int modelSize = model.getSize();

        // Проверка.
        assertEquals(0, modelSize);
    }

    // Проверка количества элементов при создании ListModel из массива строк.
    @Test
    void testCreateListModelFromStringArrayForElementsCount()
    {
        // Подготовка.
        String[] data = {"001", "002", "003", "abc", "-&t#"};

        // Выполнение.
        DefaultListModel<String> model = ListModelTools.createListModelFromStringArray(data);
        int result = model.getSize();

        // Проверка количества элементов.
        assertEquals(5, result);
    }

    // Проверка соответствия элементов при создании ListModel из массива строк.
    @Test
    void testCreateListModelFromStringArrayForMatchingOfElements()
    {
        // Подготовка.
        String[] data = {"001", "002", "003", "abc", "-&t#"};

        // Выполнение.
        DefaultListModel<String> model = ListModelTools.createListModelFromStringArray(data);

        // Проверка соответсвия элементов.
        for(String str : data)
        {
            boolean result = model.contains(str);

            assertTrue(result);
        }
    }

    // Проверка количества элементов при добавлении строки в ListModel.
    @Test
    void testAddStringInListModelForElementsCount()
    {
        // Подготовка.
        DefaultListModel<String> model = new DefaultListModel<>();

        // Выполнение.
        ListModelTools.addStringInListModel(model, "data", false);
        int modelSize = model.getSize();

        // Проверка.
        assertEquals(1, modelSize);
    }

    // Провеока соответствия элементов при добавлении строки в ListModel.
    @Test
    void testAddStringInListModelForMatchingOfElements()
    {
        // Подготовка.
        DefaultListModel<String> model = new DefaultListModel<>();

        // Выполнение.
        ListModelTools.addStringInListModel(model, "data", false);
        boolean result = model.contains("data");

        // Проверка.
        assertTrue(result);
    }

    // Проверка удаления отфильтрованных элементов при фильтрации модели.
    @Test
    void testFilterListModelByTextForDeletingFilteredElements()
    {
        // Подготовка.
        String[] allElements = {"A", "B", "C", "D", "E"};
        DefaultListModel<String> model = ListModelTools.createListModelFromStringArray(allElements);

        // Выполнение.
        ListModelTools.filterListModelByText(model, "A", allElements, true);
        boolean result = !model.contains("C");

        // Проверка.
        assertTrue(result);
    }

    // Проверка количества оставшихся элементов при фильтрации модели.
    @Test
    void testFilterListModelForRemainingElementsCount()
    {
        // Подготовка.
        String[] allElements = {"AA", "ABA", "AAC", "DADA", "AAEA", "BADEAAB"};
        DefaultListModel<String> model = ListModelTools.createListModelFromStringArray(allElements);

        // Выполнение.
        ListModelTools.filterListModelByText(model, "AA", allElements, true);
        int result = model.getSize();

        // Проверка.
        assertEquals(4, result);
    }

    // Провека порядка элементов при сортировке модели.
    @Test
    void testSortListModelForElementsOrder()
    {
        // Подготовка.
        String[] data = {"AAA", "BAA", "AAD", "AAC", "CCA", "ABA"};
        DefaultListModel<String> model = ListModelTools.createListModelFromStringArray(data);

        // Выполнение.
        ListModelTools.sortListModel(model);
        boolean result = model.elementAt(0).equals("AAA") &&  model.elementAt(3).equals("ABA");

        // Проверка.
        assertTrue(result);
    }

    // Проверка получения массива строк их пустой ListModel.
    @Test
    void testGetElementsFromEmptyListModel()
    {
        // Подготовка.
        DefaultListModel<String> model = new DefaultListModel<>();

        // Выполнение.
        String[] data = ListModelTools.getElementsFromListModel(model);
        int size = data.length;

        // Проверка.
        assertEquals(0, size);
    }

    // Проверка количества элементов при плучении массива строк из ListModel.
    @Test
    void testGetElementsFromListModelForElementsCount()
    {
        // Подготовка.
        String[] inputData = {"AAA", "BAA", "AAD", "AAC", "CCA", "ABA"};
        DefaultListModel<String> model = ListModelTools.createListModelFromStringArray(inputData);

        // Выполнение.
        String[] outputData = ListModelTools.getElementsFromListModel(model);
        int result = outputData.length;

        // Проверка.
        assertEquals(6, result);
    }

    // Проверка плучения массива строк из ListModel на соответствие элементов.
    @Test
    void testGetElementsFromListModelForMatchingOfElements()
    {
        // Подготовка.
        String[] inputData = {"AAA", "BAA", "AAD", "AAC", "CCA", "ABA"};
        DefaultListModel<String> model = ListModelTools.createListModelFromStringArray(inputData);

        // Выполнение.
        String[] outputData = ListModelTools.getElementsFromListModel(model);

        // Проверка.
        assertArrayEquals(inputData, outputData);
    }
}