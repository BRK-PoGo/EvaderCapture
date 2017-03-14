package gameOld;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import javax.swing.JRadioButton;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Menu extends JPanel {
	private SubPanel activePanel = null;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public Menu() {
		
		addDesignerMenu();
	}

	public void addDesignerMenu() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{167, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblDesignMode = new JLabel("Design Mode:");
		GridBagConstraints gbc_lblDesignMode = new GridBagConstraints();
		gbc_lblDesignMode.insets = new Insets(0, 0, 5, 0);
		gbc_lblDesignMode.gridx = 0;
		gbc_lblDesignMode.gridy = 1;
		add(lblDesignMode, gbc_lblDesignMode);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Shape");
		buttonGroup.add(rdbtnNewRadioButton);
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton.gridx = 0;
		gbc_rdbtnNewRadioButton.gridy = 2;
		add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		buttonGroup.add(rdbtnNewRadioButton_1);
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_1.gridx = 0;
		gbc_rdbtnNewRadioButton_1.gridy = 3;
		add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
		buttonGroup.add(rdbtnNewRadioButton_2);
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_2.gridx = 0;
		gbc_rdbtnNewRadioButton_2.gridy = 4;
		add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() { //when pressed OK
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNewRadioButton.isSelected()){
					if(activePanel != null)
						activePanel.setInvisible();
					activePanel = new SubPanelShape();
					GridBagConstraints gbc_panel = new GridBagConstraints();
					gbc_panel.insets = new Insets(0, 0, 5, 0);
					gbc_panel.fill = GridBagConstraints.BOTH;
					gbc_panel.gridx = 0;
					gbc_panel.gridy = 6;
					add((Component) activePanel, gbc_panel);
					activePanel.setVisible();
				}
			}
		});
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.insets = new Insets(0, 0, 5, 0);
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 5;
		add(btnOk, gbc_btnOk);
		
		
		JButton btnNewButton = new JButton("Done");
		btnNewButton.addActionListener(new ActionListener() { //when pressed DONE
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}

}
