package by.it.group351001.viktor.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)
Поиск самой длинной невозрастающей последовательности чесел и вывод индексов этих чисел
Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();  //читаем первое число - длину массива
        int[] m = new int[n];   //создаем массив m длиной n
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();   //читаем элементы массива
        }
        int[] elementsIndex = new int[n + 2];  //массив для хранения индексов элементов
        for (int i = 0; i < elementsIndex.length; i++) {
            elementsIndex[i] = -1;    //заполняем его
        }

        int[] elements = new int[n + 2];  //массив для хранения значений элементов
        elements[0] = Integer.MAX_VALUE;
        for (int i = 1; i < elements.length; i++) elements[i] = Integer.MIN_VALUE;

        int[] prev = new int[n];   //массив для хранения предыдущих индексов элементов в подпоследовательности
        for (int i = 0; i < prev.length; i++) prev[i] = -1;  //заполняем его

        //Поиск наибольшей невозрастающей подпоследовательности
        int middle, left, right;  //для двоичного поиска
        for (int i = 0; i < m.length; i++) {   //проходим по каждому элементу массива m
            left = 1;
            right = elements.length - 2;
            //выполняем двоичный поиск для нахождения правильной позиции текущего элемента в массиве elements
            while (left < right) {
                middle = (right + left) / 2;
                if (elements[middle] < m[i]) {
                    right = middle;
                }
                else if (elements[middle] >= m[i]) {
                    left = middle + 1;
                }
            }
            if (elements[right - 1] >= m[i] && elements[right] <= m[i]) {
                elements[right] = m[i];
                elementsIndex[right] = i;
                prev[i] = elementsIndex[right - 1];
            }
        }

        int k = elements.length - 1;
        //определяем длину наибольшей невозрастающей подпоследовательности k
        while (elements[k] == Integer.MIN_VALUE) {
            k--;
        }
        //находим последний индекс этой подпоследовательности
        int index = elementsIndex[k];
        //строим последовательность индексов элементов, составляющих наибольшую невозрастающую подпоследовательность
        List<Integer> result = new ArrayList<>();
        result.add(index + 1);
        while (prev[index] != -1) {
            index = prev[index];
            result.add(index + 1);
        }
        //выводим результат
        result = result.reversed();
        System.out.println(result.toString());
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return k;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

}