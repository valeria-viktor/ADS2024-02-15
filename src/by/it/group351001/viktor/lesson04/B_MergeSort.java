package by.it.group351001.viktor.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    public static void mergeSort(int[] array) {  //метод, который принимает одномерный массив для сортировки
        if (array.length <= 1) {  //Проверка, если массив состоит из одного элемента или пуст
          return;
        }

        int mid = array.length / 2;  //вычисление среднего индекса массива
        int[] left = new int[mid];   //создание подмассива для левой половины
        int[] right = new int[array.length - mid];  //создание подмассива для правой половины

        //копируем элементы во временные подмассивы
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }
        mergeSort(left);  //рекурсивная сортировка левой половины
        mergeSort(right); //рекурсивная сортировка правой половины
        merge(array, left, right);  //соединение двух отсортированных подмассивов
    }

    public static void merge(int[] array, int[] left, int[] right) {  //метод для слияния двух отсортированных подмассивов
        int i = 0;  //индекс для левого подмассива
        int j = 0;  //индекс для правого подмассива
        int k = 0;  //индекс для итогового массива

        //слияние элементов левого и правого подмассивов в итоговый массив
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
            k++;
        }
        //копируем оставшиеся элементы из левого подмассива
        while (i < left.length) {
            array[k] = left[i];
            i++;
            k++;
        }
        //копируем оставшиеся элементы из правого подмассива
        while (j < right.length) {
            array[k] = right[j];
            j++;
            k++;
        }
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);  // считываем данные
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //считываем размер массива
        int n = scanner.nextInt();
        //считываем сам массив
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

        mergeSort(a);  //вызываем сортировку

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
