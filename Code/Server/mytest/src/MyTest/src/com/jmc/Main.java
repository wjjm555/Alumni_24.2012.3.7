package com.jmc;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Hello World !");
		MyWebSocketServer tc=new MyWebSocketServer(6080);
		Scanner sc=new Scanner(System.in);
		tc.start();
		while(true) {
			tc.sendAll(sc.nextLine());
		}
		
	}
}
