package Practise_May;

public class College {
	 int id,cNo,pcode;
	 String name,addr;
	public College(int id, int cNo, int pcode, String name, String addr)
	 {

	this.id = id;
	this.cNo = cNo;
	this.pcode = pcode;
	this.name = name;
	this.addr = addr;
	}
	public int getId() 
	{
	return id;
	}
	public void setId(int id) 
	{
	this.id = id;
	}
	public int getContactNo() 
	{
	return cNo;
	}
	public void setContactNo(int cNo) 
	{
	this.cNo = cNo;
	}
	public int getPincode() 
	{
	return pcode;
	}
	public void setPincode(int pcode) 
	{
	this.pcode = pcode;
	}
	public String getName() 
	{
	return name;
	}
	public void setName(String name) 
	{
	this.name = name;
	}
	public String getAddress() 
	{
	return addr;
	}
	public void setAddress(String addr) 
	{
	this.addr = addr;
	}
	}

