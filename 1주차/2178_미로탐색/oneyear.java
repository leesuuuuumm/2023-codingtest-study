import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B2178_미로탐색 {

    static int N,M ;
    static int [] di = {1,0,-1,0}, dj ={0, 1, 0, -1};
    static boolean [][] visited;
    static int [][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] st = br.readLine().split(" ");
        N = Integer.parseInt(st[0]);
        M = Integer.parseInt(st[1]);

        arr = new int[N][M];
        for(int i=0; i<N; i++) {
            char [] ch =  br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                arr[i][j]=Integer.parseInt(String.valueOf(ch[j]));
            }
        }

        visited = new boolean[N][M];
        visited[0][0]=true;
        bfs(0,0); // 방법 1
        gogo(0,0); // 방법 2
        System.out.println(arr[N-1][M-1]);

    }


    // 방법 1
    public static void bfs(int x, int y) {
        Queue<int []> q = new LinkedList<>();
        q.add(new int [] {x,y});

        while(!q.isEmpty()) {
            int [] tmp = q.poll();
            int r = tmp[0] , c = tmp[1];

            for(int d=0; d<4; d++) {
                int ni = r + di[d];
                int nj = c + dj[d];

                if(ni>=0 && ni<N && nj>=0 && nj<M && arr[ni][nj]==1 && visited[ni][nj]==false) {
                    visited[ni][nj]=true;
                    arr[ni][nj] = arr[r][c] + 1;
                    q.add(new int [] {ni,nj});
                }
            }
        }
    }


    // 방법 2
    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void gogo(int x , int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x,y));

        while (!q.isEmpty()) {
            Point point = q.poll();
            int r = point.x;
            int c = point.y;

            for(int i=0; i<4; i++) {
                int nr = r + di[i];
                int nc = c + dj[i];

                if(nr>=0 && nr<N && nc>=0 && nc<M && visited[nr][nc]==false && arr[nr][nc]==1) {
                    visited[nr][nc]=true;
                    arr[nr][nc] = arr[r][c] + 1;
                    q.add(new Point(nr,nc));
                }
            }
        }
    }
}
