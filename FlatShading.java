import java.nio.Buffer;
import java.nio.FloatBuffer;

import javax.swing.JFrame;
import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;





public class Part2 extends JFrame implements GLEventListener{
	private static final long serialVersionUID = 1L;
	final static String name = "[name]";
    
	static float width = 32;
	static float height = 16;
	static float gNumVertices = (height - 2) * width + 2;
	static float gNumTriangles = (height - 2) * (width - 1) * 2;
	float[] gIndexBuffer = new float[3 * (int) (gNumTriangles)];
	Vertex[] vertexArray = new Vertex[3 * (int) gNumVertices];
	static float Carray[][]=new float[(int) gNumTriangles][3] ;

	// Vertex indices for the triangles.

	final int w = 512;
	final int h = 512;
	float [][] zbuffer = new float [w][h];
	float[] data =  new float[(int) (w * h * 3)];
	Buffer scene;

	public Part2() {
		super(name + "'s Raster");
		this.scene = renderScene();

		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities caps = new GLCapabilities(profile);

		GLCanvas canvas = new GLCanvas(caps);
		canvas.addGLEventListener(this);

		this.setName(name + "'s Raytracer");
		this.getContentPane().add(canvas);

		this.setSize(w, h);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		canvas.requestFocusInWindow();
	}

	public static void main(String[] args) {
		Part2 game = new Part2();

	}
	public void trans(int i){
		float l = -.1F;
		float n = -.1F;
		float r = .1F;
		float b = -.1F;
		float t = .1F;
		float f = -1000;
		float nx = 512;
		float ny = 512;
		int k0 = (int) gIndexBuffer[3 * i + 0];
		int k1 = (int) gIndexBuffer[3 * i + 1];
		int k2 = (int) gIndexBuffer[3 * i + 2];
		Matrix v1 = new Matrix(4,1);
		v1.insert(vertexArray[k0].x, 0, 0);
		v1.insert(vertexArray[k0].y, 1, 0);
		v1.insert(vertexArray[k0].z, 2, 0);
		v1.insert(1, 3, 0);
		Matrix v2 = new Matrix(4,1);
		v2.insert(vertexArray[k1].x, 0, 0);
		v2.insert(vertexArray[k1].y, 1, 0);
		v2.insert(vertexArray[k1].z, 2, 0);
		v2.insert(1, 3, 0);
		 Matrix v3 = new Matrix(4,1);
	        v3.insert(vertexArray[k2].x, 0, 0);
			v3.insert(vertexArray[k2].y, 1, 0);
			v3.insert(vertexArray[k2].z, 2, 0);
			v3.insert(1, 3, 0);
		Matrix real = new Matrix(4);
		real.insert(2, 0, 0);
		real.insert(0, 0, 1);
		real.insert(0, 0, 2);
		real.insert(0, 0, 3);
		real.insert(0, 1, 0);
		real.insert(2, 1, 1);
		real.insert(0, 1, 2);
		real.insert(0, 1, 3);
		real.insert(0, 2, 0);
		real.insert(0, 2, 1);
		real.insert(2, 2, 2);
		real.insert(-7, 2, 3);
		real.insert(0, 3, 0);
		real.insert(0, 3, 1);
		real.insert(0, 3, 2);
		real.insert(1, 3, 3);
	

	
	}
	public void rasterize(int i, float x1,float x2,float x3,float y1,float y2,float y3,float z1,float z2,float z3){
		float maxX = (float) Math.max(x2, Math.max(x3, x1));
		float minX = (float) Math.min(x2, Math.min(x3, x1));
		float maxY = (float) Math.max(y2, Math.max(y3, y1));
		float minY = (float) Math.min(y2, Math.min(y3, y1));
		int x =0; int y =0;
		
		
	
		
		
		for ( x = (int) minX; x <  maxX; x++) {
			for ( y = (int) minY; y < maxY; y++) {
		int j = (y*h ) + x;
		j *= 3;
		
		float gamma = ((y1 - y2) * x + (x2 - x1) * y + x1 * y2 - x2
				* y1)
				/ ((y1 - y2) * x3 + (x2 - x1) * y3 + x1 * y2 - x2
						*y1);
		float beta = ((y1 - y3) * x + (x3 - x1) * y + x1 * y3 - x3
				* y1)
				/ ((y1 - y3) * x2 + (x3 - x1) * y2 + x1 * y3 - x3
						*y1);
		float alpha = 1 - beta - gamma;
	
		if (gamma >= 0 && gamma <= 1 && beta >= 0 && beta <= 1
				&& alpha >= 0 && alpha <= 1 ) {
			float realz=z1+(y-y1)*((x2-x1)*(z3-z1)-(x3-x1)*(z2-z1))/((x2-x1)*(y3-y1)-(x3-x1)*(y2-y1))-(x-x1)*((y2-y1)*(z3-z1)-(y3-y1)*(z2-z1))/((x2-x1)*(y3-y1)-(x3-x1)*(y2-y1));
			
			if(zbuffer[x][y]<realz){
				zbuffer[x][y] =realz;
				
			data[j + 0] = (float) Math.pow(Carray[i][0],1/2.2); // red
			data[j + 1] = (float) Math.pow(Carray[i][1],1/2.2); // green
			data[j + 2] = (float) Math.pow(Carray[i][2],1/2.2); // blue
			}
		}

			}
		}


	}

	public Buffer renderScene() {
		for( int i =0; i<w;i++){
			for(int j=0;j<h;j++){
				zbuffer[i][j]=Float.NEGATIVE_INFINITY;
				
			}
		}
		

		for (int i = 0; i < gNumTriangles; i++) {
			vertex();
			transform(i);
			
			
			
		}

		return FloatBuffer.wrap(data);

	}
 
	@Override
	public void display(GLAutoDrawable drawable) {

		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		gl.glDrawPixels(w, h, GL2.GL_RGB, GL2.GL_FLOAT, this.scene);

	}

	@Override
	public void dispose(GLAutoDrawable arg0) {

	}

	@Override
	public void init(GLAutoDrawable arg0) {

	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {

	}

	public void transform(int i) {
		float l = -.1F;
		float n = -.1F;
		float r = .1F;
		float b = -.1F;
		float t = .1F;
		float f = -1000;
		float nx = 512;
		float ny = 512;
		
		Matrix ortho = new Matrix(4);
		ortho.insert(2f / (r - l), 0, 0);
		ortho.insert(0, 0, 1);
		ortho.insert(0, 0, 2);
		ortho.insert((-(r + l)) / (r - l), 0, 3);
		ortho.insert(0, 1, 0);
		ortho.insert(2f / (t - b), 1, 1);
		ortho.insert(0, 1, 2);
		ortho.insert(-(t + b) / (t - b), 1, 3);
		ortho.insert(0, 2, 0);
		ortho.insert(0, 2, 1);
		ortho.insert(2 / (n - f), 2, 2);
		ortho.insert(-(n + f) / (n - f), 2, 3);
		ortho.insert(0, 3, 0);
		ortho.insert(0, 3, 1);
		ortho.insert(0, 3, 2);
		ortho.insert(1, 3, 3);

		Matrix vp = new Matrix(4);
		vp.insert(nx / 2F, 0, 0);
		vp.insert(0, 0, 1);
		vp.insert(0, 0, 2);
		vp.insert((nx - 1) * .5F, 0, 3);
		vp.insert(0, 1, 0);
		vp.insert(ny / 2F, 1, 1);
		vp.insert(0, 1, 2);
		vp.insert((ny - 1) * .5F, 1, 3);
		vp.insert(0, 2, 0);
		vp.insert(0, 2, 1);
		vp.insert(1, 2, 2);
		vp.insert(0, 2, 3);
		vp.insert(0, 3, 0);
		vp.insert(0, 3, 1);
		vp.insert(0, 3, 2);
		vp.insert(1, 3, 3);

		Matrix cam = new Matrix(4);
		cam.insert(1, 0, 0);
		cam.insert(0, 0, 1);
		cam.insert(0, 0, 2);
		cam.insert(0, 0, 3);
		cam.insert(0, 1, 0);
		cam.insert(1, 1, 1);
		cam.insert(0, 1, 2);
		cam.insert(0, 1, 3);
		cam.insert(0, 2, 0);
		cam.insert(0, 2, 1);
		cam.insert(1, 2, 2);
		cam.insert(0, 2, 3);
		cam.insert(0, 3, 0);
		cam.insert(0, 3, 1);
		cam.insert(0, 3, 2);
		cam.insert(1, 3, 3);

		Matrix real = new Matrix(4);
		real.insert(2, 0, 0);
		real.insert(0, 0, 1);
		real.insert(0, 0, 2);
		real.insert(0, 0, 3);
		real.insert(0, 1, 0);
		real.insert(2, 1, 1);
		real.insert(0, 1, 2);
		real.insert(0, 1, 3);
		real.insert(0, 2, 0);
		real.insert(0, 2, 1);
		real.insert(2, 2, 2);
		real.insert(-7, 2, 3);
		real.insert(0, 3, 0);
		real.insert(0, 3, 1);
		real.insert(0, 3, 2);
		real.insert(1, 3, 3);

		Matrix perspect = new Matrix(4);
		perspect.insert(n, 0, 0);
		perspect.insert(0, 0, 1);
		perspect.insert(0, 0, 2);
		perspect.insert(0, 0, 3);
		perspect.insert(0, 1, 0);
		perspect.insert(n, 1, 1);
		perspect.insert(0, 1, 2);
		perspect.insert(0, 1, 3);
		perspect.insert(0, 2, 0);
		perspect.insert(0, 2, 1);
		perspect.insert(n + f, 2, 2);
		perspect.insert(-f * n, 2, 3);
		perspect.insert(0, 3, 0);
		perspect.insert(0, 3, 1);
		perspect.insert(1, 3, 2);
		perspect.insert(0, 3, 3);
		int k0 = (int) gIndexBuffer[3 * i + 0];
		int k1 = (int) gIndexBuffer[3 * i + 1];
		int k2 = (int) gIndexBuffer[3 * i + 2];
		Matrix v1 = new Matrix(4,1);
		v1.insert(vertexArray[k0].x, 0, 0);
		v1.insert(vertexArray[k0].y, 1, 0);
		v1.insert(vertexArray[k0].z, 2, 0);
		v1.insert(1, 3, 0);
		Matrix result = (cam).multiply(real).multiply(v1);
		float s = result.getValue(3, 0);
		result.scalarMultiply(1/s);
		Matrix v2 = new Matrix(4,1);
		v2.insert(vertexArray[k1].x, 0, 0);
		v2.insert(vertexArray[k1].y, 1, 0);
		v2.insert(vertexArray[k1].z, 2, 0);
		v2.insert(1, 3, 0);
		Matrix result2 = (cam).multiply(real).multiply(v2);
		float s2 = result2.getValue(3, 0);
	     result2.scalarMultiply(1/s2);
	     Matrix v3 = new Matrix(4,1);
	        v3.insert(vertexArray[k2].x, 0, 0);
			v3.insert(vertexArray[k2].y, 1, 0);
			v3.insert(vertexArray[k2].z, 2, 0);
			v3.insert(1, 3, 0);
			Matrix result3 = (cam).multiply(real).multiply(v3);
			float s3 = result3.getValue(3, 0);
		     result3.scalarMultiply(1/s3);
		     Vector A = new Vector(result.getValue(0,0),result.getValue(1,0),result.getValue(2,0));
		     Vector B = new Vector(result2.getValue(0,0),result2.getValue(1,0),result2.getValue(2,0));
		     Vector C = new Vector(result3.getValue(0,0),result3.getValue(1,0),result3.getValue(2,0));
		     Vector edge1 =B.sub(A);
		     Vector edge2 = C.sub(A);
		     Vector norm = edge1.cross(edge2);
		     norm.normalize();
		     Vector center = new Vector((A.x+B.x+C.x)/3,(A.y+B.y+C.y)/3,(A.z+B.z+C.z)/3);
		     Vector light = new Vector(-4F,4,-3F).sub(center);
		     light.normalize();
		     Vector ka = new Vector(0,1,0);
		     Vector kd = new Vector(0,.5F,0);
		     Vector ks= new Vector(.5F,.5F,.5F);
		     Vector La =ka.scale(.2F);
		     Vector Ld = kd.scale(Math.max(0, light.dot(norm)));
		     center =new Vector(0,0,0).sub(center);
		     center.normalize();
		     Vector h= center.add(light);
		     h.normalize();
		     Vector Ls = ks.scale((float) Math.pow(norm.dot(h), 32));
		     Vector L = Ld.add(La).add(Ls);
		     Carray[i][0] = L.x;
		     Carray[i][1]=L.y;
		     Carray[i][2]=L.z;
		     
		     result =vp.multiply(ortho).multiply(perspect).multiply( result);
		     result.scalarMultiply(1/result.getValue(3, 0));
		     result2 =vp.multiply(ortho).multiply(perspect).multiply( result2);
		     result2.scalarMultiply(1/result2.getValue(3, 0));
		     result3 =vp.multiply(ortho).multiply(perspect).multiply( result3);
		     result3.scalarMultiply(1/result3.getValue(3, 0));
		     rasterize(i,result.getValue(0,0), result2.getValue(0,0), result3.getValue(0, 0), result.getValue(1,0), result2.getValue(1,0), result3.getValue(1, 0),result.getValue(2, 0),result2.getValue(2, 0),result3.getValue(2, 0));
		    
	}

	public float gamma(Vertex v1, Vertex v2, Vertex v3, int x, int y) {

		float gamma = ((v1.y - v2.y) * x + (v2.x - v1.x) * y + v1.x * v2.y - v2.x
				* v1.y)
				/ ((v1.y - v2.y) * v3.x + (v2.x - v1.x) * v3.y + v1.x * v2.y - v2.x
						* v1.y);

		return gamma;
	}

	public float beta(Vertex v1, Vertex v2, Vertex v3, int x, int y) {

		float beta = ((v1.y - v3.y) * x + (v3.x - v1.x) * y + (v1.x * v3.y) - (v3.x * v1.y))
				/ ((v1.y - v3.y) * v2.x + (v3.x - v1.x) * v2.y + (v1.x * v3.y) - (v3.x * v1.y));

		return beta;
	} 
	public void vertex(){
		
		float theta, phi;
	int t1=0;
		
		
		for (int j = 1; j < height; ++j) {
			for (int i = 0; i < width; ++i) {
				theta = (float) ((float) j / (height - 1) * Math.PI);
				phi = (float) ((float) i / (width - 1) * Math.PI * 2);

				float x = (float) (Math.sin(theta) * Math.cos(phi));
				float y = (float) Math.cos(theta);
				float z = (float) (-Math.sin(theta) * Math.sin(phi));

				Vertex v = new Vertex(x, y, z);
		        vertexArray[t1] = v;
				// TODO: Set vertex t in the vertex array to {x, y, z}.

				t1++;
			}
		}

		// TODO: Set vertex t in the vertex array to {0, 1, 0}.
		vertexArray[t1] = (new Vertex(0F, 1F, 0F));
		
		t1++;

		// TODO: Set vertex t in the vertex array to {0, -1, 0}.
		vertexArray[t1] = new Vertex(0F, -1F, 0F);
		
		t1++;

		t1 = 0;
		for (int j = 0; j < height - 3; ++j) {
			for (int i = 0; i < width - 1; ++i) {
				gIndexBuffer[t1++] = j * width + i;
				gIndexBuffer[t1++] = (j + 1) * width + (i + 1);
				gIndexBuffer[t1++] = j * width + (i + 1);
				gIndexBuffer[t1++] = j * width + i;
				gIndexBuffer[t1++] = (j + 1) * width + i;
				gIndexBuffer[t1++] = (j + 1) * width + (i + 1);
			}
		}
		for (int i = 0; i < width - 1; ++i) {
			gIndexBuffer[t1++] = (height - 2) * width;
			gIndexBuffer[t1++] = i;
			gIndexBuffer[t1++] = i + 1;
			gIndexBuffer[t1++] = (height - 2) * width + 1;
			gIndexBuffer[t1++] = (height - 3) * width + (i + 1);
			gIndexBuffer[t1++] = (height - 3) * width + i;
		}
	
		
	}


}
