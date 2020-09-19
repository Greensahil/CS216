
public class circleObjectOriented {
	int radius;
	double area, perimeter;
	
	public void circleArea() { //formal paramter
		area=  Math.PI*radius*radius;	
	}
	
	public void circlePerimeter() {
		perimeter =  2 * Math.PI * radius;
	}
	
	public void display() {
		System.out.println("The area of circle 1 is " + area + " and perimeter of circle1 is " + perimeter);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}


