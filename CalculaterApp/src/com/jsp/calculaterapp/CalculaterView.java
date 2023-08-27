package com.jsp.calculaterapp;

import java.util.Scanner;

import com.jsp.claculatere.LogicController;

public class CalculaterView {
	static {
		System.out.println("Welcome To Calculater App");
	}

	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		LogicController controller = new LogicController();
		while (true) {
			int num1 = 0;
			int num2 = 0;
			System.out.println("Select the Choice:");
			System.out.println("1.Add\n2.Sub\n3.Mul\n4.Div\n5.Exit");
			int choice = read.nextInt();
			if (choice >= 1 && choice < 5) {
				System.out.println("Enter the Number 1 :");
				num1 = read.nextInt();
				System.out.println("Enter the Number 2 :");
				num2 = read.nextInt();
			}
			switch (choice) {
			case 1:
				int sum = controller.addtion(num1, num2);
				display(sum);
				break;
			case 2:
				int sub = controller.sub(num1, num2);
				display(sub);
				break;
			case 3:
				int mul = controller.mul(num1, num2);
				display(mul);
				break;
			case 4:
				int div = controller.div(num1, num2);
				display(div);
				break;
			case 5:
				System.exit(0);
				break;
			default:
				System.out.println("Select The Right Choice!!");
				break;
			}
		}
	}

	public static void display(int result) {
		System.out.println("Result = " + result);
	}

}
