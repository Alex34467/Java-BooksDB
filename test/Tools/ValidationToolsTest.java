package Tools;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


// Тест класса проверки данных.
class ValidationToolsTest
{
    // Проверка правильной строки года.
    @Test
    void testValidateYearWithValidData()
    {
        boolean result = ValidationTools.validateYear("2000");

        assertTrue(result);
    }

    // Проверка строки с отрицательным годом.
    @Test
    void testValidateYearWithNegativeYear()
    {
        boolean result = ValidationTools.validateYear("-1950");

        assertFalse(result);
    }

    // Проверка строки меньшего года.
    @Test
    void testValidateYearWithLessYear()
    {
        boolean result = ValidationTools.validateYear("1460");

        assertFalse(result);
    }

    // Проверка строки будущего года.
    @Test
    void testValidateYearWithFutureYear()
    {
        boolean result = ValidationTools.validateYear("2050");

        assertFalse(result);
    }

    // Проверка неправильной строки года.
    @Test
    void testValidateYearWithInvalidData()
    {
        boolean result = ValidationTools.validateYear("2000&abc");

        assertFalse(result);
    }
}