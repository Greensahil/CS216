import java.util.Scanner;
public class circle {
	
	public static double circleArea(int radius) { //formal paramter
		return Math.PI*radius*radius;	
	}
	
	public static double circlePerimeter(int radius) {
		return 2 * Math.PI * radius;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int radius1,radius2;
		double area1, area2;
		double perimeter1, perimeter2;
		
		System.out.println("Enter the radius of circle-1");
		radius1 = input.nextInt();
		area1 = circleArea(radius1);
		perimeter1 = circlePerimeter(radius1);
		System.out.println("The area of circle 1 is " + area1 + " and perimeter of circle1 is " + perimeter1);
		
		
	}

}
