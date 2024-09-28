package DSA_RSA;

public class Section05_Queue06_SortingQueue_WithCustomLogic {

	int rear;
	int front;
	int[] a;
	int size;

	public Section05_Queue06_SortingQueue_WithCustomLogic(int size)
	{
		rear = -1;
		this.size=size;
		//front = 0;
	this.a = new int[size];
	}
	
	public void enQueue(int x)
	{
		if(rear == -1)
		{
			rear++;
			a[rear]=x;
		}
		else
		{
		for(int i =rear;i>=0;i--)
		{
			if(a[i]>x)
			{
				a[i+1] = a[i];
				if(i == 0)
					a[i]= x;
			}
			else {
				a[i+1]=x;
				break;
			}
		}
			rear++;
		}

	}
	
	public int deQueue()
	{
		
		int ele =a[0];
		for(int i =0;i<rear;i++)
		{
			a[i] = a[i+1];
			
		}
		rear--;
		return ele;
	}
	
	public void printQueue()
	{
		for(int i =0;i<=rear;i++)
		{
			System.out.println(a[i]);
		}
	}

	
	public static void main(String[] args) {
		Section05_Queue06_SortingQueue_WithCustomLogic qd = new Section05_Queue06_SortingQueue_WithCustomLogic(10);
		qd.enQueue(4);
		qd.enQueue(2);
		qd.deQueue();
		qd.enQueue(9);
		qd.enQueue(3);
		qd.enQueue(12);
		qd.deQueue();
		qd.deQueue();
		qd.printQueue();

	}
}
