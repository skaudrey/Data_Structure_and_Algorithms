package Ex5;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Ex5
 * @Description: TODO
 * @date 2020/2/8 Tree
 */
public class Tree<T> {

    public TreeNode root;
    private int nDepth=0;

    public Tree(LinkedList<T> data){
        createBinaryTree(0,data);
    }
    private TreeNode<T> createBinaryTree(int nIndex, LinkedList<T> data){
        // create a tree by pre order
        if(data.isEmpty()) return null;
        T d = data.pop();
        if(d==null ) return null; //The null node is denoted by "#" or null.
        TreeNode<T> aNode = new TreeNode<T>(d);
        if(d.equals('#')) { // caution: for character, you need '#' rather than "#"
            return null;}

        if(nIndex == 0) this.root = aNode;

        aNode.left = createBinaryTree(++nIndex,data);
        aNode.right = createBinaryTree(++nIndex,data);
         return aNode;
    }

    public int getHeight(TreeNode aNode){
        if(aNode==null ) return 0;

        int nH_L = getHeight(aNode.left);
        int nH_R = getHeight(aNode.right);
        return nH_L>nH_R? nH_L+1: nH_R+1;
    }

    public int getExposition(TreeNode aNode){
        /*
        * Not the same as height, need the branch with smaller height + 1
        * */
        if(aNode==null ) return 0;

        int nH_L = getExposition(aNode.left);
        int nH_R = getExposition(aNode.right);
        return nH_L<nH_R? nH_L+1: nH_R+1;
    }

    public int getDepth(TreeNode aNode){
        getDepth(root, 0);

        if(aNode==null) return nDepth;
        else{
            TreeNode tmp_l = root;
            TreeNode tmp_r = root;

            while(tmp_l!=null && tmp_r!=null && !tmp_l.data.equals(aNode.data) && !tmp_l.data.equals(aNode.data) ){
                tmp_l = tmp_l.left;
                tmp_r = tmp_r.left;
            }
            int nD = tmp_l.nDepth>tmp_r.nDepth? tmp_l.nDepth: tmp_r.nDepth;
            return nD;
        }
    }

    public void getDepth(TreeNode aNode, int nDepth){
        if(aNode==null) return ;
        aNode.nDepth = nDepth;
        if(aNode.left==null && aNode.right==null){
            this.nDepth = this.nDepth>aNode.nDepth? this.nDepth:aNode.nDepth;
        }
        getDepth(aNode.left,nDepth+1);
        getDepth(aNode.right,nDepth+1);

    }

    public int interNodesPath(TreeNode aNode, int nDepth){
        if(aNode==null) return 0;
//        aNode.nDepth = nDepth;
        if(aNode.left==null && aNode.right==null){;}

        int dl = interNodesPath(aNode.left,nDepth+1);
        int dr = interNodesPath(aNode.right,nDepth+1);
        return dl>dr? dl+nDepth:dr+nDepth;

    }

    public int exterNodesPath(TreeNode aNode, int nDepth){
        if(aNode==null) return 0;
//        aNode.nDepth = nDepth;
        if(aNode.left==null && aNode.right==null){
            return 1;
        }
        int dl = exterNodesPath(aNode.left,nDepth+1);
        int dr = exterNodesPath(aNode.right,nDepth+1);
        return dl+dr+nDepth;
    }

    public int getSize(){
        return getSize(root);
    }
    private int getSize(TreeNode aNode){
        if(aNode==null) return 0;
        int nSize_left = getSize(aNode.left);
        int nSize_right = getSize(aNode.right);
        return nSize_left+nSize_right+1;
    }


    public void preOrderTraverse(TreeNode aNode){
        if(aNode==null){
            return;
        }
        System.out.print(aNode.data+" ");
        preOrderTraverse(aNode.left);
        preOrderTraverse(aNode.right);
    }


    public void midOrderTraverse(TreeNode aNode){
        if(aNode==null)
            return;
        midOrderTraverse(aNode.left);
        System.out.print(aNode.data+" ");
        midOrderTraverse(aNode.right); // before print the right of C, print the left of it
    }


    public void lastOrderTraverse(TreeNode aNode){
        if(aNode==null) return;
        lastOrderTraverse(aNode.left);
        lastOrderTraverse(aNode.right);
        System.out.print(aNode.data+" ");
    }

    public void traverseDescending(TreeNode x){
        traverseDescending(x,(Character) x.data);
    }

    private Character traverseDescending(TreeNode x, Character nCurMax){
        if(x==null){ return nCurMax;}
        if(x!=null && x.left==null && x.right==null){
            Character s = (Character) x.data>nCurMax? (Character)x.data:nCurMax;
            System.out.println(s);
            return s;
        }
        Character a = traverseDescending(x.left,nCurMax);
        Character b = traverseDescending(x.right,nCurMax);
        nCurMax = a>b? a: b;
        System.out.println(nCurMax);
//        traverseDescending(x,nCurMax);
        return nCurMax;
    }

    public int leafNum(TreeNode aNode){
        if(aNode!=null){
            if((aNode.left==null) && (aNode.right==null)){return 1;}
            return leafNum(aNode.left) + leafNum(aNode.right);
        }
        return 0;

    }

    public boolean isHalfBalanced(TreeNode aNode){
        if(aNode==null)
            return false;
        int[] arr = getMinMaxHeight(aNode);

        System.out.printf("height: %d;  min-height: %d",arr[0],arr[1]);
        return arr[0]>arr[1]*2?false:true;
    }

    public int[] getMinMaxHeight(TreeNode aNode){
        if(aNode==null){
            int[] arr = {0,0};
            return arr;
        }
        int[] arrL = getMinMaxHeight(aNode.left);
        int[] arrR = getMinMaxHeight(aNode.right);
        int h = arrL[0]>arrR[0]? arrL[0]+1:arrR[0]+1;
        int r = arrL[1]<arrR[1]? arrL[1]+1:arrR[1]+1;
        int[] arr = {h,r};
        return arr;
    }

    public TreeNode lca(T u, T v){
//        TreeNode tmp = root;

        return lca(root,u,v);
    }

    public TreeNode lca(TreeNode aNode, T u, T v){
        //during the demo given by TA, the value in left child is smaller than the parent,
        // and the right one is larger than the parent, so demo defines which branch to take by comparing values.
//        while(aNode!=null){
//            if(u<aNode.data && v<aNode.data){
//                aNode = aNode.left;
//            }
//            else if(u>aNode.data && v>aNode.data){
//                aNode = aNode.right;
//            }
//            else {System.out.println(aNode.toString());return aNode;}
//        }

        if((aNode.left!=null &&aNode.right!=null) && ((aNode.left.data.equals(u) &&aNode.right.data.equals(v)) || (aNode.right.data.equals(u) && aNode.left.data.equals(v)))){
            return aNode;
        }
        else if(aNode.left!=null || aNode.right!=null){
            TreeNode a  = lca(aNode.left,u,v);
            TreeNode b = lca(aNode.right,u,v);
            return a==null? (b==null? null: b):a;
        }
        else{
            return null;
        }
    }


    public String encode(TreeNode aNode){
        if(aNode==null) return "#";
        else
            return encode(aNode, "");
    }

    private String encode(TreeNode aNode, String str){
        if(aNode==null){return str+" # ";}
//        if(aNode.left==null && aNode.right==null){return str+" O O ";}
        str += "O ";
        String strl = encode(aNode.left, str);
        String strr = encode(aNode.right, strl);
        return strr;
    }

    public Character minLeaf(TreeNode aNode,Character curMin){
        if(aNode==null)
            return Character.MAX_VALUE>curMin? curMin:Character.MAX_VALUE;
        if (aNode.left==null && aNode.right==null)
            return (Character)aNode.data<curMin? (Character) aNode.data:curMin;
        else{
            Character min_l = minLeaf(aNode.left,curMin);
            Character min_r = minLeaf(aNode.right,curMin);
            return min_l<min_r?min_l:min_r;
        }
    }

    public TreeNode sortLeaf(TreeNode aNode, TreeNode lc, TreeNode rc){
        /* @Description:  Sort the tree rooted by aNode, so as the left leaf is smaller than the right one.
         * @param aNode
        * @Return:
        */
        if(aNode==null)
            return null;
        if(lc==null || rc==null) // one child or no child
            return aNode;
//        if(aNode!=null && lc.left==null && aNode.right==null && rc.left==null && rc.right==null){
//            if((Character)lc.data>(Character)rc.data){
//                aNode.left = rc;
//                aNode.right = lc;
//            }
//            return aNode;
//        }
        TreeNode a = sortLeaf(aNode.left,aNode.left.left,aNode.left.right);
        TreeNode b = sortLeaf(aNode.right,aNode.right.left,aNode.right.right);
        if((Character)a.data>(Character) b.data){
            aNode.left = b;
            aNode.right = a;
        }
        return aNode;
    }



    private class TreeNode<T>{

        public T data;
        public int index;
        public TreeNode left;
        public TreeNode right;
        public int nDepth;

        public TreeNode(T cVal){

            data = cVal;
            left = null;
            right = null;
            index = -1;  // when it is not set, the index is invalid.
        }

        public void setIndex(int nIndex){
            this.index = nIndex;
        }


        @Override
        public String toString() {
            if(this==null){
                return "null";
            }

            return "TreeNode{" +
                    "data=" + data +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static void main(String[] args){
        String formatStr = "\n---------------%s---------------\n";
        Character[] tmp = {'A','B','D','#','#','#','C','E','#','F'};
        LinkedList<Character> data = new LinkedList<>();
        for(Character i: tmp) data.offer(i);

        System.out.printf(formatStr,"Create Tree and preorder traverse");
        Tree<Character> myTree = new Tree<Character>(data);
        myTree.preOrderTraverse(myTree.root);
        System.out.printf(formatStr,"Create Tree and midorder traverse");
        myTree.midOrderTraverse(myTree.root);
//        System.out.printf(formatStr,"Create Tree and last order traverse");
//        myTree.lastOrderTraverse(myTree.root);
//        System.out.printf(formatStr,"a.1a, a.1b: the exposition for each node, not height, need the branch with smallest height+1");
//        System.out.print(myTree.getHeight(myTree.root.right));
//        System.out.printf(formatStr,"depth");
//        System.out.print(myTree.getDepth(myTree.root.left.left));
//        System.out.printf(formatStr,"depth");
//        System.out.print(myTree.getDepth(myTree.root.left));
//        System.out.printf(formatStr,"size");
//        System.out.print(myTree.getSize());
//
//        System.out.printf(formatStr,"a.1c: the boudary of exposition");
//        System.out.println("n nodes, each nodes has at most k children. \n Maximum: n; Minimum: log(d,n)");
//        System.out.printf(formatStr,"size");
//        System.out.print(myTree.getSize());
//
//        System.out.printf(formatStr,"a.2: lca");
//        System.out.print(myTree.lca('B','C').toString());
//
//        System.out.printf(formatStr,"a.3a: the length of internal path");
//        System.out.print(myTree.interNodesPath(myTree.root.right.left,0));
//
//        System.out.printf(formatStr,"a.3b: the length of external path");
//        System.out.print(myTree.exterNodesPath(myTree.root,0));
//
//        System.out.printf(formatStr,"a.4a: encode");
//        System.out.print(myTree.encode(myTree.root));
//
//        System.out.printf(formatStr,"intra-H2011-E5-half-balanced-tree: get height and min height in parallel.");
//        System.out.print(myTree.isHalfBalanced(myTree.root));
//
//        System.out.printf(formatStr,"intra-A2010-E5-labeled-tree: print the leaf with minimal value.");
//        Character s = myTree.minLeaf(myTree.root,Character.MAX_VALUE);
//        System.out.println(s.toString());

//        System.out.printf(formatStr,"intra-A2010-E5-labeled-tree: sort leaves so that the left one is smaller than the right one's.");
//        myTree.sortLeaf(myTree.root,myTree.root.left,myTree.root.right);
//        System.out.println(myTree.root.toString());

//        System.out.printf(formatStr,"intra-H2006-3-binary-tree: traversing the tree and show all elements in descending");
//        myTree.traverseDescending(myTree.root);

    }
}
