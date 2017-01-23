


public class Vertex extends Vector {
	float x,y,z;
public Vertex(float x, float y,float z){
	super(x,y,z);
	this.x = x;
	this.y = y;
	this.z = z;
}
public Vertex(float x, float y){
	super(x,y,0);
	this.x = x;
	this.y = y;
	
}


public void setX(float a){
	x = a;
}
public void setY(float a){
	y=a;
}
public void setZ(float a){
	z =a;
}



}
