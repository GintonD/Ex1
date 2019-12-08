package Ex1;

public class ComplexFunction implements complex_function 
{
	Operation op;
    private function left;
    private function right;
    
 //******************  Constructors  ****************  
    /**
     * Regular constructor
     * @param o operation
     * @param l left
     * @param r right
     */
	public ComplexFunction(Operation o,function l,function r )
	{
	this.op=o;
	this.left=l.copy();
	this.right=r.copy();		
	}
    
    
    

	@Override
	public double f(double x)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public function initFromString(String s)
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	@Override
	/**
	 * doing a DeepCopy to the function.
	 * 
	 */
	public function copy() 
	{
		function l = this.left.copy();
		function r = this.right.copy();
		return new ComplexFunction(this.op,l,r);
	}
	
	
	
	/**
	 * Create from the function to String.
	 * 
	 */
	public String toString() 
	{
		String str= this.op + "("+ this.left.toString()+","+this.right.toString()+")";
		return str;
		
	}
	
	
	
//********************* Arithmetic Operation************
	@Override
	public void plus(function f1)
	{
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Plus;
		
	}

	@Override
	public void mul(function f1) 
	{
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Times;
		
	}

	@Override
	public void div(function f1)
	{
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Divid;
		
	}

	@Override
	public void max(function f1)
	{
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Max;
		
	}

	@Override
	public void min(function f1) 
	{
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Min;
		
	}

	@Override
	public void comp(function f1) 
	{
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Comp;
		
	}
	
	
	
//******************* Getters ****************************
	@Override
	public function left()
	{
		
		return this.left;
	}

	
	@Override
	public function right() 
	{
		
		return this.right;
	}

	
	@Override
	public Operation getOp() 
	{
		
		return this.op;
	}

}
