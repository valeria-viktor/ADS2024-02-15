package by.it.group351001.viktor.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак без повторов

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        число золотых слитков
                    (каждый можно использовать только один раз).
Следующая строка содержит n целых чисел, задающих веса каждого из слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.

Sample Input:
10 3
1 4 8
Sample Output:
9

*/

public class B_Knapsack {

    int getMaxWeight(InputStream stream ) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        // считываем входные данные
        int w=scanner.nextInt();
        int n=scanner.nextInt();
        int gold[]=new int[n];
        // создаем массив для хранения весов слитков
        for (int i = 0; i < n; i++) {
            gold[i]=scanner.nextInt();
        }
        int[] temp = new int[w + 1];  // создаем массив для хранения максимального веса золота

        for (int i = 0; i < n; i++) {  // проходим по всем слиткам золота
            for (int j = w; j >= gold[i]; j--) {  // проходим по возможным вместимостям рюкзака
                temp[j] = Math.max(temp[j], temp[j - gold[i]] + gold[i]);
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return temp[w];
    }


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_Knapsack.class.getResourceAsStream("dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }

}
