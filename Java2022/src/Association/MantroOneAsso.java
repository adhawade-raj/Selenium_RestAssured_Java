package Association;

public class MantroOneAsso {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Branch1 branch=new Branch1("b-111", "cs");
Student1 std1=new Student1("s-11","AAA","Hyd",branch);
Student1 std2=new Student1("s-121","Abc","Hyd",branch);
std1.getStudentDetails();
std2.getStudentDetails();
	}

}
