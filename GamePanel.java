package frame.game;

import game.Taquin;
import javax.swing.*;
import java.awt.*;
import frame.game.listener.*;
import myException.*;

public class GamePanel extends JPanel{
	Taquin taquin;
	JButton solve;
	JButton show;
	SolveListener listener;
	SolutionListener solution;

	public GamePanel(){
		this.init();
		this.setTaquin(new Taquin());
	}
	public GamePanel(Taquin taquin){
		this.init();
		this.setTaquin(taquin);
	}

	public void init(){
		this.listener = new SolveListener(this);
		this.solution = new SolutionListener(this);
		this.setLayout(null);
		this.setPreferredSize( new Dimension(300 , 300));
		this.setBackground( Color.WHITE );
		this.setSolve();
		this.setSolution();
		this.getSolve().addMouseListener(listener);
		this.getSolution().addMouseListener(solution);
		this.getSolve().setBounds( 50 , 325 , 100 , 25 );
		this.getSolution().setBounds( 175 , 325 , 100 , 25 );
		this.add(this.getSolve());
		this.add(this.getSolution());
		this.setDoubleBuffered(true);
	}

	public void paint(Graphics e){
		super.paint(e);
		String[][] states = this.getTaquin().getCurrent();
		int gap = ( int ) (this.getWidth() / states.length);
		for( int i = 0 ; i <= states.length ; i++ ){
			e.setColor( Color.BLACK );
			e.drawLine( i * gap, 0, i * gap, (int)this.getPreferredSize().getHeight());
			e.drawLine( 0, i * gap, this.getWidth(), i * gap);
			for( int j = 0 ; i < states.length && j < states[i].length ; j++ ){
				int x = (i * gap) + (int)(gap / 2);
				int y = (j * gap) + (int)(gap / 2);
				if( !states[j][i].equals("*") )
					e.drawString( states[j][i] , x , y );
				else{
					e.fillRect( i * gap , j * gap , gap , gap  );
				}
			}
		}
	}

	public void startSolving(){
		try{
			this.getTaquin().start();
			this.getTaquin().swapTiles( this );
			this.disableSolving();
		}catch(NoSolutionException e){
			JOptionPane.showMessageDialog( new JFrame() ,  e.getMessage() );
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void setTaquin( Taquin taquin ){
		this.taquin = taquin;
	}
	public Taquin getTaquin(){
		return this.taquin;
	}

	public void setSolve(){
		this.solve = new JButton("Solve");
	}
	public JButton getSolve(){
		return this.solve;
	}

	public void setSolution(){
		this.show = new JButton("Solution");
	}
	public JButton getSolution(){
		return this.show;
	}

	public void disableSolving(){
		this.getSolve().setEnabled(false);
	}

}