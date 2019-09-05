//-----------------------------------------------------------------------------
//   Complex.java
//   Axel Murillo
//   aemurill
//	 pa6
//   Represents complex numbers as a pair of doubles
//-----------------------------------------------------------------------------

class Complex{

   //--------------------------------------------------------------------------
   // Private Data Fields 
   //--------------------------------------------------------------------------
   private double re;
   private double im;
   
   //--------------------------------------------------------------------------
   // Public Constant Fields 
   //--------------------------------------------------------------------------
   public static final Complex ONE = Complex.valueOf(1,0);
   public static final Complex ZERO = Complex.valueOf(0,0);
   public static final Complex I = Complex.valueOf(0,1);

   //--------------------------------------------------------------------------
   // Constructors 
   //--------------------------------------------------------------------------
   Complex(double a, double b){
      this.re = a;
      this.im = b;
   }

   Complex(double a){
      this.re = a;
      this.im = 0;
   }

   Complex(String s){
      // Fill in this constructor.
      // It should accept expressions like "-2+3i", "2-3i", "3", "5i", etc..
      // Throw a NumberFormatException if s cannot be parsed as Complex.
	  double[] part =  parseComplex(s);
	  this.re = part[0];
	  this.im = part[1];
   }


   //---------------------------------------------------------------------------
   // Public methods 
   //---------------------------------------------------------------------------

   // Complex arithmetic -------------------------------------------------------

   // copy()
   // Return a new Complex equal to this Complex
   Complex copy(){
      // Fill in
	  Complex C = new Complex(0, 0);
	  C.re = this.re;
	  C.im = this.im;
	  return C;
   }
   
   // add()
   // Return a new Complex representing the sum this plus z.
   Complex add(Complex z){
      // Fill in
	  Complex C = new Complex(0, 0);
	  C.re = this.re + z.re;
	  C.im = this.im + z.im;
	  return C;
   }
   
   // negate()
   // Return a new Complex representing the negative of this.
   Complex negate(){
	  // Fill in
	  Complex C = new Complex(0, 0);
	  C.re = (-1)*this.re;
	  C.im = (-1)*this.im;
	  return C;
      
   }

   // sub()
   // Return a new Complex representing the difference this minus z.
   Complex sub(Complex z){
	  // Fill in
	  Complex C = new Complex(0, 0);
	  C.re = this.re - z.re;
	  C.im = this.im - z.im;
      return C;
   }

   // mult()
   // Return a new Complex representing the product this times z.
   Complex mult(Complex z){
      // Fill in
	  Complex C = new Complex(0, 0);
	  C.re = ((this.re * z.re) - (this.im * z.im));
	  C.im = ((this.re * z.im) + (this.im * z.re));
	  return C;
   }

   // recip()
   // Return a new Complex representing the reciprocal of this.
   // Throw an ArithmeticException with appropriate message if 
   // this.equals(Complex.ZERO).
   Complex recip(){
      // Fill in
	  Complex C = this.copy();
	  double d;
	  if(this.equals(Complex.ZERO)){
		  System.err.println("Error, division by zero");
	  }
	  d = Math.pow(this.re, 2) + Math.pow(this.im, 2);
	  C.re = (this.re)/d;
	  C.im = ((-1)*(this.im))/d;
	  return C;
   }

   // div()
   // Return a new Complex representing the quotient of this by z.
   // Throw an ArithmeticException with appropriate message if 
   // z.equals(Complex.ZERO).
   Complex div(Complex z){
      // Fill in
	  Complex C = new Complex(0, 0);
	  C.re = this.re;
	  C.im = this.im;
	  z = z.recip();
	  C = C.mult(z);
	  return C;
   }

   // conj()
   // Return a new Complex representing the conjugate of this Complex.
   Complex conj(){
      // Fill in
	  Complex C = new Complex(0, 0);
	  C.re = this.re;
	  C.im = (-1)*this.im;
	  return C;
   }
   
   // Re()
   // Return the real part of this.
   double Re(){
      return re;
   }

   // Im()
   // Return the imaginary part of this.
   double Im(){
      return im;
   }

   // abs()
   // Return the modulus of this Complex, i.e. the distance between 
   // points (0, 0) and (re, im).
   double abs(){
      // Fill in
	  Complex C = this.conj();
	  return Math.sqrt(Math.pow(C.re, 2) + Math.pow(C.im, 2));//C.im = 0
   }

   // arg()
   // Return the argument of this Complex, i.e. the angle this Complex
   // makes with positive real axis.
   double arg(){
      return Math.atan2(im, re);
   }

   // Other functions ---------------------------------------------------------
   
   // toString()
   // Return a String representation of this Complex.
   // The String returned will be readable by the constructor Complex(String s)
   public String toString(){
      // Fill in
	  String A;
	  double a = this.re;
	  double b = this.im;
	  String c = Double.toString(a);
	  String d = Double.toString(b);
	  
	  if(a == 0){ //(0, y)
		  if(b == 0){ //(0, 0)
			  A = "0";
			  return A;
		  }
		  A = d;
		  A += "i";
	  }else{ //(x, y)
		  A = c;
		  if(b == 0){ //(x, 0)
			  return A; 
		  }
		  if(b > 0){ //(x, +y)
			  A += "+";
		  }
		  A += d;
		  A += "i";
	  }
	  
	  
	  return A;
   }

   // equals()
   // Return true iff this and obj have the same real and imaginary parts.
   public boolean equals(Object obj){
      // Fill in
	  Complex C;
	  boolean eq = false;
	  if(obj instanceof Complex){
	     C = (Complex) obj;
	     eq = this.re == C.re && this.im == C.im;
	  }
	  return eq;
   }

   // valueOf()
   // Return a new Complex with real part a and imaginary part b.
   static Complex valueOf(double a, double b){
      // Fill in
	  Complex C = new Complex(0, 0);
	  C.re = a;
	  C.im = b;
	  return C;
   }

   // valueOf()
   // Return a new Complex with real part a and imaginary part 0.
   static Complex valueOf(double a){
      // Fill in
	  Complex C = new Complex(0, 0);
	  C.re = a;
	  C.im = 0;
	  return C;
   }

   // valueOf()
   // Return a new Complex constructed from s.
   static Complex valueOf(String s){
      // Fill in
	  Complex C = new Complex(0, 0);
	  double[] D = parseComplex(s);
	  C.re = D[0];
	  C.im = D[1];
	  return C;
   }

   
   // parseComplex()
   // Returns a double[] of length 2 containing (real, imaginary) parts
   // of a complex number represented by string argument str.  Throws a
   // NumberFormatException if str cannot be parsed as a complex number.
   static double[] parseComplex(String str){
      double[] part = new double[2];
      String s = str.trim();
      String NUM = "(\\d+\\.\\d*|\\.?\\d+)";
      String SGN = "[+-]?";
      String OP =  "\\s*[+-]\\s*";
      String I =   "i";
      String OR =  "|";
      String REAL = SGN+NUM;
      String IMAG = SGN+NUM+"?"+I;
      String COMP = REAL+OR+
                    IMAG+OR+
                    REAL+OP+NUM+"?"+I;
      
      if( !s.matches(COMP) ){
         throw new NumberFormatException(
                   "Cannot parse input string \""+s+"\" as Complex");
      }
      s = s.replaceAll("\\s","");     
      if( s.matches(REAL) ){
         part[0] = Double.parseDouble(s);
         part[1] = 0;
      }else if( s.matches(SGN+I) ){
         part[0] = 0;
         part[1] = Double.parseDouble( s.replace( I, "1.0" ) );
      }else if( s.matches(IMAG) ){
         part[0] = 0;
         part[1] = Double.parseDouble( s.replace( I , "" ) );
      }else if( s.matches(REAL+OP+I) ){
         part[0] = Double.parseDouble( s.replaceAll( "("+REAL+")"+OP+".+" , "$1" ) );
         part[1] = Double.parseDouble( s.replaceAll( ".+("+OP+")"+I , "$1"+"1.0" ) );
      }else{   //  s.matches(REAL+OP+NUM+I) 
         part[0] = Double.parseDouble( s.replaceAll( "("+REAL+").+"  , "$1" ) );
         part[1] = Double.parseDouble( s.replaceAll( ".+("+OP+NUM+")"+I , "$1" ) );
      }
      return part;
   }
   
}