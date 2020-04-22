package Algo;

import java.lang.reflect.Array;
import java.util.*;



/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Some
 * @Description: TODO
 * @date 2020/3/9 Undirected graph
 */
public class Graph {
    // undirected graph, and thus each edge is saved twice.
    class Edge implements Comparable<Edge>{ // useful while comparing the edges in graph.
        int src, dest, weight;

        public int compareTo(Edge comparedEdge){
            return this.weight-comparedEdge.weight;
        }

        public boolean isEqual(Edge b){
            return ((src==b.src) && (dest == b.dest) && (weight==b.weight));
        }

        public Edge(int src, int dest, int weight){
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
        public Edge(){
            this.src = -1;
            this.dest = -1;
            this.weight = -1;
        }

        public int getSrc() {
            return src;
        }

        public int getDest() {
            return dest;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int n){
            this.weight = n;
        }

        public void setSrc(int src) {
            this.src = src;
        }

        @Override
        public String toString() {
            return "Edge{" + src +
                    "==>" + dest +
                    ", weight=" + weight +
                    '}';
        }
    };


    private int V;
    private int E;
    LinkedList<Edge> edge;
    int[][] adjMatrix;
    EdgeNode[] AdjList;
    int[] color = new int[10];
    int min_color = 10000;

    boolean[] visited;
    int[] edgeTo;

    Graph(int V, int E){
        this.V = V;
        this.E = E;
        edge = new LinkedList<Edge>();
        for(int i=0; i<E; i++){
            edge.add(new Edge());
        }
        this.adjMatrix = new int[V][V];
        this.AdjList = new EdgeNode[V];
        this.visited = new boolean[V];
        this.edgeTo = new int[V];
    }

    Graph(int[][] adjMatrix,int nE){
        // Initialize a graph by adjacent matrix.
        this.V = adjMatrix.length;
        int k = 0;
        this.E = nE;
        edge = new LinkedList<Edge>();
        for(int i = 0; i<this.V; i++){
            for(int j = 0; j<this.V; j++){
                if(adjMatrix[i][j]>0 && adjMatrix[i][j]!=Integer.MAX_VALUE){
                    edge.add(new Edge(i,j,adjMatrix[i][j]));
                    k++;
                }
            }
        }
        this.E = k;
        this.adjMatrix = adjMatrix;
        this.visited = new boolean[V];
        this.edgeTo = new int[V];
        buildAdjListByMat();
    }

    Graph(EdgeNode[] adjList,int nE){
        //Initialize a graph by adjacent linked list.
        this.V = adjList.length;
        int k = 0;
        edge = new LinkedList<Edge>();
        for(int i = 0; i<this.V; i++){
            EdgeNode ptmp = adjList[i].next;
                while(ptmp!=null){
                    edge.add(new Edge(i,ptmp.nAdjNode,ptmp.weight));
                    ptmp = ptmp.next;
                    k++;
                }
            }
        this.E = nE;
        this.AdjList = adjList;
        this.visited = new boolean[V];
        this.edgeTo = new int[V];
        buildAdjMatByList();
    }

    public void printG(){
        for(Edge e: edge){
            System.out.println(e.toString());
        }
    }

    public int getV(){return V;}
    public int getE(){return E;}

    public void BFS(int s){
        /* @Description: Breath first searching. Use queue to maintain the 1st in node is searched as widely as possible.
         * @param s: the starting point
        * @Return:
        */
        Arrays.fill(visited,false);
        LinkedList<Integer> queue = new LinkedList<>();

        queue.offer(s);
        visited[s] = true;

        while(queue.size()!=0){
            s = queue.poll();
            System.out.print(s+" ");
            EdgeNode tmp = AdjList[s].next;
            while(tmp!=null){
                int j = tmp.nAdjNode;
                if(!visited[j]){
                    visited[j] = true;
                    edgeTo[j] = s;
                    queue.offer(j);
                }
                tmp = tmp.next;
            }
        }
    }

    public void DFS(){
        Arrays.fill(visited,false);
        for(int i=0; i<V; i++) // This loop is needed because may the graph is not whole
            // connected and thus some will be missed in sub-DFS
            if(!visited[i])
                DFS(i);
    }

    private void DFS(int s){
        /* @Description: Depth first searching, a stack fits.
         * @param s: the source node
        * @Return:
        */
        visited[s] = true;
        System.out.print(s+" ");
        EdgeNode ll = AdjList[s].next;
        while(ll!=null){
            int j = ll.nAdjNode;
            if(!visited[j]){
                edgeTo[j] = s;
                DFS(j);
            }
            ll = ll.next;
        }
    }

    public boolean pathTo(int u, int v, boolean method){
        /* @param method: true for DFS and false for BFS*/
        for(int i=0; i<V; i++) edgeTo[i] = i; //Initialization of edgeTo array, each node's parent is itself.

        int[] path = new int[V]; int i =0;   path[i++] = v; // maintain the path
        boolean flag = false;

        if(u>V && v>V) return flag; // whether the node index is valid
        if(method)  DFS();
        else BFS(0);

        int nP = edgeTo[v], nS = v;

        while(true){
            path[i] = nP;   i++;
            if(nP==nS) break;
            else if(nP==u){ flag = true; break;} // find a path, break
            else{  nS = nP;  nP = edgeTo[nS];} // else, continue to check its parent.
        }
        path[i++] = u;
        // print path while there is one (denoted by flag)
        if(flag){
            System.out.println("Path from " + u +" to "+v +":");
            for(int j=i-2; j>=0; j--)
                System.out.print(path[j]+"==>");
        }
        return flag;}

    public boolean isCycle(boolean method){
        /* @Description:
         * @param method: true for DFS and false for BFS.
        * @Return:
        */
        if(method) return DFSCycle();
        else return BFSCycle(0);
    }

    private boolean DFSCycle(){
        Arrays.fill(visited,false);
        boolean flag = false;
        for(int i=0; i<V; i++)
            if(!visited[i])
                flag =  DFSCycle(i,i);

        return flag;
    }
    private boolean DFSCycle(int u, int v){
        boolean flag = false;
        visited[u] = true;
        EdgeNode ll = AdjList[u].next;
        while(ll!=null){

            int nj = ll.nAdjNode;
            if(!visited[nj]) DFSCycle(nj,v);
            else if(nj!=u) {flag = true; break;}
            // Because it always start from u,
            // therefore as long as one find a node is already visited b a node but node u,
            // it means there is a cycle and passed u.

            ll = ll.next;
        }
        return flag;
    }


    private boolean BFSCycle(int s){
        // use color to denote whether a node is already visited or not, or is going to be visited. When there is a cycle,
        // the node that is gonna visited is waiting for decision of whether it is done
        // (When a node is done, it is colored to black so as to denote all the adjacent node related are visited.).
        // Because for queue, the node is visited is dequeued, then all the node that adjacent to it and also not visited is enqueued.
        int[] color = new int[V];
        Arrays.fill(color,0); // 0 for not visited, 1 for waiting to be decided, and -1 for done.
        Queue<Integer> q = new LinkedList<>();  q.offer(s);

        while(!q.isEmpty()){
            int m = q.poll();
            color[m] = 1;

            EdgeNode ll = AdjList[m].next;
            while(ll!=null){
                int n = ll.nAdjNode;
                if(color[n]==0){
                    color[n] = 1;
                    q.offer(n);
                }
                else if(color[n]==1){ return true;}
                ll = ll.next;
            }
            color[m] = -1;

        }
        return false;
    }


    public boolean isAllColored(int depth,int c){// depth actually denotes the index of checked node.
        for(int i=0;i<V;i++){
            if(adjMatrix[i][depth]<Integer.MAX_VALUE||
                    adjMatrix[depth][i]<Integer.MAX_VALUE){
                if(color[i]==c)
                    return false;
                if(color[i]==0)
                    continue;
            }
        }
        return true;
    }

    public void dfs_color(int depth,int color_s){
        String str = "dfs_color: depth = %d, color_s = %d\n";
        System.out.format(str,depth,color_s);
        if(color_s>=min_color)//The color currently is larger than min_color, backtrack
            return;
        if(depth>=V){    //coloring complete
            min_color = min_color<color_s? min_color:color_s;
            return;
        }

        for(int i=0;i<color_s;i++){ // each time check use color 0 to color_s can color nodes 0 to depth successfully.
            if(isAllColored(depth,i)){
                color[depth]=i;
                dfs_color(depth+1,color_s);
                color[depth]=0; // denote that this node is already colored correctly.
            }
        }
        /*update to a new color*/
        color[depth]=color_s+1;
        dfs_color(depth+1,color_s+1);
        color[depth]=0;
    }


    private void buildAdjListByMat(){
        this.AdjList = new EdgeNode[V];
        for(int i=0; i<V; i++){
            EdgeNode eltmp = new EdgeNode(i,-1);
            for(int j = 0; j<V; j++){
                if(adjMatrix[i][j]<Integer.MAX_VALUE)
                    eltmp.add(j,adjMatrix[i][j]);
            }
            AdjList[i] = eltmp;
        }
    }

    private void buildAdjMatByList(){
        this.adjMatrix = new int[V][V];
        for(int i=0;i<V;i++){
            Arrays.fill(adjMatrix[i],Integer.MAX_VALUE);
        }
        for(int i=0; i<this.AdjList.length; i++){
            EdgeNode tmp = AdjList[i].next;
            while(tmp!=null){
                adjMatrix[i][tmp.nAdjNode]=tmp.weight;
                tmp = tmp.next;
            }
        }
    }

    private int find(int i,int[] parent){
        while(parent[i]!=i)
            i = parent[i];

        return i;
    }

    private void unionlgn(int i, int j, int[] parent, int[] H){
        int a = find(i,parent);
        int b = find(j,parent);
        if (H[a]>H[b]){
            parent[b] = a;
            H[a] += H[b];
        }
        else if(H[a]<=H[b]){
            parent[a] = b;
            H[b] += H[a];
        }
    }

    private void unionlgstarn(int i, int j, int[] parent, int[] H){
        int a = find(i,parent);
        int b = find(j,parent);

        if (H[a]>H[b]){
            parent[b] = a;
            H[a] += H[b];
        }
        else if(H[a]<=H[b]){
            parent[a] = b;
            H[b] += H[a];
        }
        int m = 0;
        int k = 0;
        while(k<H.length){

            if(H[k]==1){
                m = k;
                int mp = m;
                while(parent[mp]!=mp) mp = parent[mp];
                parent[m] =  mp; //link the leaf node directly to root

            }
            k++;
        }
    }

    public LinkedList<int[]> Kruskal(){
        /* @Description: To solve MST, or the minimal total path. There's no priority queue used in this algo.
            One specific requirement for this is detect whether there's a cycle in a tree and the tree one node
            belongs to. One solution for this is union-find, check here for details: https://algs4.cs.princeton.edu/15uf/
         * @param
        * @Return:
        */

        int[] H = new int[V];
        int[] parent = new int[V];
        for(int i=0; i<V; i++){
            H[i]=1;
            parent[i] = i;
        }

        LinkedList<int[]> T = new LinkedList<>();
        PriorityQueue<Edge> edges_heap = new PriorityQueue<>();
        edges_heap.addAll(edge);


        int cost = 0;

        while(T.size()<V-1){
            Edge tmp = edges_heap.poll();
            int a = find(tmp.getSrc(),parent);
            int b = find(tmp.getDest(),parent);
            // only when a and b from different tree can then be merged, otherwise they will generate a cycle.
            if(a!=b){
                int[] added = {tmp.getSrc(),tmp.getDest()};
                T.offer(added);
                cost += tmp.getWeight();
                unionlgstarn(a,b,parent,H);
            }
        }
        System.out.println("The minimal cost: " + cost);
        System.out.println("Edges in MST (kruskal): ");
//        for(int i=0; i<T.size(); i++){
//            System.out.print(Arrays.toString(T.get(i))+", ");
//        }

        return T;
    }

    private void deleteObj(PriorityQueue<Edge> pq, Edge e){
        Iterator<Edge> itr = pq.iterator();
        while(itr.hasNext()){
            Edge e_tmp = itr.next();
            if(e.isEqual(e_tmp)){
                pq.remove(e_tmp);
                break;
            }
        }
    }

    public LinkedList<int[]> Prim(){
        /* @Description: To solve MST, or the minimal total path.
         * @param
         * @Return:
         */
        int[] costmin = new int[V];
        int[] visited = new int[V];

        LinkedList<int[]> T = new LinkedList<>();

        //Initialization
        Arrays.fill(visited,0);
        visited[0] = -1;
        Arrays.fill(costmin,-1);
        PriorityQueue<Edge> pq = new PriorityQueue<>(); // To get the next visted node.
        for(int i=1; i<V;i++){
            costmin[i] = adjMatrix[0][i];
            pq.add(new Edge(0,i,costmin[i]));
        }

        int nCost = 0;
        while(T.size()<V-1){
            Edge next_e = pq.poll();
            int next = next_e.dest;
            costmin[next] = -1;

            nCost += next_e.weight;
            int[] added = {next,visited[next]};
            T.add(added); // Do add new edge here!

            int n_src = next_e.src;
            for(int i=1; i<V; i++){
                if(adjMatrix[i][next]<costmin[i] || adjMatrix[next][i]<costmin[i]){
                    int w_new = Math.min(adjMatrix[i][next],adjMatrix[next][i]);
                    costmin[i] = w_new;
                    visited[i] = next;

                    // update node in pq corresponding to visited and costmin
                    Edge d = new Edge(n_src,i,Math.min(adjMatrix[n_src][i],adjMatrix[i][n_src]));
                    deleteObj(pq,d);
                    d.setWeight(w_new); d.setSrc(next);
                    pq.add(d); // The elements in pq should be updated synchronously.
                }
            }

        }

        System.out.println("The cost of MST (prim): "+nCost);
//        System.out.println("Edges in MST (prim): ");
//        for(int i=0; i<T.size(); i++){
//            System.out.print(Arrays.toString(T.get(i))+", ");
//        }

        return T;
    }

    public LinkedList<Edge> Dijstra(){
        /* @Description: To get the shortest path between any two nodes.
         * @param
        * @Return:
        */
        int[] D = new int[V]; // D[i] maintain the distance between node i and P[i]
        int[] P = new int[V]; // P[i] is the node in shortest path ahead of node i.
        int nDSum = 0;

        LinkedList<Edge> T = new LinkedList<Edge>(); // Tree of shortest path.
        Arrays.fill(P,0);
        P[0] = -1;// node 0 is the source.
        D[0] = 0;
        for(int i=1;i<V;i++){
            D[i] = adjMatrix[0][i];
        }

        // set C maintains the unchecked index of nodes.
        ArrayList<Integer> C = new ArrayList<>();
        for(int i=1; i<V; i++){
            C.add(new Integer(i));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i=1;i<V;i++){
            pq.add(new Edge(0,i,D[i]));
        }


        while(C.size()>0){
            Edge next_e = pq.poll();
            int next = next_e.dest;
            int n_src = next_e.src;

            T.add(next_e);
            nDSum += next_e.weight;

            C.remove(new Integer(next));

            for(int i: C){
                int na = Math.min(adjMatrix[next][i],adjMatrix[i][next]);
                if(na<Integer.MAX_VALUE && D[next]<Integer.MAX_VALUE && D[i]>na+D[next]){
                    int d_old = D[i];
                    D[i] = na+D[next];
                    P[i] = next;

                    //update edges in pq
                    Edge d = new Edge(n_src,i,d_old);
                    deleteObj(pq, d);
                    d.setSrc(next); d.setWeight(D[i]);
                    pq.add(d);
                }
                // delete the node of
            }

        }
        System.out.println("Edges in shortest path (dijstra): ");
        System.out.println("Prenode "+ Arrays.toString(P));
        System.out.println("Distance to node 0"+ Arrays.toString(D));

//        printPath(T);
        return T;
    }

    public int[][] floyd(){
        int[][] D = new int[V][V];
        D = adjMatrix.clone();

        for(int k=0; k<V; k++){
            for(int i=0; i<V; i++){
                for(int j=0; j<V; j++){
                    if(D[i][k]<Integer.MAX_VALUE && D[k][j]<Integer.MAX_VALUE)
                        D[i][j] = Math.min(D[i][j], D[i][k]+D[k][j]);
                }
            }
        }

        return D;
    }

    public void printT(LinkedList<int[]> T){
        String formatStr = "{%d,%d},";
        for(int[] e: T){
            System.out.format(formatStr,e[0],e[1]);
        }
        System.out.print("\n");
    }

    public void printPath(LinkedList<Edge> P){
        for(Edge p: P){
            System.out.println(p.toString());
        }
    }

    public static int[][] createAdjMatrix(){
        int[][] es = {
                {1,2,4},
                {2,3},
                {3,4},
                {5},
                {5},
                {},
        };
        int[][] esw = {
                {2,8,7},
                {5,1},
                {9,8},
                {4},
                {3},
                {},
        };
        int nV = es.length;
        int[][] adj = new int[nV][nV];
        for(int i=0; i<nV; i++){
            Arrays.fill(adj[i],Integer.MAX_VALUE);
        }

        for(int i = 0; i<es.length;i++) {
            for (int j = 0; j < es[i].length; j++) {
                adj[i][es[i][j]] = esw[i][j];
                adj[es[i][j]][i] = esw[i][j];
            }
        }

        return adj;
    }

    public static EdgeNode[] createTestAdjList(){
        int[][] es = {
                {1,2,4},
                {2,3},
                {3,4},
                {5},
                {5},
                {},
        };
        int[][] esw = {
                {2,8,7},
                {5,1},
                {9,8},
                {4},
                {3},
                {},
        };
        // each edge is denoted once in original data.

        EdgeNode[] edges = new EdgeNode[es.length];
        for(int i = 0; i<es.length; i++){
            EdgeNode eltmp = edges[i];
            if(eltmp==null)
                eltmp = new EdgeNode(i,-1);
            for(int j = 0; j<es[i].length; j++){
                int u = i; int v = es[i][j]; int nw = esw[i][j];
                eltmp.add(v,nw);
                // non-oriented graph, and thus one edge is saved twice in adjacent list.
                if(edges[v]!=null){
                    edges[v].add(u,nw);
                }else{
                    EdgeNode ll = new EdgeNode(v,-1);
                    ll.add(u,nw);
                    edges[v] = ll;
                }
            }
            edges[i] = eltmp;
        }
        return edges;
    }

    public static void main(String[] args){
        int nE = 15;
        String formstr = "\n----------%s----------\n";

//        System.out.format(formstr,"build graph: adjacent matrix");
//        Graph g1 = new Graph(createAdjMatrix(),nE);
//        g1.printG();

        System.out.format(formstr,"build graph: adjacent linked list");
        Graph g2 = new Graph(createTestAdjList(),nE);
//        g2.printG();

        // Graph coloring: the minimal number of colors to color a non-oriented graph
//        g2.dfs_color(0,0);
//        System.out.println(g2.min_color);
//        System.out.println(Arrays.toString(g2.color));

//        System.out.format(formstr,"traversal: BFS by adjacent list");
//        g2.BFS(0);
//        System.out.format(formstr,"traversal: DFS by adjacent list -- recursion");
//        g2.DFS(); // start from 0 in default.
//
//        System.out.format(formstr,"is a path between nodes "+0+" and "+3 +"exists?-- DFS");
//        g2.pathTo(0,3,true);
//        System.out.format(formstr,"is a path between nodes "+0+" and "+3 +"exists?-- BFS");
//        g2.pathTo(0,3,false);
//
//        System.out.format(formstr,"is a cycle exists?-- DFS");
//        System.out.println(g2.isCycle(true));
//        System.out.format(formstr,"is a cycle exists?-- BFS");
//        System.out.println(g2.isCycle(false));



        System.out.format(formstr,"MST: kruskal with priority queue");
        LinkedList<int[]> T_k = g2.Kruskal();
        g2.printT(T_k);

        System.out.format(formstr,"MST: prim");
        LinkedList<int[]> T_p = g2.Prim();
        g2.printT(T_p);

        System.out.format(formstr,"Shortest path (source to others): dijstra");
        LinkedList<Edge> P_dij = g2.Dijstra();
        g2.printPath(P_dij);
//
//        System.out.format(formstr,"Shortest path (source to others): floyd, useful for graph with negative edges but not negative cycles");
//        int[][] mat_floyd = g2.floyd();
//        for(int i=0; i<mat_floyd.length;i++)
//            System.out.println(Arrays.toString(mat_floyd[i]));


    }


}
