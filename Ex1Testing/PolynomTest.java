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
	public void testSubMultShouldBeEqual() {
		Polynom_able p0 = new Polynom();
		Polynom_able p1 = new Polynom();
		Polynom_able p2 = new Polynom();
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
		p0.substract(p1);
		assertTrue("should be true", p1.equals(p2));
	}

	@Test
	public void testEqualsMethodShouldBeTrue() {
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
	@Test
	public void testCreationFromString(){
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
		assertFalse(p1.equals(new Polynom(p1.toString())));
		assertFalse(p1.equals(new Polynom("-4.7x^2-1.0x+6.0")));
	}

	/*@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPolynom() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testPolynomString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testF() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddPolynom_able() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddMonom() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSubstract() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testMultiplyPolynom_able() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testIsZero() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRoot() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCopy() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDerivative() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testArea() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testIteretor() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testMultiplyMonom() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testToString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetSetPointer() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetSetPointer() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetPol() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testInitFromString() {
		fail("Not yet implemented"); // TODO
	}*/

}
