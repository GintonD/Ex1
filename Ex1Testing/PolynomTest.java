package Ex1Testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;
public class PolynomTest {
	
	@Test
	public void TestEqual() 
	{
		Polynom_able p0 = new Polynom();
		Polynom_able p1 = new Polynom();
		Polynom_able p2 = new Polynom();
		String[][] monoms = {{"1", "x", "x^2", "0.5x^2", "0"},{"2.54x^4", "1"},{"2.54x^4", "1"}};
		for (int i = 0; i < monoms[0].length; i++) {
			p0.add(new Monom(monoms[0][i]));
		}
		for (int i = 0; i < monoms[1].length; i++) {
			p1.add(new Monom(monoms[1][i]));
		}
		for (int i = 0; i < monoms[2].length; i++) {
			p2.add(new Monom(monoms[2][i]));
		}
		p0.substract(p1);
		assertTrue("should be true", p1.equals(p2));
	}

	@Test
	public void testEqualsMethodShouldBeTrue()
	{
		Polynom_able p0 = new Polynom();
		Polynom_able p1 = new Polynom();
		Polynom_able p2 = new Polynom();
		Polynom_able p3 = new Polynom();
		Monom m0 = new Monom(1,2);
		String[][] monoms = {{"1", "x", "x^2", "0.5x^2", "0"}, {"2.54x^4", "x", "x^2", "0.5x^2", "1"},
				{"2.54x^4", "1"}};
		for (int i = 0; i < monoms[0].length; i++) {
			p0.add(new Monom(monoms[0][i]));
		}
		for (int i = 0; i < monoms[1].length; i++) {
			p1.add(new Monom(monoms[1][i]));
		}
		for (int i = 0; i < monoms[2].length; i++) {
			p2.add(new Monom(monoms[2][i]));
		}
		assertFalse("Identical polynomial comparison", p1.equals(p0));
		p0.add(new Monom(1,1));
		assertFalse("Check Monom != Polynom_able", p1.equals(p0));
		p2.add(new Monom(monoms[1][2]));
		
		p3.add(new Monom(1,0));
		p3.add(new Monom(2.54,4));
		p2.substract(p3);

		assertTrue("Check Monom = Polynom_able", p2.equals(m0));
		assertFalse("Check Monom != Polynom_able", p1.equals(m0));
		m0 = Monom.ZERO;
		assertFalse("Check Monom != Polynom_able", p2.equals(m0));
	} 
	
	
	
	
	
	
	@Test
	public void testString()
	{
		Polynom_able p1 = new Polynom(), p2 =  new Polynom();
		
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		assertTrue(p1.equals(new Polynom(p1.toString())));
		assertTrue(p1.equals(new Polynom("-4.7x^2-1.0x+6.0")));
	}
	
	
	
	
	@Test
	public void test1()
	{
		
		
		Polynom p1 = new Polynom();
		Polynom p2 = new Polynom();
		Polynom p3 = new Polynom();
		
	
		
		String[] monoms = {"-5x","x^2", "2","x^2", "2","-1x^2"};
		for(int i=0;i<monoms.length-1;i++)
		{
			Monom m = new Monom(monoms[i]);
			p1.add(m);
		}

		System.out.println("***************Test 1****************");
		
		System.out.println("----f(x) method---");
		System.out.println("f(x)= "+p1);
		System.out.println("f(3)="+p1.f(3)+"\n"); // f(x)
		
		System.out.println("----Add monom method---");
		System.out.println("f(x)= "+p1);
		p1.add(new Monom(-4,0));
		System.out.println("f(x)+(-4)="+p1+"\n"); //add
		
		System.out.println("----Subtract polynom method---");
		System.out.println("f(x)= "+p1);
		p2.add(new Monom(-4,0));
		p1.substract(p2); 
		System.out.println("f(x)-(-4)="+p1+"\n"); //subtract
		
		p2.add(new Monom(1,2));
		p2.add(new Monom(3,1));
		
		
		
		System.out.println("----toString and equal method's---");
		System.out.println("p1: "+p1);
		String s1 = p1.toString();
		System.out.println("this is the 'toString' of p1: "+s1);
		Polynom_able pp1 =new Polynom(s1);
		System.out.println("polynom p3 constructed from string: "+pp1);// to string
		System.out.println("p3 equal to p1:"+p1.equals(pp1)+"\n");//equal

		System.out.println("----Add polynom method---");
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2); 
		System.out.println("p1+p2: "+p1+"\n"); //add polynoms
		
		System.out.println("----Multiply polynoms method---");
		p1.multiply(p2);
		System.out.println("( (p1+p2) * p2 ): "+p1+"\n"); //multuply polinoms
		
		
		System.out.println("----equal after self subtract and clear  method---");
		p3=(Polynom) pp1;
		p1.substract(p1);
		System.out.println("p1 map size: "+p1.getPol().size());
		p3.getPol().clear();
		p1.add(new Monom(-4.0,0));
		p3.add(new Monom(-3.9999999,0));
		System.out.println("p1: "+p1);
		System.out.println("p3: "+p3);
		System.out.println("p1 = p3 ? :"+p1.equals(p3)+"\n"); //is equale
		
		
		
		System.out.println("----area  method---");
		p1.add(new Monom(-1,1));
		p1.add(new Monom(2,2));
		System.out.println("p1: "+p1);
		double aa = p1.area(-2, -1.19, 0.0000001);
		System.out.println("the area of p1 is: "+aa+"\n");// area
		
		System.out.println("----derivative---");
		System.out.println("p1: "+p1);
		System.out.println("(p1)' = "+p1.derivative()+"\n"); //derivative
		
		System.out.println("----root---");
		System.out.println("the root of the polynom is: "+p1.root(-2, 1, 0.0000001)+"\n");// root
	}
	
	
	@Test
	public void test2()
	{
System.out.println("******Test 2******* ");
		
		
		
		System.out.println("----------Erors--------");
		String [] monoms1 = {"x^3","x^-1"}; // eror negtive power
		Polynom p11= new Polynom();
		try {
			for(int i=0;i<monoms1.length;i++)
			{
				Monom m = new Monom(monoms1[i]);
				p11.add(m);
			}
			}
		catch(Exception e) 
		{
			System.out.println("the monom power is negative");
			System.out.println(e+"\n"); // 
		}
		
		String [] monoms2 = {"-9x^*2","2x^3"}; // char * is exit in the term
		Polynom p22= new Polynom();
		try {
			for(int i=0;i<monoms2.length;i++)
			{
				Monom m = new Monom(monoms2[i]);
				p11.add(m);
			}
			}
		catch(Exception e) 
		{
			
			System.out.println("char * is exit in the term");
			System.out.println(e+"\n"); // 
		}
		
		
		try
		{
		Polynom p8 = new Polynom("x^9 +x^8 +x^6");
		System.out.println();
		
		}
		catch(Exception e) 
		{
				
			System.out.println("there is a backspace in the string");
			System.out.println(e+"\n"); // 
		}	
		
	
	}

	@Test
	public void Moretest1()
	{
	
		
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("******ORIGINAL TEST*****");
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		System.out.println(p1.root(-1, -1.5,0.0000001));

		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
		Polynom_able pp1 =new Polynom(s1);
		System.out.println("from string: "+pp1);
		
		
	}
	
	@Test
	public void Moretest2()
	{
		System.out.println("\n\n-------test2-------\n");

        Polynom_able p31 = new Polynom("5x^5-4x^4+3x^3+10");
        Polynom_able p32 = new Polynom("2x^5-4x^4+x^3+1");

        System.out.println("Adding p2 to p1\n");
        System.out.println("("+p31 + ")+(" + p32+")");

        p31.add(p32);
        System.out.println("Expected \"11+4*X^3-8*X^4+7*X^5\":");
        System.out.println("Result: " + p31+"\n");


        System.out.println("Substract p2 from p1\n");

        System.out.println(p31 + "-(" + p32+")");
        p31.substract(p32);
        System.out.println("Expected \"10+3*X^3-4*X^4+5*X^5\":");
        System.out.println("Result: " + p31+"\n");

        System.out.println("Multiply p3 by p2\n");

        Polynom_able p33 = (Polynom)p31.copy();
        System.out.println("p1 Copied to p3");
        System.out.println("("+p33+")*("+p32+")");
        p33.multiply(p32);
        System.out.println("Expected \"10+13*X^3-44*X^4+25*X^5+3*X^6-16*X^7+27*X^8-28*X^9+10*X^10\":");
        System.out.println("Result: "+p33+"\n");

        System.out.println("Derivative of p1\n");
        System.out.println("("+p31+")\'");
        System.out.println("Expected \"9*X^2-16*X^3+25*X^4\":");
        System.out.println("Result: " + p31.derivative()+"\n");

        System.out.println("Derivative of p2\n");
        System.out.println("("+p32+")\'");
        System.out.println("Expected \"3*X^2-16*X^3+10*X^4\":");
        System.out.println("Result: " + p32.derivative() +"\n");

        System.out.println("Function check of p1 and p2\n");
        System.out.println(p31 + " : f(5) -> Expected 13510: " + p31.f(5));
        System.out.println(p32 + " : f(-8) -> Expected -82431: " + p32.f(-8));

        double eps = 0.00001;
        System.out.println("\nRoot check of p1 and p2\n");

        System.out.println("Root of p1 | Expected result around -0.95704: " + p31.root(-200, 300, eps));

        System.out.println("Root of p2 | Expected result around -0.6180: " + p32.root(-100, 100, eps));

        eps = 0.00001;
        System.out.println("\nArea check of p1 and p2\n");

        System.out.println("("+p31+")\'");
        System.out.println("p1 | x0: 0.5, x1: 2.5 | Expected result around 174.588: " + p31.area(0.5, 2.5, eps));
        System.out.println("p2 | x0: -0.5, x1: 1 | Expected result around 1.2375: " + p32.area(-0.5, 1, eps));
        System.out.println("p2 | x0: 0, x1: 1 | Expected result around 0.7833: " + p32.area(0, 1, eps));
	}
	
	
	@Test
	public void moreofthequals() {
		Polynom_able p0 = new Polynom();
		Polynom_able p1 = new Polynom();
		Polynom_able p2 = new Polynom();
		Monom m0 = new Monom(1,2);
		String[][] monoms = {{"1", "x", "x^2", "0.5x^2", "0"}, {"1", "x", "x^2", "0.5x^2"}};
		for (int i = 0; i < monoms[0].length; i++) {
			p0.add(new Monom(monoms[0][i]));
		}
		for (int i = 0; i < monoms[1].length; i++) {
			p1.add(new Monom(monoms[1][i]));
		}
		assertTrue("Identical polynomial comparison", p1.equals(p0));
		p0.add(new Monom(1,1));
		assertFalse("Check Monom != Polynom_able", p1.equals(p0));
		p2.add(new Monom(monoms[1][2]));
		assertTrue("Check Monom = Polynom_able", p2.equals(m0));
		assertFalse("Check Monom != Polynom_able", p1.equals(m0));
		m0 = Monom.ZERO;
		assertFalse("Check Monom != Polynom_able", p2.equals(m0));
	}

}
