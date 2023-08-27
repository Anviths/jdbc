package com.tyss;

import java.util.Scanner;

public class ProductController {

	public static void main(String[] args) {

		ProductDao product = new ProductDao();
		Scanner s = new Scanner(System.in);
		Product p = new Product();
		System.out.println("1.save user");
		System.out.println("2.get data by id");
		System.out.println("3.update data");
		System.out.println("4.delete data");
//		System.out.println("5.exit");
		System.out.println("enter choice");
		int choice = s.nextInt();

		switch (choice) {
		case 1: {
			product.saveProduct(p);
			break;
		}
		case 2: {
			System.out.println("enter id to fetch data");
			Product p1 = product.findProductById(s.nextInt());
			if (p1.getId() != 0)
				p1.display();
			break;
		}
		case 3: {
			product.updateProductById(p);

			break;
		}
		case 4: {
			System.out.println("enter id");
			product.removeProductById(s.nextInt());
			;

			break;
		}
//		case 5:
//			System.exit(0);
		default:
			System.out.println("invalid choice");
		}
		s.close();

	}

}
