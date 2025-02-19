package by.it.group351001.viktor.lesson10;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyArrayDeque<E> implements Deque<E> {
    private int minCapacity = 5; //минимальная начальная емкость
    private E[] arr = (E[]) new Object[minCapacity];  //массив для эл очереди
    private int size = 0;
    private int head = 0;
    private int tail = 0;

    private void resize() {  //увеличивает емкость массива, когда он становится полным
        E[] newArr = (E[]) new Object[size * 2 + 1];  //новый массив
        int j = 0;
        for (int i = head; i != dec(tail); i = inc(i)) {  //перекидывание элементов из старого
            newArr[j] = arr[i];
            arr[i] = null;
            j++;
        }
        newArr[j] = arr[dec(tail)];

        head = 0;
        tail = size;
        arr = newArr;
    }

    private int dec(int i) {  //метод для создания круговой структуры
        i--;
        if (i < 0)
            i = arr.length - 1;
        return i;
    }

    private int inc(int i) {  //метод для создания круговой структуры
        i++;
        if (i >= arr.length)
            i = 0;
        return i;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("[");  //объект для построения итоговой строки

        String del = "";
        for (int i = head; i != dec(tail); i = inc(i)) {  //проходим по массиву
            res.append(del).append(arr[i]);  //добавляем элементы
            del = ", ";  //разделяем запятыми
        }
        res.append(del).append(arr[dec(tail)]).append("]");  //добавляем последний элемент
        return res.toString();
    }

    @Override
    public int size() {  //возвращ размер
        return size;
    }

    @Override
    public boolean add(E e) {  //добавляем новый элемент
        if (size == arr.length)
            resize();  //увелич если надо

        arr[tail] = e;
        tail = inc(tail);
        size++;
        return true;
    }

    @Override
    public void addFirst(E e) {  //добавляем эл в начало
        if (size == arr.length)
            resize();

        head = dec(head);
        arr[head] = e;
        size++;
    }

    @Override
    public void addLast(E e) {  //добавляем в конец
        add(e);
    }

    @Override
    public E element() {  //возвращает первый элемент очереди, иначе null
        if (size == 0)
            return null;
        return arr[head];
    }

    @Override
    public E getFirst() {  //возвращ первый элемент очереди
        return element();
    }

    @Override
    public E getLast() {  //Возвращает последний элемент очереди
        if (size == 0)
            return null;
        return arr[dec(tail)];
    }

    @Override
    public E poll() {  //Удаляет и возвращает первый элемент очереди
        if (size == 0)
            return null;

        E el = arr[head];
        arr[head] = null;
        head = inc(head);
        size--;

        return el;
    }

    @Override
    public E pollFirst() {  //Аналогично poll().
        return poll();
    }

    @Override
    public E pollLast() { //Удаляет и возвращает последний элемент очереди
        if (size == 0)
            return null;

        E el = arr[dec(tail)];
        arr[tail] = null;
        tail = dec(tail);
        size--;

        return el;
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

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
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
    public boolean remove(Object o) {
        return false;
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

}
