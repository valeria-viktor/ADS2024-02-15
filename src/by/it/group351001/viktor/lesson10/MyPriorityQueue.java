package by.it.group351001.viktor.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyPriorityQueue<E> implements Queue<E> {
    private int minCapacity = 8;  //Минимальная начальная емкость массива
    private E[] arr = (E[]) new Object[minCapacity];  //Массив, который хранит элементы очереди
    int size = 0;

    private int parent(int i) {  //Возвращает индекс родительского элемента
        return (i - 1) / 2;
    }

    private int right(int i) {  //Возвращает индекс правого дочернего элемента
        return i * 2 + 2;
    }

    private int left(int i) {  //Возвращает индекс левого дочернего элемента
        return i * 2 + 1;
    }

    private void resize() {  //Увеличивает размер массива, когда он заполняется
        E[] newArr = (E[]) new Object[size * 2 + 1];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    private void siftDown(int i) {  //осстанавливает свойства кучи, начиная с i
        int left = left(i);
        int right = right(i);

        if (left >= size)
            return;

        int largest = left;
        if (right < size && ((Comparable<E>) arr[right]).compareTo(arr[left]) < 0)
            largest = right;

        if (((Comparable<E>) arr[largest]).compareTo(arr[i]) >= 0)
            return;

        E tmp = arr[largest];
        arr[largest] = arr[i];
        arr[i] = tmp;

        siftDown(largest);
    }

    private void siftUp(int i) {  //Восстанавливает свойства кучи, начиная с i
        if (i == 0) {
            return;
        }
        int parent = parent(i);
        if (((Comparable<E>) (arr[parent])).compareTo(arr[i]) < 0) {
            return;
        }

        E tmp = arr[parent];
        arr[parent] = arr[i];
        arr[i] = tmp;

        siftUp(parent);
    }

    @Override
    public int size() {  //возвращает текущий размер очереди
        return size;
    }

    public String toString() {  //формирует строковое представление приоритетной очереди
        StringBuilder res = new StringBuilder("[");
        String del = "";

        for (int i = 0; i < size; i++) {
            res.append(del).append(arr[i]);
            del = ", ";
        }

        return res.append("]").toString();
    }

    @Override
    public void clear() {  //очищает очередь, устанавливая значения в null и сбрасывая размер до 0
        for (int i = 0; i < size; i++)
            arr[i] = null;
        size = 0;
    }

    @Override
    public boolean add(E e) {
        if (size == arr.length)
            resize();

        arr[size] = e;
        siftUp(size);
        size++;
        return true;
    }

    @Override
    public E remove() {   //удаляет и возвращает корневой элемент
        if (size == 0) {
            return null;
        }

        size--;
        E el = arr[0];
        arr[0] = arr[size];
        arr[size] = null;

        siftDown(0);

        return el;
    }

    @Override
    public boolean contains(Object o) {  //проверяет, содержится ли элемент в очереди
        for (int i = 0; i < size; i++) {
            if (o.equals(arr[i]))
                return true;
        }
        return false;
    }

    @Override
    public boolean offer(E e) {  //добавляет элемент в очередь
        return add(e);
    }

    @Override
    public E poll() {  //удаляет и возвращает корневой элемент, но не выбрасывает исключение, если очередь пуста
        if (size == 0) {
            return null;
        }

        E el = arr[0];
        size--;
        arr[0] = arr[size];
        arr[size] = null;

        siftDown(0);

        return el;
    }

    @Override
    public E peek() {  //Возвращает корневой элемент без его удаления.
        if (size == 0)
            return null;
        return arr[0];
    }

    @Override
    public E element() {  //Возвращает корневой элемент
        return peek();
    }

    @Override
    public boolean isEmpty() { //Проверяет, пуста ли очередь
        return size == 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {  //Проверяет, содержатся ли все элементы из коллекции в очереди
        E[] a = (E[]) new Object[c.size()];
        a = c.toArray(a);

        for (E e : a)
            if (!contains(e))
                return false;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {  //Добавляет все элементы из коллекции в очередь
        int prevSize = size;
        E[] newArr = (E[]) c.toArray();
        for (E e : newArr)
            add(e);

        return prevSize < size;
    }

    public void heapify(){
        int start = size - 1;
        while(start >= 0){
            siftDown(start);
            start--;
        }
        return;
    }

    @Override
    public boolean removeAll(Collection<?> c) { //Удаляет все элементы, которые содержатся в указанной коллекции
        boolean changed = false;
        int i = 0;
        while (i < size) {
            if (c.contains(arr[i])) {
                System.arraycopy(arr, i+1, arr, i, size-i-1);
                size--;
                changed = true;
            } else {
                i++;
            }
        }
        heapify();

        return changed;
    }

    public boolean retainAll(Collection<?> c) {  //Удаляет все элементы, которые не содержатся в указанной коллекции
        boolean changed = false;
        int i = 0;
        while (i < size) {
            if (!c.contains(arr[i])) {
                System.arraycopy(arr, i+1, arr, i, size-i-1);
                size--;
                changed = true;
            } else {
                i++;
            }
        }
        heapify();

        return changed;
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
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(arr[i])) {
                arr[i] = arr[--size];
                siftDown(i);
                return true;
            }
        }

        return false;
    }
}
