package Ex1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;
import java.util.HashMap;


import Ex1.Monom;


/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Ginton Durlacher & Dolev Brender
 *
 */
public class Polynom implements Polynom_able
{
	private HashMap<Integer, Monom> pol; // Hashmap contain all the monoms of the polynom.
	private Set pointerSet ;
	private Map.Entry entry1;
	private Iterator iterator1 ; //Temp Iterator, Set, Map.Entry pointers for handle the hashmap
	private Iterator iterator2 ;
	private Iterator iterator3 ;

	
	/** Empty Constructor
	 * 
	 * Zero (empty polynom)
	 */
	public Polynom() 
	{
		pol  = new HashMap<Integer, Monom>() ;
	}
	
	
	
	
	/** String constructor
	 * 
	 * take a Polynom string and make it Polynom object
	 *  
	 * Highlights:
	 * 
	 * -ALL what we write in the MONOM String Constructor
	 * and
	 * init a Polynom from a String such as:
	 * {"x", "3+1.4X^3-34x"}
	 * 
	 *   
	 * example for Not allowed string format and symbol is:
	 * "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))" - using: *,),(
	 * "x^9 +x^8 +x^6" - using backspace.
	 * 
	 * 
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) 
	{
		pol  = new HashMap<Integer, Monom>() ;
		Monom montemp = null;
		if((s.contains("("))  || (s.contains(")")) || (s.contains("*") || (s.contains(" ")))) 
			throw new IllegalArgumentException("The string is illigal :\n"
					+ " -the string cant be from the format (p1)*(p2)\n"
					+ " -cant contain '*' like the format monom a*x^n \n"
					+ " -cant contain Backspaces."); 

			

		String [] monomArray= s.split("(?=[+-])"); //split to monoms array.

		for(int i=0;i<monomArray.length;i++)
		{
			montemp= new Monom(monomArray[i]);
			this.add(montemp);
		}
		
	}
	
	
	
	
	
	/**
	 * return the value of the polynom-f(x) for the given x.
	 * 
	 * @param x: given x
	 */
	@Override
	public double f(double x) 
	{
		Double res=0.0;
		iterator1=this.iteretor();
		Monom montemp = null;
		while(iterator1.hasNext())
		{
			this.entry1=(Map.Entry) iterator1.next();
			montemp = (Monom) this.entry1.getValue();
			res= res+(montemp.f(x));
		}
		return res;
	}
	
	
	
	
	
	/**
	 * 
	 * Add the given polynom to the current polynom.
	 * 
	 * @param p1: given polynom
	 * 
	 */
	@Override
	public void add(Polynom_able p1) 
	{
		iterator1=p1.iteretor();
		Monom montemp = null;
		while(iterator1.hasNext())
		{
			this.entry1=(Map.Entry) iterator1.next();
			montemp = (Monom) this.entry1.getValue();
			this.add(montemp);

			
		}
	}
	
	
	
	
	
	/**
	 * 
	 * Add the given monom to the current polynom.
	 * 
	 * @param m1: given monom
	 * 
	 */
	@Override
	public void add(Monom m1) 
	{
		Monom montemp = null;
		if (!pol.containsKey(m1.get_power()))
			pol.put(m1.get_power(), m1);
		else
		{
				montemp = new Monom(pol.get(m1.get_power()));
				montemp.add(m1);
				pol.replace(m1.get_power(), montemp);

		}
		
		if(this.isZero())
			this.getPol().clear();
		
	}
	
	
	
	
	/**
	 * 
	 * subtract the given polynom from to the current polynom.
	 * 
	 * @param p1: given polynom
	 * 
	 */
	@Override
	public void substract(Polynom_able p1) 
	{
		Polynom p2 =(Polynom) p1.copy();
		this.iterator1=p2.iteretor();
		Monom montemp = null;
		while(iterator1.hasNext())
		{
			this.entry1=(Map.Entry) iterator1.next();
			montemp = (Monom) this.entry1.getValue();
			montemp.multipy(new Monom(-1.0,0)); // multiply by minus
			this.add(montemp);
			
		}
	
		
	}

	
	
	
	
	
	/**
	 * this method multiplies this polynom with a given Polynom_able.
	 * 
	 * @param p1 - given Polynom_able.
	 * 
	 */
	@Override
	public void multiply(Polynom_able p1)
	{
		this.iterator1= null;
		this.iterator2= p1.iteretor();
		Monom montemp = null;
		Monom montemp2 = null;
		Polynom temPolynom =null;
		
		ArrayList<Monom> multMonomArray = new ArrayList<Monom>();
		
		while(iterator2.hasNext())
		{
			this.entry1 = (Map.Entry) this.iterator2.next();
			montemp2= (Monom) this.entry1.getValue();
			temPolynom =(Polynom)this.copy();
			temPolynom.multiply(montemp2);
			
			iterator1=temPolynom.iteretor();
			while(iterator1.hasNext())
			{
				this.entry1 = (Map.Entry) this.iterator1.next();
				montemp= (Monom) this.entry1.getValue();
				multMonomArray.add(montemp);
			}
			
			
			
		}	
		
		this.getPol().clear();
		for(int i=0;i<multMonomArray.size();i++)
			this.add(multMonomArray.get(i));
	}
	
	
	
	
	
	
	/**
	 * @param p1 - given Polynom_able
	 * 
	 * @return true if the polynoms are equal.
	 * 
	 */
	@Override
	//public boolean equals(Polynom_able p1) 
		public boolean equals (Object p1)
	{
		
		//if(p1 instanceof Polynom) - true to this time i dont got answer from the techers if  i need it.
		Polynom p11= (Polynom) p1;
		if(p11.isZero() && this.isZero())
			return true;
		else if(p11.getPol().size() != this.getPol().size())
			return false;
		else
		{
			iterator1=this.iteretor();
			Monom montemp = null;
			while(iterator1.hasNext())
			{
				this.entry1=(Map.Entry) iterator1.next();
				montemp = (Monom) this.entry1.getValue();
				if(!p11.getPol().get(montemp.get_power()).equals(montemp))
					return false;
				
			}
		}
		return true;	
			
	}
	
	
	
	
	
	/**
	 * Check if the polynom equal '0'.
	 * 
	 * 
	 * @return True if equal and False if not equal.
	 */
	@Override
	public boolean isZero() 
	{
		if(this.getPol().isEmpty())
			return true;
		
		else
		{
			iterator3=this.iteretor();
			Monom montemp = null;
			while(iterator3.hasNext())
			{
				this.entry1=(Map.Entry) iterator3.next();
				montemp = (Monom) this.entry1.getValue();
				if(montemp.get_coefficient()!=0)
					return false;
			}
		}
		
		return true;
	}
	
	
	
	
	/** return an approximated value (root) for this (cont.) function
	 * QUATE FROM THE INTERFACE
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, else should throws Exception 
	 * computes f(x') such that:
	 * 	(i) x0<=x'<=x1 && 
	 * 	(ii) |f(x')|<eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps>0 (positive) representing the epsilon range the solution should be within.
	 * 
	 * @return an approximated value (root) for this (cont.) function 
	 */
	@Override
	public double root(double x0, double x1, double eps)
	{	//based on the "bisection method" algorithm 
		double mid;
		if(this.f(x1) * this.f(x0)>0)
			throw new ArithmeticException("illegal f(x1)*f(x0) > 0 in the bisection method");
		
		else if(Math.abs(x0-x1)<eps) // * 	(ii) |f(x')|<eps
			return ((x0+x1)/2);
		
		else if(this.f(x0)==0)    //f(x)=0 --- meaning of root
			return x0;
		
		else if(this.f(x1)==0)
			return x1;

		mid=(x0+x1)/2;				//* 	(i) x0<=x'<=x1 &&
		
		if(this.f(mid)*this.f(x0)>=0)
			return root(mid,x1,eps);
		else
			return root(x0,mid,eps);
	}
	

	
	
	
	
	
	/**
	 * create a deep copy of this polynom
	 * @return Polynom_able copy of this polynom 
	 * 
	 * 
	 */
	@Override
	public Polynom_able copy() 
	{	
		Polynom p1 =  new Polynom();
		iterator1=this.iteretor();
		Monom montemp = null;
		while(iterator1.hasNext())
		{
			this.entry1=(Map.Entry) iterator1.next();
			montemp = (Monom) this.entry1.getValue();
			p1.add(new Monom(montemp.get_coefficient(),montemp.get_power()));
		}
		return p1;
	}

	
	
	
	
	
	/**
     * @return the polynom derivative.
     * 
     *  
     *  
     */
	@Override
	public Polynom_able derivative() 
	{	Polynom p1 =  new Polynom();
		Polynom p2 =(Polynom) this.copy(); 
		this.iterator1=p2.iteretor();
		Monom montemp = null;
		while(iterator1.hasNext())
		{
			this.entry1=(Map.Entry) iterator1.next();
			montemp = (Monom) this.entry1.getValue();
			p1.add(montemp.derivative());
		}
		return p1;
	}

	
	
	
	
	/**
	 * Compute a Riman's integral from x0 to x1 in eps steps. 
	 * @param x0 starting pooint
	 * @param x1 end point
	 * @param eps positive step value
	 * @return the approximated area above X-axis below this function bounded in the range of [x0,x1]
	 */
	@Override
	public double area(double x0, double x1, double eps)
	{
		double temp = 0, sum = 0;
		
		if(eps<=0  || Math.abs(x1-x0)<eps)
			throw new ArithmeticException("the epsilon that defined isn't iiligal");
		
		else if(x1<x0)
			return 0.0;
			//{
			//temp=x1;
			//x1=x0;
			//x0=temp;
			//}
			
		
		while(x0< x1)
		{
			if(f(x0) > 0)     // the approximated area JUST above X-axis 
				sum = sum + eps*f(x0);	
			
			x0 = x0 + eps;
		}
		
		return sum;
	}

	
	
	
	
	
	
	/**
	 * @return iterator of the polynom.
	 * 
	 * 
	 * 
	 */
	@Override
	public Iterator<Monom> iteretor() 
	{
		this.setSetPointer(this.getPol().entrySet());
		return this.getSetPointer().iterator();
	}
	
	
	
	
	
	/**
	 * this method multiplies this polynom with a given monom.
	 * 
	 * 
	 * @param m1- given monom.
	 */
	@Override
	public void multiply(Monom m1)
	{
		this.iterator1= this.iteretor();
		Monom montemp = null;
		ArrayList<Monom> multMonomArray = new ArrayList<Monom>();
		
		
		while(iterator1.hasNext())
		{
			this.entry1 = (Map.Entry) this.iterator1.next();
			montemp= (Monom) this.entry1.getValue();
			montemp.multipy(m1);
			multMonomArray.add(montemp);
		}
		
		this.getPol().clear();
		for(int i=0;i<multMonomArray.size();i++)
			this.add(multMonomArray.get(i));
		
	}
	
	
	
	
	
	/** Make a string that representing the polynom.
	 *  @return string representation of this polynom.
	 *  
	 *  
	 *  
	 *  
	 */
	public String toString() 
	{
		if (this.isZero())
		return "0";
		Monom montemp = null;
		String ans = "";
		iterator1=this.iteretor();
		while(iterator1.hasNext())
		{
			this.entry1 = (Map.Entry) this.iterator1.next();
			montemp= (Monom) this.entry1.getValue();
			if (montemp.get_coefficient()!=0)
			ans=ans+montemp;
		}
		return ans;
	}
	
	
	
	
	
	/** SetPointer Getter
	 * 
	 * @return pointer set
	 * 
	 */
	public Set getSetPointer ()
	{
		return this.pointerSet;
	}
	
	
	
	/** SetPointer Setter
	 * 
	 * 
	 * 
	 */
	public void setSetPointer (Set s)
	{
		 this.pointerSet =s;
	}
	
	
	
	/** HashMap Polynom Getter
	 * 
	 * @return hashmap that contain the current polynom
	 * 
	 */
	public HashMap<Integer, Monom> getPol() 
	{
		return this.pol;
	}




	@Override
	public function initFromString(String s)
	{
		// TODO Auto-generated method stub
		return new Polynom(s);
	}
	
}
