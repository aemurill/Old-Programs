//-----------------------------------------------------------------------------
//   RationalTest.java
//   Tests the Rational data type
//-----------------------------------------------------------------------------

class RationalTest{

   public static void main(String[] args){

      Rational a = new Rational("6/4");
      Rational b = new Rational(8, 10);

      System.out.println(a);
      System.out.println(b);
      System.out.println(a.add(b));
      System.out.println(a.sub(b));
      System.out.println(a.mult(b));
      System.out.println(a.divide(b));
      System.out.println(a.recip());

      Rational c = new Rational(12, 8);

      System.out.println(a.equals(b));
      System.out.println(a.equals(c));
      System.out.println(a.compareTo(b));
      System.out.println(b.compareTo(c));
      System.out.println(a.compareTo(c));
      System.out.println(c.doubleValue());
      System.out.println(Rational.valueOf("9/-2"));
      System.out.println(Rational.valueOf(9,-2));

      try{ Rational d = new Rational(1,0); }
      catch(ArithmeticException e){ System.out.println(e.getMessage()); }

      try{ System.out.println(Rational.valueOf(0,1).recip()); }
      catch(ArithmeticException e){  System.out.println(e.getMessage()); }

      try{ System.out.println(a.divide(Rational.valueOf(0,1))); }
      catch(ArithmeticException e){ System.out.println(e.getMessage()); }

      System.out.println(Rational.ONE);
      System.out.println(Rational.ZERO);
      System.out.println(Rational.valueOf(3,1));

      try{ System.out.println(Rational.valueOf("x53/27")); }
      catch(NumberFormatException e){ System.out.println(e.getMessage()); }

      try{ System.out.println(Rational.valueOf("53/x27")); }
      catch(NumberFormatException e){ System.out.println(e.getMessage()); }

      try{ System.out.println(Rational.valueOf("53/27/")); }
      catch(NumberFormatException e){ System.out.println(e.getMessage()); }

      try{ System.out.println(Rational.valueOf("53//27")); }
      catch(NumberFormatException e){ System.out.println(e.getMessage()); }
      
   }

}


/* Output:
3/2
4/5
23/10
7/10
6/5
15/8
2/3
false
true
1
-1
0
1.5
-9/2
-9/2
Cannot create Rational with zero denominator
Cannot create reciprocal of zero
Cannot divide by zero
1
0
3
Initialization string for Rational must be: 'integer' or 'integer/integer'
Initialization string for Rational must be: 'integer' or 'integer/integer'
Initialization string for Rational must be: 'integer' or 'integer/integer'
Initialization string for Rational must be: 'integer' or 'integer/integer'
*/

