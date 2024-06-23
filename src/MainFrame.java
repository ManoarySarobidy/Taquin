package frame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
	// Inona no atao ato
	// Mila manana panels aloha izy
	MainPanels panels;

	public MainFrame(){
		this.setSize(300 , 400);
		this.panels = new MainPanels( 100 , 200 );
		this.setTitle("Taquin Solver");
		this.setVisible(true);
		// this.setLayout( new GridLayout( 1 , 1) );
		this.add(panels);
		// this.add(panels);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}