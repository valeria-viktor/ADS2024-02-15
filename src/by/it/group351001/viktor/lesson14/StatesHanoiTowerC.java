package by.it.group351001.viktor.lesson14;

import java.util.Scanner;
//моделирует задачу “Ханойская башня” и использует структуру данных “Система непересекающихся множеств” для отслеживания состояний и объединения шагов, которые приводят к одному и тому же состоянию
public class StatesHanoiTowerC {
    private static class DSU {
        int[] parent; //массив для хранения родительских узлов
        int[] size; //массив для хранения размеров множеств

        DSU(int size) {
            parent = new int[size];
            this.size = new int[size];
        }


        //инициализирует множество для элемента index
        void make_set(int index) {
            parent[index] = index;
            size[index] = 1;
        }

        //возвращает размер множества, к которому принадлежит элемент v
        int size(int v) {
            return size[getParent(v)];
        }

        //находит корень множества для элемента v с применением сжатия путей
        int getParent(int v) {
            if (v == parent[v])
                return v;
            return parent[v] = getParent(parent[v]);
        }

        //объединяет два множества, к которым принадлежат элементы a и b
        void unite(int a, int b) {
            a = getParent(a);
            b = getParent(b);
            if (a != b) {
                if (size[a] < size[b]) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                parent[b] = a;
                size[a] += size[b];
            }
        }
    }

    //рассчитывает перемещение дисков между стержнями в зависимости от текущего шага и количества дисков
    static int[] carryingOver(int N, int step, int k) {
        //N: количество дисков. step: текущий шаг. k: номер диска, который перемещается.
        int t, axisX, axisY, axisZ;
        if (N % 2 == 0) {
            axisX = 0;
            axisY = 1;
            axisZ = 2;
        } else {
            axisX = 0;
            axisY = 2;
            axisZ = 1;
        }
        int[] result = new int[3];
        t = (step / (1 << (k - 1)) - 1) / 2;
        int from = 0, to = 0;
        if (k % 2 != 0)
            switch (t % 3) {
                case 0:
                    from = axisX;
                    to = axisY;
                    break;
                case 1:
                    from = axisY;
                    to = axisZ;
                    break;
                case 2:
                    from = axisZ;
                    to = axisX;
                    break;
            }
        else
            switch (t % 3) {
                case 0:
                    from = axisX;
                    to = axisZ;
                    break;
                case 1:
                    from = axisZ;
                    to = axisY;
                    break;
                case 2:
                    from = axisY;
                    to = axisX;
                    break;
            }
        result[from] = -1;
        result[to] = 1;
        //возвращаем массив, указывающий, с какого стержня диск был перемещен и на какой стержень
        return result;
    }

    public static void main(String[] args) {

        //Вводится количество дисков N
        int N;
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();

        //Вычисляется общее количество шагов size
        int size = (1 << N) - 1;
        int[] steps_heights = new int[N];

        for (int i = 0; i < N; i++) {
            steps_heights[i] = -1;
        }

        DSU dsu = new DSU(size);
        int[] heights = new int[3];

        heights[0] = N;
        //Для каждого шага вычисляется перемещение дисков с помощью метода carryingOver
        for (int i = 0; i < size; i++) {
            int step = i + 1;
            int[] delta;
            if (step % 2 != 0) {
                delta = carryingOver(N, step, 1);
            } else {

                int count = step;
                int countDisk = 0;

                while (count % 2 == 0) {
                    countDisk++;
                    count /= 2;
                }

                delta = carryingOver(N, step, countDisk + 1);
            }

            for (int j = 0; j < 3; j++) {
                heights[j] += delta[j];
            }

            int max = max(heights);
            dsu.make_set(i);
            if (steps_heights[max - 1] == -1) {
                steps_heights[max - 1] = i;
            } else {
                dsu.unite(steps_heights[max - 1], i);
            }
        }

        int[] sizes = new int[N];
        for (int i = 0; i < N; i++) {
            if (steps_heights[i] != -1) {
                sizes[i] = dsu.size(steps_heights[i]);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {

            int max = i;
            for (int j = i + 1; j < N; j++) {
                if (sizes[max] < sizes[j]) {
                    max = j;
                }
            }

            if (sizes[max] == 0) {
                break;
            }

            int temp = sizes[max];
            sizes[max] = sizes[i];
            sizes[i] = temp;
            sb.insert(0, sizes[i] + " ");
        }

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    // метод находит максимальное значение среди трех высот стержней
    private static int max(int[] heights) {
        return Math.max(Math.max(heights[0], heights[1]), heights[2]);
    }
}
