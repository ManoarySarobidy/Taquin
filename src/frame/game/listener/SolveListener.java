package frame.game.listener;

import java.awt.event.*;
import javax.swing.*;
import frame.game.*;

public class SolveListener implements MouseListener{
	GamePanel panel;

	public SolveListener(){

	}

	public SolveListener(GamePanel game){
		this.setPanel(game);
	}

	public void mouseExited(MouseEvent args){

	}
	public void mouseClicked(MouseEvent args){
		if( this.getPanel().getSolve().isEnabled() ){
			this.getPanel().startSolving();
		}else{
			JOptionPane.showMessageDialog( new JFrame() , "Ce puzzle est déja résolu" );
		}
	}
	public void mouseEntered(MouseEvent args){

	}
	public void mousePressed(MouseEvent args){

	}
	public void mouseReleased(MouseEvent args){

	}

	public void setPanel( GamePanel panels ){
		this.panel = panels;
	}
	public GamePanel getPanel(){
		return this.panel;
	}
}