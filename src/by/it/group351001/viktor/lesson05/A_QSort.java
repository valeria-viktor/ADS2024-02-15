package by.it.group351001.viktor.lesson05;

import javax.swing.text.Segment;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь.
На площади установлена одна или несколько камер.
Известны данные о том, когда каждая из них включалась и выключалась (отрезки работы)
Известен список событий на площади (время начала каждого события).
Вам необходимо определить для каждого события сколько камер его записали.

В первой строке задано два целых числа:
    число включений камер (отрезки) 1<=n<=50000
    число событий (точки) 1<=m<=50000.

Следующие n строк содержат по два целых числа ai и bi (ai<=bi) -
координаты концов отрезков (время работы одной какой-то камеры).
Последняя строка содержит m целых чисел - координаты точек.
Все координаты не превышают 10E8 по модулю (!).

Точка считается принадлежащей отрезку, если она находится внутри него или на границе.

Для каждой точки в порядке их появления во вводе выведите,
скольким отрезкам она принадлежит.
    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/

public class A_QSort {

    //отрезок

    private int partition(Segment[] bufSegment, int low, int high) {
        int middle = low + (high - low) / 2;  //находим середину массива
        Segment mainSegment = bufSegment[middle];
        Segment tempSegment = bufSegment[middle];
        bufSegment[middle] = bufSegment[high];  //меняем местами элементы middle и high
        bufSegment[high] = tempSegment;
        int i = low - 1;
        for (int j = low; j < high; j++) {  //проходимся по элементам массива
            //является ли текущий сегмент меньшим или равным mainSegment по стартовому и конечному значению
            if (bufSegment[j].start <= mainSegment.start && bufSegment[j].stop <= mainSegment.stop) {
                i++;
                tempSegment = bufSegment[i];  //меняем местами элементы bufSegment[i] и bufSegment[j]
                bufSegment[i] = bufSegment[j];
                bufSegment[j] = tempSegment;
            }
        }
        tempSegment = bufSegment[i + 1];  //меняем местами элементы bufSegment[i + 1] и bufSegment[high]
        //чтобы mainSegment находился на конечном месте в отсортированном массиве.
        bufSegment[i + 1] = bufSegment[high];
        bufSegment[high] = tempSegment;
        return i + 1;  //возвращаем mainSegment
    }

    //реализация алгоритма быстрой сортировки для массива seg
    void quicksort(Segment[] seg, int low, int high) {
        if (low <= high) {
            int temp = partition(seg, low, high); //делим массив seg на две части и возвращаем средний элем
            quicksort(seg, low, temp - 1);  //вызываем сортировку для левой части
            quicksort(seg, temp + 1, high);  //вызываем сортировку для правой части
        }
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //читаем число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n]; //создаем массив объектов Segment размером n, который будет содержать отрезки
        //читаем число точек
        int m = scanner.nextInt();
        int[] points = new int[m]; //создаем массив для хранения точек
        int[] result = new int[m]; //создаем массив для хранения результатов

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка и сохраняем в segments
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        //читаем точки из выхода и сохраняем в points
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        quicksort(segments, 0, segments.length - 1); //quicksort для сортировки отрезков segments
        for (int i = 0; i < m; i++) {  //цикл по всем точкам
            int point = points[i];   //читаем точку
            //инициализируем переменную для хранения количества отрезков кот принадлежит точка
            int pointcount = 0;
            //устанавливаем границы для бинарного поиска
            int left = 0;
            int right = n - 1;
            boolean exitflag = false; //для выхода
            //начинаем бинарный поиск
            while ((left < right) && !exitflag) {
                int middle = left + (right - left) / 2;
                //ищем точку в текущем отрезке
                if (point >= segments[middle].start && point <= segments[middle].stop) {
                    pointcount = 1;
                    exitflag = true;
                } else if (point < segments[middle].start) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
            //проверяем оставшиеся отрезки, если точка принадлежит устанавливаем pointcount в 1.
            for (int j = left + 1; j < segments.length; j++) {
                if (point >= segments[j].start && point <= segments[j].stop)
                    pointcount = 1;
            }
            result[i] = pointcount;
        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private class Segment implements Comparable<Segment> {  //класс который представляет отрезок
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков

            return 0;
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
