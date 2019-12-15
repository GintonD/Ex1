# Ex1


Readme: 
Authors : dolev and ginton. 
General Explanation: 
This explanation is general and to understand the whole picture please read ALL the notes in the code! 
 
We wrote 4 clasess :  Monom,Polynom,ComplexFunction and Functions_GUI that together represent the A Graph that can show complicated functions. 
The polynom contain by the HashMap data structure.
the functions in the gui contain by ArrayList data structure.

Monom class: 
This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative),  
 see: https://en.wikipedia.org/wiki/Monomial  
  The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. Etc.. 
The  monom string should look like this -  >  “  +/- ax^b  “ .  
(These characters are invalid :”   ) , * ,  ( , “ 
 
Polynom class: 
This class represents a Polynom based on the monom class and contain whole polynom in the HashMap data structure. 
The  polyom string should look like this   ->  “+/- a1x^b  +/- a2x^b “  - >  +10+3x^3-4x^4+5x^5 
(with no backspace between the monoms and all highlights of the monom.) 
 These are some of the function classes can doing: 
• add - there is two options to add function add Monom/Polynom(using Polynom_able) . • derivative • f(x) • multiply • subtract • root - Finds the point of intersection of the polynomial in the given range • area - Returns the value of the area in the given range • copy • is equal • is zer0

ComplexFunction class:

This class represents a ComplexFunction based on the monom and polynom class.
some of the arithmatic opreation that we can use between the functions:
Complex: f(g(x))
Max: Max(g,f)
Min: min(g,f)
Plus: +
Multiply: *
Divide: \

Attention 1!!: there is fake Operation called None. the only way shuld he can use is by this shape: None(f,0) (f - current function)
for example: None(2x^2+2,Times(3,2x)) will throw exception.

Attention 2!!:
the polynom is organize like this: plus(+3.1 +2.4x^2 -1.0x^4) (powers is growing from left to right)
and NOT like that:                 plus(-1.0x^4 +2.4x^2 +3.1)

Attention3!!: there is always - or + in the coefficient of the monom.




