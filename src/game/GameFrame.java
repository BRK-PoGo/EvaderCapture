package game;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JSlider;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.FocusTraversalPolicy;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	
	private JPanel contentPane;
	private GamePanel gamePanel;
	
	private JPanel AIMenu;
	private JPanel menuDesigner;
	private JTextField textField;
	private JTextField textField_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_2;
	private JTextField textField_3;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final Action action = new SwingAction();
	private JPanel speedControl = new JPanel();
	protected MainGameLoop gameLoop;

	
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
		
		makeAIMenu(splitPane);
		AIMenu.setVisible(false);
		
		gamePanel = new GamePanel();
		contentPane.add(gamePanel, BorderLayout.CENTER);
		
		speedControl.setVisible(false);
		contentPane.add(speedControl, BorderLayout.NORTH);
		
		JLabel lblGameSpeed = new JLabel("Game Speed");
		JSlider slider = new JSlider();
		slider.setMaximum(300);
		slider.setValue(100);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		
		JLabel lblX = new JLabel("x1");
		
		JLabel lblX_1 = new JLabel("x0");
		
		JLabel lblX_2 = new JLabel("x3");
		
		JLabel lblX_3 = new JLabel("x2");
		
		JButton btnNewButton = new JButton("x1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnX = new JButton("x10");
		
		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GroupLayout gl_speedControl = new GroupLayout(speedControl);
		gl_speedControl.setHorizontalGroup(
			gl_speedControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_speedControl.createSequentialGroup()
					.addGap(49)
					.addComponent(btnPause, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addGap(54)
					.addComponent(lblGameSpeed, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_speedControl.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_speedControl.createSequentialGroup()
							.addComponent(lblX_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addGap(44)
							.addComponent(lblX)
							.addGap(51)
							.addComponent(lblX_3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblX_2))
						.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnX, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(164, Short.MAX_VALUE))
		);
		gl_speedControl.setVerticalGroup(
			gl_speedControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_speedControl.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_speedControl.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_speedControl.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton)
							.addComponent(btnX))
						.addGroup(gl_speedControl.createParallelGroup(Alignment.LEADING)
							.addComponent(lblGameSpeed, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_speedControl.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnPause)
								.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_speedControl.createParallelGroup(Alignment.LEADING)
						.addComponent(lblX_2)
						.addComponent(lblX_1)
						.addComponent(lblX)
						.addComponent(lblX_3))
					.addContainerGap())
		);
		speedControl.setLayout(gl_speedControl);
		speedControl.setFocusTraversalPolicy(new FocusTraversalPolicy(new Component[]{lblGameSpeed, lblX_1, lblX, lblX_3, lblX_2, slider, btnNewButton, btnX}));
		}
	private void makeAIMenu(JSplitPane splitPane) {
		AIMenu = new JPanel();
		
		splitPane.setRightComponent(AIMenu);
		
		JLabel lblAiSettings = new JLabel("AI Settings:");
		
		JTabbedPane AIsettingPanel = new JTabbedPane(JTabbedPane.TOP);
		JRadioButton rdbtnRandomEvad = new JRadioButton("Random");
		buttonGroup_1.add(rdbtnRandomEvad);
		rdbtnRandomEvad.setSelected(true);
		JRadioButton rdbtnComandEvad = new JRadioButton("Comand");
		buttonGroup_1.add(rdbtnComandEvad);
		JRadioButton randomPurs = new JRadioButton("Random");
		randomPurs.setSelected(true);
		JRadioButton comandPurs = new JRadioButton("Comand");



		JButton button = new JButton("Done");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//here Start Game LOOP.
				for(Entity ent:gamePanel.graph.getEntities()){
					if(ent instanceof Evader){
						ent.setSpeed(Integer.parseInt(textField.getText()));
						ent.setViewAngle(Integer.parseInt(textField_1.getText()));
						if(rdbtnRandomEvad.isSelected())///set algorithm
							ent.setAlgorithm(new Random());
						else if(rdbtnComandEvad.isSelected()){}
							//add command
						
					}else if(ent instanceof Pursuer){
						ent.setSpeed(Integer.parseInt(textField_3.getText()));
						ent.setViewAngle(Integer.parseInt(textField_2.getText()));
						if(randomPurs.isSelected())///set algorithm
							ent.setAlgorithm(new Random());
						else if(comandPurs.isSelected()){}
							//add command
					}
				}
				AIMenu.setVisible(false);
				speedControl.setVisible(true);
				gameLoop = new MainGameLoop(gamePanel);
				gameLoop.gameLoop();
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AIMenu.setVisible(false);
				menuDesigner.setVisible(true);
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(AIMenu);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(32)
							.addComponent(lblAiSettings, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(AIsettingPanel, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(24)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(42)
							.addComponent(btnBack)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(58)
					.addComponent(lblAiSettings)
					.addGap(43)
					.addComponent(button)
					.addGap(18)
					.addComponent(AIsettingPanel, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
					.addComponent(btnBack)
					.addGap(45))
		);
		
		JPanel Evader = new JPanel();
		AIsettingPanel.addTab("Evader", null, Evader, null);
		
		JLabel lblSpeed = new JLabel("Speed");
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.TRAILING);
		textField.setText("100");
		textField.setColumns(10);
		
		JLabel lblViewAngle = new JLabel("View Angle");
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText("360");
		textField_1.setHorizontalAlignment(SwingConstants.TRAILING);
		textField_1.setColumns(10);
		
		JLabel lblMovingAlgorithm = new JLabel("Moving Algorithm");
		
		JLabel lblComandOnlySingle = new JLabel("Comand only when");
		
		JLabel lblSingleEvader = new JLabel("single evader");
		
		JLabel lblComandsOnWasd = new JLabel("Comands on WASD");
		GroupLayout gl_Evader = new GroupLayout(Evader);
		gl_Evader.setHorizontalGroup(
			gl_Evader.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Evader.createSequentialGroup()
					.addGroup(gl_Evader.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Evader.createSequentialGroup()
							.addGap(32)
							.addGroup(gl_Evader.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSpeed)))
						.addGroup(gl_Evader.createSequentialGroup()
							.addGap(24)
							.addGroup(gl_Evader.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_Evader.createSequentialGroup()
									.addGap(10)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblViewAngle))))
					.addContainerGap(31, Short.MAX_VALUE))
				.addGroup(gl_Evader.createSequentialGroup()
					.addContainerGap(19, Short.MAX_VALUE)
					.addComponent(lblMovingAlgorithm)
					.addContainerGap())
				.addGroup(gl_Evader.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnRandomEvad)
					.addContainerGap(36, Short.MAX_VALUE))
				.addGroup(gl_Evader.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnComandEvad, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_Evader.createSequentialGroup()
					.addComponent(lblComandOnlySingle)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_Evader.createSequentialGroup()
					.addComponent(lblComandsOnWasd, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_Evader.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSingleEvader)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_Evader.setVerticalGroup(
			gl_Evader.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Evader.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSpeed)
					.addGap(12)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblViewAngle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(lblMovingAlgorithm)
					.addGap(18)
					.addComponent(rdbtnRandomEvad)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnComandEvad)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblComandOnlySingle)
					.addGap(1)
					.addComponent(lblSingleEvader)
					.addGap(18)
					.addComponent(lblComandsOnWasd, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		Evader.setLayout(gl_Evader);
		
		JPanel pursuerPanel = new JPanel();
		AIsettingPanel.addTab("Pursuer", null, pursuerPanel, null);
		
		textField_2 = new JTextField();
		textField_2.setText("360");
		textField_2.setHorizontalAlignment(SwingConstants.TRAILING);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		
		JLabel label = new JLabel("Moving Algorithm");
		
		buttonGroup.add(randomPurs);
		
		buttonGroup.add(comandPurs);
		
		JLabel label_1 = new JLabel("Comand only when");
		
		JLabel lblSinglePursuer = new JLabel("single pursuer");
		
		textField_3 = new JTextField();
		textField_3.setText("100");
		textField_3.setHorizontalAlignment(SwingConstants.TRAILING);
		textField_3.setColumns(10);
		
		JLabel label_3 = new JLabel("Speed");
		
		JLabel label_4 = new JLabel("View Angle");
		
		JLabel lblComandsOnArrow = new JLabel("Comands on arrows");
		GroupLayout gl_pursuerPanel = new GroupLayout(pursuerPanel);
		gl_pursuerPanel.setHorizontalGroup(
			gl_pursuerPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pursuerPanel.createSequentialGroup()
					.addGroup(gl_pursuerPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pursuerPanel.createSequentialGroup()
							.addGap(32)
							.addGroup(gl_pursuerPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_3)))
						.addGroup(gl_pursuerPanel.createSequentialGroup()
							.addGap(24)
							.addGroup(gl_pursuerPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pursuerPanel.createSequentialGroup()
									.addGap(10)
									.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
								.addComponent(label_4))))
					.addContainerGap(26, Short.MAX_VALUE))
				.addGroup(gl_pursuerPanel.createSequentialGroup()
					.addContainerGap(19, Short.MAX_VALUE)
					.addComponent(label)
					.addContainerGap())
				.addGroup(gl_pursuerPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(randomPurs)
					.addContainerGap(31, Short.MAX_VALUE))
				.addGroup(gl_pursuerPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(comandPurs, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_pursuerPanel.createSequentialGroup()
					.addComponent(label_1)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_pursuerPanel.createSequentialGroup()
					.addComponent(lblComandsOnArrow)
					.addContainerGap(13, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_pursuerPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSinglePursuer)
					.addContainerGap(29, Short.MAX_VALUE))
		);
		gl_pursuerPanel.setVerticalGroup(
			gl_pursuerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pursuerPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_3)
					.addGap(12)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(label)
					.addGap(18)
					.addComponent(randomPurs)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comandPurs)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_1)
					.addGap(1)
					.addComponent(lblSinglePursuer)
					.addGap(18)
					.addComponent(lblComandsOnArrow, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		pursuerPanel.setLayout(gl_pursuerPanel);
		AIMenu.setLayout(gl_panel);
	}
	public JPanel makeMenuDesigner() {
		menuDesigner = new JPanel();
		
		JLabel lblNewLabel = new JLabel("Designer Menu:");
		
		JLabel lblSelectDesigner = new JLabel("Select Designer:");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gamePanel.graph!=null){
					gamePanel.setEdit("");
					menuDesigner.setVisible(false);
					AIMenu.setVisible(true);
				}
			}
		});
		GroupLayout gl_menuDesigner = new GroupLayout(menuDesigner);
		gl_menuDesigner.setHorizontalGroup(
			gl_menuDesigner.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuDesigner.createSequentialGroup()
					.addGroup(gl_menuDesigner.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_menuDesigner.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_menuDesigner.createParallelGroup(Alignment.LEADING)
								.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel)
								.addComponent(lblSelectDesigner, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
						.addGroup(gl_menuDesigner.createSequentialGroup()
							.addGap(27)
							.addComponent(btnDone, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
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
					.addGap(45)
					.addComponent(btnDone)
					.addContainerGap(55, Short.MAX_VALUE))
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
		
		JButton btnPerimeter = new JButton("Perimeter");
		btnPerimeter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(gamePanel.graph != null){
					for(int i=0; i<gamePanel.graph.getNodeGrid().length;i++){//go trough every row
						//change 1st and last element in wall
						if(gamePanel.graph.getNodeGrid()[i][0].getValue().equals(""))
							gamePanel.graph.addWall(i, 0);
						if(gamePanel.graph.getNodeGrid()[i][gamePanel.graph.getNodeGrid()[i].length-1].getValue().equals(""))
							gamePanel.graph.addWall(i, gamePanel.graph.getNodeGrid()[i].length-1);			
					}for(int i=0; i<gamePanel.graph.getNodeGrid()[0].length;i++){//go trough every column
						//change 1st and last element in wall
						if(gamePanel.graph.getNodeGrid()[0][i].getValue().equals(""))
							gamePanel.graph.addWall(0, i);
						if(gamePanel.graph.getNodeGrid()[gamePanel.graph.getNodeGrid().length-1][i].getValue().equals(""))  //fix here, perimeter BUG TODO
							gamePanel.graph.addWall(gamePanel.graph.getNodeGrid()[i].length-1, i);			
					}
					repaint();
				}
			}
		});
		
		JButton btnNewMap = new JButton("New Map");
		btnNewMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.setGraph(new Graph(Integer.parseInt(widthField.getText()),Integer.parseInt(heigthField.getText()), Integer.parseInt(NodeSizeField.getText())));
				gamePanel.repaint();
			}
		});
		
		
		
		GroupLayout gl_drawPanel = new GroupLayout(drawPanel);
		gl_drawPanel.setHorizontalGroup(
			gl_drawPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addGap(39)
					.addComponent(NodeSizeField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(53, Short.MAX_VALUE))
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addGroup(gl_drawPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_drawPanel.createSequentialGroup()
							.addComponent(heigthField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(widthField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNodeSize, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
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
					.addComponent(btnPursu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(20))
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnEnd, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
					.addGap(20))
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnPerimeter)
					.addContainerGap(18, Short.MAX_VALUE))
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addComponent(actionLabel)
					.addContainerGap(45, Short.MAX_VALUE))
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addGap(26)
					.addComponent(btnDrawWall)
					.addContainerGap(36, Short.MAX_VALUE))
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewMap)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_drawPanel.setVerticalGroup(
			gl_drawPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addGap(10)
					.addComponent(actionLabel)
					.addGap(18)
					.addComponent(btnDrawWall)
					.addGap(2)
					.addComponent(btnPerimeter)
					.addGap(15)
					.addComponent(btnPursu)
					.addGap(1)
					.addComponent(btnEnd)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDelete)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_drawPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(heigthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(widthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNodeSize)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(NodeSizeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewMap)
					.addGap(74))
		);
		drawPanel.setLayout(gl_drawPanel);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Polygons", null, panel, null);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 119, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 418, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		menuDesigner.setLayout(gl_menuDesigner);

		return menuDesigner;
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}