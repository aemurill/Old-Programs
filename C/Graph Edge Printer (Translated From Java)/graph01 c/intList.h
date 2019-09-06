/* intList.h
 * Shares the functions definitions of intList.c with other programs,
 * as well as listing the required preconditions and resulting 
 * postconditions of the operations.
 */

#ifndef C101IntList
#define C101IntList
/* Multiple typedefs for the same type are an error in C. */

typedef struct IntListNode * IntList;

/** intNil denotes the empty IntList */
extern const IntList intNil;

/* Access functions
 *   All require that L not be nil
 */

/** first
 * Requires that oldL is not nil
 */
int intFirst(IntList oldL);

/** rest
 * Requires that oldL is not nil.
 */
IntList intRest(IntList oldL);

/* Constructors
 */

/** cons
 * Returns a new object that is not nil.
 * The first element in the object is newE, the rest is oldL.
 */
IntList intCons(int newE, IntList oldL);

#endif

