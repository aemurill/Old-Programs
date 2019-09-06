//Axel E Murillo | aemurill
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "intList.h"
#include "intVec.h"
#include "loadGraph.h"

struct EdgeNode{
	int from;
	int to;
	double weight;
};

IntList* initEdgesL(int n){
	IntList*  adjVertices = calloc(n+1, sizeof(IntList*));
	for(int i = 1; i <= n; i++) adjVertices[i] = intNil;
	return adjVertices;
}

IntVec* initEdgesV(int n){
	IntVec* adjVertices = calloc(n+1, sizeof(IntVec*));
	for(int i = 1; i <= n; i++) adjVertices[i] = intMakeEmptyVec();
	return adjVertices;
}

Edge parseEdge(char *line){
	Edge newE = calloc(1, sizeof(Edge));
	char junk;
	int num = sscanf(line, "%d %d %lf %c",
		&newE->from, &newE->to, &newE->weight, &junk);
	if(num < 2 || num > 3){
		fprintf(stderr, "Bad input: %s", line);
		exit(1);
	}
	if(num == 2) newE->weight = 0.0;
	return newE;
}

int loadEdgesL(FILE *file, IntList *adjVertices, int n){
	int num = 0;
	char line[1024];
	char* fgetsRetn = fgets(line, 1024, file);
	while (fgetsRetn == line){
		Edge e = parseEdge(line);
		if(e->from > n || e->from <= 0){
			fprintf(stderr, "Bad Edge: %d out of range\n", e->from);
		}else if(e->to > n || e->to <= 0){
			fprintf(stderr, "Bad Edge: %d out of range\n", e->to);
		}else{
			adjVertices[e->from] = intCons(e->to, adjVertices[e->from]);
		}
		
		num ++;
		fgetsRetn = fgets(line, 1024, file);
	}
	if(!feof(file)) printf("An error has occured in reading\n");
	return num;
}

int loadEdgesV(FILE *file, IntVec *adjVertices, int n){
	int num = 0;
	char line[1024];
	char* fgetsRetn = fgets(line, 1024, file);
	while (fgetsRetn == line){
		Edge e = parseEdge(line);
		if(e->from > n || e->from <= 0){
			fprintf(stderr, "Bad Edge: %d out of range\n", e->from);
		}else if(e->to > n || e->to <= 0){
			fprintf(stderr, "Bad Edge: %d out of range\n", e->to);
		}else{
			
			intVecPush(adjVertices[e->from], e->to);
			num ++;
		}
		fgetsRetn = fgets(line, 1024, file);
	}
	if(!feof(file)) printf("An error has occured in reading\n");
	return num;
}

int parseN(char *line){
	int n;
	char junk;
	int num = sscanf(line, "%d %c", &n, &junk);
	if(num != 1){
		fprintf(stderr, "Bad input: %s", line);
		exit(1);
	}
	
	if(n < 1){
		fprintf(stderr, "Invalid number of nodes");
		exit(1);
	}
	
	return n;
}
