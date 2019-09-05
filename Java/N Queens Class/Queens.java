///////////////////////////////////////////////////////////////////////////////
// Queens.java
// Axel Murillo
// aemurill
// pa5
// Determines the Greatest Common Denominator of 2 positive integers
///////////////////////////////////////////////////////////////////////////////
class UsageException extends Exception{
        public UsageException(String message){
            super(message);
        }
    }


class Queens{
    private static void error(Exception e){
        System.out.println(e.toString());
        System.err.println("Usage: Queens [-v] number");
        System.err.println("Option:  -v   verbose output, print all solutions");
        System.exit(0);
    }
    
    static boolean isSolution(int[] A){
        int i, j;
        
        //i and j are the indexes
        for(i = 1; i < A.length; i++){
            for(j = 1; j < A.length; j++){
                if( i == j){
                    continue;
                }
                if((A[j] - A[i]) == (j - i)){
                    return false;
                }
                if((A[i] - A[j]) == (j - i)){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    static void nextPermutation(int[] A){
        int i, j, pIndex = 0, pValue = 0, sIndex =0, sValue = 0;
        int[] B;
        boolean pState = false;
        
        //Scanner for Pivot
        for(i = (A.length-1); i > 0; i--){
            if((i+1) != A.length){
                if(A[i] < A[i+1]){
                    pIndex = i;
                    pValue = A[i];
                    pState = true;
                    break;
                }
            }
        }
        
        //Reverses Array if false
        if(pState == false){
            A = reverseArray1(A);
            return;
        }
        
        //Search for Successor
        for(i = (A.length-1); i > 0; i--){
            if(A[i]>pValue){
                sIndex = i;
                sValue = A[i];
                break;
            }
        }
        
        //SWAP PIVOT AND SUCCESSOR
        swap(A, pIndex, sIndex);
        
        //REVERSE ALL RIGHT OF pINDEX;
        B = new int[A.length-(pIndex+1)];
        
        //INPUT A TO B
        j = 0;
        for(i = (pIndex+1); i < A.length; i++){
            B[j] = A[i];
            j++;
        }
        
        //REVERSE ARRAY
        B = reverseArray2(B);
        
        //INPUT B TO A
        j = 0;
        for(i = (pIndex+1); i < A.length; i++){
            A[i] = B[j];
            j++;
        }
    }
        
    static void swap(int[] A, int pI, int sI) {
        int temp;
        
        temp = A[pI];
        A[pI] = A[sI];
        A[sI] = temp;
    }
    
    //Writes a new array that is a reversed version of the input
    //WITHOUT modifying the 0th index's values
    static int[] reverseArray1(int[] A){
        int i;
        int[] C = new int[A.length];
        
        for(i = 1; i < A.length; i++){
            C[i] = A[A.length-i];
        }
        
        return C;
    }
        
    //Writes a new array that is a reversed version of the input
    static int[] reverseArray2(int[] A){
        int i;
        int[] C = new int[A.length];
        
        for(i = 0; i < A.length; i++){
            C[i] = A[A.length-(i+1)];
        }
        
        return C;
    }
    
    
    public static void main(String[] args){
        int i, n = args.length, m = 0, j;
        long solutions = 0, a = 1, k;
        boolean solution;
        
        //HANDLE # OF ARGS
        try{
            if(n > 2 || n <1){
                throw new UsageException("Invalid # of arguments!");
            }
        }catch(UsageException e){
            error(e);
        }
        
        //HANDLE ARGS
        try{
            if(n == 2){
                if(true != (args[0].equals("-v"))){
                    throw new UsageException("Invalid Arguments!");
                }
                m = Integer.parseInt(args[1]);
            }else{
                m = Integer.parseInt(args[0]);
            }
        }catch(UsageException e){
            error(e);
        }catch(NumberFormatException e){
            error(e);
        }
        
        //SET INITIAL SET
        int[] A = new int[m+1];
        for(i = 0; i < A.length; i++){
            A[i] = i;
        }
        
        //  Set # of permutations to test
        //  Each permutation is an n-Rook solution
        for(i = 1; i <= m; i++){
            a *= i; //m factorial
        }
        
        //TEST PERMUTATIONS
        //Switch which Permutation is to be tested
        //should contain n-1 permutations, which will exclude the initial set!
        for(k = 1; k <= a; k++){ 
            solution = isSolution(A);
            if(solution == true){
                solutions += 1;
            }
            
            if(args[0].equals("-v")){
                if(isSolution(A)){
                    System.out.print("(");
                    for(j = 1; j < A.length-1; j++){
                        System.out.print(A[j]+", ");
                    }
                    System.out.print(A[A.length-1]+")");
                    System.out.println();
                }
            }
            
            nextPermutation(A);
        }
        System.out.println(m+"-Queens has "+solutions+" solutions");
    }
}