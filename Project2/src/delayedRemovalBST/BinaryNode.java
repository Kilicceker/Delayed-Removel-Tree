package delayedRemovalBST;

public class BinaryNode<Type> {
	/**
	 * True if this node has been removed from the tree, false otherwise.
	 */
	protected boolean isRemoved;
	protected Type data;
	protected BinaryNode<Type> left;
	protected BinaryNode<Type> right;
	
	public BinaryNode(Type data) {
		this.data = data;
		left = right = null;
		isRemoved = false;
	}
	
	public BinaryNode(Type data, BinaryNode<Type> left, BinaryNode<Type> right) {
		this.data = data;
		this.left = left;
		this.right = right;
		isRemoved = false;
	}
	
	public Type getData() {
		return data;
	}
	
	public String toString() {
		return data.toString();
	}
}
