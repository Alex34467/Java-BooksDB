package DB;

import java.io.File;


// Класс, управляющий БД.
public class DBService
{
    // Данные.
    private static DBService instance;
    private DBExecutor executor;
    private String fileName = "Data/Books.db";
    private String URL = "jdbc:sqlite:" + fileName;


    // Геттер.
    public static DBService getInstance()
    {
        if (instance == null) instance = new DBService();
        return instance;
    }

    // Конструктор.
    private DBService()
    {
        // Инициализация исполнителя.
        executor = new SQLiteExecutor();

        // Подключение к БД.
        File file = new File(fileName);
        if (file.exists())
        {
            executor.connect(URL);
        }
        else
        {
            executor.connect(URL);
            createDB();
        }
    }

    // Созвдание БД.
    private void createDB()
    {
        // Создание таблицы состояний.
        executor.executeUpdate("CREATE TABLE ReadStates (\n" +
                "    id   INTEGER   PRIMARY KEY AUTOINCREMENT,\n" +
                "    name CHAR (16) UNIQUE\n" +
                "                   NOT NULL\n" +
                ");\n");

        // Создание таблицы языков.
        executor.executeUpdate("CREATE TABLE Languages (\n" +
                "    id   INTEGER   PRIMARY KEY AUTOINCREMENT\n" +
                "                   NOT NULL,\n" +
                "    name CHAR (32) NOT NULL\n" +
                "                   UNIQUE\n" +
                ");");

        // Создание таблицы расширений файлов.
        executor.executeUpdate("CREATE TABLE Extensions (\n" +
                "    id   INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                 NOT NULL,\n" +
                "    name         UNIQUE\n" +
                "                 NOT NULL\n" +
                ");\n");

        // Создание таблицы категорий.
        executor.executeUpdate("CREATE TABLE Categories (\n" +
                "    id   INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                 NOT NULL,\n" +
                "    name TEXT    NOT NULL\n" +
                "                 UNIQUE\n" +
                ");\n");

        // Содание таблицы авторов.
        executor.executeUpdate("CREATE TABLE Authors (\n" +
                "    id   INTEGER   PRIMARY KEY AUTOINCREMENT\n" +
                "                   NOT NULL,\n" +
                "    name TEXT (20) NOT NULL\n" +
                "                   UNIQUE\n" +
                ");\n");

        // Создание таблицы книг.
        executor.executeUpdate("CREATE TABLE Books (\n" +
                "    id            INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                          NOT NULL,\n" +
                "    name          TEXT    NOT NULL,\n" +
                "    year          INTEGER NOT NULL,\n" +
                "    read_state_id INTEGER NOT NULL\n" +
                "                          REFERENCES ReadStates (id),\n" +
                "    language_id           REFERENCES Languages (id) \n" +
                "                          NOT NULL,\n" +
                "    extension_id          NOT NULL\n" +
                "                          REFERENCES Extensions (id),\n" +
                "    UNIQUE (\n" +
                "        name,\n" +
                "        year\n" +
                "    )\n" +
                ");\n");

        // Создание таблицы книг и авторов.
        executor.executeUpdate("CREATE TABLE BooksAndAuthors (\n" +
                "    id        INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    author_id         REFERENCES Authors (id),\n" +
                "    book_id           REFERENCES Books (id),\n" +
                "    UNIQUE (\n" +
                "        author_id,\n" +
                "        book_id\n" +
                "    )\n" +
                ");\n");

        // Создание таблицы книг и категорий.
        executor.executeUpdate("CREATE TABLE BooksAndCategories (\n" +
                "    id          INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    category_id         REFERENCES Categories (id),\n" +
                "    book_id             REFERENCES Books (id),\n" +
                "    UNIQUE (\n" +
                "        category_id,\n" +
                "        book_id\n" +
                "    )\n" +
                ");\n");
    }
}
