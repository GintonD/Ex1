package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.function;
import Ex1.Monom;

class ComplexFunctionTest
{
	
	@Test
	void test_deepcopy() 
	{
		System.out.println("**********test_copy**********");
		Polynom polynom = new Polynom ("2x^3 + 4x");
		ComplexFunction complexFunction = new ComplexFunction("plus",polynom,polynom);
		polynom.multiply(new Monom ("2"));
		
		assertEquals("Plus(+4.0x+2.0x^3,+4.0x+2.0x^3)", complexFunction.toString());
		
		ComplexFunction f1 = new ComplexFunction("Max(Divid(4x^2,3+x),-2)");
		function f2 = f1.copy();
		System.out.println("equal="+f1.equals( f2));
		assertEquals(f1, f2);

	}
	
	
	
	@Test
	void testInitFromString() 
	{
		System.out.println("******test_initfromstring*******");
		ComplexFunction f1 = new ComplexFunction("Plus(Divid(4x^2,3+x),-2)");
		function f2 = f1.initFromString("Plus(Divid(4x^2,3+x),-2)");
		System.out.println("equal="+f1.equals( f2));
		assertEquals(f1, f2);
	}

	
	
	
	
	@Test
	void testF() 
	{
		System.out.println("**********test_fx**********");
		ComplexFunction cof = new ComplexFunction("Plus(Divid(3x+x^2,x+2),x^3)");
		double x = 2;
		double expected = 10.5;
		System.out.println("f(x)="+cof.toString());
		System.out.println("f("+x+")="+cof.f(x));
		System.out.println("expected: "+expected);
		assertEquals(expected, cof.f(x));
		ComplexFunction cof1 = new ComplexFunction("Min(Min(3x+x^2,x+2),x^3)");

		expected = 4;
		System.out.println("f(x)="+cof1.toString());
		System.out.println("f("+x+")="+cof1.f(x));
		System.out.println("expected: "+expected);
		assertEquals(expected, cof1.f(x));
		ComplexFunction cof2 = new ComplexFunction("Max(Max(3x+x^2,x+2),x^3)");

		expected = 10;
		System.out.println("f(x)="+cof2.toString());
		System.out.println("f("+x+")="+cof2.f(x));
		System.out.println("expected: "+expected);
		assertEquals(expected, cof2.f(x));
		ComplexFunction cof3 = new ComplexFunction("Max(Times(3x+x^2,x+2),x^5)");

		expected = 40;
		System.out.println("f(x)="+cof3.toString());
		System.out.println("f("+x+")="+cof3.f(x));
		System.out.println("expected: "+expected);
		assertEquals(expected, cof3.f(x));
		x = 3;
		ComplexFunction cof4 = new ComplexFunction("Comp(Comp(x^2,x+2),x)");

		expected = 25;
		System.out.println("f(x)="+cof4.toString());
		System.out.println("f("+x+")="+cof4.f(x));
		System.out.println("expected: "+expected);
		assertEquals(expected, cof4.f(x));
		int error = 0;
		String[] badExamples = { "None(4x,2x^5)","Divid(Plus(3x+x^2,x),0)", "Error(Divid(3x+x^2,x),x^3)"};																													
		for(int i = 0; i<badExamples.length; i++ ) {
			try {
				ComplexFunction cof5 = new ComplexFunction(badExamples[i]);
				cof5.f(x);
				
			} catch (Exception e){
				error++;
				System.out.println("catch exception "+i+"\\" +(badExamples.length-1)+": "+badExamples[i]);
			}
		}
		
		assertEquals(badExamples.length, error);
			
	}


	
	void test_copy () 
	{
		System.out.println("********test copy**********");
		ComplexFunction c2= new ComplexFunction(Operation.Min, new Polynom("3x^3"), new Polynom("46"));
		ComplexFunction Exepted= new ComplexFunction(c2.getOp(), c2.left().copy(), c2.right().copy());
		System.out.println("equal="+Exepted.equals( c2));
		assertEquals(Exepted, c2);
	}
	@Test
	void test_Equal () 
	{
		System.out.println("**********test equal**********");
		ComplexFunction c1 = new ComplexFunction("plus(x,2)");
		ComplexFunction c2 = new ComplexFunction("plus(2,x)");
		ComplexFunction c3 = new ComplexFunction("mul(x,x)");
		ComplexFunction c4 = new ComplexFunction("plus(x^2,0)");
		assertEquals(c1, c2);
		System.out.println("equal="+c1.equals( c2));
		assertEquals(c3, c4);
		System.out.println("equal="+c3.equals( c4));
		
		ComplexFunction c5= new ComplexFunction(Operation.Plus, new Polynom("22x^3"), new Polynom("53"));

		Polynom p1 = new Polynom("22x^3+53");
		boolean flag = true;
		assertTrue(c5.equals(p1));


	}


	
	@Test
	void test_string () 
	{
		System.out.println("********test_string*********");

		ComplexFunction c1= new ComplexFunction(new Polynom ("x"));
		function f1 = c1.initFromString("Plus(Max(22.0x^3,53.0),Divid(Comp(Plus(3.0x^3,46.0),Plus(1.0x^2,1.0x^7)),Max(+22.0x^3,+53.0)))");//here------
		String Exepted = "Plus(Max(+22.0x^3,+53.0),Divid(Comp(Plus(+3.0x^3,+46.0),Plus(+1.0x^2,+1.0x^7)),Max(+22.0x^3,+53.0)))";
		
		assertEquals(Exepted, f1.toString());
		ComplexFunction c2= new ComplexFunction(Operation.Min, new Polynom("3x^3"), new Polynom("46"));
	
		Exepted = "Min(+3.0x^3,+46.0)";
		assertEquals(Exepted, c2.toString());
		System.out.println("equal="+Exepted.equals( c2.toString()));
		ComplexFunction c4= new ComplexFunction(Operation.Max, new Polynom("22x^3"), new Polynom("53"));


		c2.div(c4);
	
		Exepted = "Divid(Min(+3.0x^3,+46.0),Max(+22.0x^3,+53.0))";
		assertEquals(Exepted, c2.toString());
		ComplexFunction c5= new ComplexFunction(Operation.Max, new Polynom("22x^3"), new Polynom("53"));
		c5.plus(c2);
		Exepted = "Plus(Max(+22.0x^3,+53.0),Divid(Min(+3.0x^3,+46.0),Max(+22.0x^3,+53.0)))";
		System.out.println("equal="+Exepted.equals( c5.toString()));
		assertEquals(Exepted, c5.toString());
	}



}
