package Tools;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


// Тест класса проверки данных.
class ValidationToolsTest
{
    // Проверка правильной строки года.
    @Test
    void validateValidYear()
    {
        assertTrue(ValidationTools.validateYear("2000"));
    }

    // Проверка строки меньшего года.
    @Test
    void validateLessYear()
    {
        assertFalse(ValidationTools.validateYear("1460"));
    }

    // Проверка строки будущего года.
    @Test
    void validateFutureYear()
    {
        assertFalse(ValidationTools.validateYear("2025"));
    }

    // Проверка строки года с символами.
    @Test
    void validateInvalidYear()
    {
        assertFalse(ValidationTools.validateYear("2000abc"));
    }
}