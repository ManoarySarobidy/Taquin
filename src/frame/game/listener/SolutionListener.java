package frame.game.listener;

import java.awt.event.*;
import javax.swing.*;

import frame.game.*;

public class SolutionListener implements MouseListener{
	GamePanel panels;

	public SolutionListener( GamePanel panel ){
		this.setPanel( panel );
	}

	public void setPanel(GamePanel p){
		this.panels = p;
	}
	public GamePanel getPanel(){
		return this.panels;
	}

	public void mouseExited(MouseEvent event){

	}
	public void mouseEntered(MouseEvent event){
		
	}
	public void mouseClicked(MouseEvent event){
		String solution = this.getPanel().getTaquin().moveToString();
		JOptionPane.showMessageDialog( new JFrame() , solution );
	}
	public void mouseReleased(MouseEvent event){
		
	}
	public void mousePressed(MouseEvent event){
		
	}
}