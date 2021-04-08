import delayedRemovalBST.DelayedRemovalBST;

public class TestDelayedRemovalBST {
	public static void main(String[] args) {
		
		//create a new tree		
		DelayedRemovalBST<Integer> delRemBST=new DelayedRemovalBST<Integer>();
				
		//insert new nodes: 55,34,61,57,83
		delRemBST.insert(55);
		delRemBST.insert(34);
		delRemBST.insert(61);
		delRemBST.insert(57);
		delRemBST.insert(83);
		
		
		delRemBST.printTree(); //print tree in pre-order. expected output: 55 34 61 57 83
		System.out.println("Size of the tree:"+delRemBST.size());
		System.out.println("Height of the tree:"+delRemBST.height()); //current height of the tree
		
		delRemBST.remove(61);
		System.out.println("After removal of 61:");
		delRemBST.printTree(); //print tree in pre-order. expected output: 55 34 _61 57 83
				
		delRemBST.insert(6);
		System.out.println("After insertion of 6:");
		delRemBST.printTree(); //print tree in pre-order. expected output: 55 34 6 _61 57 83
		
		delRemBST.remove(83);
		System.out.println("After removal of 83:");
		delRemBST.printTree(); //print tree in pre-order. expected output: 55 34 6 _61 57 _83
		
		delRemBST.remove(34);
		System.out.println("After removal of 34:");
		delRemBST.printTree(); //print tree in pre-order. expected output: 55 _34 6 _61 57 _83

		delRemBST.insert(61);
		System.out.println("After insertion of 61:");
		delRemBST.printTree(); //print tree in pre-order. expected output: 55 _34 6 61 57 _83
		
		delRemBST.clearAllRemoved(); // clear all nodes marked removed
		delRemBST.printTree(); //print tree in pre-order. expected output: 55 6 61 57 
		System.out.println("Size of the tree:"+delRemBST.size());
		System.out.println("Height of the tree:"+delRemBST.height()); 	
	}
}
