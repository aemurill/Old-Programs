// Figure A.1

import	java.io.BufferedReader;

public class graph{
	public static void main(String[] argv){
		int	m, n;
		IntList[] adjVertices;

		if (argv.length == 0){
			System.out.println("Usage: java graph input.data");
			System.exit(0);
		}
		String infile = argv[0]; //choose file
		BufferedReader inbuf = InputLib.fopen(infile); //open file
		System.out.println("Opened " + infile + " for input."); 
		String line = InputLib.getLine(inbuf); //read line 
		n = LoadGraph.parseN(line); //read n from file
		System.out.println("n = " + n);

		adjVertices = LoadGraph.initEdges(n); //initalize edges using n
		m = LoadGraph.loadEdges(inbuf, adjVertices); //load edges into empty slots
		InputLib.fclose(inbuf); //close file
		System.out.println("m = " + m);

		for (int i = 1; i <= n; i ++){  //print out info
			if(adjVertices[i] == IntList.nil) System.out.println(i + "\t[]");
			else System.out.println(i + "\t" + adjVertices[i]);
			
			/*if(adjVertices[i] == IntList.nil) System.out.println("[]");
			else System.out.println(adjVertices[i]);
			}*/
		}
		return;
	}

}