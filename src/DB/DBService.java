package DB;

import DB.Repository.AuthorsRepository;
import DB.Repository.BooksRepository;
import DB.Repository.TagsRepository;
import Entities.Author;
import Entities.Book;
import Entities.Tag;
import java.io.File;
import java.util.Collection;


// Класс, управляющий БД.
public class DBService
{
    // Данные БД.
    private static DBService instance;
    private DBExecutor executor;
    private String fileName = "Data/Books.db";
    private String URL = "jdbc:sqlite:" + fileName;

    // Репозитории.
    private AuthorsRepository authorsRepository;
    private TagsRepository tagsRepository;
    private BooksRepository booksRepository;


    // Геттер.
    public static DBService getInstance()
    {
        if (instance == null) instance = new DBService();
        return instance;
    }

    // Получение всех авторов.
    public Collection<Author> getAllAuthors()
    {
        return authorsRepository.getAll();
    }

    // Получение всех меток.
    public Collection<Tag> getAllTags()
    {
        return tagsRepository.getAll();
    }

    // Получение автора по имени.
    public Author getAuthorByName(String name)
    {
        return authorsRepository.getByName(name);
    }

    // Получение метки по названию.
    public Tag getTagByName(String name)
    {
        return tagsRepository.getByName(name);
    }

    // Получение книги по названию и году.
    public Book getBookByNameAndYear(String name, int year)
    {
        return booksRepository.getByNameAndYear(name, year);
    }

    // Получение имен всех авторов.
    public String[] getAllAuthorsNames()
    {
        return authorsRepository.getAllNames();
    }

    // Получение названий всех меток.
    public String[] getAllTagsNames()
    {
        return tagsRepository.getAllNames();
    }

    // Добавление автора.
    public void addAuthor(Author author)
    {
        authorsRepository.add(author);
    }

    // Добавление метки.
    public void addTag(Tag tag)
    {
        tagsRepository.add(tag);
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

        // Инициализация репозиториев.
        authorsRepository = new AuthorsRepository(executor);
        tagsRepository = new TagsRepository(executor);
        booksRepository = new BooksRepository(executor);
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
