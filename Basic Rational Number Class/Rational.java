//-----------------------------------------------------------------------------
//   Rational.java
//   Represents rational numbers as a pair of ints
//-----------------------------------------------------------------------------

class Rational{

   // Private Data Fields -----------------------------------------------------
   private int numerator;
   private int denominator;

   // Public Constant Fields --------------------------------------------------
   public static final Rational ONE = Rational.valueOf(1,1);
   public static final Rational ZERO = Rational.valueOf(0,1);

   // Constructors ------------------------------------------------------------
   Rational(int n, int d){
      if( d==0 ){
         throw new ArithmeticException(
            "Cannot create Rational with zero denominator");
      }
      if( d<0 ){
         d = -d;
         n = -n;
      }
      int g = gcd(Math.abs(n), d);
      numerator = n/g;
      denominator = d/g;
   }

   Rational(int n){
      numerator = n;
      denominator = 1;
   }

   Rational(String s){
      String[] part = s.split("/",2);
      int n=0, d=1, count=part.length;

      if( count<1 || count>2 ){
         throw new NumberFormatException(
                   "Initialization string for Rational must be:"+
                   " 'integer' or 'integer/integer'");
      }

      try{ 
         n = Integer.parseInt(part[0]); 
      }catch(NumberFormatException e){
         throw new NumberFormatException(
                   "Initialization string for Rational must be:"+
                   " 'integer' or 'integer/integer'");
      }

      if( count==2 ){
         try{ d = Integer.parseInt(part[1]); }
         catch(NumberFormatException e){
            throw new NumberFormatException(
                      "Initialization string for Rational must be:"+
                      " 'integer' or 'integer/integer'");
         }
      }

      if( d==0 ){
         throw new ArithmeticException(
                   "Cannot create Rational with zero denominator");
      }
      if( d<0 ){
         d = -d;
         n = -n;
      }
      int g = gcd(Math.abs(n), d);
      numerator = n/g;
      denominator = d/g;
   }

   // Private methods ---------------------------------------------------------

   // gcd()
   // Compute the Greatest Common Divisor of two integers.
   // Pre: arguments are non-negative, and at most one argument is zero.
   private int gcd(int a, int b){
      int r, temp;
      if( a<b ){ temp = a; a = b; b = temp; }
      while( b!=0 ){ r = a%b; a = b; b = r; }
      return a;
   }

   // Public and Package methods ----------------------------------------------

   // toString()
   public String toString(){
      String str;
      if( this.denominator==1 ){
         str = String.valueOf(numerator);
      }else{
         str = String.valueOf(numerator) + "/" + String.valueOf(denominator);
      }
      return(str);
   }

   // equals()
   public boolean equals(Object x){
      boolean eq = false;
      Rational r;

      if( x instanceof Rational ){
         r = (Rational) x;
         eq = ( this.numerator==r.numerator && this.denominator==r.denominator );
      }
      return eq;
   }

   // compareTo()
   int compareTo(Rational r){
      int comp = this.numerator*r.denominator - this.denominator*r.numerator;
      return( comp>0 ? 1 : comp==0 ? 0 : -1 );
   }

   // doubleValue()
   double doubleValue(){
      return( this.numerator/(double)this.denominator );
   }

   // valueOf()
   static Rational valueOf(int n, int d){
      return( new Rational(n, d) );
   }

   // valueOf()
   static Rational valueOf(int n){
      return( new Rational(n) );
   }

   // valueOf()
   static Rational valueOf(String s){
      return( new Rational(s) );
   }

   // add()
   Rational add(Rational r){
      int n = this.numerator*r.denominator + this.denominator*r.numerator;
      int d = this.denominator*r.denominator;
      return( new Rational(n, d) );
   }

   // sub()
   Rational sub(Rational r){
      int n = this.numerator*r.denominator - this.denominator*r.numerator;
      int d = this.denominator*r.denominator;
      return( new Rational(n, d) );
   }

   // mult()
   Rational mult(Rational r){
      int n = this.numerator*r.numerator;
      int d = this.denominator*r.denominator;
      return( new Rational(n, d) );
   }

   // recip()
   Rational recip(){
      int n = this.denominator;
      int d = this.numerator;
      if( d==0 ){
         throw new ArithmeticException("Cannot create reciprocal of zero");
      }
      return( new Rational(n, d) );
   }

   // divide()
   Rational divide(Rational r){
      if( r.numerator==0 ){
         throw new ArithmeticException("Cannot divide by zero");
      }
      int n = this.numerator*r.denominator;
      int d = this.denominator*r.numerator;
      return( new Rational(n, d) );
   }

}

