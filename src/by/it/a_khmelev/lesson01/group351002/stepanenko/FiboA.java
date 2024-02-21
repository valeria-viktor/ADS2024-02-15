package by.it.a_khmelev.lesson01.group351002.stepanenko;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить рекурсивный способ вычисления чисел Фибоначчи
 */

public class FiboA {


    private long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        FiboA fibo = new FiboA();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        //вычисление чисел фибоначчи медленным методом (рекурсией)
        fibo = new FiboA();
        n = 34;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }

    private long time() {
        long res = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
        return res;
    }

    import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            int a = 0, b = 1, fib = 0;
            int n = 1;

            do {
                System.out.println("Введите номер члена, который хотите найти (отрицательное число для выхода): ");
                n = scan.nextInt();

                if (n > 0) {
                    for (int i = 2; i <= n; i++) {
                        fib = a + b;
                        a = b;
                        b = fib;
                    }
                    System.out.println(fib);
                }
            } while (n > 0);
        }
    }



    BigInteger slowA(Integer n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с математическим определением чисел Фибоначчи
        //время O(2^n)

        return BigInteger.ZERO;
    }


}

