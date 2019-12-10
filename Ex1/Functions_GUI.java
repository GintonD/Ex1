package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
		//Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK}; 
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		
		StdDraw.setPenColor(Color.LIGHT_GRAY);
		for (double i = rx.get_min(); i <= rx.get_max(); i++) {
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
		}
		for (double i = ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		}
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		StdDraw.setFont(new Font("Calibri", Font.ITALIC, 14));
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		for (double i = rx.get_min(); i <= rx.get_max(); i++) {
			StdDraw.text(i, -0.30, Integer.toString(Math.toIntExact((long) i)));
		}
		for (double i = ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.text(-0.20,i, Integer.toString(Math.toIntExact((long) i)));
		}
		for (function f : FunctiosArray) {
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
		}

		
	}

	@Override
	public void drawFunctions(String json_file) 
	{
		Gson gson = new Gson();
		
	}

}
