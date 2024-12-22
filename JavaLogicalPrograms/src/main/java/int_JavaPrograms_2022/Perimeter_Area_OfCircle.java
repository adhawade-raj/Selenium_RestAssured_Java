package int_JavaPrograms_2022;

public class Perimeter_Area_OfCircle {

	public static void main(String[] args) {

		double radius = 21.0;
		double perimeter,area;
		
//		perimeter = 2*pi*r
		perimeter = 2*Math.PI*radius;
		System.out.println("Pereimeter is :" +perimeter);
		
//		area = pi*r*r(r square)
		area = Math.PI*radius*radius;
		System.out.println("area is :"+area);
		
		
	}

}
