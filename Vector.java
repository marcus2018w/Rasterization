



public class Vector {
	public float x,y,z,w;
	public float []color;
	public float mag;
	public  float[] normal;
	public  Vector( float x, float y, float z){
		color =new float[3];
		normal = new float[3];
		this.x =x;
		this.y = y;
		this.z=z;
		mag = (float) Math.sqrt(x*x+y*y+z*z);
	}
	public  Vector( float x, float y, float z,float w){
		this.x =x;
		this.w =w;
		this.y = y;
		this.z=z;
		mag = (float) Math.sqrt(x*x+y*y+z*z+w*w);
	}
	public Vector( Vector v){
		mag = (float) Math.sqrt(x*x+y*y+z*z);
		x= v.x;
		y = v.y;
		z= v.z;
	}
	public Vector add(Vector v){
		return new Vector( x+v.x,y+v.y,z+v.z);
	}
	public Vector scale(float d){
		return new Vector( d*x,d*y,d*z);
	}
	
	public Vector sub(Vector v){
		return new Vector( x-v.x, y-v.y,z-v.z);
	}
	public float dot( Vector v){
		return ((x*v.x)+(y*v.y)+(z*v.z));
	}
	
	public void normalize(){
		   
		   x/=mag;
		   y/=mag;
		   z/=mag;
	   }
	public float getMag(){
		return mag;
	}
	public Vector cross(Vector v){
		return new Vector(v.z*this.y -this.z*v.y, this.z*v.x-this.x*v.z,this.x*v.y-v.x*this.y);
	}
public void set(float [] color){
	this.color = color;
}
public float[] getColor(){
	return color;
}
public float[] getNormal(){
	return normal;
}
public void setNormal(float[] normal){
	this.normal = normal;
}
}
