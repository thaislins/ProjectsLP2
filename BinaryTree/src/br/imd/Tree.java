package br.imd;

public class Tree {

	private Node root;
	private Tree leftTree;
	private Tree rightTree;
	
	public Tree(){
		// construtor vazio
	}
	
	public Tree getRightTree(){
		return rightTree;
	}
	
	public void setRightTree(Tree rightTree){
		this.rightTree = rightTree;
	}
	
	public Tree getLeftTree(){
		return leftTree;
	}
	
	public void setLeftTree(Tree leftTree){
		this.leftTree = leftTree;
	}
	
	 public Node getRoot() {
	    return root;
	 }

	public void setRoot(Node root) {
	        this.root = root;
	}
	
	public void insertStudent(int id, String name) {
        Student student = new Student(id, name);
        Node node = new Node(student);
        insert(node);
    }

	private void insert(Node node) {
		if(this.root == null){
		   this.root = node;
		}
		else {
			// 
			if (node.getStudent().getId() > this.root.getStudent().getId()){
				if (this.rightTree == null){
					this.rightTree = new Tree();
				}
				this.rightTree.insert(node);
			}
			else if (node.getStudent().getId() < this.root.getStudent().getId()){
				if (this.leftTree == null){
					this.leftTree = new Tree();
				}
				this.leftTree.insert(node);
			}
		}
		
	}
	
	public Node search(int id) throws NullPointerException {
		if(root == null) {
			throw new NullPointerException();
		}
		else {
			if(id > root.getStudent().getId()) {
				return rightTree.search(id);
			}
			else if (id < root.getStudent().getId()) {
				return leftTree.search(id);
			}
			else {
				return root;
			}
		}
	}
		
	// faltando implementar percorrerInOrdem();
	public void preOrderRoute(Tree tree) {
		if (tree != null) {
			System.out.print(tree.getRoot().getStudent());
			preOrderRoute(tree.getLeftTree());
			preOrderRoute(tree.getRightTree());
		}
	}
	
	// faltando implementar percorrerPreOrdem();
	public void inOrderRoute(Tree tree) {
		if (tree != null) {
			inOrderRoute(tree.getLeftTree());
			System.out.print(tree.getRoot().getStudent());
			inOrderRoute(tree.getRightTree());
		}
	}
	
	
	// faltando implementar percorrerPosOrdem();
	public void postOrderRoute(Tree tree) {
		if (tree != null) {
			postOrderRoute(tree.getLeftTree());
			postOrderRoute(tree.getRightTree());
			System.out.print(tree.getRoot().getStudent());
		}
	}

}
