package data.structure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Graph {
    private boolean directed;
    // private boolean weighted;
    private ListLinked<Vertex> vertexList;
    private Vertex[] vertexs;
    private int numVertexs;

    public Graph(boolean directed) {
        this.directed = directed;
        vertexList = new ListLinked<>();
    }

    public Graph(boolean directed, int numVertexs) {
        this.directed = directed;
        vertexs = new Vertex[numVertexs];
    }

    public boolean getDirected() {
        return directed;
    }

    public ListLinked<Vertex> getVertexList() {
        return vertexList;
    }

    public Vertex[] getVertexs() {
        return vertexs;
    }

    public int getNumVertexs() {
        return numVertexs;
    }

    public void addVertex(Vertex vertex) {
        vertexList.add(vertex);
    }

    public void addEdge(Vertex v1, Vertex v2, double weight) {
        Edge edge = new Edge(v1, v2, weight);
        v1.addEdge(edge);
        if (!directed) {
            Edge edge2 = new Edge(v2, v1, weight);
            v2.addEdge(edge2);
        }
    }

    public void BFS(Vertex vertex) {
        ListLinked<Vertex> travelBFS = new ListLinked<>();
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(vertex);
        vertex.setStatus(State.VISITADO);
        travelBFS.add(vertex);
        while (!queue.isEmpty()) {
            vertex = queue.poll();
            ListLinked<Edge> lEdges = vertex.getEdges();
            Node<Edge> node = lEdges.getHead();
            while (node != null) {
                Vertex opposite = node.getData().getV2();
                if (opposite.getState() == State.NO_VISITADO) {
                    queue.add(opposite);
                    opposite.setStatus(State.VISITADO);
                    travelBFS.add(opposite);
                }
                node = node.getLink();
            }
            vertex.setStatus(State.PROCESADO);
        }

        Node<Vertex> temp = travelBFS.getHead();
        while (temp != null) {
            System.out.println(temp.getData().getLabel());
            temp = temp.getLink();
        }
    }

    public void DFS() {

    }

    public void printGraph() {
        ListLinked<Edge> edges;
        String output = "";
        for (int i = 0; i < vertexs.length; i++) {
            Vertex vertex = vertexs[i];
            output = output + vertex.getLabel();
            edges = vertex.getEdges();
            output = output + "(" + edges.size() + ") -> ";
            Node<Edge> temp = edges.getHead();
            while (temp != null) {
                output = output + "{" + temp.getData().getV2().getLabel() + "} ";
                temp = temp.getLink();
            }
            output = output + "\n";
        }
        System.out.println(output);
    }

    /*public void printGraphMine()
    {
        ListLinked<Edge> edges;
        for(int i=0;i<3;i++)
        {
            System.out.println(vertexs[i].getLabel()+":");
            //Vertex vertice = vertexs[i];
            edges = vertexs[i].getEdges();
            Node<Edge> temp = edges.getHead();
            System.out.println("numero de aristas: "+edges.size());
            //while(temp != null)
            //{
                System.out.println(edges.getHead().getData().getV2().getLabel());
                edges.getHead().getLink();
            //}
            
        }
    }*/

    

    public void readFileInput(String filename) {
        String path = System.getProperty("user.dir") + "\\input\\" + filename;
        try {

            System.out.println(path);
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            String line = "";
            line = scanner.next();
            Pattern pattern;
            Matcher matcher;

            pattern = Pattern.compile("size\\s*=\\s*(\\d+)");
            matcher = pattern.matcher(line);
            matcher.find();
            String strSize = matcher.group(1);
            System.out.println(strSize);

            vertexs = new Vertex[Integer.parseInt(strSize)];
            // Obteniendo las lineas de informacion de vertices
            while (!(line = scanner.nextLine()).equals(";")) {

                pattern = Pattern.compile("(\\d+)\\s*=\\s*(.+)");
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    Vertex vertex = new Vertex(matcher.group(2));
                    addVertex(vertex);
                    vertexs[Integer.parseInt(matcher.group(1))] = vertex;
                }
                // System.out.println(line);
            }

            // Obteniendo las lineas de informacion de aristas
            while (!(line = scanner.nextLine()).equals(";")) {
                pattern = Pattern.compile("\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)");
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    int posV1 = Integer.parseInt(matcher.group(1));
                    int posV2 = Integer.parseInt(matcher.group(2));
                    Vertex v1 = vertexs[posV1];
                    Vertex v2 = vertexs[posV2];
                    double weight = Double.parseDouble(matcher.group(3));
                    addEdge(v1, v2, weight);
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }


    }

    public void Show()
    {
        Node<Edge> aux;
        for(int i=0;i<vertexs.length;i++)
        {
            System.out.print(vertexs[i].getLabel()+"->");

            aux = vertexs[i].getEdges().getHead();


            System.out.println("{"+aux.getData().getV2().getLabel()+"}"+"{"+aux.getLink().getData().getV2().getLabel()+"}");

            aux = aux.getLink();
        }
    }
    
    public static void main(String[] args) {
        Graph graph = new Graph(false);

        Vertex LaPaz = new Vertex("La Paz");
        Vertex Cochabamba = new Vertex("Cochabamba");
        Vertex SantaCruz = new Vertex("Santa Cruz");
        Vertex Riberalta = new Vertex("Riberalta");

        LaPaz.addEdge(new Edge(LaPaz, Cochabamba));
        LaPaz.addEdge(new Edge(LaPaz, SantaCruz));
        LaPaz.addEdge(new Edge(LaPaz, Riberalta));

        graph.addVertex(LaPaz);
        graph.addVertex(Cochabamba);
        graph.addVertex(SantaCruz);
        graph.addVertex(Riberalta);

        graph.readFileInput("bolivia.txt");
        graph.Show();
        /*System.out.println("");
        System.out.println("");
        System.out.println("");
        graph.printGraph();*/

        
    }
}
 