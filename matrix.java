
public class Matrix {
	private int rows;
	private int col;
	private float[][] matrix;
	public Matrix(int row, int column){
		rows = row;
		col = column;
		matrix = new float[row][column];
	}
	public Matrix(Matrix m){
		rows =m.rows;
		col =m.col;
		 matrix =m.matrix;
		
	}
	public Matrix(int dim){
		rows = dim;
		col = dim;
		matrix = new float [dim][dim];
	}
	public Matrix(Vector v){
		rows =4;
		col =1;
		matrix = new float[rows][col];
		
	}
	public void insert(float data, int row, int column){
	if(row >rows||col<column){
		throw new RuntimeException();
			
		}
	matrix[row][column] =data;
	}
	public Matrix copy(){
		Matrix N = new Matrix(rows,col);
		N.matrix = matrix;
		return N;
	}
	public void printMatrix(){
		for( int i = 0; i< rows;i++){
			for(int j =0;j<col;j++){
				System.out.print(" "+matrix[i][j]+" ");	
			}
			System.out.println(" ");
		}

	}
	public float getValue(int r,int c){
		return matrix[r][c];
	}
	public Matrix add(Matrix add){
		if( rows != add.rows && col!=add.col){
			throw new RuntimeException();
		}
		Matrix newM = new Matrix(add.rows,add.col);
		for( int i = 0; i< rows;i++){
			for(int j =0;j<col;j++){
				matrix[i][j]+= add.getValue(i, j) ;
			}
		}
		newM.matrix = matrix;
		return newM;
	}
	public Matrix subtract(Matrix sub){
		if( rows != sub.rows && col!=sub.col){
			throw new RuntimeException();
		}
		Matrix newM = new Matrix(sub.rows,sub.col);
		for( int i = 0; i< rows;i++){
			for(int j =0;j<col;j++){
				matrix[i][j] -= sub.getValue(i, j) ;
			}
		}
		newM.matrix = matrix;
		return newM;
	}
	public Matrix transpose(){
		Matrix N = new Matrix(rows,col);
		for(int i=0;i<rows;i++){
			for(int j=0;j<col;j++){
				N.matrix[j][i]=matrix[i][j];
			}
		}
		return N;
	}
	public Matrix scalarMultiply(float a){
		Matrix N = new Matrix(rows,col);
		N.matrix = matrix;
		for( int i = 0; i< rows;i++){
			for(int j =0;j<col;j++){
				N.matrix[i][j]*=a;
			}
	}
		return N;
	}

	public float[][] getMatrix(){
		return matrix;
	}
	public Matrix multiply(Matrix a){
		Matrix mult= new Matrix(getRow(),a.getColumn());
		for( int i = 0;i< getRow();i++){
			for(int j=0;j<a.getColumn();j++){
				for(int m =0;m<getRow();m++){
					mult.matrix[i][j]= mult.matrix[i][j]+(matrix[i][m]*a.matrix[m][j]);
				}
			}
		}
		return mult;
	}
	public boolean isSquare(){
		if(rows==col){
			System.out.println("This is a square matrix");
			return true;
		}
		return false;
	}
	public int getRow(){
		return rows;
	}
	public int getColumn(){
		return col;
	}
	public float getElement(int a, int b){
		return matrix[a][b];
	}
}
