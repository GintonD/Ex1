package Ex1Testing;


import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.Polynom_able;



public class MonomTest 
{

	@Test
	public void testStringConstructor()
	{
		Monom m2 = new Monom("+x^4");
		//Checking the function toString+constructor
		System.out.println("m2 to string= "+m2.toString());
		assertEquals("+1.0x^4", m2.toString());
		
		

		Monom m4= new Monom(-7,1);
		Monom m5= new Monom(m4);
		Monom m6= new Monom(0,0);
		Monom m7= new Monom(9,1);
		Monom m8= new Monom(m7.get_coefficient(),m5.get_power());
		Monom m9= new Monom(-7,4);
		Monom m10= new Monom(5,2);
		
		//Checking the function  constructor

		System.out.println(m8+"*"+m7+"= ");
		
		m8.multipy(m7);
		System.out.println(m8);
		assertTrue(m8.equals(new Monom("+81.0x^2")));

		
		System.out.println("m10= "+m10);
		System.out.println("(m10)'= "+m10.derivative());//Checking the function derivative_Monom
		assertTrue(m10.derivative().equals(new Monom("+10.0x")));
		
		System.out.println("(m10)''= "+m10.derivative().derivative());
		assertTrue(m10.derivative().derivative().equals(new Monom("+10.0")));
		
	
	}
	
	@Test
	public void test1()
	{
			System.out.println("*****  Test1:  *****");
			ArrayList<Monom> monoms = new ArrayList<Monom>();
			monoms.add(new Monom(0,5));
			monoms.add(new Monom(-1,0));
			monoms.add(new Monom(-1.3,1));
			monoms.add(new Monom(-2.2,2));
			
			for(int i=0;i<monoms.size();i++) {
				Monom m = new Monom(monoms.get(i));
				String s = m.toString();
				Monom m1 = new Monom(s);
				boolean e = m.equals(m1);
				System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"  \teq: "+e);
				assertTrue(m.equals(m1));
				
				
			}
	}
			
			
			
			
			@Test
			public void test2() 
			{
				System.out.println("*****  Test2:  *****");
				String[] monoms = {"2", "-x","-3.2x^2","0"};
				double [] answers = {2.0, -1.0,-12.8,0.0};
				for(int i=0;i<monoms.length;i++) {
					Monom m = new Monom(monoms[i]);
					String s = m.toString();
					m = new Monom(s);
					double fx = m.f(i);
					
					assertTrue(fx==answers[i]);
					
					System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
					
					Monom m1 = new Monom("6x^6");
					ComplexFunction cf = new ComplexFunction(Operation.Plus,new Polynom("3x^6"),new Polynom("3x^6"));
					assertTrue(m1.equals(cf));
					Polynom p = new Polynom("3x^6+3x^6-1+1");

					assertTrue(m1.equals(p));

					assertTrue(m1.equals(new Polynom("+6x^6")));
					
				}
		
	
	}
			
			
			
			

}

