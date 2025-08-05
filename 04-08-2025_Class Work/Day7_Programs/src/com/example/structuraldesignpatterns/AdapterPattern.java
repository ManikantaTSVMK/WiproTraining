package com.example.structuraldesignpatterns;

// if you have created an application with some legacy features but those will be compatible with new features or requirement
/* for eg: you have legace xml parser but in another system it only accepts JSON input 
   so instead of rewriting the XML parser again, we create an adapter ( which is like a bridge in between) 
   that wraps the XML parser and provides a jSON like interface */
// Converts the interface of one class into another interface as per the client request or expectations, 
// It's like a bridge between incompatible classes 
// Arrays.asList() -- adapts an array to a List , JDBC drivers -- different databases are adapted to a common JDBC interface

// Target interface
interface XMLReader {
    void readXML(); // Business logic
}

// Concrete adaptee #1
class JSONReader {
    public void readJSON() {
        System.out.println("Reading JSON Data :");
    }
}

// Concrete adaptee #2
class TXTReader {
    public void readTxt() {
        System.out.println("Reading Txt Data :");
    }
}

// Adapter to match XMLReader interface for JSONReader
class JSONtoXMLAdapter implements XMLReader {

    private final JSONReader jsonReader ;

    public JSONtoXMLAdapter(JSONReader reader) {
        this.jsonReader = reader;
    }

    // Internal conversion for reading the data from XML to JSON
    @Override
    public void readXML() {
        // TODO Auto-generated method stub
        System.out.println("converting XML to JSON ..");
        jsonReader.readJSON();
    }
}

// Adapter to match XMLReader interface for TXTReader
class TXTtoXMLAdapter implements XMLReader {

    private final TXTReader txtReader ;

    public TXTtoXMLAdapter(TXTReader reader) {
        this.txtReader = reader;
    }

    @Override
    public void readXML() {
        // TODO Auto-generated method stub
        System.out.println("converting XML to Txt ..");
        txtReader.readTxt();
    }
}

public class AdapterPattern {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // Through Adapter we are reading the JSON data to xml
        XMLReader xmlReader = new JSONtoXMLAdapter(new JSONReader());
        xmlReader.readXML();

        // Through Adapter we are reading the TXT data to xml
        XMLReader txtReaderAdapter = new TXTtoXMLAdapter(new TXTReader());
        txtReaderAdapter.readXML();
    }
}
