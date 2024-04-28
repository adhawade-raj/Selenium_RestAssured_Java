package Association;

public class ManytoManyContructor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Course1 c1=new Course1();
c1.setCid("123");
c1.setCname("java");
c1.setCcost("1500");

Course1 c2=new Course1();
c2.setCid("321");
c2.setCname("testing");
c2.setCcost("1800");


Student3 std1=new Student3();
std1.setSname("Sanky");
std1.setSid("01");
std1.setcourses(c1);
std1.getstudentdetails();

Student3 std2=new Student3();
std2.setSname("raj");
std2.setSid("02");
std2.setcourses(c2);

std2.getstudentdetails();




	}

}
