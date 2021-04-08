package delayedRemovalBST;

import java.util.Stack;

public class DelayedRemovalBST<Type extends Comparable<? super Type>> {
	/**
	 * Root of the BST
	 */
	private BinaryNode<Type> root;
	
	/**
	 * Number of nodes satisfying isRemoved = false.
	 */
	private int numNodes;
	
	/**
	 * Total number of nodes (regardless of isRemoved = false or true)
	 */
	private int numTotalNodes;
	
	public DelayedRemovalBST() {
		
		root=null;
	
	}
	
	public boolean isEmpty() {
		
		return root==null;              //isEmpty
	}
	
	/**
	 * Number of non-removed nodes in the BST
	 * @return Number of nodes satsifying isRemoved = false
	 */
	public int size() {
		return numNodes;
	}
	
	/**
	 * Height of the BST disregarding removed nodes
	 * @return BST height disregarding removed nodes
	 */
	public int height() {
		return height(root)-1;
		
	}
	private int height(BinaryNode<Type> subTree)
    {
        
        if (subTree == null) {
            return 0;
        }

        // recursively for left and right subtree and consider maximum depth
        return  Math.max(height(subTree.left), height(subTree.right))+1;
    }
	
		
	
	
	
	/**
	 * Searches for the specified key in this BST
	 * @param key Value to be searched
	 * @return true if key appears as a node in the BST, false otherwise 
	 */
	public boolean contains(Type key) {
		return contains(key,root);
	}
	private boolean contains(Type key, BinaryNode<Type> subTree) {		
		if(subTree == null) {    //check isEmpty
			return false;
		} 
		int comparison = key.compareTo(subTree.data);
		if (comparison == 0) { 
			return true;
		} else if(comparison < 0) {         //chose left or right child
			return contains(key, subTree.left);
		} else //right
			return contains(key, subTree.right);
	}
	
	/**
	 * Searches for the node that contains the specified key in this BST.
	 * If the node found is tagged with isRemoved = true, find will fail. 
	 * @param key The data to be located
	 * @return Reference of the node containing the key
	 */
	public BinaryNode<Type> find(Type key) {     
		
		return find(key,root);
	}
	
	
	private BinaryNode<Type> find(Type key, BinaryNode<Type> subTree) {		
		if(subTree == null) {
			return null;
		} 
		int comparison = key.compareTo(subTree.data);
		if (comparison == 0 && subTree.isRemoved==false) { //control isRemoved
			return subTree;
		} else if(comparison < 0 ) { //chose left or right child
			return find(key, subTree.left);
		} else //right
			return find(key, subTree.right);
	}
	
	/**
	 * Finds the node that contains the smallest data in the BST.
	 * Nodes marked removed will be disregarded.
	 * @return Reference to the min-data node in the BST
	 */
	public BinaryNode<Type> findMin() {
		
		return findMin(root);
		
		
	}
	private BinaryNode<Type> findMin(BinaryNode<Type> subTree) {
		if(subTree == null) {  //check isEmpty
			return null;
		} else if(subTree.left == null && subTree.isRemoved==false) { //find left
			
			return subTree;
		}
		
		return findMin(subTree.left);
		
	}
	
	/**
	 * Finds the node that contains the highest data in the BST.
	 * Nodes marked removed will be disregarded.
	 * @return Reference to the max-data node in the BST
	 */
	public BinaryNode<Type> findMax() {
		
		return findMax(root);
	}
	private BinaryNode<Type> findMax(BinaryNode<Type> subTree) {
		if(subTree == null) { //check isEmpty
			return null;
		}
		while(subTree.right != null && subTree.isRemoved==false )
			subTree = subTree.right;   //find right
		return subTree;
	}
	
	/**
	 * Prints ALL nodes of the BST using pre-order traversal. 
	 * For removed nodes, an underscore character ('_')
	 * will be printed before the node data.
	 */
	public void printTree() {
		printTree(root,0);
		System.out.println();
	}
	private void printTree(BinaryNode<Type> subTree, int level) {
		if(subTree != null) {
			if(subTree.isRemoved) {
				
				System.out.print("_"+subTree.data.toString()+" ");
				
			}else { System.out.print(subTree.data.toString()+" ");}
			printTree(subTree.left, level+1);
			printTree(subTree.right, level+1);
		}
	}
	
	/**
	 * Inserts the given data into the tree. Notice that there are 3 cases:
	 * (i) data already appears in the tree and isRemoved = false for this node. In this case, do nothing.
	 * (ii) data already appears in the tree and isRemoved = true for this node. In this case, set isRemoved = false for the node.
	 * (iii) data does not appear in the tree. In this case, insert a new node as usual.
	 * @param data Data to be inserted
	 */
	public void insert(Type data) {
		
		root=insert(data,root);
		numNodes++;	 //for size     						
	}
	private BinaryNode<Type> insert(Type data, BinaryNode<Type> subTree) {
		
		
		if(subTree == null) {        //check isEmpty
			return new BinaryNode<>(data);
			
		}
		
		int comparison = data.compareTo(subTree.data); 
		if(comparison == 0) {
			
		} else if(comparison < 0) { 
			subTree.left = insert(data, subTree.left); //smaller
			
		} else { 
			subTree.right = insert(data, subTree.right);  //biger
			
		} 
		if(subTree.isRemoved==true) {  //insert marked data
			subTree.isRemoved=false;
		}
		return subTree;
	}
	
	/**
	 * Removes the given data from the tree. Notice that there are 3 cases:
	 * (i) data does not appear in the tree. In this case, do nothing.
	 * (ii) data appears in the tree and isRemoved = false for this node. In this case, set isRemoved = true for the node.
	 * (iii) data appears in the tree and isRemoved = true for this node. In this case, do nothing.
	 * @param data
	 */
	public void remove(Type data) {
		numNodes--;
		
		
		find(data).isRemoved=true;  //mark removed data
		
		
	}
	
	/**
	 * Removes all nodes of the BST that are marked removed.
	 * Resulting BST will have the same numNodes, and numNodes = numTotalNodes after the call.
	 */
	public void clearAllRemoved() {
		
		root=clearAllRemoved(root);
		
	}
     private BinaryNode<Type> clearAllRemoved(BinaryNode<Type> subTree) {
		
    	 Stack<BinaryNode> stack=new Stack<>();  //create stack
    	 DelayedRemovalBST<Type> d=new DelayedRemovalBST<>();
    	 BinaryNode<Type> p;
    	 stack.push(root);  
    	 while(!stack.isEmpty()){  // check isEmpty
    	     if(stack.peek().isRemoved!=true) //is peek false
    	         d.insert((Type) stack.peek().data);
    	     p=stack.pop();        
    	     if(p.right!=null)
    	         stack.push(p.right);
    	     if(p.left!=null)
    	         stack.push(p.left);
    	 }
    	     return d.root;  //return root
		
	}
	
}
	   

