import java.io.BufferedReader;import java.io.IOException;import java.io.InputStreamReader;/** * Created by lijian on 2016/11/23. */public class DKNAP {    public int backmax(int i,int l,int h,int []w,int []W,int M){        int k = l;        int max = 0;        for(int r = l;r<=h;r++){            if((W[r] + w[i] <=M)&&(W[r]+w[i]>max)){                max = W[r]+w[i];                k = r;            }        }        return k;    }    public boolean judge(int start,int end,int []F,int p,int w,int []P,int []W){        for(int i = start;i<=end;i++){            if(p == P[i] && w == W[i]){                return true;            }        }        return false;    }    public void PARTS(int []F,int []p,int []w,int []P,int []W,int []X){        int pp = 0,ww = 0;        for(int i = F[F.length-1];i<P.length;i++){            if(P[i+1] == 0&&W[i+1] == 0){                pp = P[i];                ww = W[i];                break;            }        }        for(int i = F.length -1;i>=1;i--){            if(!judge(F[i-1],F[i]-1,F,pp,ww,P,W)){                X[i] = 1;                pp = pp - p[i];                ww = ww - w[i];            }else{                X[i] = 0;            }        }        for(int i = 1;i<X.length;i++){            System.out.print("X"+i+" = "+X[i]+"    ");        }        System.out.println();    }    public void dknap(int []p,int []w,int size,int M,int t){        int []F = new int[size+1];        int []P = new int[t+1];        int []W = new int[t+1];        F[0] = 1;        P[1] = 0;        W[1] = 0;        int start = 1,end=1;        int next,u,pp,ww;        F[1] = 2;        next = 2;        for(int i =1;i<=size;i++){            int k = start;            u = backmax(i,start,end,w,W,M);            for(int j = start;j<=u;j++){                pp = P[j] + p[i]; //S1(i)中的下一个元素的效益                ww = W[j] + w[i]; //S1(i)中的下一个元素的重量                while((k<=end)&&(W[k]<ww)) {                    P[next] = P[k];                    W[next] = W[k];                    next = next + 1;                    k = k + 1;                }                //把已经存在的PW加入到新的Si中                if((k<=end)&&(W[k]==ww)){                    pp = pp > P[k]?pp:P[k];                    k = k + 1;                }                //选择效益高的加入到i中                if(pp > P[next -1]){                    P[next] = pp;                    W[next] = ww;                    next  =next +1;                }                //将最新的加入到Si中                while((k<=end)&&(P[k]<=P[next-1])){                    k = k + 1;                }                //找到            }            while(k<=end){                P[next] = P[k];                W[next] = W[k];                next = next + 1;                k = k + 1;            }            start = end+1;            end = next -1;            if((i+1)<size+1) {                F[i + 1] = next;            }        }        int []X = new int[size+1];        PARTS(F,p,w,P,W,X);    }    public static void main(String []args)throws IOException{        String judge = "y";        while(judge.equals("y")||judge.equals("Y")) {            DKNAP d = new DKNAP();            BufferedReader ss = new BufferedReader(new InputStreamReader(System.in));            System.out.println("请输入物品的数量以及背包的容量(空格键隔开)");            String temp = ss.readLine();            String []num = temp.split(" ");            int size = Integer.parseInt(num[0]);            int M = Integer.parseInt(num[1]);            int []w = new int[size+1];            int []p = new int[size+1];            System.out.println("请输入"+size+"个物品的重量和效益(空格隔开)");            int count = 1;            int t =1;            while(count <= size){                temp = ss.readLine();                String []res = temp.split(" ");                w[count] = Integer.parseInt(res[0]);                p[count] = Integer.parseInt(res[1]);                count = count +1;                t = t+t*2;            }            d.dknap(p,w,size,M,t);            System.out.println("continue?(y/n)");            judge = ss.readLine();        }    }}