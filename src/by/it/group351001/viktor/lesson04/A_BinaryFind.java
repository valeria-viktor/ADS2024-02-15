package by.it.group351001.viktor.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
В первой строке источника данных даны:
        - целое число 1<=n<=100000 (размер массива)
        - сам массив A[1...n] из n различных натуральных чисел,
          не превышающих 10E9, в порядке возрастания,
Во второй строке
        - целое число 1<=k<=10000 (сколько чисел нужно найти)
        - k натуральных чисел b1,...,bk не превышающих 10E9 (сами числа)
Для каждого i от 1 до kk необходимо вывести индекс 1<=j<=n,
для которого A[j]=bi, или -1, если такого j нет.

        Sample Input:
        5 1 5 8 12 13
        5 8 1 23 1 11

        Sample Output:
        3 1 -1 1 -1

(!) Обратите внимание на смещение начала индекса массивов JAVA относительно условий задачи
*/

public class A_BinaryFind {

    private static int binarySearch(int[] arr, int target, int low, int high) {
        while (low <= high) {   //пока левая граница не станет больше правой
            int mid = low + (high - low) / 2;  //находим середину

            if (arr[mid] == target) {  //если mid равно искомому
                return mid + 1;        //возращаем
            } else if (arr[mid] < target){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;  //если ничего не найдено возвращаем -1
    }
    int[] findIndex(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //считываем размер отсортированного массива
        int n = scanner.nextInt();
        //инициализируем сам отсортированный массива
        int[] a=new int[n];
        for (int i = 1; i <= n; i++) {  //считываем элементы отсортированного массива
            a[i-1] = scanner.nextInt();   //сохраняем в массиве a
        }

        //считываем размер массива индексов
        int k = scanner.nextInt();
        int[] result=new int[k];  //инициализируем массив result для результатов поиска
        for (int i = 0; i < k; i++) {   //Для каждого элемента, который нужно найти, считываем его значение
            int value = scanner.nextInt();     //вызываем метод binarySearch для поиска индекса этого элемента в массиве
            //тут реализуйте бинарный поиск индекса
            result[i] = binarySearch(a, value, 0, n-1);
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;  //Возвращается массив result с индексами найденных элементов
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataA.txt");
        A_BinaryFind instance = new A_BinaryFind();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.findIndex(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
