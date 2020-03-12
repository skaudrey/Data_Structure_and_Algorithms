package Algo;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Some
 * @Description: TODO
 * @date 2020/3/9 edge node
 */
class EdgeNode{ // the linked list for adjacent list.
    int nAdjNode;
    int weight;
    EdgeNode next;
    EdgeNode pcur=null;
    public EdgeNode(int nAdjNode, int weight){
        this.nAdjNode = nAdjNode; // which is the tail of a header
        this.weight = weight;
        this.next = null;
        pcur = this;
    }
    public void add(int nAdjNode, int weight){
        if(pcur==null){
            this.nAdjNode = nAdjNode; // which is tha tail of a header
            this.weight = weight;
            this.next = null;
            pcur = this;
        }
        else{
            pcur.next = new EdgeNode(nAdjNode, weight);
            pcur = pcur.next;}
    }
};
