package by.it.group351001.viktor.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyLinkedHashSet<E> implements Set<E> {

    // Внутренний класс для представления узла в односвязном списке
    private class Node<E> {
        E value;
        Node<E> next;
        Node<E> after;
        Node<E> before;

        // Конструктор для создания узла с заданным значением и ссылками на следующий, предыдущий и следующий в связном списке узлы
        public Node(E value, Node<E> next, Node<E> before, Node<E> after) {
            this.value = value;
            this.next = next;
            this.before = before;
            this.after = after;
        }

        // Конструктор для создания узла с заданной ссылкой на следующий узел
        public Node(Node<E> next) {
            this.next = next;
        }
    }

    private final int DEFAULT_CAPACITY = 16; // Начальная емкость массива
    private int _size; // Количество элементов в наборе
    private Node<E>[] _items; // Массив узлов для хранения элементов
    private Node<E> head; // Голова связного списка
    private Node<E> tail; // Хвост связного списка

    private int getHashCode(Object o) {
        return o.hashCode() % _items.length;
    }

    private void addNode(Node<E> node){ //Добавляет элемент в множество. Перед добавлением проверяет на наличие дубликатов в том же хэш-ведре.
        if (head == null) {
            head = tail = node;
        }
        else {
            node.before = tail;
            tail.after = node;
            tail = tail.after;
        }
    }

    private void removeNode(Node<E> node) { //Удаляет элемент из множества, если он существует, обновляя как хэш-таблицу, так и связный список.
        if (node.before != null) {
            node.before.after = node.after;
        }
        else {
            head = node.after;
        }
        if (node.after != null) {
            node.after.before = node.before;
        }
        else {
            tail = node.before;
        }
    }

    public MyLinkedHashSet() {
        _size = 0;
        _items = new Node[DEFAULT_CAPACITY];
        head = tail = null;
    }

    public String toString() { //возвращает строковое представление набора, проходя по всем узлам в массиве и добавляя их значения в строку.
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        Node<E> current = head;
        while (current != null) {
            stringBuilder.append(current.value).append(", ");
            current = current.after;
        }
        if (stringBuilder.length() > 1) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    @Override
    public boolean add(E e) {
        int hash = getHashCode(e);
        Node<E> current = _items[hash];
        while (current != null) {
            if (current.value.equals(e))
                return false;
            current = current.next;
        }
        // add to _items
        _items[hash] = new Node<>(e,_items[hash],tail,null);
        // add to tail
        addNode(_items[hash]);
        _size++;
        return true;
    }

    @Override
    public boolean remove(Object o) { // Удаляет элемент из множества, если он существует, обновляя как хэш-таблицу, так и связный список.
        int hash = getHashCode(o); //вычисляет индекс в массиве , используя хэш-код элемента o.
        Node<E> current = _items[hash]; // первый узел в ведре
        Node<E> prev = null; //отслеживания узла перед current
        while (current != null) { // пока не будут пройдены все узлы в ведре.
            if (current.value.equals(o)) { // проверка на равенство
                if (prev == null) {
                    _items[hash] = current.next;
                }
                else {
                    prev.next = current.next;
                }
                removeNode(current);
                _size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }
    @Override
    public boolean contains(Object o) {
        Node<E> current = _items[getHashCode(o)];
        while (current != null) {
            if (current.value.equals(o))
                return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public void clear() {
        _items = new Node[DEFAULT_CAPACITY];
        _size = 0;
        head = tail = null;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (var obj: collection) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        boolean isModified = false;
        for (var obj: collection) {
            if (add(obj) && !isModified)
                isModified = true;
        }
        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean isModified = false;
        Node<E> current = head;
        while (current != null) {
            Node<E> tempNext = current.after;
            if (!collection.contains(current.value)) {
                remove(current.value);
                if (!isModified)
                    isModified = true;
            }
            current = tempNext;
        }
        return isModified;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean isModified = false;
        for (var obj: collection) {
            if (remove(obj) && !isModified)
                isModified = true;
        }
        return isModified;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }
}