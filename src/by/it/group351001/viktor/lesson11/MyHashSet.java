package by.it.group351001.viktor.lesson11;

 /* реализует интерфейс Set<E> и работает на основе массива с адресацией по хеш-коду
            и односвязным списком для элементов с коллизиями */
/* Основные методы включают добавление, удаление, проверку наличия элементов и очистку набора */
/*Инициализирует массив узлов ->  индекс в массиве на основе хеш-кода объекта ->
 * Проверяет наличие элемента в соответствующем ведре.нету - добавляет в начало списка.  */

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
public class MyHashSet<E> implements Set<E> {

    // Внутренний класс для представления узла в односвязном списке
    private class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.next = next;
            this.value = value;
        }

        public Node(Node<E> next) {
            this.next = next;
        }
    }

    private int _size; // Количество элементов в наборе
    private Node<E>[] _arr; // Массив узлов для хранения элементов
    private final int DEFAULT_CAPACITY = 32; // Начальная емкость массива

    // Вычисляет индекс в массиве для заданного объекта на основе его хеш-кода
    private int getHashCode(Object o) {
        return o.hashCode() % _arr.length;
    }

    // Конструктор, инициализирующий массив узлов и размер набора
    public MyHashSet() {
        _arr = new Node[DEFAULT_CAPACITY];
        _size = 0;
    }

    // Возвращает строковое представление набора, проходя по всем узлам в массиве и добавляя их значения в строку
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (var current : _arr) {
            Node<E> temp = current;
            while (temp != null) {
                stringBuilder.append(temp.value).append(", ");
                temp = temp.next;
            }
        }
        if (stringBuilder.length() > 1) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    // Метод добавляет элемент в набор. Если элемент уже существует, он не добавляется
    @Override
    public boolean add(E e) {
        int hash = getHashCode(e);
        Node<E> current = _arr[hash];
        while (current != null) {
            if (current.value.equals(e))
                return false;
            current = current.next;
        }
        _arr[hash] = new Node<E>(e, _arr[hash]);
        _size++;
        return true;
    }

    // Возвращает количество элементов в наборе
    @Override
    public int size() {
        return _size;
    }

    // Метод удаляет элемент из набора, если он существует
    @Override
    public boolean remove(Object o) {
        int hash = getHashCode(o);
        Node<E> current = _arr[hash];
        Node<E> prev = null;
        while (current != null) {
            if (current.value.equals(o)) {
                if (prev == null) {
                    _arr[hash] = current.next;
                } else {
                    prev.next = current.next;
                }
                _size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    // Проверяет, пуст ли набор
    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    // Проверяет, содержится ли элемент в наборе
    @Override
    public boolean contains(Object o) {
        int hash = getHashCode(o);
        Node<E> current = _arr[hash];
        while (current != null) {
            if (current.value.equals(o))
                return true;
            current = current.next;
        }
        return false;
    }

    // Очищает набор, создавая новый пустой массив узлов
    @Override
    public void clear() {
        _arr = new Node[DEFAULT_CAPACITY];
        _size = 0;
    }

    // Возвращает итератор для набора (пока не реализован)
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    // Преобразует набор в массив (пока не реализован)
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    // Преобразует набор в массив заданного типа (пока не реализован)
    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    // Проверяет, содержатся ли все элементы из заданной коллекции в наборе (пока не реализован)
    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }
}