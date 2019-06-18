package com.example.omaradel.nweave;

/**
 * Created by omar adel on 7/4/2018.
 */

public class product {
    private String productid;
    private String name;
    private String description;
   private double price;
    public product(String productid,String name,String description,double price)
    {
        this.productid=productid;
        this.name=name;
        this.description=description;
        this.price=price;

    }
    public String getProductid()
    {return productid;}
    public String getName()
    {return name;}
    public String getDescription()
    {return description;}
    public double getPrice()
    {return price;}
}
