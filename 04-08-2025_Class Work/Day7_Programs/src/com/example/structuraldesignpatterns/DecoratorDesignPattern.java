package com.example.structuraldesignpatterns;


/* Mostly used for dynamic feature Enhancements*/
/* Eg: Applying different text styles such as bold, italics or underline dynamically , Instead of creating separate classes for each combination we use decorators*/
interface Text{
	
	String format();
}

// concrete component -- 1step
class PlainText implements Text
{

	@Override
	public String format() {
		// TODO Auto-generated method stub
		return "This is a plain text ";
	}
	

}

// Decorator Class
abstract class TextDecorator implements Text
{
protected Text text;
public TextDecorator(Text text)
{
this.text = text;	
}
}

// Concrete Decorators (Implementation)
class BoldText extends TextDecorator
{
public BoldText(Text text)
{
	
super(text);

}

@Override
public String format() {
	// TODO Auto-generated method stub
	return "<b>" + text.format() + "</b>";
}


}


class ItalicsText extends TextDecorator
{
public ItalicsText(Text text)
{
	
super(text);

}

@Override
public String format() {
	// TODO Auto-generated method stub
	return "<I>" + text.format() + "</I>";
}


}


//Client Code 
public class DecoratorDesignPattern {

	public static void main(String[] args) {
		Text myText = new PlainText();
		//myText = new BoldText(myText);
		myText = new ItalicsText(myText);
		
		System.out.println(myText.format());

	}

}
