package utils;

public class Points{
	int x , y;
	String name;
	public Points(){}
	public Points( int x, int y ){
		this.setX(x);
		this.setY(y);
	}
	public Points( int x, int y , String name){
		this.setX(x);
		this.setY(y);
		// System.out.println(name);
		this.setName( name );
	}

	public Points combine(Points a){
		Points p = new Points( this.getX() + (a.getX()) , this.getY() + (a.getY()) , a.getName() );
		return p;
	}

	public boolean isTheOppositeOf( Points a ){
		Points b = this.combine(a);
		if( b.getX() == 0 && b.getY() == 0 )
			return true;
		return false;
	}

	public int getX(){
		return this.x;
	}
	public void setX(int x){
		this.x = x;
	}
	public int getY(){
		return this.y;
	}
	public void setY( int y ){
		this.y = y;
	}

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}

	public String toString(){
		return "[ [ " + this.getX() + " , " + this.getY() + " ] , " + this.getName() + "]";
	}
}