package DSA_RSA;

public class Queue01_ArrayEnqueue_Dequeue {

	int rear;
	int front;
	int[] a;
	int size;
	

	public Queue01_ArrayEnqueue_Dequeue(int size)
	{
		rear = -1;
		this.size=size;
		//front = 0;
	this.a = new int[size];
	}
	
	/**
	 * To Add the element at end
	 */
	public void enQueue(int x)
	{
			rear++;
			a[rear]= x;
	}
	
	/**
	 * To remove the first element
	 * @return
	 */
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
		
		Queue01_ArrayEnqueue_Dequeue qd = new Queue01_ArrayEnqueue_Dequeue(5);
		qd.enQueue(4);
		qd.enQueue(2);
		qd.enQueue(9);
		qd.enQueue(3);
		qd.enQueue(12);
		System.out.println("_______After adding the elements with Enqueue______");
		qd.printQueue();
		qd.deQueue();
		qd.deQueue();
		qd.deQueue();
		System.out.println("_______After removing the elements with Dequeue______");
		qd.printQueue();
		

	}

}
