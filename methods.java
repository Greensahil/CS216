
public class methods {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum = 0;
		for(int i = 20; i <=30;i++)
			sum += i;
		System.out.println(sum);
		System.out.println(sum(20,30));
		
		System.out.println(returnMax(400,100));
		
		double power = Math.pow(5, 2);    //5^2
		
	}
	
	public static int sum(int num1, int num2) {
		int sum = 0;
		for(int i = num1; i <=num2;i++)
			sum += i;
		return sum;
	}
	
	
	public static int returnMax(int num1, int num2) {
		if(num1 > num2)
			return num1;
		return num2;
	}
}
