import java.util.*;
import java.io.IOException;

public class TurtleDraw{

	public static Vector<String> names = new Vector<String>();
	public static Vector<String> commands = new Vector<String>();
	
	public static char matrix[][] = new char[200][200];
	public static int X, Y = 100;

	public static int currentDirection = 0;
	public static int minX, minY = 199;
	public static int maxX, maxY = 0;
	/*
					  0 - up
					  |
		left - 1 -------------- 3 - right
					  |
					  2
	*/


public static void setMinMax(){
	Vector<Integer> x_i = new Vector<Integer>();
	Vector<Integer> y_j = new Vector<Integer>();
	for(int i = 0; i < 200; i++){
		for(int j = 0; j < 200; j++){
			if(matrix[i][j] != 0){
				x_i.add(i);
				y_j.add(j);
			}
		}
	}
	minX = Collections.min(x_i);
	minY = Collections.min(y_j);
	maxX = Collections.max(x_i);
	maxY = Collections.max(y_j);
}

public static void processCharacter(char _char){
	if(_char=='M'){
		if(currentDirection == 0){
			X -= 1;
		}
		else if(currentDirection == 1){
			Y -= 1;
		}
		else if(currentDirection == 3){
			Y += 1;
		}
		else if(currentDirection == 2){
			X += 1;
		}
		
	}

	else if(_char=='L'){
		if(currentDirection == 0){
			currentDirection = 1;
		}
		else if(currentDirection == 1){
			currentDirection = 2;
		}
		else if(currentDirection == 2){
			currentDirection = 3;
		}
		else if(currentDirection == 3){
			currentDirection = 0;
		}
	}

	else if(_char=='R'){
		if(currentDirection == 0){
			currentDirection = 3;
		}
		else if(currentDirection == 3){
			currentDirection = 2;
		}
		else if(currentDirection == 2){
			currentDirection = 1;
		}
		else if(currentDirection == 1){
			currentDirection = 0;
		}
	}

	else{
		matrix[X][Y] = _char;
		// if(X<minX)
		// 	minX = X;
		// if(X>maxX)
		// 	maxX = X;
		// if(Y<minY)
		// 	minY = Y;
		// if(Y>maxY)
		// 	maxY = Y;
		//System.out.println(X+","+Y);
		processCharacter('M');
	}
}

public static void main(String args[]) throws IOException{

	readInputs();
	Collections.reverse(names);
	Collections.reverse(commands);

	X = 100;
	Y = 100;

	int count = names.size()-1;
	while(count>=0){
		X=100;
		Y=100;
		currentDirection = 0;
		minX = minY = 199;
		maxX = maxY = 0;
		System.out.print(names.get(count));
		//System.out.println(commands.get(count));
		char characters[] = commands.get(count).toCharArray();

		for(char _char : characters){
			processCharacter(_char);
		}
		setMinMax();
		displayMatrix();
		clearMatrix();

        count--;
    } // End of Loop: While

} // End of Function: Main

public static void clearMatrix(){
	matrix = new char[200][200];
}

public static void displayMatrix(){
	System.out.println("\n");
	for(int i = minX; i <= maxX; i++)
	{
		for(int j = minY; j <= maxY ; j++){
			//char _c = matrix[i][j];
			if(matrix[i][j]==0)
				System.out.print(" ");
			else
				System.out.print(matrix[i][j]);
		}
		System.out.println();
	}
	System.out.println();
}

public static void readInputs() throws IOException{
		
	Scanner scanner = new Scanner(System.in);
	String data = null;
	String _data = "";
	int i = 0;
	while(scanner.hasNextLine()){
		data = scanner.nextLine();
		if(data.length()==0)
			break;
				if(data.equals(".")){
					commands.add(_data);
					_data = "";
					i=0;
					continue;
				}
					
				if(i==0){
					names.add(data.trim());
					i++;
					continue;
				}		
				if(i==1){
					_data += data.trim();
				}						
			
		}
		scanner.close();
	}

} // End of Class: TurtleDraw 