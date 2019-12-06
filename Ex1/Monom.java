
package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * 
 * @author Ginton Durlacher & Dolev Brender
 *
 */
public class Monom implements function
{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	
	public static Comparator<Monom> getComp()
	{
		return _Comp;
	}
	
	
	
	/**
	 * Normal constructor
	 *
	 * @param a coefficient
	 * @param b power
	 */
	public Monom(double a, int b)
	{
		this.set_coefficient(a);
		this.set_power(b);
	}
	
	
	
	/**
	 * ByMonom constructor
	 * @param ot  Monom
	 */
	public Monom(Monom ot)
	{
		this(ot.get_coefficient(), ot.get_power());
	}
	
	
	/** Coefficient Getter
	 * 
	 * @return Monom coefficient
	 */
	public double get_coefficient() 
	{
		return this._coefficient;
	}
	
	
	/** Power Getter
	 * 
	 * @return Monom Power
	 */
	public int get_power() 
	{
		return this._power;
	}
	
	
	
	/** 
	 * The method returns the derivative of this monom
	 * 
	 * @return derivative of monom
	 */
	public Monom derivative()
	{
		if(this.get_power()==0)
		{
			return getNewZeroMonom();
		}
		
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	
	
	
	
	/**
	 * Calculate value of f(x)(monom) in the current x. 
	 * @param x
	 * @return  value at given x.
	 */
	public double f(double x)
	{
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	
	
	/**
	 * Check if the monom equal '0'.
	 * @return True if equal and False if not equal.
	 */
	public boolean isZero() 
	{
		return this.get_coefficient() == 0;
	}
	// ***************** add your code below **********************
	
	
	
	/**
	 * String constructor
	 * take a Monom string and make it Monom object
	 * @param s input string
	 * 
	 * 
	 * the alowd format of Monom is: ax^n.
	 * when:
	 * a - real number
	 * n- natural number
	 * 
	 * example for Not allowed string format and symbol is:
	 *  "*",  ")",  "(",  " ".
	 *  "(2x^2-4.5)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"
	 *  
	 *  Attention: 
	 *  if you put a non-integer power the program cast it automaticly to integer!
	 *  example: power =2.5 wil be power=2.
	 *  
	 *  
	 */
	public Monom(String s)
	{
		if((s.contains("("))  || (s.contains(")")) || (s.contains("*") ))
			throw new IllegalArgumentException("The string is illigal : the string cant be from the format monom a*x^n. \n please remove the '*' or the '()'!!"); 
		s=s.toLowerCase();
		
		if (!s.contains("x")) //power=0
		{
			this.set_coefficient(Double.parseDouble(s));
			this.set_power(0);	
		}
		
		else
		{
			if(!s.contains("x^")) //power =1
			{
				this.set_power(1);

				if(s.charAt(0) == 'x' || s.substring(0, 2).equals("+x")) //2 option of represent when power=1
				{
					this.set_coefficient(1);	
					
				}
				
				
				
				else if(s.substring(0, 2).equals("-x"))
				{
					this.set_coefficient(-1);
					
				}
				
				else
				{
					this.set_coefficient(Double.parseDouble(s.substring(0, s.length()-1)));
				}
			}	
		
			else //power >1
			{
				if(s.charAt(0) == 'x' || s.substring(0, 2).equals("+x"))
				{	
					this.set_coefficient(1);
					if(s.charAt(0) == 'x')
						this.set_power(Integer.parseInt(s.substring(2)));
					else
						this.set_power(Integer.parseInt(s.substring(3)));
					
				}
				
				else if(s.substring(0, 2).equals("-x"))
				{
					this.set_coefficient(-1);
					this.set_power(Integer.parseInt(s.substring(3)));
					
				}
				
				else
				{
					String[] monExp = s.split("x");
					this.set_coefficient(Double.parseDouble(monExp[0]));
					if(monExp[1].substring(1).contains("."))
						throw new RuntimeException("ERR the power of Monom should not be double, got: "+monExp[1].substring(1));
					else	
					this.set_power(Integer.parseInt(monExp[1].substring(1)));
					
				}
				
				
			}	
			
		}

		
	}
	
	
	
	
	
	/**
	 * Add moonom m to the current monom
	 * @param m - monom m.
	 */
	public void add(Monom m) 
	{
		if(this.get_power() != m.get_power())
		{
	         throw new ArithmeticException("Error: is not the same monom"); 
	    }
	    else
	    {
	    	this.set_coefficient(this.get_coefficient() + m.get_coefficient());
	    }
	}
	
	
	
	
	
	/**
	 *multipy the current monom  by the monom m
	 * @param m - monom m.
	 */
	public void multipy(Monom d) 
	{
		this.set_coefficient(this.get_coefficient() * d.get_coefficient());
		this.set_power(this.get_power() + d.get_power());
	}
	
	
	
	
	/** Make a string that representing monom.
	 *  @return string representation of this monom.
	 *  
	 */
	public String toString() 
	{
		String ans = "";
		
		if(this.get_coefficient()==0)
			return "0";
		else
		{
			if(Math.abs(this.get_coefficient())!=1)
				ans=ans+this.get_coefficient();
			
			else if(this.get_coefficient() ==-1)
				ans=ans+"-1.0";
				
			else if(this.get_coefficient()==1)
				ans=ans+"1.0";
		
		
			if(this.get_power()!=0)
			{
				if (this.get_power()==1)
					ans=ans+"x";
				else
					ans=ans+"x^"+this.get_power();
			}
		
		}
			
		if(ans.charAt(0)!='-' && ans.charAt(0)!='+') //when there is no +\- before the monom
			ans="+"+ans;
		return ans;
	}


	
	
	
	
	
	/**
	 * this function comparing this monom with given monom.
	 * @param m - given monom.
	 * @return true if given monom equals this monom.
	 */
	public boolean equals(Monom m)   // maybe we need to use Object here and instanceof!!
	{
		
		if( ((m.get_coefficient() == this.get_coefficient()) || ((Math.abs(m.get_coefficient() - this.get_coefficient()) < EPSILON))  ) && (m.get_power()==this.get_power()))
			return true;
		
		else if((m.get_coefficient() == this.get_coefficient()) && (this.get_coefficient() == 0))
			return true;
		else
			return false;
		
	}

	//****************** Private Methods and Data *****************
	
	/** Coefficient Setter
	 * 
	 * @param a -Coefficient
	 * 
	 */
	private void set_coefficient(double a)
	{
		
		this._coefficient = a;
	}
	
	
	
	
	/** Power Setter
	 * 
	 * @param p -Power
	 * 
	 */
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	
	
	
	/**
	 * 
	 * @return Monom that equal 0.
	 */
	private static Monom getNewZeroMonom() 
	{
		return new Monom(ZERO);
	}
	
	
	private double _coefficient; 
	private int _power;

	@Override
	public function initFromString(String s) 
	{
		// TODO Auto-generated method stub
		return new Monom(s);
	}



	@Override
	public function copy()
	{
		// TODO Auto-generated method stub
		return new Monom(this.get_coefficient(),this.get_power());
	}
	
	
}
