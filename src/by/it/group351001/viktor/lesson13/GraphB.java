package by.it.group351001.viktor.lesson13;

import java.util.*;

/* проверяем, содержит ли ориентированный граф циклы.
Если в графе есть цикл, программа выводит “yes”, иначе — “no” */
public class GraphB {

    //метод считывает граф из ввода пользователя и строит граф
    private static void getGraph(Map<String, ArrayList<String>> graph) {
        Scanner in = new Scanner(System.in);

        boolean isEnd = false;
        while (!isEnd) {
            String vertexOut = in.next();
            if (!graph.containsKey(vertexOut)) {
                graph.put(vertexOut, new ArrayList<>());
            }
            String edge = in.next();
            String vertexIn = in.next();
            if (vertexIn.charAt(vertexIn.length() - 1) == ',') {
                vertexIn = vertexIn.substring(0, vertexIn.length() - 1);
            } else {
                isEnd = true;
            }
            graph.get(vertexOut).add(vertexIn);
        }
    }

    //метод проверяет, содержит ли граф цикл, начиная с заданной вершины
    //выполняет рекурсивный обход графа в глубину для проверки наличия циклов
    private static boolean isCyclic(String node, Map<String, ArrayList<String>> graph, Set<String> visited) {
        boolean res = false;
        visited.add(node); //множество visited для отслеживания посещенных вершин.
        if (graph.get(node) != null) {
            for (String nextNode : graph.get(node)) {
                if (!visited.contains(nextNode)) {
                    res = isCyclic(nextNode, graph, new HashSet<>(visited));
                } else {
                    res = true;
                    return res;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Map<String, ArrayList<String>> graph = new HashMap<>();
        getGraph(graph);
        for (String node :
                graph.keySet()) {
            boolean res = isCyclic(node, graph, new HashSet<>());
            if (res) {
                System.out.println("yes");
                return;
            }
        }
        System.out.println("no");
    }
}
