#include <stdlib.h>
#include <stdio.h>
#include "intVec.h"

int main(void){

   IntVec testVec = intMakeEmptyVec();
   printf("Made an empty testVec\n");
   for(int i = 1; i <= 65; i++){
      intVecPush(testVec, i);
      printf("Pushed '%d' into the array\n", i);
   }
   printf("Top element is %d, array size is %d, capacity is %d\n", 
      intTop(testVec), intSize(testVec), intCapacity(testVec));
 
   printf("Array: {");
   for(int i = 0; i < 64; i++){
      printf("%d, ", intData(testVec, i));
   }
   printf("%d}\n", intData(testVec, 64));
   
   return(0);

}