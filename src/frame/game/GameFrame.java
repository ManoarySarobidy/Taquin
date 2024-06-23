package frame.game;
import javax.swing.*;
import game.Taquin;

public class GameFrame extends JFrame{
	GamePanel panels;
	public GameFrame(){
		this.panels = new GamePanel();
		this.init();
	}

	public GameFrame(Taquin taquin){
		this.panels = new GamePanel(taquin);
		this.init();
	}

	public void init(){
		this.setSize(300 , 400);
		this.add(this.panels);
		// this.pack();
		// this.setLayout( new GridLayout(1,1) );
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}