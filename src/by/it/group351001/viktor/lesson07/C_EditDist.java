package by.it.group351001.viktor.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {
    //метод возвращает 0, если символы совпадают, и 1, если не совпадают
    int m(int i0, int j0, String s1, String s2) {
        i0--;
        j0--;
        if (s1.charAt(i0) == s2.charAt(j0)) {
            return 0;
        } else {
            return 1;
        }
    }
    //метод возвращает минимальное значение из трех чисел
    int min(int n1, int n2, int n3) {
        if (n1 > n2) {
            n1 = n2;
        }
        if (n1 > n3) {
            n1 = n3;
        }
        return n1;
    }

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int n = one.length();  //длина первой строки
        int m = two.length();  //длина второй строки
        // Создаем массив для хранения результатов
        int[][] matrix = new int[n + 1][m + 1];
        //инициализация первой строки и столбца
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if ((i == 0) && (j == 0)) {
                    matrix[i][j] = 0;
                } else if (j == 0) {
                    matrix[i][j] = i;
                } else if (i == 0) {
                    matrix[i][j] = j;
                } else {
                    matrix[i][j] = min(matrix[i][j - 1] + 1, matrix[i - 1][j] + 1, matrix[i - 1][j - 1] + m(i, j, one, two));
                }
            }
        }

        String result = "";
        int i = n, j = m;
        while (i > 0 && j > 0) {   //начинаем с нижнего правого угла
            if (one.charAt(i - 1) == two.charAt(j - 1)) {  //сли символы совпадают
                result = "#" + "," + result;               //добавляем #
                i--;
                j--;
            //если не совпадают, добавляются другие соответсвующие операции
            } else if (matrix[i][j] == matrix[i - 1][j - 1] + 1) {
                result = "~" + two.charAt(j - 1) + "," + result;
                i--;
                j--;
            } else if (matrix[i][j] == matrix[i - 1][j] + 1) {
                result = "-" + one.charAt(i - 1) + "," + result;
                i--;
            } else if (matrix[i][j] == matrix[i][j - 1] + 1) {
                result = "+" + two.charAt(j - 1) + "," + result;
                j--;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_EditDist.class.getResourceAsStream("dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}