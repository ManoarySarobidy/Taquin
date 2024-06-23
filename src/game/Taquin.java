package game;
import java.util.Random;
import java.util.Vector;
import utils.Points;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import frame.game.*;
import myException.*;
import javax.swing.Timer;
import java.awt.event.*;


public class Taquin{

	String[][] __solved__;
	int size = 3;
	Points[] directionss = { 
		new Points( 0 , -1 , "Up") , 
		new Points( 0 , 1 , "Down" ) , 
		new Points( -1 , 0 , "Left" ) ,
		new Points( 1 , 0 , "Right" ) 
	};
	public static String solved_state = "[[1, 2, 3], [4, 5, 6], [7, 8, *]]";
	Points departState;
	String[][] current ;
	Taquin parent;
	Vector<Taquin> childs;
	boolean visited = false;
	Points fromParrent;
	Taquin lastMoves;

	Vector<Points> moves = new Vector<Points>();


	public int getSize(){
		return this.size;
	}

	public void setMoves( Vector<Points> moves ){
		this.moves = moves;
	}
	public Vector<Points> getMoves(){
		return this.moves;
	}

	public Taquin(){
		this.setSolvedState();
		this.current = this.__solved__;
	}

	public Taquin( int size ){
		this.setSolvedState(size);
	}

	private void setSolvedState(){
		this.__solved__ = this.createSolvedMatrix();
		this.departState = new Points( 2 , 2 );
	}

	private void setSolvedState(int size){
		this.__solved__ = this.createSolvedMatrix( size );
		this.departState = new Points( size - 1 , size - 1 );
	}

	public String[][] createSolvedMatrix( ){
		String[][] s = new String[this.getSize()][this.getSize()];
		s[0][0] = "1" ; s[1][0] = "4" ; s[2][0] = "7" ;
		s[0][1] = "2" ; s[1][1] = "5" ; s[2][1] = "8" ; 
		s[0][2] = "3" ; s[1][2] = "6" ; s[2][2] = "*" ; 
		return s;
	}

	public String[][] createSolvedMatrix( int size ){
		String[][] s = new String[size][size];
		int value = 1;
		for( int i = 0 ; i < size ; i++ ){
			for( int j = 0 ; j < size ; j++ )
				s[i][j] = ( i == size - 1 && j == size - 1 ) ? "*" : String.valueOf(value);  
		}
		return s;
	}

	public void setCurrent( String[][] current ){
		this.current = current;
	}

	public String[][] getCurrent(){
		return this.current;
	}

	public void setDepart( Points p ){
		this.departState = p;
	}
	public Points getDepart(){
		return this.departState;
	}

	public void generateGraph(){
		this.childs = new Vector<>();
		for( Points p : this.directionss ){
			Taquin child = new Taquin();
			String[][] newMove = this.clones();
			child.setCurrent(newMove);
			child.parent = this;
			child.setDepart(this.getDepart());
			boolean isMoveable = child.moveTo(p);
			if( !isMoveable ){ continue; }
			child.change(p);
			this.childs.add(child);
		}

	}
	
	public String[][] clones(){
		String[][] o = new String[this.getSize()][this.getSize()];
		for( int i = 0 ; i < o.length ; i++ ){
			for( int j = 0 ; j < o[i].length ; j++ )
				o[i][j] = this.current[i][j];
		}
		return o;
	}

	public void shuffle(){
		String[][] shuffled = this.createSolvedMatrix();
		int moves = 50;
		int i = 1;
		this.current = shuffled;
		Points precedentMove = null;
		while( i <= moves ){
			Random r = new Random();
			int direction = r.nextInt(4);
			Points d = this.directionss[direction];
			boolean isMoveable = this.moveTo(d);
			while( !isMoveable || ( precedentMove != null && precedentMove.isTheOppositeOf(d) ) ){
				direction = r.nextInt(4);
				d = this.directionss[direction];
				isMoveable = this.moveTo(d);
			}
			this.change( d );
			precedentMove = d;
			i++;
		}
	}

	public void setChilds(){
		this.childs = new Vector<>();
	}

	public Vector<Taquin> getChilds(){
		return this.childs;
	}

	public boolean moveTo( Points direction ){
		Points n = this.departState.combine(direction);
		if(( n.getX() < 0 || n.getX() >= this.getSize() || n.getY() < 0 || n.getY() >= this.getSize() ))
			return false;
		return true;
	}

	public void change( Points direction ){
		Points n = this.departState.combine(direction);
		String v = this.current[ n.getY() ][ n.getX() ];
		this.current[ n.getY() ][ n.getX() ] = "*";
		this.current[ departState.getY() ][ departState.getX() ] = v;
		this.departState = n;
		this.fromParrent = n;
	}

	public Taquin solve(){
		Queue<Taquin> notVisited = new LinkedList<Taquin>();
		Set<String> visited = new HashSet<>();
		notVisited.offer(this);
		while( !notVisited.isEmpty() ){
			Taquin taquin = notVisited.poll();
			String t = Arrays.deepToString(taquin.getCurrent());
			
			if( t.equalsIgnoreCase(Taquin.solved_state) ) return taquin;
			
			if( visited.contains(t) ) continue;

			taquin.generateGraph();
			Vector<Taquin> childs = taquin.getChilds();
			visited.add(t);
			for( Taquin child : childs ){
				String representation = Arrays.deepToString(child.getCurrent());
				if( !visited.contains(representation) )
					notVisited.offer(child);
			}
		}
		return null;
	}

	public void mains(){
		Taquin solved = null;
		solved = this.solve();
		this.lastMoves = solved;
	}

	public void start() throws NoSolutionException{
		if( this.lastMoves == null )
			throw new NoSolutionException( "This puzzle doesn't have a solution");
		this.moves = this.lastMoves.displaySolution();
	}

	public Vector<Points> displaySolution(){
		Vector<Taquin> lists = new Vector<Taquin>();
		Vector<Points> pointss = new Vector<Points>();
		Taquin t = this;
		while( t != null ){
			lists.add(t);
			t = t.parent;
		}
		for( int i = lists.size() - 1 ; i >= 0 ; i-- )
			pointss.add(lists.get(i).fromParrent);
		return pointss;
	}

	public void swapTiles( GamePanel panel ){
		int delay = 500;
		ActionListener listener = new ActionListener(){
			int i = 0;
			public void actionPerformed( ActionEvent e ){
				if( i < moves.size() ){
					Points depart = getDepart();
					Points current = moves.get(i);
					String value = getCurrent()[ depart.getY() ][ depart.getX() ];
					String value_1 = getCurrent()[ current.getY() ][ current.getX() ];
					getCurrent()[ depart.getY() ][ depart.getX() ] = value_1; 
					getCurrent()[ current.getY() ][ current.getX() ] = value;
					setDepart(current);
					panel.repaint();
					i++;
				}else{
					javax.swing.JOptionPane.showMessageDialog( new javax.swing.JFrame() ,  "Solved with " + getMoves().size() + "moves" );
					((Timer) e.getSource()).stop();
				}
			}
		};
		Timer timer = new Timer(delay , listener);
		timer.start();
	}

	public void print(){
		for( String[] s : this.getCurrent() ){
			for( String ss : s )
				System.out.print( ss + " " );
			System.out.println();
		}
		System.out.println("=============>");
	}

	public String moveToString(){
		String solutions = "";
		for( int i = 1 ; i < this.getMoves().size() ; i++ ){
			solutions = solutions + this.getMoves().get(i).getName() + (( i != this.getMoves().size() - 1) ? " , " : " ");
		}
		return solutions;
	}

}