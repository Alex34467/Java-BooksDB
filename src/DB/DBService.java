package DB;

import DB.Repository.*;
import Entities.*;

import java.io.File;
import java.util.Collection;
import java.util.List;


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
    private LanguagesRepository languagesRepository;
    private ExtensionsRepository extensionsRepository;
    private BookAndAuthorsRepository bookAndAuthorsRepository;
    private BooksAndTagsRepository booksAndTagsRepository;
    private BooksViewRepository booksViewRepository;


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

    // Получение id авторов по именам.
    public int[] getAuthorsIdsByNames(String[] names)
    {
        return authorsRepository.getIdsByNames(names);
    }

    // Получение метки по названию.
    public Tag getTagByName(String name)
    {
        return tagsRepository.getByName(name);
    }

    // Получение id меток по названию.
    public int[] getTagsIdsByNames(String[] names)
    {
        return tagsRepository.getIdsByNames(names);
    }

    // Получение книги по названию и году.
    public Book getBookByNameAndYear(String name, int year)
    {
        return booksRepository.getByNameAndYear(name, year);
    }

    // Получение языка на названию.
    public Language getLanguageByName(String name)
    {
        return languagesRepository.getByName(name);
    }

    // Получение расширения по названию.
    public Extension getExtensionByName(String name)
    {
        return extensionsRepository.getByName(name);
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

    // Получение всех записей из представления книг.
    public List<BooksViewEntity> getAllBooksViewEntities()
    {
        return booksViewRepository.getAll();
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

    // Добавление книги.
    public void addBook(Book book)
    {
        booksRepository.add(book);
    }

    // Добавление языка.
    public void addLanguage(Language language)
    {
        languagesRepository.add(language);
    }

    // Добавление расширения.
    public void addExtension(Extension extension)
    {
        extensionsRepository.add(extension);
    }

    // Добавление записи об книге и авторе.
    public void addBookAndAuthor(BooksAndAuthorsEntity entity)
    {
        bookAndAuthorsRepository.add(entity);
    }

    // Добавление записи об книге и метке.
    public void addBookAndTag(BooksAndTagsEntity entity)
    {
        booksAndTagsRepository.add(entity);
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
        languagesRepository = new LanguagesRepository(executor);
        extensionsRepository = new ExtensionsRepository(executor);
        bookAndAuthorsRepository = new BookAndAuthorsRepository(executor);
        booksAndTagsRepository = new BooksAndTagsRepository(executor);
        booksViewRepository = new BooksViewRepository(executor);
    }

    // Созвдание БД.
    // FIXME: Доработать.
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
        executor.executeUpdate("CREATE TABLE Tags (\n" +
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
                "    year          INTEGER,\n" +
                "    read_state_id INTEGER NOT NULL\n" +
                "                          REFERENCES ReadStates (id),\n" +
                "    language_id           REFERENCES Languages (id) \n" +
                "                          NOT NULL,\n" +
                "    extension_id          REFERENCES Extensions (id),\n" +
                "    UNIQUE (\n" +
                "        name,\n" +
                "        year\n" +
                "    )\n" +
                ");");

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
        executor.executeUpdate("CREATE TABLE BooksAndTags (\n" +
                "    id          INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    tag_id         REFERENCES Categories (id),\n" +
                "    book_id             REFERENCES Books (id),\n" +
                "    UNIQUE (\n" +
                "        tag_id,\n" +
                "        book_id\n" +
                "    )\n" +
                ");\n");

        // Создание представления.
        executor.executeUpdate("CREATE VIEW BooksView AS\n" +
                "    SELECT Authors.Name AS Author,\n" +
                "           Books.Name,\n" +
                "           Year,\n" +
                "           Languages.name AS Language,\n" +
                "           ReadStates.name AS ReadState\n" +
                "      FROM Books\n" +
                "           LEFT JOIN\n" +
                "           BooksAndAuthors ON Books.id = BooksAndAuthors.book_id\n" +
                "           LEFT JOIN\n" +
                "           Authors ON BooksAndAuthors.author_id = Authors.id\n" +
                "           LEFT JOIN\n" +
                "           ReadStates ON Books.read_state_id = ReadStates.id\n" +
                "           LEFT JOIN\n" +
                "           Languages ON Books.language_id = Languages.id;");

        // Заполнение таблицы состояний.
        executor.executeUpdate("INSERT INTO ReadStates(Name) VALUES(\"Не прочитано\"), (\"Чтение\"), (\"Прочитано\")");
    }
}
