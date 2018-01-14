package GUI;

import javax.swing.table.AbstractTableModel;


// Модель таблицы.
public class MyTableModel extends AbstractTableModel
{
    // Возврат количества строк.
    @Override
    public int getRowCount()
    {
        return 50;
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
        return null;
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
