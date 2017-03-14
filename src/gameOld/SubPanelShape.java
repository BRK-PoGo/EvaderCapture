package gameOld;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JList;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class SubPanelShape extends JPanel implements SubPanel {
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public SubPanelShape() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblWall = new JLabel("wall");
		add(lblWall, "2, 2");
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("");
		buttonGroup.add(rdbtnNewRadioButton);
		add(rdbtnNewRadioButton, "4, 2");
		
		JLabel lblNewLabel = new JLabel("Evader");
		add(lblNewLabel, "2, 4");
		
		JRadioButton radioButton = new JRadioButton("");
		buttonGroup.add(radioButton);
		add(radioButton, "4, 4");
		
		JLabel lblNewLabel_1 = new JLabel("Pursuer");
		add(lblNewLabel_1, "2, 6");
		
		JRadioButton radioButton_1 = new JRadioButton("");
		buttonGroup.add(radioButton_1);
		add(radioButton_1, "4, 6");
		
		JButton btnPlaceNew = new JButton("Place New");
		add(btnPlaceNew, "2, 10");
		
		JButton btnDeleteElement = new JButton("Delete");
		btnDeleteElement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		add(btnDeleteElement, "2, 12");

	}

	public void setVisible() {
		this.setVisible(true);
	}


	public void setInvisible() {
		this.setVisible(false);
	}

}
