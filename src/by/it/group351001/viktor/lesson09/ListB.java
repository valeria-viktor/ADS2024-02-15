package by.it.group351001.viktor.lesson09;

import java.util.*;

public class ListB<E> implements List<E> {


    //Создайте аналог списка БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    private E[] data = (E[]) new Object[0];  //массив для хранения эл списка

    private int size = 0;  //для текущего кол-ва эл в списке

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");  //объект для построения строки
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);  //добавляем эл в строку
            if (i == size - 1) {
                sb.append("]");
                return sb.toString();
            }
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E e) {
        try {
            if (size >= data.length) {  // нужно ли увеличить размер массива
                E[] ndata = (E[]) new Object[data.length != 0 ? data.length << 1 : 10]; //создаем массив больший в 2 раза или размером 10(если текущий был пуст
                System.arraycopy(data, 0, ndata, 0, size);  // копир эл массива в новый
                data = ndata;  // присваиваем новому массиву ссылку на data
            }
            data[size++] = e;  //добавляем новый эл е и увелич размер
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public E remove(int index) {
        if (index >= size) {  // проверка допустим ли индекс эл
            return null;
        }
        E rem = data[index];
        System.arraycopy(data, index + 1, data, index, size - 1 - index);  // сдвигаем эл после удаленного влево
        size--;
        return rem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {  //добавление элемента по заданному индексу
        if (size >= data.length) {  // нужно ли увеличить размер массива
            E[] ndata = (E[]) new Object[data.length != 0 ? data.length << 1 : 10];  //создаем массив больший в 2 раза или размером 10(если текущий был пуст
            System.arraycopy(data, 0, ndata, 0, size); // копир эл массива в новый
            data = ndata;  // присваиваем новому массиву ссылку на data
        }
        System.arraycopy(data, index, data, index + 1, size - index); //сдвигаем эл с позиции index вправо на 1
        data[index] = element; //добавлем элемент
        size++;
    }

    @Override
    public boolean remove(Object o) {  //удаление первого элемента равного переданному
        for (int i = 0; i < size; i++) {
            if (o.equals(data[i])) { //сравниваем эл
                remove(i);  //удаляем, если найден
                return true;
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {  //заменяет эл под переданным индексом на переданный элемент
        if (index >= size) {
            return null;
        }
        E tmp = data[index];  //сохраняем старый эл
        data[index] = element;  //заменяем его на переданный
        return tmp; //возвращаем знач старого эл
    }


    @Override
    public boolean isEmpty() {  //проверяет, пуст ли список
        return size == 0;
    }


    @Override
    public void clear() {  //очищает список
        data = (E[]) new Object[0];
        size = 0;
    }

    @Override
    public int indexOf(Object o) {  //ищет индекс первого вхождения элемента о
        for (int i = 0; i < size; i++) {
            if (o.equals(data[i]))  //если эл найден, возвращаем индекс
                return i;
        }
        return -1;
    }

    @Override
    public E get(int index) {  //возвращает элемент по заданному индексу
        if (index >= size)
            return null;
        return data[index];
    }

    @Override
    public boolean contains(Object o) {  //проверяет есть ли объект в списке
        for (int i = 0; i < size; i++)
            if (o.equals(data[i]))
                return true;
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {  //ищет индекс последнего вхождения эл о
        for (int i = size - 1; i >= 0; i--)
            if (o.equals(data[i]))
                return i;
        return -1;
    }


    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////


    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }


    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////        Эти методы имплементировать необязательно    ////////////
    ////////        но они будут нужны для корректной отладки    ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public Iterator<E> iterator() {
        return null;
    }

}
