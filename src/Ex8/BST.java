package Ex8;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Ex8
 * @Description: TODO
 * @date 2020/3/29 Binary search tree
 */
public class BST {
    private class bNode{
        public bNode parent;
        public bNode left;
        public bNode right;
        public int key;
        public int value;
        public int count;

        public bNode(int key, int value, bNode parent){
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = null;
            this.right = null;
            this.count = getSize(this);
        }

        public int getSize(bNode root){
            if(root==null) return 0;
            return 1+getSize(root.left)+getSize(root.right);
        }

        @Override
        public String toString() {
            return "bNode{" +
                    " key=" + key + ","+
                    " value=" + value +
                    '}';
        }
    }
    private int nSize = 0;
    private static bNode root=null;

    public BST(int[] A){
        for(int i: A)
            insert(i,i);

    }

    public void insert(int key, int value){
        if(nSize==0) {
            root = new bNode(key,value,null);
        }
        else{
            insert(root,key,value);
        }

        nSize++;
    }

    private void insert(bNode tmp,int key, int value){
        bNode t = insertLoc(tmp, key);
        if(t.key==key) {
            t.value = value;
            return;
        }
        int np = t.key;
        if(np>key){
            t.left = new bNode(key,value,t);
        }
        else{
            t.right = new bNode(key,value,t);
        }

    }

    private bNode insertLoc(bNode root, int key){
        if(root==null) return root;
        bNode tmp = null;
        if(key<root.key)
            tmp = insertLoc(root.left,key);
        else if(key>root.key)
            tmp = insertLoc(root.right,key);
        else if(key==root.key)
            return root;

        return tmp==null? root: tmp;
    }

    public bNode search(bNode root, int key, int nCount){
        if(root!=null && root.key==key) {
            System.out.print("Cost " +nCount +"steps.\t");
            return root;
        }

        if(key<root.key)
            return search(root.left,key,++nCount);
        else if(key>root.key)
            return search(root.right,key,++nCount);

        return null;
    }

    @Override
    public String toString() {
        return "BST{" +
                "nSize=" + nSize +
                '}';
    }

    public static Queue<bNode> inorder(bNode root, Queue<bNode> q){
        if(root.left!=null)
            inorder(root.left,q);
        if(root!=null)
            q.offer(root);
        if(root.right!=null)
            inorder(root.right,q);
        return q;
    }

    public static void printBST(bNode root){
        Queue<bNode> q = new LinkedList<>();
        inorder(root,q);
        for(bNode aq: q){
            System.out.println(aq.toString());
        }
    }

    public static bNode min(bNode x){
        if(x==null) return null;
        if(x.left==null && x.right==null) return x;
        bNode tmp = x;
        if(x.left!=null){
            tmp =  min(x.left);}
        return tmp;
    }

    public static bNode max(bNode x){
        if(x.left!=null && x.right==null) return x;
        else if(x.left==null && x.right==null) return null;
        bNode tmp = max(x.right);
        return tmp==null? tmp:x;
    }

    public static void delete(bNode z){
        if(z==null) return;
        bNode y = null, x = null;
        if(z.left!=null || z.right!=null)  y = z;
        else  y = min(z);

        if(y.left!=null) x = y.left;
        else x = y.right;
        if(x!=null)  x.parent = y.parent;
        if(y.parent==null)  root = x;
        else{
            if(y.parent.left==y){  y.parent.left = x;}
            else  y.parent.right = x;
        }
        // replace z by y
        if(y!=z){
            if(z.left!=null) z.parent.left = y;
            else if(z.right!=null){z.parent.right = y;}
            y.parent = z.parent;
        }
    }

    public static bNode zip(bNode x, bNode y){
        bNode root = min(y);
        bNode root_rep = min(root.right);
        if(root.parent==null && root_rep==null){
            bNode tmp = x;
            while(tmp.right!=null) tmp = tmp.right;
            tmp.right = y;
            y.parent = tmp;
            return x;
        }
        if(root.parent.left == root) {
            root.parent.left = root_rep;
            root_rep.parent = y;
        }
        else if(root.parent.right==root) {
            root.parent.right = root_rep;
            root_rep.parent = y;
        }
        root.left = null;
        root.right = null;
        x.parent = root;
        y.parent = root;
        root.left = x;
        root.right = y;
        root.parent = null;

        return root;
    }

    public static bNode[] split(bNode r, int key){
        // split the tree rooted by r into 3 parts, the 1st is smaller than key, the last part is larger than key.
        Queue<bNode> x = new LinkedList<>();
        bNode y = null;
        Queue<bNode> z = new LinkedList<>();


        y = split(r,key,x,z);

        bNode[] xyz = new bNode[3];
        xyz[0] = x.poll(); xyz[2] = z.poll();
        while(x.size()>0){
            xyz[0] = zip(xyz[0],x.poll());
        }
        while(z.size()>0){
            xyz[2] = zip(xyz[2],z.poll());
        }
        xyz[1] = y;
        return xyz;
    }

    private static bNode split(bNode r, int key, Queue<bNode> x, Queue<bNode> z){
        bNode rr = null;
        bNode rl = null;
        bNode t =  null;
        if(r.key==key){
            r.left = null;
            r.right = null;
            r.parent = null;
            return r;
        }
        if(r.key<key){ // The left sub-tree rooted in r are smaller than key
            bNode tmp = r;
            rr = r.right;
            tmp.right.parent = null;
            tmp.right = null;
            x.offer(tmp);
            t = split(rr,key,x,z);
        }
        else if(r.key>key){
            bNode tmp = r;
            rl = r.left;
            tmp.left.parent = null;
            tmp.left = null;
            z.offer(tmp);
            t = split(rl,key,x,z);
        }
        return t;
    }

    public static void main(String[] args){
        String formstr = "\n----------%s----------\n";

        System.out.format(formstr,"abr1: build a worst bst");
        int[] A = {2,3,5,7,11,13,17};
        BST abst = new BST(A);
        printBST(abst.root);

        System.out.format(formstr,"abr2-b: searching");
        int[] b1 = {10,110,210,612,450,420,430,444};
        int[] b2 = {873,225,230,666,333,555,400,444};
        int[] b3 = {448,441,447,442,446,443,445,444};
        int[] b4 = {210,901,270,280,450,803,460,444};
        int[] b5 = {935,278,347,621,299,392,358,444};
        BST abst1 = new BST(b1);
        bNode i1 = abst1.search(abst1.root,444,0);
        System.out.println(i1.toString());
        BST abst2 = new BST(b2);
        bNode i2 = abst2.search(abst2.root,444,0);
        System.out.println(i2.toString());
        BST abst3 = new BST(b3);
        bNode i3 = abst3.search(abst3.root,444,0);
        System.out.println(i3.toString());
        BST abst4 = new BST(b4);
        bNode i4 = abst4.search(abst4.root,444,0);
        System.out.println(i4.toString());
        BST abst5 = new BST(b5);
        bNode i5 = abst5.search(abst5.root,444,0);
        System.out.println(i5.toString());

        System.out.format(formstr,"abr2-c: sorting: do inorder traversing");
        printBST(abst5.root);

        System.out.format(formstr,"abr3-zip: merge x and y BST, where elements in x are smaller than elements in y");
        bNode rzip = zip(abst.root,abst2.root);
        printBST(rzip);

        System.out.format(formstr,"abr3-split: split a BST to three parts, the 1st part is smaller than k, the last is larger than k");
        bNode[] xyz = split(rzip,17);
        char[] cxyz = {'x','y','z'};
        int id=0;
        for(bNode i: xyz){
            System.out.println("Show  "+cxyz[id++] +":");
            printBST(i);
        }
        System.out.format(formstr,"abr4-demonstration: ");
        System.out.format(formstr,"x.right==null and x.left!=null, which means y is x's parent ");
        System.out.format(formstr,"x.right!=null and y.left==null, which means y is x's right child ");




    }



}
