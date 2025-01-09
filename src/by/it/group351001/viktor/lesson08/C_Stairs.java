package by.it.group351001.viktor.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Даны число 1<=n<=100 ступенек лестницы и
целые числа −10000<=a[1],…,a[n]<=10000, которыми помечены ступеньки.
Найдите максимальную сумму, которую можно получить, идя по лестнице
снизу вверх (от нулевой до n-й ступеньки), каждый раз поднимаясь на
одну или на две ступеньки.

stairs[] - массив, содержащий значения на каждой ступеньке.
n - текущая ступенька.
result - текущая сумма.
size - общее количество ступенек.

Sample Input 1:
2
1 2
Sample Output 1:
3

Sample Input 2:
2
2 -1
Sample Output 2:
1

Sample Input 3:
3
-1 2 1
Sample Output 3:
3

*/

public class C_Stairs {
    //  метод max принимает два целых числа и возвращает наибольшее из них
    int max(int v1, int v2){
        if (v1<v2){
            v1 = v2;
        }
        return v1;
    }

    int calc(int stairs[], int n, int result, int size){
        if (n<size-1) {
            int case1 = calc(stairs, n+1, result, size) + stairs[n];      // вызов calc для следующей ступеньки               //Наступать
            int case2 = calc(stairs, n+2, result, size) + stairs[n+1];    // вызов calc для ступеньки через одну
            result = result + max(case1, case2);
        }
        else if (n == size - 1){
            if (stairs[n]>=0) {
                result = result + stairs[n];
            }
            n++;
        }
        return result;
    }

    int getMaxSum(InputStream stream ) {
        Scanner scanner = new Scanner(stream);
        int n=scanner.nextInt();
        int stairs[]=new int[n];
        for (int i = 0; i < n; i++) {
            stairs[i]=scanner.nextInt();   // массив, содержащий значения на каждой ступеньке
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!





        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return calc(stairs, 0, 0, n);
    }


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_Stairs.class.getResourceAsStream("dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res=instance.getMaxSum(stream);
        System.out.println(res);
    }

}
