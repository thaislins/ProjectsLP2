package br.imd.model;

public class Node {
	 
	private Student student;
	private int rootX;
	private int rootY;
	
	public Node() {
		
	}
	
	public int getRootX() {
		return rootX;
	}

	public void setRootX(int rootX) {
		this.rootX = rootX;
	}

	public int getRootY() {
		return rootY;
	}

	public void setRootY(int rootY) {
		this.rootY = rootY;
	}

    public Node(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    public String toString() {
		return "" + student.getId();
    	
    }
    
}
