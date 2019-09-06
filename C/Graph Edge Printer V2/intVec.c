//Axel E Murillo | aemurill
#include <stdlib.h>
#include "intVec.h"

struct IntVecNode{
	int * data;
	int sz;
	int capacity;
};

static const int intInitCap;

int intTop(IntVec myVec){
	return myVec->data[myVec->sz - 1];
}

int intData(IntVec myVec, int i){
	return myVec->data[i];
}

int intSize(IntVec myVec){
	return myVec->sz;
}

int intCapacity(IntVec myVec){
	return myVec->capacity;
}

IntVec intMakeEmptyVec(void){
	IntVec newVec = calloc(1, sizeof(IntVec));
	newVec->data = calloc(intInitCap, sizeof(int));
	newVec->sz = 0;
	newVec->capacity = intInitCap;
	return newVec;
}

void intVecPush(IntVec myVec, int newE){
	if( (myVec->sz+1) > (myVec->capacity)){
		int newCap = 2 * myVec->capacity;
		int* newData = realloc(myVec->data, sizeof(int)*newCap);
		if(newData != myVec->data) myVec->data = newData;
		myVec->capacity = newCap;
	}
	myVec->data[myVec->sz] = newE;
	myVec->sz++;
}

void intVecPop(IntVec myVec){
	myVec->sz--;
}