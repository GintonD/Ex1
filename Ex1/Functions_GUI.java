package Ex1;


import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.google.gson.*;


public class Functions_GUI implements functions 
{
	private ArrayList<function> FunctiosArray;
	
	
	/**
	 * Init Consturctor
	 */
	public Functions_GUI()
	{
		this.FunctiosArray=new ArrayList<function>();
	}
	
	
	
	@Override
	public boolean add(function arg0) 
	{
		return this.FunctiosArray.add(arg0);
	}

	
	@Override
	public boolean addAll(Collection<? extends function> arg0) 
	{
		return this.FunctiosArray.addAll(arg0);
	}

	
	@Override
	public void clear() 
	{
		this.FunctiosArray.clear();
		
	}

	
	@Override
	public boolean contains(Object arg0) 
	{
		
		return this.FunctiosArray.contains(arg0);
	}

	
	@Override
	public boolean containsAll(Collection<?> arg0) 
	{
		
		return this.FunctiosArray.containsAll(arg0);
	}

	
	@Override
	public boolean isEmpty() 
	{
		return this.FunctiosArray.isEmpty();
	}

	
	@Override
	public Iterator<function> iterator() 
	{
		
		return this.FunctiosArray.iterator();
	}

	
	@Override
	public boolean remove(Object arg0) 
	{
		
		return this.FunctiosArray.remove(arg0);
	}

	
	@Override
	public boolean removeAll(Collection<?> arg0) 
	{
		return this.FunctiosArray.removeAll(arg0);
	}

	
	@Override
	public boolean retainAll(Collection<?> arg0) 
	{
		return this.FunctiosArray.retainAll(arg0);
	}

	
	@Override
	public int size() 
	{
		return this.FunctiosArray.size();
	}

	
	@Override
	public Object[] toArray() 
	{
		return this.FunctiosArray.toArray();
	}

	
	@Override
	public <T> T[] toArray(T[] arg0) 
	{
		return this.FunctiosArray.toArray(arg0);
	}
	
	
	
	public String toString()
	{
		String str = "";
		for(int i = 0; i<FunctiosArray.size(); i++)
			str =str+"f"+i+"="+ FunctiosArray.get(i) + ".\n";
		return str;
	}
	

	@Override
	public void initFromFile(String file) throws IOException 
	{
		FileReader fr = new FileReader(file);
		BufferedReader MyBuffer = new BufferedReader(fr);
		
		String st; 
		while ((st = MyBuffer.readLine()) != null)
		{
			try
			{
				this.add(new Polynom(st));
			}
			catch (Exception e) 
			{
				this.add(new ComplexFunction(st));
			}
		}
		
		
	}

	@Override
	public void saveToFile(String file) throws IOException 
	{
	try
	{
		FileWriter fw = new FileWriter(file);
		BufferedWriter MyBuffer = new BufferedWriter(fw);
		for(int i = 0; i<this.size(); i++)
		{
			MyBuffer.write(FunctiosArray.get(i).toString()+"\n");
		}
		MyBuffer.close();
	}
	
	catch (Exception e) 
	{
		throw new IOException("Error: cant save or creat the file");
	}
		
	}

	
	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) 
	{
		 
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		
		StdDraw.setPenColor(Color.LIGHT_GRAY);
		for (double i = rx.get_min(); i <= rx.get_max(); i++) 
		{
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
		}
		
		for (double i = ry.get_min(); i <= ry.get_max(); i++)
		{
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		}
		
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		StdDraw.setFont(new Font("Calibri", Font.ITALIC, 14));
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		for (double i = rx.get_min(); i <= rx.get_max(); i++) 
		{
			StdDraw.text(i, -0.30, Integer.toString(Math.toIntExact((long) i)));
		}
		for (double i = ry.get_min(); i <= ry.get_max(); i++) 
		{
			StdDraw.text(-0.20,i, Integer.toString(Math.toIntExact((long) i)));
		}
		for (function f : FunctiosArray) 
		{
			
			double step = (Math.abs(rx.get_min())+Math.abs(rx.get_max()))/resolution;
			int R = (int)(Math.random()*256);
			int G = (int)(Math.random()*256);
			int B= (int)(Math.random()*256);
			Color color = new Color(R, G, B);
			StdDraw.setPenColor(color);
			for (double i = rx.get_min(); i < rx.get_max(); i+=step)
			{
				StdDraw.line(i, f.f(i), i+step, f.f(i+step));
			}
			System.out.println(color+"    f(x)= "+f);
		}

		
	}

	@Override
	public void drawFunctions(String json_file) 
	{
		Gson Mygson = new Gson();
		
		DrawFunctionsArguments dfa = new DrawFunctionsArguments(); //object that contain the argument that need for the drawFunctions.
		
	
		try 
		{
			FileReader fr = new FileReader(json_file);

			dfa= Mygson.fromJson(fr, DrawFunctionsArguments.class);
			
			
			Iterator<JsonElement> iterX = dfa.Range_X.iterator();
			Iterator<JsonElement> iterY = dfa.Range_Y.iterator();

			Range rx = new Range(iterX.next().getAsDouble(), iterX.next().getAsDouble());
			Range ry = new Range(iterY.next().getAsDouble(), iterY.next().getAsDouble());
			
			
			drawFunctions(dfa.Width, dfa.Height, rx, ry, dfa.Resolution);
			
		} 		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
		
		
	}
	
	
	/**
	 * class that have been built to contain the arguments for the drawFunctions. 
	 * @author Ginton
	 *
	 */
	public class DrawFunctionsArguments  
	{
		int Width;
		int Height;
		JsonArray Range_X;
		JsonArray Range_Y;
		int Resolution;
		
	/*	public String toString() 
		{
			String str="width=" +this.Width+ "\nheight="+this.Height + "\nRange_X="+this.Range_X+ "\nRange_Y="+this.Range_Y+"\nresolution="+this.Resolution;
			return str;
			
		}*/
		
	}
	

}
