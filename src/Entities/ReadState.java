package Entities;


// Перечисление состояний чтения.
public enum ReadState
{
    // Экземпляры.
    NotRead(1), Reading(2), Read(3);

    // Данные.
    private int value;


    // Конструктор.
    ReadState(int value)
    {
        this.value = value;
    }

    // Геттер.
    public int getValue()
    {
        return value;
    }
}
