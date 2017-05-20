package br.imd.model;

import java.lang.NullPointerException;
import br.imd.treeview.TreeGUI;

public class Tree {

	private Node root;
	private Tree leftTree;
	private Tree rightTree;

	public Tree() {
		
	}

	//Getters and Setters
	
	public Tree getRightTree() {
		return rightTree;
	}

	public void setRightTree(Tree rightTree) {
		this.rightTree = rightTree;
	}

	public Tree getLeftTree() {
		return leftTree;
	}

	public void setLeftTree(Tree leftTree) {
		this.leftTree = leftTree;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	/* Returns height of the tree
	 */ 
	public int getHeight(Tree tree) {
		if (tree == null)
			return 0;
		return Math.max(getHeight(tree.getLeftTree()), getHeight(tree.getRightTree())) + 1;
	}

	/* Inserts Student in the tree
	 * A new Student object is created and inserted into a node,
	 * which is then inserted into the tree through the method "insert"
	 */ 
	public void insertStudent(int id, String name) {
		Student student = new Student(id, name);
		Node node = new Node(student);
		insert(node);
	}

	/* Inserts a Node in the tree
	 * If the Node's id is bigger than it's root, then the value
	 * is inserted on the right, if not it's inserted on the left
	 */ 
	private void insert(Node node) {
		if (this.root == null) {
			this.root = node;
		} else {
			//
			if (node.getStudent().getId() > this.root.getStudent().getId()) {
				if (this.rightTree == null) {
					this.rightTree = new Tree();
				}
				this.rightTree.insert(node);
			} else if (node.getStudent().getId() < this.root.getStudent().getId()) {
				if (this.leftTree == null) {
					this.leftTree = new Tree();
				}
				this.leftTree.insert(node);
			}
		}

	}

	/* Searches for a value inside the tree
	 * If value is found then the root is returned,
	 * if not an exception is thrown
	 */
	public Node search(int id) throws NullPointerException {
		if (root == null) {
			throw new NullPointerException();
		} else {
			if (id > root.getStudent().getId()) {
				return rightTree.search(id);
			} else if (id < root.getStudent().getId()) {
				return leftTree.search(id);
			} else {
				return root;
			}
		}
	}

	/* Traverses tree in Pre-order
	 */ 
	public void preOrderTraversal(Tree tree) {
		if (tree != null) {
			System.out.print(tree.getRoot().getStudent());
			preOrderTraversal(tree.getLeftTree());
			preOrderTraversal(tree.getRightTree());
		}
	}
	
	/* Traverses tree in In-order
	 */ 
	public void inOrderTraversal(Tree tree) {
		if (tree != null) {
			inOrderTraversal(tree.getLeftTree());
			System.out.print(tree.getRoot().getStudent());
			inOrderTraversal(tree.getRightTree());
		}
	}

	/* Traverses tree in Post-order
	 */ 
	public void postOrderTraversal(Tree tree) {
		if (tree != null) {
			postOrderTraversal(tree.getLeftTree());
			postOrderTraversal(tree.getRightTree());
			System.out.print(tree.getRoot().getStudent());
		}
	}

	/* Removes Node from tree
	 * The Node to be removed is determined by the student's id
	 * If the value is removed, then the method returns true,
	 * if not it returns false
	 */
	public boolean remove(int id) {
		if (this.root == null) {
			System.out.println("Value not found");
			return false;
		}
		if (this.root.getStudent().getId() == id) {
			if (this.leftTree == null && this.rightTree == null) {
				this.root = null;
			} else if (this.leftTree != null && this.rightTree == null) {
				this.root = this.leftTree.root;
				this.rightTree = this.leftTree.rightTree;
				this.leftTree = this.leftTree.leftTree;
			} else if (this.rightTree != null && this.leftTree == null) {
				this.root = this.rightTree.root;
				this.leftTree = this.rightTree.leftTree;
				this.rightTree = this.rightTree.rightTree;
			} else {
				Tree old = this;
				Tree curr = this.leftTree;
				while (curr.rightTree != null) {
					old = curr;
					curr = curr.rightTree;
				}
				this.root = curr.root;
				if (old == this) {
					this.leftTree = curr.leftTree;
				} else {
					old.rightTree = curr.leftTree;
				}
			}
				System.out.println("Value removed");
				return true;
		}
		Tree old = null;
		Tree curr = this;
		while (curr != null && curr.root.getStudent().getId() != id) {
			if (curr.root.getStudent().getId() > id) {
				old = curr;
				curr = curr.leftTree;
			} else {
				old = curr;
				curr = curr.rightTree;
			}
		}

		if (curr == null) {
			System.out.println("Value not found");
			return false;
		}

		if (curr.leftTree == null && curr.rightTree == null) {
			if (old.leftTree == curr) {
				old.leftTree = null;
			} else {
				old.rightTree = null;
			}
		} else if (curr.leftTree != null && curr.rightTree == null) {
			if (old.leftTree == curr) {
				old.leftTree = curr.leftTree;
			} else {
				old.rightTree = curr.leftTree;
			}
		} else if (curr.leftTree == null && curr.rightTree != null) {
			if (old.leftTree == curr) {
				old.leftTree = curr.rightTree;
			} else {
				old.rightTree = curr.rightTree;
			}
		} else {
			Tree old2 = curr;
			Tree curr2 = curr.leftTree;
			while (curr2.rightTree != null) {
				old2 = curr2;
				curr2 = curr2.rightTree;
			}
			curr.root = curr2.root;
			if (old2 == curr) {
				curr.leftTree = curr2.leftTree;
			} else {
				old2.rightTree = curr2.leftTree;
			}
		}
		System.out.println("Value removed");
		return true;
	}
}
