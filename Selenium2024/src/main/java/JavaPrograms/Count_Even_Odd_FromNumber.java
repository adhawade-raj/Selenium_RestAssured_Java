package JavaPrograms;

public class Count_Even_Odd_FromNumber {

	public static void main(String[] args) {
    
		int num = 1234;
        int even_Count = 0;
        int odd_Count = 0;
        
        while(num>0)
        {
        	int rem = num%10;
        	
        	if(rem%2==0)
        	{
        		even_Count++;
        	}
        	
        	else {
        		odd_Count++;
        	}
        	
        	num = num/10;
        }
        
        System.out.println("No of even numbers is:" +even_Count);
        System.out.println("No of odd numbers is:" +odd_Count);
	}

}
