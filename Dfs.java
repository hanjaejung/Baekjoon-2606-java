package com.test;

import java.util.*;
import java.io.*;

/**
 * dfs 정리 바이러스 좌표 찾을시
 * for문으로 x 방향이든 y 방향이든 둘중 하나의 방향으로 센다!!
 *     -->
 * 예) 0 0 0 0 0 0 0 0
 *     0 0 1 0 0 1 0 0
 *     0 1 0 1 0 0 0 0
 *     ~
 *     
 *    그러다 1을 만나면 거기다 false일때 다음 x행이동
 *    예) 0 0 1 --> 1을 만났다
 *       0 1 0 1 0 0 0 0 바로 이 행으로 이동
 *       
 *       for문을 다돌아 맨 밑의 재귀가 끝난다 --> 그것은 바로 같은 행 같은 열에 1로 지정한 수가 없다는 뜻
 *       그전에 행으로 돌아가 못돌린 for문을 돌린다
 * 예) 0 0 0 0 0 0 0 0
 *     0 0 1 0 0 1 0 0
 *     0 1 0 1 0 0 0 0
 *     0 0 1 0 0 0 0 0 --> 여기서  세번째 일 다음에 이어지는 1이 없어서
 *     ~
 *     
 *     그전 행인  0 1 0 1 0 0 0 0 로 돌아가
 *     네번째 1부터 다시 for문 진행
 *     
 *     여기서 핵심 계속 전 재귀로 돌아가는건 1과 연결된 같은 라인을 찾기 위해서다
 *     즉 좌표 dfs는 세로줄을 센다
 */
public class Dfs {

	static int arr[][];
	static boolean visit[];
	static int length;
	static int pairLength;
	static int result;
	
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
    	
        dfs(1);
        System.out.println(result-1);
    }
    
    public static void dfs(int j) {
    	visit[j] = true;
    	result++;
    	
    	for(int k = 1; k <= length; k++) {
    		if(arr[j][k] == 1 && !visit[k]) {
    			dfs(k);
    		}
    		
    	}
    }


}
