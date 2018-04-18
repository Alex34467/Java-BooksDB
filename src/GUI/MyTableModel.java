package GUI;

import DB.DBService;
import Entities.BooksViewEntity;
import javax.swing.table.AbstractTableModel;
import java.util.List;


// Модель таблицы.
public class MyTableModel extends AbstractTableModel
{
    // Данные.
    private List<BooksViewEntity> booksViewEntities = DBService.getInstance().getAllBooksViewEntities();


    // Возврат количества строк.
    @Override
    public int getRowCount()
    {
        return booksViewEntities.size();
    }

    // Возврат количества столбцов.
    @Override
    public int getColumnCount()
    {
        return 5;
    }

    // Возврат значения ячейки.
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        // Получение объекта.
        BooksViewEntity booksViewEntity = booksViewEntities.get(rowIndex);

        // Возврат значений.
        switch (columnIndex)
        {
            case 0:
                return booksViewEntity.getAuthors();
            case 1:
                return booksViewEntity.getName();
            case 2:
                return booksViewEntity.getYear();
            case 3:
                return booksViewEntity.getLanguage();
            case 4:
                return booksViewEntity.getReadState();
            default:
                return null;
        }
    }

    // Возврат флага редактирования ячейки.
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }

    // Возврат имени слолбца.
    @Override
    public String getColumnName(int column)
    {
        switch (column)
        {
            case 0:
                return "Авторы";
            case 1:
                return "Название книги";
            case 2:
                return "Год";
            case 3:
                return "Язык";
            case 4:
                return "Состояние";
            default:
                return "ERROR";
        }
    }
}
