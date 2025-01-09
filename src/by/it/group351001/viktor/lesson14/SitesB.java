package by.it.group351001.viktor.lesson14;

import java.util.*;

/*реализует алгоритм для объединения сайтов в группы на основе их связей, используя структуру данных
“Система непересекающихся множеств”*/
//Создаются случайные сайты (например, “example.com”, “test.org”).
//Формируется строка с входными данными, включающая пары связанных сайтов.
public class SitesB {
    private static class DSU {
        int rank[]; //массив для хранения рангов (высоты) деревьев
        int parent[];  //массив для хранения родительских узлов
        int max_size;  //максимальное количество элементов


        //Инициализирует массивы rank и parent, устанавливая каждый элемент своим собственным родителем
        public DSU(int amount) {
            rank = new int[amount];
            parent = new int[amount];
            max_size = amount;
            for (int i = 0; i < amount; i++) {
                rank[i] = 0;
                parent[i] = i;
            }
        }

        //рекурсивно находит корень множества, к которому принадлежит элемент index
        public int getParent(int index) {
            if (index == parent[index]) {
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

        //выводит размеры всех компонент связности в порядке убывания
        public void print(int max_size) {
            int[] dsu_size_array = new int[max_size];
            for (int i = 0; i < max_size; i++) {
                dsu_size_array[getParent(i)]++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < max_size; i++) {
                int max = i;
                for (int j = i + 1; j < max_size; j++)
                    if (dsu_size_array[max] < dsu_size_array[j])
                        max = j;
                if (dsu_size_array[max] == 0)
                    break;
                int temp = dsu_size_array[max];
                dsu_size_array[max] = dsu_size_array[i];
                dsu_size_array[i] = temp;
                sb.append(dsu_size_array[i]);
                sb.append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
        }
    }
    public static void main(String args[]){
        //Считывает входные данные: пары связанных сайтов
        List<String> sites = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        String line = scan.next();
        //Создает экземпляр DSU для хранения компонент связности
        DSU dsu = new DSU(1000);
        int index1;
        int index2;
        //Обрабатывает каждую пару сайтов, объединяя их в одну компоненту, если они связаны
        while(line.compareTo("end")!=0) {
            String[] pair = line.split("\\+");

            if (sites.contains(pair[0])){
                index1 = sites.indexOf(pair[0]);
            }
            else{
                index1 = sites.size();
                sites.add(pair[0]);
            }

            if (sites.contains(pair[1])){
                index2 = sites.indexOf(pair[1]);
            }
            else{
                index2 = sites.size();
                sites.add(pair[1]);
            }

            dsu.unite(index1, index2);
            line = scan.next();
        }
        //Выводит размеры всех компонент связности
        dsu.print(sites.size());
    }
}