/* loadGraph.h
 * Loads the Adjacency List into IntList format
 * Made by Axel E Murillo (aemurill)
 */
 
#ifndef C101Edge
#define C101Edge

typedef struct EdgeNode * Edge;

IntList* initEdgesL(int n);

IntVec* initEdgesV(int n);

int loadEdgesL(FILE *file, IntList *adjVertices, int n);

int loadEdgesV(FILE *file, IntVec *adjVertices, int n);

int parseN(char *line);

#endif