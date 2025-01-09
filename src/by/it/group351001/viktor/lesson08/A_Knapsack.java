package by.it.group351001.viktor.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак с повторами

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        сколько есть вариантов золотых слитков
                     (каждый можно использовать множество раз).
Следующая строка содержит n целых чисел, задающих веса слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.

w — максимальная вместимость рюкзака
n — количество вариантов золотых слитков
gold — массив весов золотых слитков

Sample Input:
10 3
1 4 8
Sample Output:
10

Sample Input 2:

15 3
2 8 16
Sample Output 2:
14

*/

public class A_Knapsack {
    //Метод для вычисления максимального веса золота, который можно унести в рюкзаке
    int calculateMaxWeight(int w, int n, int[] gold) {
        int[] temp = new int[w + 1];  //Создание массива для хранения значений максимального веса длиной w + 1
        //Заполнение массива
        for (int i = 1; i <= w; i++) {  // проходим по возможным вместимостям рюкзака
            for (int j = 0; j < n; j++) {   // проходим по всем слиткам золота
                if (gold[j] <= i) {    // может ли текущий слиток поместиться в рюкзак вместимостью i
                    int tempWeight = temp[i - gold[j]] + gold[j];  // новый возможный вес, если добавить текущий слиток
                    if (tempWeight > temp[i]) {
                        temp[i] = tempWeight;
                    }
                }
            }
        }
        return temp[w];
    }

    int getMaxWeight(InputStream stream ) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int w=scanner.nextInt();
        int n=scanner.nextInt();
        int gold[]=new int[n];
        for (int i = 0; i < n; i++) {
            gold[i]=scanner.nextInt();
        }


        int result = calculateMaxWeight(w, n, gold);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_Knapsack.class.getResourceAsStream("dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }
}
