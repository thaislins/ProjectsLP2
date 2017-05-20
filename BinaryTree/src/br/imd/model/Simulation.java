package br.imd.model;

import br.imd.treeview.TreeGUI;

public class Simulation {

	public static void main(String[] args) {
		Tree tree1 = new Tree();
		TreeGUI t = new TreeGUI();
		t.setTree(tree1);
		t.pause(200);
		
		tree1.insertStudent(4, "N");
		tree1.insertStudent(2, "S");
		tree1.insertStudent(5, "J");
		tree1.insertStudent(1, "A");
		tree1.insertStudent(3, "K");
		tree1.insertStudent(7, "K");
		t.repaint();
	}
}
