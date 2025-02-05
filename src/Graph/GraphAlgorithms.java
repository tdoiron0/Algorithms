package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphAlgorithms {
    public static <T> List<Vertex<T>> bredthFirstSearch(Vertex<T> start, Graph<T> graph) {
        Set<Vertex<T>> vs = new HashSet<>();
        Stack<Vertex<T>> s = new Stack<>();
        List<Vertex<T>> result = new ArrayList<>();
        s.push(start);
        while (!s.empty()) {
            Vertex<T> v = s.pop();
            result.add(v);
            if (!vs.contains(v)) {
                vs.add(v);
                for (VertexDistance<T> w : graph.getAdjList().get(v)) {
                    if (!vs.contains(w.getVertex())) {
                        s.add(w.getVertex());
                    }
                }
            }
        }
        return result; 
    }

    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start, Graph<T> graph) {
        List<Vertex<T>> result = new ArrayList<>();
        rDepthFirstSearch(start, new HashSet<>(), result, graph);
        return result;
    }

    private static <T> void rDepthFirstSearch(Vertex<T> curr, Set<Vertex<T>> vs, List<Vertex<T>> list, Graph<T> graph) {
        vs.add(curr);
        list.add(curr);
        for (VertexDistance<T> vd : graph.getAdjList().get(curr)) {
            if (!vs.contains(vd.getVertex())) {
                rDepthFirstSearch(vd.getVertex(), vs, list, graph);
            }
        }
    }

    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Start and graph cannot be null.");
        }
        if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Start must be in the graph.");
        }

        Set<Vertex<T>> vs = new HashSet<>();
        Map<Vertex<T>, Integer> dm = new HashMap<>();
        Queue<VertexDistance<T>> pq = new PriorityQueue<>();
        for (Vertex<T> v : graph.getVertices()) {
            dm.put(v, Integer.MAX_VALUE);
        }
        pq.add(new VertexDistance<>(start, 0));
        while (!pq.isEmpty() && vs.size() < graph.getVertices().size()) {
            VertexDistance<T> temp = pq.remove();
            if (!vs.contains(temp.getVertex())) {
                vs.add(temp.getVertex());
                dm.put(temp.getVertex(), temp.getDistance());
                for (VertexDistance<T> it : graph.getAdjList().get(temp.getVertex())) {
                    if (!vs.contains(it.getVertex())) {
                        pq.add(new VertexDistance<>(it.getVertex(), temp.getDistance() + it.getDistance()));
                    }
                }
            }
        }
        return dm;
    }

    public static <T> Set<Edge<T>> kruskals(Graph<T> graph) {
        return null;
    }
}
