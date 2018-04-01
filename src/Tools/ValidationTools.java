package Tools;

import java.util.Calendar;

// Класс для валидации.
public class ValidationTools
{
    // Проверка года.
    public static boolean validateYear(String yearStr)
    {
        // Проверка наличия строки.
        if (yearStr.isEmpty()) return false;

        try
        {
            // Проверка на число.
            int year = Integer.parseInt(yearStr);

            // Проверка значения года.
            if (year <= 1500 || year > Calendar.getInstance().get(Calendar.YEAR)) return false;
        }
        catch (NumberFormatException e)
        {
            return false;
        }

        // Возврат результата.
        return true;
    }
}
