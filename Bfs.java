package com.test;

import java.util.*;
import java.io.*;

/**
 *    중요 첫 1이 첫 가로줄을 센다 무엇을 세냐면 그 가로줄에 존재하는 1을 센다
 *    1은 무조건 세는거라서 센다
 *    조건이 1을 찾는거라 서다
 *    큐에 쌓아서 마지막 쌓은걸 찾으면 그 가로줄로 이동 그리고 선택한 가로줄은 큐에서 없앤다
 *    
 *    예) 2 5 쌓으면
 *    5로 이동해서 1을 찾는다
 *    
 *    이게 충격적인게 해당 가로줄로 이동하면 2 5 에서 5의 똑같은 숫자 즉 같은 포지션으로 이동한다 저절로
 *    5번재 가로줄로 이동하면 그건 이미 false로 되어있다 당연히 왜냐하면 첫줄은 이미 false로 설정했기 때문이다
 *    
 *    그렇게 진행해서 큐를 다비우면 저절로 다 세진다
 *    아까 2 5 에서 안세진 2로 돌아가서 다센다
 *                               offer   offer poll   offer poll    poll
 *    예)0 0 0 0 0 0 0 0  0   예) 2 ->    5 ->         6 ->          2
 *     0 0 1 0 0 1 0 0  1  	 2 5 5는 셋으니 밑에인 2로 돌아간다 2가 큐에 남았기 때문이다
 *     0 1 0 1 0 0 0 0  2  	 그러면 0 1 0 1 이 쓰여긴것에서 마지막1인 3번째를 센다
 *     0 0 1 0 0 0 0 0  3
 *     0 0 0 0 0 0 0 1  4
 *     0 0 0 0 0 0 1 0  5
 *     0 0 0 0 0 1 0 0  6
 *     0 0 0 0 1 0 0 0  7
 *     ~
 *     
 */
public class Bfs {
	static int arr[][];
	static boolean visit[];
	static int length;
	static int pairLength;
	static int result = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        length = Integer.parseInt(st.nextToken());
        
        
        arr = new int[length+1][length+1];
        visit = new boolean[length+1];
        
        st = new StringTokenizer(br.readLine());
        pairLength = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i < pairLength; i++) {
        	st = new StringTokenizer(br.readLine(), " ");
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	arr[x][y] = arr[y][x] = 1;
        }
    	
        
        System.out.println(bfs(1));
    }
    
    public static int bfs(int j) {
    	Queue<Integer> queue = new LinkedList<>();
    	visit[j] = true;
    	queue.offer(j); //검사할 가로줄 입력
    	
    	
    	while(!queue.isEmpty()) {
      int temp = queue.poll();
      
	    	for(int k = 1; k <= length; k++) {
	      if(arr[temp][k] == 1 && !visit[k]) { // 가로줄 검사해서 1이면서 한번도 검사안한 가로줄 찾기
	      queue.offer(k); //검사한걸 또 찾으면 센걸 또세버린다 검사했다는건 그 가로줄에 1이 존재했다는것
	      visit[k] = true;
	      result++;
	      }
	      
	    	}
    	}
    	
    	return result;
    }


}
