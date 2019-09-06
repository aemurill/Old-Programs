#include <stdlib.h>
#include "intList.h"


struct IntListNode{
	int element;
	IntList next;
};

const IntList intNil = NULL;

int intFirst(IntList oldL){
	return oldL->element;
}

IntList intRest(IntList oldL){
	return oldL->next;
}

IntList intCons(int newE, IntList oldL ){
	IntList newL = calloc(1, sizeof(struct IntListNode));
	newL->element = newE;
	newL->next = oldL;
	return newL;
}