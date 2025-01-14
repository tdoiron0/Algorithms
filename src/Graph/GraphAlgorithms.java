package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
        return null;
    }

    public static <T> Set<Edge<T>> kruskals(Graph<T> graph) {
        return null;
    }
}
