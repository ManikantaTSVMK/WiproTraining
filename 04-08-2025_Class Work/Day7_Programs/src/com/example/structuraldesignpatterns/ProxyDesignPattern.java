package com.example.structuraldesignpatterns;


/* 
 *  When you need to control access to an object (security checks)
 * when implementing lazy loading for objects which are crucial  
 * 
 * */


interface Image
{
void display();	

}


// REal Image class(crucial / Expensive)
final class RealImage implements Image{
	
	private String filename;
	
	public RealImage(String filename)
	{
		this.filename = filename;
		loadImage();
	}
	private void loadImage()
	{
	System.out.println("Loading Image :" + filename);
	}
	
	public void  display()
	{
	System.out.println("Displaying Image :" + filename);
	}
}

class ProxyImage implements Image
{
private RealImage realImage;
private String filename;
public ProxyImage( String filename) {
	
	this.filename = filename;
}
@Override
public void display() {
	if(realImage == null)
	{
		realImage = new RealImage(filename); //  this is lazy loading
		
	}
	
	realImage.display();
}





}


//client 
public class ProxyDesignPattern {

	public static void main(String[] args) {
			Image image = new ProxyImage("photo.jpg");
			image.display(); // Load and display  -- might be here it will take some to load because it's first time loaded as a lazy initialization
			image.display();// uses already a loaded image  -- second time when you are calling then it uses the same loaded objects 

	}

}

// Enables lazy loading as well as reducing the memory usage 
//Controlling access permissions securely 

