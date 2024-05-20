package by.it.group351001.viktor.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая кратная подпоследовательность

Дано:
    целое число 1≤n≤1000
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] делится на предыдущий
    т.е. для всех 1<=j<k, A[i[j+1]] делится на A[i[j]].
Поиск самой длинной последовательности чесел у которых следующее делится на предыдущее без остатка
Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    4
    3 6 7 12

    Sample Output:
    3
*/

public class B_LongDivComSubSeq {


    int getDivSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();  //читаем первое число - длину массива
        int[] m = new int[n];  //создаем массив m длиной n
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();  //читаем элементы массива
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        int result = 0;
        int[] elements = new int[n];  //создаем массив elements для хранения наибольшей кратной последовательности

        for (int i = 0; i < n; i++) {  //проходимся по элементам массива n
            elements[i] = 1;           //начальное значение длины последовательности

            for (int j = 0; j < i; j++) {   //сравниваем текущий эл m[i] с предыдущим m[j]
                if ((m[i] % m[j] == 0) && (elements[j] + 1 > elements[i])) {   //если делится без остатка
                    elements[i] = elements[j] + 1;
                }
            }
            result = Math.max(elements[i], result);  //максимальная длина последовательности
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        System.out.print(result);
    }

}