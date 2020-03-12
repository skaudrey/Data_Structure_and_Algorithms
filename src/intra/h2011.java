package intra;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: intra
 * @Description: TODO
 * @date 2020/2/10 H2011 E4: the heap of points
 */
public class h2011 {
    private class Point{
        public float x;
        public float y;
        public int indice;
        public Point(float x, float y, int indice){
            this.x = x;
            this.y = y;
            this.indice = indice;
        }


    }

    private Point[] heapX;
    private  Point[] heapY;
    int nSize;

    public h2011(){
        heapX = new Point[1000];
        heapY = new Point[1000];
        heapX[0] = null;
        heapY[0] = null; // make sure the indices starts from 1.
        nSize = 0;
    }

    private float getCoords(Point aP, boolean isX){
        return isX? aP.x: aP.y;
    }

    private Point getPoint(boolean isX, int i){
        return isX? heapX[i] : heapY[i];
    }

    private boolean setPoint(int i, Point changed, boolean isX){
        /* @Description:
         * @param i	: the position to put changed
         * @param changed
         * @param isX
        * @Return:
        */
        if(isX){
            heapX[i] = changed;
        }else{
            heapY[i] = changed;
        }
        return true;
    }

    public void insert(float x, float y){
        // do insertion twice, one to adjust heapX, one to adjust heapY.
        if(nSize==0){
            heapX[1] = new Point(x,y,1);
            heapY[1] = new Point(x,y,1);
            return;
        }
        int i = nSize;
        int j = insert(true,x,y,i);
        int m = insert(false,x,y,i);

        // Do insertion finally, cause now we get all the indices for a same node, both in heapX and heapY.
        heapX[j] = new Point(x,y,m);
        heapX[m] = new Point(x,y,j);
        nSize++;
    }

    private int insert(boolean isX, float x, float y,int i){
        // do insertion for heapX or heapY.
        // no size increment in it, only do it in public function.
        Point tmp = getPoint(isX,i);

        while(i!=1 && getCoords(tmp, isX)<getCoords(getPoint(isX,i/2),isX)){
            setPoint(i,getPoint(isX,i/2),isX);
            synchronize(i,tmp.indice,isX);
            tmp = getPoint(isX, i/2);
            i /= 2;
        }
        return i;
    }

    private boolean synchronize(int x_ind, int y_ind, boolean isX){
        if(isX){
            heapX[x_ind].indice = y_ind;
            heapY[y_ind].indice = x_ind;
        }else {
            heapX[y_ind].indice = x_ind;
            heapY[x_ind].indice = y_ind;
        }
        return true;
    }

    public Point deleteMinX(){
        if(nSize==0)
            return null;
        Point aDPoint = heapX[1];
        int yInd_d = heapX[1].indice;
        Point tmp_x = heapX[nSize]; // the one is gonna be deleted.
        Point tmp_y = heapY[nSize];
        int i = 1;
        int j = 1;

        // delete for heapX
        while(i*2+1<=nSize){
            float x1 = heapX[2*i].x;
            float x2 = heapX[2*i+1].x;
            int j_x = x1<x2?2*i:2*i+1;
            heapX[i] = heapX[j_x];
            heapY[heapX[i].indice].indice = i; // after each changing, do update the indices in heapY.
            i = j_x;
        }

        //delete for heapY, need to decide wither sink or insert.
//        tmp_y.indice = i;
        heapY[tmp_x.indice].indice = i;
        // update the correpsonding one who's at the end of heapX but may not at the end of heapY.
        if(tmp_y.y>heapY[yInd_d].y){
            // delete for heapY
            while(j*2+1<=nSize){
                float y1 = heapY[2*j].y;
                float y2 = heapY[2*j+1].y;
                int j_y = y1<y2?2*j:2*j+1;
                heapY[j] = heapY[j_y];
                heapX[heapY[j].indice].indice = j; // after each changing, do update the indices in heapY.
                j = j_y;
            }
        }
        else{
            // the indices stored in heapY has been updated, but the one in heapX should be updated.
            j = yInd_d;
        }
        // set tmp_y in heapY, and also update the corresponding indice in heapX
        heapX[tmp_y.indice].indice = j;
        heapY[j] = tmp_y;

        // set tmp_x in heapX, and also update the corresponding indice in heapY
        tmp_x.indice = heapY[tmp_x.indice].indice;
        heapX[i] = tmp_x;

        // set null for the last one in heap
        heapX[nSize] = null;
        heapY[nSize] = null;
        nSize--;

        return aDPoint;
    }

    public Point deleteMinY(){
        if(nSize==0)
            return null;
        Point aDPoint = heapY[1];
        int xInd_d = heapY[1].indice;
        Point tmp_x = heapX[nSize]; // the one is gonna be deleted.
        Point tmp_y = heapY[nSize];
        int i = 1;
        int j = 1;

        // delete for heapY
        while(i*2+1<=nSize){
            float y1 = heapY[2*i].y;
            float y2 = heapY[2*i+1].y;
            int j_y = y1<y2?2*i:2*i+1;
            heapY[i] = heapY[j_y];
            heapX[heapY[i].indice].indice = i; // after each changing, do update the indices in heapX.
            i = j_y;
        }

        //delete for heapX, need to decide wither sink or insert.
        heapX[tmp_y.indice].indice = i;
        if(tmp_x.x>heapX[xInd_d].x){
            // delete for heapY
            while(j*2+1<=nSize){
                float x1 = heapX[2*j].x;
                float x2 = heapX[2*j+1].x;
                int j_x = x1<x2?2*j:2*j+1;
                heapX[j] = heapX[j_x];
                heapY[heapX[j].indice].indice = j; // after each changing, do update the indices in heapY.
                j = j_x;
            }
        }
        else{
            // the indices stored in heapY has been updated, but the one in heapX should be updated.
            j = xInd_d;
        }
        // set tmp_x in heapX, and also update the corresponding indice in heapY
        heapY[tmp_x.indice].indice = j;
        heapX[j] = tmp_x;

        // set tmp_x in heapY, and also update the corresponding indice in heapX
        tmp_y.indice = i;
        heapX[i] = tmp_y;

        // set null for the last one in heap
        heapX[heapX.length] = null;
        heapY[heapY.length] = null;
        nSize--;

        return aDPoint;
    }





    public static void main(String[] args){


    }
}
