package by.it.group351001.viktor.lesson14;

/* реализует алгоритм для поиска компонент связности в трехмерном пространстве, используя структуру данных “Система непересекающихся множеств */
//Создаются случайные точки в трехмерном пространстве.
//Формируется строка с входными данными, включающая координаты этих точек и максимальное расстояние.
import java.util.*;
public class PointsA {

    private static class DSU{
        int rank[]; //массив для хранения рангов (высоты) деревьев
        int parent[];  //массив для хранения родительских узлов
        int max_size;  //максимальное количество элементов

        //Инициализирует массивы rank и parent, устанавливая каждый элемент своим собственным родителем
        public DSU(int amount){
            rank = new int[amount];
            parent = new int[amount];
            max_size = amount;
            for (int i = 0; i < amount; i++){
                rank[i] = 0;
                parent[i] = i;
            }
        }

        //рекурсивно находит корень множества, к которому принадлежит элемент index
        public int getParent(int index){
            if (index == parent[index]){
                return index;
            }
            return getParent(parent[index]);
        }

        //объединяет два множества, к которым принадлежат элементы a и b
        void unite(int a, int b) {
            a = getParent(a);
            b = getParent(b);
            if (a != b) {
                if (rank[a] < rank[b]) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                parent[b] = a;
                if (rank[a] == rank[b])
                    rank[a]++;
            }
        }

        public void print(){
            int[] dsu_size_array = new int[max_size];
            for(int i = 0; i < max_size; i++){
                dsu_size_array[getParent(i)]++;
            }

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < max_size; i++){
                int max = i;
                for(int j = i+1; j < max_size;j++)
                    if(dsu_size_array[max]<dsu_size_array[j])
                        max = j;
                if (dsu_size_array[max]==0)
                    break;
                int temp = dsu_size_array[max];
                dsu_size_array[max] = dsu_size_array[i];
                dsu_size_array[i] = temp;
                sb.append(dsu_size_array[i]);
                sb.append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb);
        }
    }
    public static void main(String args[]){
        //Считывает входные данные: максимальное расстояние dist и количество точек amount
        Scanner scan = new Scanner(System.in);
        int dist = scan.nextInt();
        int amount = scan.nextInt();

        //Создает экземпляр DSU для хранения компонент связности
        DSU data = new DSU(amount);

        //Считывает координаты точек в массив points
        int[][] points = new int[amount][3];

        //Проверяет каждую пару точек и объединяет их в одну компоненту, если расстояние между ними не превышает dist
        for (int i = 0; i < amount; i++){
            int[] temp = new int[3];
            for (int j = 0; j < 3; j++){
                temp[j] = scan.nextInt();
            }
            points[i] = temp;
        }

        for (int i = 0; i < amount; i++){
            for (int j = i + 1; j < amount; j++){
                if (check(points, i, j, dist)){
                    data.unite(i, j);
                }
            }
        }

        //Выводит размеры всех компонент связности
        data.print();

    }
    static boolean check(int[][] points, int a, int b, int max_dist){
        return Math.hypot(Math.hypot(points[a][0] - points[b][0], points[a][1] - points[b][1]), points[a][2] - points[b][2])<=max_dist;
    }

}