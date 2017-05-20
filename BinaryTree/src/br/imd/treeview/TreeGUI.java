package br.imd.treeview;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.imd.model.Node;
import br.imd.model.Tree;

public class TreeGUI extends JFrame {

	private int frameSize;
	private int nodeSize;
	private int nodeGap;
	private int rootX;
	private int rootY;
	private Tree tree;
	private HashMap<Node, Color> nodeColor;

	public TreeGUI() {
		Container ct = this.getContentPane();
		ct.setLayout(null);

		frameSize = 960;
		nodeSize = 30;
		nodeGap = 20;
		nodeColor = new HashMap<>();

		setTitle("Binary Tree");
		setSize(frameSize, frameSize);
		ct.setBackground(Color.white);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JLabel lblTraversals = new JLabel("Traversals");
		JLabel lblStudent = new JLabel("Student");
		JLabel lblName = new JLabel("Name:");
		JLabel lblId = new JLabel("Id:");
		JButton btnPreorder = new JButton("PreOrder");
		JButton btnInorder = new JButton("InOrder");
		JButton btnPostorder = new JButton("PostOrder");
		JButton btnInsert = new JButton("Insert");
		JButton btnSearch = new JButton("Search");
		JButton btnRemove = new JButton("Remove");
		JTextField nameText = new JTextField();
		JTextField idText = new JTextField();

		Font fontTitles = new Font("Sans Serif", Font.BOLD, 14);
		Font fontBody = new Font("Sans Serif", Font.PLAIN, 12);

		// Bounds
		lblTraversals.setBounds(40, 50, 120, 15);
		lblStudent.setBounds(290, 500, 120, 15);
		lblName.setBounds(290, 550, 70, 15);
		lblId.setBounds(490, 550, 70, 15);
		btnPreorder.setBounds(60, 90, 120, 25);
		btnInorder.setBounds(60, 130, 120, 25);
		btnPostorder.setBounds(60, 170, 120, 25);
		btnInsert.setBounds(420, 590, 120, 25);
		btnSearch.setBounds(420, 630, 120, 25);
		btnRemove.setBounds(420, 670, 120, 25);
		nameText.setBounds(340, 550, 120, 19);
		idText.setBounds(510, 550, 120, 19);

		// Fonts
		lblTraversals.setFont(fontTitles);
		lblStudent.setFont(fontTitles);
		lblName.setFont(fontBody);
		lblId.setFont(fontBody);
		btnPreorder.setFont(fontBody);
		btnInorder.setFont(fontBody);
		btnPostorder.setFont(fontBody);
		btnInsert.setFont(fontBody);
		btnSearch.setFont(fontBody);
		btnRemove.setFont(fontBody);

		// Add to Content Pane
		ct.add(lblTraversals);
		ct.add(lblStudent);
		ct.add(lblName);
		ct.add(lblId);
		ct.add(btnPreorder);
		ct.add(btnInorder);
		ct.add(btnPostorder);
		ct.add(btnInsert);
		ct.add(btnSearch);
		ct.add(btnRemove);
		ct.add(nameText);
		ct.add(idText);

		btnInsert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameText.getText();
				String idstr = idText.getText();
				int id = Integer.valueOf(idstr);

				tree.insertStudent(id, name);
				System.out.println("Inserting value");
				
				nameText.setText("");
				idText.setText("");
				pause(200);
				repaint();
				System.out.println("Value inserted in tree");
			}
		});

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameText.getText();
				String idstr = idText.getText();
				int id = Integer.valueOf(idstr);
				idText.setText("");
				nameText.setText("");
				
				if(name != "" && idstr != "") {
					System.out.println("Only the id value was considered in the search");
				}

				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						if(tree == null || idstr == "")
							return;
						pause(200);
						repaint();
						
						try {
							Node node = tree.search(id);
							System.out.println("Value found");
							highlightNode(node);
							pause();
							repaint();
							unhighlightNode(node);
						}
						catch (NullPointerException n) {
							System.out.println("Value not found");
						}
					}
				});
				t.start();
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameText.getText();
				String idstr = idText.getText();
				int id = Integer.valueOf(idstr);
				idText.setText("");
				nameText.setText("");
				
				if(name != "" && idstr != "") {
					System.out.println("Only the id value was considered in the removal");
				}
				
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						if (tree == null)
							return;
						tree.remove(id);
						System.out.println("Removing value");
						pause();
						ct.repaint();
						pause();
						repaint();
						System.out.println("Value removed and tree updated");
					}
				});
				t.start();
			}
		});

		btnPreorder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Initializing PreOrder Traversal...");
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						if (tree == null)
							return;
						pause(200);
						repaint();
						preOrderTraversalGUI(tree);
						System.out.println("Finished");
					}
				});
				t.start();
			}
		});

		btnInorder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Initializing InOrder Traversal...");
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						if (tree == null)
							return;
						pause(200);
						repaint();
						inOrderTraversalGUI(tree);
						System.out.println("Finished");
					}
				});
				t.start();
			}
		});

		btnPostorder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Initializing PostOrder Traversal...");
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						if (tree == null)
							return;
						pause(200);
						repaint();
						postOrderTraversalGUI(tree);
						System.out.println("Finished");
					}
				});
				t.start();
			}
		});
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}

	@Override
	public void paint(Graphics g) {
		rootX = frameSize / 2 - nodeSize;
		rootY = 40;

		printTree(tree, g);
	}

	public void printTree(Tree tree, Graphics g) {
		if (tree == null || tree.getRoot() == null)
			return;

		Node root = tree.getRoot();
		Tree rTree = tree.getRightTree();
		Tree lTree = tree.getLeftTree();

		if (nodeColor.containsKey(root)) {
			g.setColor(nodeColor.get(root));
		} else {
			g.setColor(Color.black);
		}

		g.drawOval(rootX, rootY, nodeSize, nodeSize);

		g.setColor(Color.black);
		g.drawString(root.toString(), rootX + (nodeSize - 10) / 2, rootY + (nodeSize + 10) / 2);

		root.setRootX(rootX);
		root.setRootY(rootY);

		if (lTree != null) {
			// deslocFactor = (int) Math.pow(2, tree.getHeight(lTree) - 1);
			drawLineLeft(g, root.getRootX(), root.getRootY());
			printTree(lTree, g);
		}
		if (rTree != null) {
			// deslocFactor = (int) Math.pow(2, tree.getHeight(rTree) - 1);
			drawLineRight(g, root.getRootX(), root.getRootY());
			printTree(rTree, g);
		}
	}

	public void highlightNode(Node node) {
		nodeColor.put(node, Color.red);
	}

	public void unhighlightNode(Node node) {
		nodeColor.remove(node);
	}

	public void drawLineLeft(Graphics g, int X, int Y) {
		rootX = X - (nodeSize + nodeGap); // * deslocFactor;
		rootY = Y + (nodeSize + nodeGap);

		g.drawLine(X, Y + nodeSize, rootX + nodeSize, rootY);
	}

	public void drawLineRight(Graphics g, int X, int Y) {
		rootX = X + (nodeSize + nodeGap); // * deslocFactor;
		rootY = Y + (nodeSize + nodeGap);

		g.drawLine(X + nodeSize, Y + nodeSize, rootX, rootY);
	}

	public void pause() {
		pause(1500);
	}

	public void pause(int value) {
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void preOrderTraversalGUI(Tree tree) {
		if (tree != null) {
			highlightNode(tree.getRoot());
			pause();
			repaint();
			unhighlightNode(tree.getRoot());
			repaint();
			preOrderTraversalGUI(tree.getLeftTree());
			preOrderTraversalGUI(tree.getRightTree());
		}
	}

	public void inOrderTraversalGUI(Tree tree) {
		if (tree != null) {
			inOrderTraversalGUI(tree.getLeftTree());
			highlightNode(tree.getRoot());
			pause();
			repaint();
			unhighlightNode(tree.getRoot());
			repaint();
			inOrderTraversalGUI(tree.getRightTree());
		}
	}

	public void postOrderTraversalGUI(Tree tree) {
		if (tree != null) {
			postOrderTraversalGUI(tree.getLeftTree());
			postOrderTraversalGUI(tree.getRightTree());
			highlightNode(tree.getRoot());
			pause();
			repaint();
			unhighlightNode(tree.getRoot());
			repaint();
		}
	}
}