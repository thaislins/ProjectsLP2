package br.imd;

public class Node {
	 
	private Student student;
	
	public Node() {
		
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
    
}
