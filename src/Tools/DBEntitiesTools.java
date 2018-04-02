package Tools;

import DB.DBService;
import Entities.*;


// Вспомогательный класс для работы с БД.
public class DBEntitiesTools
{
    // Добавление книги в БД.
    public static boolean addBook(String bookName, String yearStr, int readStateId, String langStr, String extStr)
    {
        // Получение основных данных.
        int year = 0;
        if (!yearStr.isEmpty())
        {
            year = Integer.parseInt(yearStr);
        }

        // Поиск книги.
        Book book = DBService.getInstance().getBookByNameAndYear(bookName, year);

        // Проверка на наличие.
        if (book == null)
        {
            // Получение дополнительных данных.
            int languageId = getLanguageId(langStr);
            int extensionId = DBEntitiesTools.getExtensionId(extStr);

            // Сборка объекта.
            book = new Book(bookName, year, readStateId, languageId, extensionId);

            // Добавление в БД.
            DBService.getInstance().addBook(book);

            // Завершение.
            return true;
        }
        else
        {
            return false;
        }
    }

    // Добавление авторов в БД.
    public static void addAuthors(String[] authorsNames)
    {
        // Обход авторов.
        for (String authorName : authorsNames)
        {
            // Поиск автора.
            Author author = DBService.getInstance().getAuthorByName(authorName);

            // Проверка на отсутствие результата.
            if (author == null)
            {
                // Сборка объекта.
                author = new Author(authorName);

                // Добавление автора.
                DBService.getInstance().addAuthor(author);
            }
        }
    }

    // Добавление меток в БД.
    public static void addTags(String[] tagsNames)
    {
        // Обход меток.
        for (String tagName : tagsNames)
        {
            // Поиск метки.
            Tag tag = DBService.getInstance().getTagByName(tagName);

            // Проверка на отсутствие результата.
            if (tag == null)
            {
                // Сборка объекта.
                tag = new Tag(tagName);

                // Добавление метки.
                DBService.getInstance().addTag(tag);
            }
        }
    }

    // Получение Id языка.
    private static int getLanguageId(String languageName)
    {
        // Поиск языка.
        Language language = DBService.getInstance().getLanguageByName(languageName);

        // Проверка наличия.
        if (language == null)
        {
            // Сборка объекта.
            language = new Language(languageName);

            // Добавление в БД.
            DBService.getInstance().addLanguage(language);

            // Получение языка с id.
            language = DBService.getInstance().getLanguageByName(languageName);
        }

        // Возврат результата.
        return language.getId();
    }

    // Получение Id расширения.
    private static int getExtensionId(String extensionName)
    {
        // Проверка на пустую строку.
        if (extensionName.isEmpty()) return 0;

        // Поиск расширения.
        Extension extension = DBService.getInstance().getExtensionByName(extensionName);

        // Проверка наличия.
        if (extension == null)
        {
            // Сборка объекта.
            extension = new Extension(extensionName);

            // Добавление в БД.
            DBService.getInstance().addExtension(extension);

            // Получение расширения с id.
            extension = DBService.getInstance().getExtensionByName(extensionName);
        }

        // Возврат результата.
        return extension.getId();
    }
}
