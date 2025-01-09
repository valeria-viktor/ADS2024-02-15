package by.it.group351001.viktor.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyLinkedList<E> implements Deque<E> {
    static class Elem<E> {  //статический класс, представляющий элемент списка
        public Elem<E> next;
        public Elem<E> prev;
        public E elem;
    }

    private Elem<E> head;
    private Elem<E> tail;
    private int size = 0;

    @Override
    public String toString() { //формирует строковое представление списка
        StringBuilder res = new StringBuilder("[");  //объект для построения итоговой строки
        String del = "";

        Elem<E> curr = head;
        while (curr != tail) {
            res.append(del).append(curr.elem);  //добавляем элементы
            curr = curr.next;
            del = ", ";  //разделяем запятыми
        }

        return res.append("]").toString();
    }

    @Override
    public boolean add(E e) {  //добавляем новый элемент
        if (size == 0) {  //если список пустой, создаем новый
            head = new Elem<E>();
            tail = new Elem<E>();
            head.next = tail;
            tail.prev = head;
            head.prev = null;
            tail.next = null;
            size++;
            tail.prev.elem = e;
            return true;
        }

        tail.elem = e;  //иначе добавлем в хвост элемент
        Elem<E> newElem = new Elem<E>();
        tail.next = newElem;
        newElem.prev = tail;
        tail = newElem;

        size++;
        return true;
    }

    @Override
    public void addFirst(E e) {  //добавляет элемент в начало списка
        if (size == 0) {  //если пустой список, создаем новый
            head = new Elem<E>();
            tail = new Elem<E>();
            head.next = tail;
            tail.prev = head;
            head.prev = null;
            tail.next = null;
            size++;
            tail.prev.elem = e;
            return;
        }

        Elem<E> newElem = new Elem<E>();  //иначе добавляем на место head
        head.prev = newElem;
        newElem.next = head;
        newElem.elem = e;

        head = newElem;
        size++;
    }

    @Override
    public void addLast(E e) { //добавляет элемент в конец списка
        add(e);
    }

    public E remove(int index) {  //удаляет элемент по индексу
        if (index >= size || size == 0)  //Если индекс выходит за пределы размера списка или список пуст
            return null;

        if (index == 0) { //Если индекс 0, ставит на место head
            E el = head.elem;
            head = head.next;
            head.prev = null;

            size--;
            return el;
        }

        Elem<E> curr = head;  //иначе удаляет эл и обновляет ссылки
        int j = 0;
        while (curr != tail.prev) {
            if (j == index) {
                E el = curr.elem;

                curr.next.prev = curr.prev;
                curr.prev.next = curr.next;
                curr.prev = null;
                curr.next = null;

                size--;
                return el;
            }

            curr = curr.next;
            j++;
        }

        if (j == index) {
            E el = tail.prev.elem;
            tail = tail.prev;
            tail.next = null;

            size--;
            return el;
        }
        return null;
    }

    @Override
    public boolean remove(Object e) { //удаляет по значению
        if (size == 0)
            return false;

        if (e.equals(head.elem)) {  //если первый, удаляется первый элемент, и head обновляется
            head = head.next;
            head.prev = null;

            size--;
            return true;
        }

        Elem<E> curr = head; //если в середине списка, обновляются ссылки на соседние элементы
        while (curr != tail.prev) {
            if (e.equals(curr.elem)) {
                curr.next.prev = curr.prev;
                curr.prev.next = curr.next;
                curr.next = null;
                curr.prev = null;

                size--;
                return true;
            }

            curr = curr.next;
        }

        if (e.equals(tail.prev.elem)) {
            tail = tail.prev;
            tail.next = null;

            size--;
            return true;
        }

        return false;
    }

    @Override
    public E element() {  //возвращает первый элемент списка
        if (size == 0)
            return null;
        return head.elem;
    }

    @Override
    public E getFirst() {  //возвращает первый элемент списка
        return element();
    }

    @Override
    public E getLast() { //Возвращает последний элемент
        if (size == 0)
            return null;
        return tail.prev.elem;
    }

    @Override
    public E poll() {  //Удаляет и возвращает первый элемент списка
        if (size == 0)
            return null;

        E el = head.elem;
        head = head.next;
        head.prev = null;

        size--;
        return el;
    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        if (size == 0)
            return null;

        E el = tail.prev.elem;
        tail = tail.prev;
        tail.next = null;

        size--;
        return el;
    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
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
    public void clear() {

    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
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
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }
}