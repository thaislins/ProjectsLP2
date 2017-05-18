package br.imd.treeview;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Graphics;
import java.awt.Color;

import br.imd.model.Tree;

public class TreeGUI extends JFrame {
	
	private int frameSize;
	private int nodeSize;
	private int rootX;
	private int rootY;
	private Tree tree;
	private JTextField textField;
	private JButton btnInsert;
	
	public TreeGUI() {
		
		frameSize = 960;
		nodeSize = 50;
		
		setTitle("Binary Tree");
		setSize(frameSize,frameSize);
		setBackground(Color.white);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//textField = new JTextField();
		//textField.setBounds(40, 66, 114, 19);
		//getContentPane().add(textField);
		//textField.setColumns(10);
		
		/*btnInsert = new JButton("Insert");
		btnInsert.setBounds(40, 108, 117, 25);
		getContentPane().add(btnInsert);*/
	}
	
	public void setTree(Tree tree) {
		this.tree = tree;
	}
	
	public void paint(Graphics g) throws NullPointerException {
		
		rootX = frameSize/2 - 50;
		rootY = frameSize/24;
	
		g.setColor(Color.black);
		printTree(tree,g);

	}
	
	public void printTree(Tree tree,Graphics g) {
		
		g.drawOval(rootX,rootY,nodeSize,nodeSize);
		g.drawString(tree.getRoot().toString(), rootX + 20, rootY + 30);
		
		tree.getRoot().setRootX(rootX);
		tree.getRoot().setRootY(rootY);
		
		if(tree.getLeftTree() != null) {
			drawNodeLeft(g,tree.getLeftTree().getRoot().toString(),tree.getRoot().getRootX(),tree.getRoot().getRootY());
			printTree(tree.getLeftTree(),g);
		}
		if(tree.getRightTree() != null) {
			drawNodeRight(g,tree.getRightTree().getRoot().toString(),tree.getRoot().getRootX(),tree.getRoot().getRootY());
			printTree(tree.getRightTree(),g);
		}	
	}
	
	public void drawNodeLeft(Graphics g, String str, int X, int Y) {
		
		rootX = X - 100;
		rootY = Y + 100;
		
		//LeftSon: 330,140
		g.drawOval(X - 100,Y + 100,nodeSize,nodeSize);
		
		//To leftSon: 440,100,370,140
		g.drawLine(X + 10, Y + 50, X - 60, Y + 100);
		g.drawString(str, rootX + 20, rootY + 30);
	}

	public void drawNodeRight(Graphics g,String str, int X, int Y) {
		
		rootX = X + 100;
		rootY = Y + 100;
		
		//RightSon: 530,140
		g.drawOval(X + 100,Y + 100,nodeSize,nodeSize);
	
		//To RightSon: 480,100,540,140
		g.drawLine(X + 40, Y + 50, X + 110, Y + 100);
		g.drawString(str, rootX + 20, rootY + 30);
		
	}
	
	
	public static void main(String args[]) {
		
		Tree tree1 = new Tree();
		TreeGUI t = new TreeGUI();
		t.setTree(tree1);
		
		tree1.insertStudent(4, "N");
		tree1.insertStudent(2, "S");
		tree1.insertStudent(6, "J");
		tree1.insertStudent(1, "A");
		//tree1.insertStudent(3, "K");
		tree1.insertStudent(5, "B");
		tree1.insertStudent(7, "M");
		tree1.insertStudent(8, "Q");
		
		try {
			t.paint(null);
		}
		catch(NullPointerException e) {
			//Do Nothing
		}		
	}
}
