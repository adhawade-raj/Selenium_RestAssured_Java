package dsa_RSA_2024;

public class Section09_BinaryTrees01_BinarySearchTree {

private class Node {
		int value;
		Node left;
		Node right;
	
	public Node(int value)
	  {
		this.value =value;
	  }
	}
	Node root;
	
	public void insert(int value)
	{
		root = insertNode(root, value);
	}
	
	/**
	 * TO Insert Node
	 * @param args
	 */
	public Node insertNode(Node root, int value)
	{
		
		if ( root == null)
		{
		root = new Node(value);
		return root;
		}
		
		if( value < root.value)
			root.left = insertNode(root.left,value);
		else if (value > root.value)
			root.right = insertNode(root.right, value);
		
		return root;
		
	}
	
	/**
	 * TO search Value
	 * @param value
	 * @return
	 */
	public Boolean valueSearch(int value)
	{
		return search (root,value);
	}
	
	public boolean search(Node root, int value)
	{
		
		if (root == null)
			return false;
		if(value == root.value)
			return true;
		
		if (value< root.value)
		return search(root.left,value);
		
		else
			return search(root.right,value);
		
	}
	
	/**
	 * To get minimum node value
	 * @return
	 */
	public int getMinElement()
    {
    	return minElement(root);
    }
    
    public int minElement(Node root)
    {
    	Node current;
    	current = root;
  
    	
    	while (current.left != null) 
    	{
    		current = current.left;
    	
    	}
    	return current.value;
   
    	}
    
    /**
     * TO get maximum node value
     * @return
     */
    public int getMaxElement()
    {
    	return maxElement(root);
    } 
    
    public int maxElement(Node root)
    {
    	Node current;
    	current = root;
  
    	
    	while (current.right != null) 
    	{
    		current = current.right;
    	
    	}
    	return current.value;
   
    	}
    
    /**
     * To print tree in sorted order
     */
    public void SortedOrder()
	{
		inorderTraverse(root);
	}
	
	public void inorderTraverse(Node root)
	{
		if(root!= null)
		{
			inorderTraverse(root.left);
			System.out.println(root.value);
			inorderTraverse(root.right);
		}
	}
	
	/**
	 * To get height
	 * @return
	 */
	   public int getHeight()
	    {
	    	return height(root);
	    }
	    public int height(Node root)
	    {
	    	if(root == null)
	    		return -1;
	    	
	    	return 1 + Math.max(height(root.left),height(root.right));
	    }
		
	
	public static void main(String[] args) {

Section09_BinaryTrees01_BinarySearchTree tree = new Section09_BinaryTrees01_BinarySearchTree();
		tree.insert(4);
	 	tree.insert(6);
        tree.insert(12);
        tree.insert(3);
        tree.insert(1);
        tree.insert(7);
        tree.insert(14);
        tree.insert(17);
        tree.insert(2);
        //sort logic In order traversal
        System.out.println("Minimum element is "+tree.getMinElement());
        System.out.println("Maximum element is "+tree.getMaxElement());
        System.out.println("Height of tree is "+tree.getHeight());
        System.out.println("Node Value is Present or not : "+tree.valueSearch(14)); //O(logn)
        System.out.println("Node Value is Present or not : "+tree.valueSearch(55));
	}

}
