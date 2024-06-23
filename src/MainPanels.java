package frame;
import javax.swing.*;
import java.awt.*;
import frame.listener.*;

public class MainPanels extends JPanel{
	// Manana boutton 3 ny main Panel
	// De manana combobox iray

	JComboBox matrix;
	JButton random;
	JButton specific;
	JButton solved;

	JLabel title;
	MainPanelListener listener;
	// De inona ihany koa ny manaraka

	public MainPanels(){
		this.init();
		this.setBackground(Color.BLACK);
		JOptionPane.showMessageDialog(new JFrame() , this.getWidth());
	}

	public MainPanels( int width , int height ){
		this.init();
		this.setBackground(Color.BLACK);
		this.setSize( width , height );
		this.setLayout(null);

		this.setElement();
	}

	public void paint(Graphics e){
		super.paint(e);
		int middleX = this.getWidth() / 3;
		int middleY = this.getHeight() / 3;
		int x = middleX - 50;
		int button = 150;
		this.getTitle().setBounds( 75 , 25 , 100 , 25 );
		this.getRandom().setForeground(Color.WHITE);
		this.getRandom().setBackground(Color.BLACK);
		this.getSpecific().setForeground(Color.WHITE);
		this.getSpecific().setBackground(Color.BLACK);
		this.getSolved().setForeground(Color.WHITE);
		this.getSolved().setBackground(Color.BLACK);
		this.getRandom().setFocusable(false);
		this.getSpecific().setFocusable(false);
		this.getSolved().setFocusable(false);
		this.getRandom().setBounds( x , middleY , button , button / 3 );
		this.getSpecific().setBounds( x , middleY + (55 *1) , button , button / 3 );
		this.getSolved().setBounds( x , middleY +  (55 * 2), button , button / 3 );
	}

	public void init(){
		this.listener = new MainPanelListener(this);
		this.setRandom();
		this.setSpecific();
		this.setSolved();
		this.setTitle();
		this.setDoubleBuffered(true);
	}

	public void setElement(){
		this.getRandom().addMouseListener(listener);
		this.getSpecific().addMouseListener(listener);
		this.getSolved().addMouseListener(listener);
		this.add( this.getTitle() );
		this.add(this.getRandom());
		this.add(this.getSpecific());
		this.add(this.getSolved());
	}


	public void setRandom(){
		this.random = new JButton( "random state" );
	}
	public JButton getRandom(){
		return this.random;
	}
	public void setSpecific(){
		this.specific = new JButton( "specific state" );
	}
	public JButton getSpecific(){
		return this.specific;
	}
	public void setSolved(){
		this.solved = new JButton( "solved state" );
	}
	public JButton getSolved(){
		return this.solved;
	}

	public void setTitle(){
		this.title = new JLabel( "Taquin Solver" );
		this.title.setFont( new Font("Arial" , Font.ITALIC , 16) );
		this.title.setForeground(Color.WHITE);
	}
	public JLabel getTitle(){
		return this.title;
	}
}