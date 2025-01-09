package by.it.group351001.viktor.lesson13;

import java.util.*;

/* выполняем поиск компонент сильной связности в ориентированном графе.
Компонента сильной связности — это подграф, в котором каждая вершина достижима из любой другой
вершины этой компоненты */
public class GraphC {
    static Integer depth = 0;

    static class mapEntryComparator implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> set1, Map.Entry<String, Integer> set2) {
            int cmp = set1.getValue().compareTo(set2.getValue());
            if (cmp == 0) {
                return set2.getKey().compareTo(set1.getKey());
            }
            return cmp;
        }
    }

    // считывает вершины и ребра из ввода пользователя и строит граф и его обратный граф
    private static void getGraph(Map<String, ArrayList<String>> graph, Map<String, ArrayList<String>> reversedGraph) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String vertexOut = in.next();
            if (!graph.containsKey(vertexOut)) {
                graph.put(vertexOut, new ArrayList<>());
            }

            if (!in.hasNext()) break;
            String edge = in.next();

            if (!in.hasNext()) break;
            String vertexIn = in.next();
            if (vertexIn.charAt(vertexIn.length() - 1) == ',') {
                vertexIn = vertexIn.substring(0, vertexIn.length() - 1);
            }
            graph.get(vertexOut).add(vertexIn);
            if (!reversedGraph.containsKey(vertexIn)) {
                reversedGraph.put(vertexIn, new ArrayList<>());
            }
            reversedGraph.get(vertexIn).add(vertexOut);
        }
    }

    // вычисляет глубину каждой вершины в графе, используя обход в глубину
    private static void getDepth(String node, Map<String, ArrayList<String>> graph, Set<String> visited, Map<String, Integer> depthMap) {
        visited.add(node);

        if (graph.get(node) != null) {
            for (String nextNode : graph.get(node)) {
                if (!visited.contains(nextNode)) {
                    depth++;
                    getDepth(nextNode, graph, visited, depthMap);
                }
            }
        }
        depthMap.put(node, depth++);
    }

    // выполняет обход обратного графа для поиска компонент сильной связности
    private static void getPath(String node, Map<String, ArrayList<String>> graph, Set<String> visited, List<String> path) {
        visited.add(node);
        path.add(node);
        if (graph.get(node) != null) {
            for (String nextNode : graph.get(node)) {
                if (!visited.contains(nextNode)) {
                    getPath(nextNode, graph, visited, path);
                }
            }
        }
    }

    public static void main(String[] args) {
        Map<String, ArrayList<String>> graph = new HashMap<>();
        Map<String, ArrayList<String>> reversedGraph = new HashMap<>();
        getGraph(graph, reversedGraph);

        Set<String> visited = new HashSet<>();
        Map<String, Integer> depthMap = new HashMap<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                getDepth(node, graph, visited, depthMap);
            }
        }

        // Вершины сортируются по глубине в порядке убывания
        List<Map.Entry<String, Integer>> array = new ArrayList<>(depthMap.entrySet());
        array.sort(new mapEntryComparator());
        String[] vertices = new String[array.size()];
        for (int i = array.size() - 1; i > -1; i--) {
            vertices[array.size() - 1 - i] = array.get(i).getKey();
        }

        visited = new HashSet<>();
        // Компоненты сильной связности выводятся в алфавитном порядке
        for (String node : vertices) {
            if (!visited.contains(node)) {
                List<String> paths = new ArrayList<>();
                getPath(node, reversedGraph, visited, paths);
                paths.sort(Comparator.naturalOrder());
                for (String path : paths) {
                    System.out.print(path);
                }
                System.out.println();
            }
        }
    }
}
