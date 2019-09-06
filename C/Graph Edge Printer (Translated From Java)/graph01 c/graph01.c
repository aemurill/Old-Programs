#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "intList.h"

typedef struct{
	int from;
	int to;
	double weight;
}Edge;

void toString(IntList L){
	if(intRest(L) != intNil){
		printf(", %d", intFirst(intRest(L)));
		toString(intRest(L));
	}else printf("] \n");
	return;
}

int parseN(char *line){
	int n;
	char junk;
	int num = sscanf(line, "%d %c", &n, &junk);
	if(num != 1){
		printf("Bad input: %s", line);
		exit(1);
	}
	return n;
}

Edge* parseEdge(char *line){
	Edge* newE = calloc(1, sizeof(Edge));
	char junk;
	int num = sscanf(line, "%d %d %lf %c",
		&newE->from, &newE->to, &newE->weight, &junk);
	if(num < 2 || num > 3){
		printf("Bad input: %s", line);
		exit(1);
	}
	if(num == 2) newE->weight = 0.0;
	return newE;
}


int main(int argc, char **argv) {
	int m = 0, n = 0;
	
	//check file
	if(argc == 1){
		printf("Usage: graph01 input.data\n");
		return(0);
	}
	
	//open file
	FILE* file;
	if(strcmp(argv[1], "-") != 0){
		printf("Opened %s for input.\n", argv[1]);
		file = fopen(argv[1], "r");
	}else{
		printf("Opened stdin for input.\n");
		file = stdin;
	}
	
	//read from file
	char line[1024];
	char* fgetsRetn = fgets(line, 1024, file);
	
	//get n from file
	n = parseN(line);
	printf("n = %d \n", n);
	
	//initialize edges
	IntList* adjVertices = calloc(n+1, sizeof(IntList*));
	for(int i = 1; i <= n; i++){
		adjVertices[i] = intNil;
	}
		
	//read information
	fgetsRetn = fgets(line, 1024, file);
	while(fgetsRetn == line){
		//load edges
		Edge* e = parseEdge(line);
		adjVertices[e->from] = intCons(e->to, adjVertices[e->from]);
		m++;
		fgetsRetn = fgets(line, 1024, file);
	}
	if(!feof(file)) printf("An error has occured in reading\n");
	
	//close file
	fclose(file);
	printf("m = %d\n", m);
	
	//print results
	for(int i = 1; i < n+1; i++){
		if(adjVertices[i] == intNil) printf("%d\tnull\n", i);
		else{
			printf("%d\t[%d", i, intFirst(adjVertices[i]));
			toString(adjVertices[i]);
		}
	}
	return 0;
}








