package edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "products")
public class ProductList {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "product")
    public List<Product> products;

    public ProductList() {}

    public ProductList(List<Product> products) {
        this.products = products;
    }
}
