package edu.ty.foodapp.view;

import java.util.Scanner;

import edu.ty.foodapp.bean.Order;
import edu.ty.foodapp.bean.Product;
import edu.ty.foodapp.controller.OrderController;
import edu.ty.foodapp.controller.ProductController;

public class ProductView {

	{
		System.out.println("Welcome to Product App");
	}
	static Scanner read=new Scanner(System.in);
	public static void main(String[] args) {
        ProductController productController=new ProductController();
        OrderController orderController=new OrderController();
        while(true) {
		System.out.println("1.Save product");
		System.out.println("2.find  product by id");
		System.out.println("3.update product price by id ");
		System.out.println("4.delete product by id");
		System.out.println("5.save order");
		System.out.println("6.find order by id");
		System.out.println("7.update order number by id");
		System.out.println("8.delete order by id");
		System.out.println("9.exit");
		System.out.println("Enter the choice");
		int choice=read.nextInt();
		switch (choice) {
		case 1:{
			productController.saveProduct(createProduct());
			
			break;
		}
		case 2:{
			Product product=productController.findProductById(idInput());
			System.out.println(product);
			break;
		}
		case 3:{
			productController.updateProductPriceByProductId(idInput(), doubleInput());
			break;
		}
		case 4:{
			productController.removeProductById(idInput());
			break;
		}
		case 5:{
			orderController.saveOrder(createOrder());
			
			break;
		}
		case 6:{
			Order order =orderController.findOrderById(idInput());
			System.out.println(order);
			break;
		}
		case 7:{
			orderController.updateOrderNameByOrderId(idInput(), StringInput());
			break;
		}case 8:{
			orderController.removeOrderById(idInput());
			break;
		}
		case 9:{
			System.exit(0);
		}

		default:
			System.out.println("invalid choice");
		}
		
	}
	}
	private static double doubleInput() {
		// TODO Auto-generated method stub
		System.out.println("enter the price");
		return read.nextDouble();
	}
	private static String StringInput() {
		// TODO Auto-generated method stub
		System.out.println("enter the value");
		return read.next();
	}
	private static int idInput() {
		System.out.println("enter id");
		return read.nextInt();
		
	}
	private static Order createOrder() {
		Order order=new Order();
		System.out.println("enter the order id");
		order.setOrderId(read.nextInt());
		System.out.println("enter order number");
		order.setOrderNumber(read.next());
		return order;
	}
	private static Product createProduct() {
		Product product=new Product();
		System.out.println("enter the product id");
		product.setProductId(read.nextInt());
		System.out.println("enter the product name");
		read.nextLine();
		product.setProductName(read.nextLine());
		System.out.println("enter the product description");
		product.setProductDescription(read.nextLine());
		System.out.println("enter the product price");
		product.setProductPrice(read.nextDouble());
		System.out.println("enter the order id");
		product.setOrderId(read.nextInt());
		return product;
	}

}
