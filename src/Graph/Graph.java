package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph<T> {
    private Set<Vertex<T>> vertices; 
    private Set<Edge<T>> edges; 
    private Map<Vertex<T>, List<VertexDistance<T>>> adjList; 

    public Graph(Set<Vertex<T>> verticies, Set<Edge<T>> edges) {
        if (verticies == null || edges == null) {
            throw new IllegalArgumentException("No null arguments");
        }
        this.vertices = new HashSet<>(verticies);
        this.edges = new HashSet<>(edges);
        adjList = new HashMap<>();

        for (Vertex<T> it : verticies) {
            adjList.put(it, new ArrayList<>());
        }
        for (Edge<T> e : edges) {
            if (adjList.containsKey(e.getU())) {
                adjList.get(e.getU()).add(new VertexDistance<>(e.getV(), e.getWeight()));
            } else {
                throw new IllegalArgumentException("Vertex set must contain all vertices of the graph");
            }
        }
    }

    public Set<Vertex<T>> getVertices() {
        return vertices;
    }

    public Set<Edge<T>> getEdges() {
        return edges;
    }

    public Map<Vertex<T>, List<VertexDistance<T>>> getAdjList() {
        return adjList;
    }
}
