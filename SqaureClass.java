
class SquareClass {
	double length;
	SquareClass(){
		length = 1;
	}
	SquareClass(double inputLength){
		length = inputLength;
	}
	
	double getArea() {
		return Math.pow(length, 2);
	}
	
	double perimeter() {
		return 4*length;
	}
}
