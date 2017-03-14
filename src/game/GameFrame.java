package game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Color;

public class GameFrame extends JFrame {
	
	
	private JPanel contentPane;
	private GamePanel gamePanel;
	
	private JPanel AIMenu;
	private JPanel menuDesigner;
	/**
	 * Create the frame.
	 */
	public GameFrame() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);// till here is automatically generated from the plug in.
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.WEST);
		
		JPanel menuDesigner = makeMenuDesigner();
		splitPane.setLeftComponent(menuDesigner);
		
		JPanel AIMenu = makeAIMenu();
		splitPane.setRightComponent(AIMenu);
		AIMenu.setVisible(false);//start with menuDesigner on
		
		gamePanel = new GamePanel();
		contentPane.add(gamePanel, BorderLayout.CENTER);
		}
	public JPanel makeAIMenu() {
		AIMenu = new JPanel();
		return AIMenu;
		
	}

	public JPanel makeMenuDesigner() {
		menuDesigner = new JPanel();
		
		JLabel lblNewLabel = new JLabel("Designer Menu:");
		
		JLabel lblSelectDesigner = new JLabel("Select Designer:");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_menuDesigner = new GroupLayout(menuDesigner);
		gl_menuDesigner.setHorizontalGroup(
			gl_menuDesigner.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuDesigner.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_menuDesigner.createParallelGroup(Alignment.LEADING)
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(lblSelectDesigner, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_menuDesigner.setVerticalGroup(
			gl_menuDesigner.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuDesigner.createSequentialGroup()
					.addGap(60)
					.addComponent(lblNewLabel)
					.addGap(48)
					.addComponent(lblSelectDesigner)
					.addGap(18)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(135, Short.MAX_VALUE))
		);
		
		JPanel drawPanel = new JPanel();
		tabbedPane.addTab("Draw", null, drawPanel, null);
		
		JLabel actionLabel = new JLabel("Select Action");
		actionLabel.setForeground(Color.BLUE);
		
		JButton btnDrawWall = new JButton("Wall");
		btnDrawWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 actionLabel.setText("Placing Wall");	
			 gamePanel.setEdit("wall");

			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 actionLabel.setText("Deleting");
				 gamePanel.setEdit("delete");

			}
		});
		
		JButton btnPursu = new JButton(" pursuiter");
		btnPursu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 actionLabel.setText("Placing Pursuiter");
				 gamePanel.setEdit("pursuiter");
			}
		});
		
		JButton btnEnd = new JButton("evader");
		btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 actionLabel.setText("Placing Evader");
				 gamePanel.setEdit("evader");

			}
		});
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Map Dimention");
		
		JTextField widthField = new JTextField();
		widthField.setText("30");
		widthField.setColumns(10);
		
		JTextField heigthField = new JTextField();
		heigthField.setText("30");
		heigthField.setColumns(10);
		
		JLabel lblNodeSize = new JLabel("Node Size");
		
		JTextField NodeSizeField = new JTextField();
		NodeSizeField.setText("20");
		NodeSizeField.setColumns(10);
		
		JButton btnNewMap = new JButton("New Map");
		btnNewMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.setGrid(new Grid(Integer.parseInt(heigthField.getText()), Integer.parseInt(widthField.getText()), Integer.parseInt(NodeSizeField.getText())));
			}
		});
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gamePanel.grid!=null){
					gamePanel.setEdit("");
					menuDesigner.setVisible(false);
					AIMenu.setVisible(true);
				}
			}
		});
		
		
		
		GroupLayout gl_drawPanel = new GroupLayout(drawPanel);
		gl_drawPanel.setHorizontalGroup(
			gl_drawPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnDone, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewMap)
					.addContainerGap(22, Short.MAX_VALUE))
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addGap(40)
					.addComponent(NodeSizeField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(52, Short.MAX_VALUE))
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addGap(21)
					.addComponent(heigthField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(widthField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addComponent(lblNodeSize, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addContainerGap(22, Short.MAX_VALUE))
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addGap(20)
					.addComponent(btnDelete)
					.addContainerGap(30, Short.MAX_VALUE))
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnEnd, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
					.addGap(20))
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnPursu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(20))
				.addGroup(Alignment.LEADING, gl_drawPanel.createSequentialGroup()
					.addGap(23)
					.addComponent(btnDrawWall)
					.addContainerGap(56, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_drawPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(actionLabel)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		gl_drawPanel.setVerticalGroup(
			gl_drawPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addGap(10)
					.addComponent(actionLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDrawWall)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnPursu)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnEnd)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDelete)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_drawPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(widthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(heigthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNodeSize)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(NodeSizeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewMap)
					.addGap(32)
					.addComponent(btnDone)
					.addContainerGap(63, Short.MAX_VALUE))
		);
		drawPanel.setLayout(gl_drawPanel);
		
		JPanel guiBild = new JPanel();
		tabbedPane.addTab("Builder", null, guiBild, null);
		
		JButton btnWall = new JButton("Add Wall");
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnStapBack = new JButton("Stap Back");
		GroupLayout gl_guiBild = new GroupLayout(guiBild);
		gl_guiBild.setHorizontalGroup(
			gl_guiBild.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_guiBild.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_guiBild.createParallelGroup(Alignment.LEADING)
						.addComponent(btnWall)
						.addComponent(btnStapBack))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_guiBild.setVerticalGroup(
			gl_guiBild.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_guiBild.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnWall)
					.addGap(78)
					.addComponent(btnStapBack)
					.addContainerGap(277, Short.MAX_VALUE))
		);
		guiBild.setLayout(gl_guiBild);
		menuDesigner.setLayout(gl_menuDesigner);
		
		
		
		
		
		//--------------
		return menuDesigner;
	}
}
