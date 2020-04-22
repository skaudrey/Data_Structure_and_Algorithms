package intra.h2020;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: intra.h2020
 * @Description: TODO
 * @date 2020/4/13 Heap built as an array, but each elements is a node, and the node has three pointers which are parent, left and right.
 */
public class nodeheap {
    private class node{
        public int pi; // the priority of this node
        public node parent;
        public node left;
        public node right;

        public node(){
            pi = Integer.MAX_VALUE;
            parent = null;
            left = null;
            right = null;
        }
        public node(int pi){
            this.pi = pi;
            parent = null;
            left = null;
            right = null;
        }
    }

    node[] heap;
    int size;
    node root;

    public nodeheap(int n){// initialize a table in size n
        heap = new node[n];
        size = n;
        root = new node(0);
        heap[0] = root;
        for(int i=1; i<n; i++){
            int pInd = (i-1)/2;
            node p = heap[pInd];

            node aNode = new node();
            aNode.parent = p;
            if(i%2==1){
                p.left = aNode;
            }
            else{
                p.right = aNode;
            }
            heap[i] = aNode;
        }
    }

    public void decreaseKey(int i, int p){ // i is the id of the node is gonna be decreased, and p is the priority.
        // Note, the priority of node at heap[i] is bigger or equal to p before, and thus the original node at heap[i]
        // before decreaseKey is hoped to be putted by like swim() cause in priority queue, one with larger priority is supposed
        // to be closed to root so as to access it quickly.
        node aNode = heap[i];
        aNode.pi = p;
        node P = aNode.parent; // So, one need to check up along N's parent.

        while(P!=null && P.pi<aNode.pi) {
            // continue until find a node that has bigger priority (aka smaller value in min-heap) compared with pi
            node G = P.parent;
            aNode.parent = G;
            // replace P by N
            if(G==null) root = aNode;// aNode's parent is null means it has been at root position.
            else if(P == G.left) G.left = aNode;
            else G.right = aNode;
            if(aNode == P.left){// Then replace N by P
                P.left = aNode.left;
                if(P.left!=null) P.left.parent = P;
                aNode.left = P;
                P.parent = aNode;
                node r = aNode.right;
                aNode.right = P.right;
                if(aNode.right!=null) aNode.right.parent = aNode;
                P.right = r;
                if(P.right!=null) P.right.parent = P;}
            else{
                P.right = aNode.right;
                aNode.right.parent = P;
                if(P.right!=null) P.right.parent = P;

                aNode.right = P;
                P.parent = aNode;

                node l = aNode.left; //tmp variable

                aNode.left = P.left;
                if(aNode.left!=null) aNode.left.parent = aNode;

                P.left = l;
                if(P.left!=null) P.left.parent = P;}
            P = G;}
    }

}
