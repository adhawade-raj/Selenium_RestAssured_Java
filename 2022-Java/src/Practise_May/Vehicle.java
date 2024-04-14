package Practise_May;

public class Vehicle {
	int number;
    String name;
    double price;
    public Vehicle(int number,String name,double price)
    {
        super();
        this.number=number;
        this.name=name;
        this.price=price;
    }
    public int getNumber()
    {
        return number;
    }
    public void setNumber(int number)
    {
        this.number=number;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public double getPrice()
    {
        return price;
    }
    public void setPrice(double price)
    {
        this.price=price;
    }
}
