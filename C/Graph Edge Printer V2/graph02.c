//Axel E Murillo | aemurill
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "intList.h"
#include "intVec.h"
#include "loadGraph.h"

void toStringL(IntList L){
	if(intRest(L) != intNil){
		printf(", %d", intFirst(intRest(L)));
		toStringL(intRest(L));
	}else printf("] \n");
	return;
}

int main(int argc, char **argv) {
	int n = 0, flag = 0;
	
	//open file
	FILE* file;
	
	//read arguments
	if(argc == 3){
		//check flag
		if(strcmp(argv[1], "-L") == 0){
			//redundant
		}else if(strcmp(argv[1], "-V") == 0){
			flag = 1;
		}else{
			fprintf(stderr, "Usage: graph02 [OPTION] input.data\n");
			exit(1);
		}
		//check input source
		if(strcmp(argv[2], "-") != 0){
			file = fopen(argv[2], "r");
			if(file == NULL){
				fprintf(stderr, "File not Found\n");
				exit(2);
			} 
		}else{
			file = stdin;
		}
	}else{
		fprintf(stderr, "Usage: graph02 [OPTION] input.data\n");
		exit(1);
	}
	
	
	
	//get n from file
	char line[1024];
	fgets(line, 1024, file);
	n = parseN(line);
	
	//init edges
	if(flag == 0){
		//IntList procedures
		IntList* adjVerticesL = initEdgesL(n);
		//load edges
		loadEdgesL(file, adjVerticesL, n);
		
		//print results
		for(int i = 1; i < n+1; i++){
			if(adjVerticesL[i] == intNil) printf("%d\t[]\n", i);
			else{
				printf("%d\t[%d", i, intFirst(adjVerticesL[i]));
				toStringL(adjVerticesL[i]);
			}
		}
	}else{
		//IntVec procedures
		IntVec* adjVerticesV = initEdgesV(n);
		
		loadEdgesV(file, adjVerticesV, n);
		for(int i = 1; i < n+1; i++){
			if(intSize(adjVerticesV[i]) <= 0) printf("%d\t[]\n", i);
			else{
				printf("%d\t[%d", i, intTop(adjVerticesV[i]));
				intVecPop(adjVerticesV[i]);
				while(intSize(adjVerticesV[i]) > 0){
					printf(", %d", intTop(adjVerticesV[i]));
					intVecPop(adjVerticesV[i]);
				}
				printf("]\n");
			}
		}
	}
	
	
	return 0;
}








