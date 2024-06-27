package main;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Lottery extends Thread {
	
	private int[] values = new int[61];
	private static int bet, num1, num2, num3;
	
	public Lottery() {
		initializeArray();
	}
	
	public void run() {
		
		int counter1 = 1, counter2 = 0;
		mix(values);
		
		for(int i = 0; i <= 15; i++) {
			
			if(values[i] == num1 || values[i] == num2 || values[i] == num3) {
				System.out.println(values[i] + " You got it right! (" + counter1++ + ")");
				counter2++;
			}
			else {
				System.out.println(values[i]);
			}
			
			try {
				sleep(700);
				
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		switch(counter2) {
		
			case 0:
				bet = 0;
				System.out.println("You acerted " + counter2 + " numbers. Profits: $" + bet);
				break;
			case 1:
				bet = bet + (bet / 2);
				System.out.println("You acerted " + counter2 + " numbers. Profits: $" + bet);
				break;
			case 2:
				bet = bet * 2;
				System.out.println("You acerted " + counter2 + " numbers. Profits: $" + bet);
				break;
			case 3:
				bet = bet * 3 ;
				System.out.println("You acerted " + counter2 + " numbers. Profits: $" + bet);
				break;
				
		}
		
		System.out.println("Press ctrl + Z to exit (ctrl + C in Unix)");
		
	}
	
	private void mix(int[] arr) {
		
		int index;
		int s;
		Random r = new Random();
		int i = arr.length - 1;
		
		while(i > 0) {
			index = r.nextInt(i + 1);
			s = arr[index];
			arr[index] = arr[i];
			arr[i] = s;
			i--;
		}
		
	}
	
	private void initializeArray() {
		for(int i = 0; i < 60; i++) {
			values[i] = i;
		}
	}
	
	public static void main(String[] args) {
		
		Lottery l = new Lottery();
		int scan = 0;
		
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("Welcome! - Write the amount to bet (integer number) and then press ENTER - ctrl + Z to exit (ctrl + C in Unix)");
			
			try {
				scan = in.nextInt();
				bet = scan;
				
				while(scan >= 0) {
					System.out.println("Write the first number (from 0 to 60) to bet and then press ENTER");
					scan = in.nextInt();
					num1 = scan;
					System.out.println("Write the second number (from 0 to 60) to bet and then press ENTER");
					scan = in.nextInt();
					num2 = scan;
					System.out.println("Write the third number (from 0 to 60) to bet and then press ENTER");
					scan = in.nextInt();
					num3 = scan;
					
					System.out.println("Drawing numbers...");
					l.start();
					scan = in.nextInt();
				}
				
			} catch(InputMismatchException e) {
				e.printStackTrace();
			} catch(NoSuchElementException ex) {
				ex.printStackTrace();
			}
			
			if(scan < 0) {
				throw new IllegalArgumentException("Bet cannot be negative");
			}
		}
		
	}

}
