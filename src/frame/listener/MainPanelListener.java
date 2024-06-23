package frame.listener;

import javax.swing.*;
import java.awt.event.*;
import game.Taquin;
import frame.game.*;
public class MainPanelListener implements MouseListener{

	MainPanels panels;

	public MainPanelListener(){

	}

	public MainPanelListener( MainPanels panel ){
		this.setPanels(panel);
	}

	public void mouseClicked( MouseEvent event ){
		if( event.getSource() instanceof JButton ){
			Taquin taquin = null;
			if( ((JButton)event.getSource()).getText().contains("random") ){
				taquin = new Taquin();
				taquin.shuffle();
				taquin.mains();
			}else{
				taquin = new Taquin();
			}
			new GameFrame(taquin);
		}
	}
	public void mousePressed( MouseEvent event ){

	}

	public void mouseEntered( MouseEvent event ){

	}

	public void mouseExited( MouseEvent event ){

	}

	public void mouseReleased( MouseEvent event ){


	}






	public void setPanels( MainPanels panel ){
		this.panels = panel;
	}
	public MainPanels getPanel(){
		return this.panels;
	}

}