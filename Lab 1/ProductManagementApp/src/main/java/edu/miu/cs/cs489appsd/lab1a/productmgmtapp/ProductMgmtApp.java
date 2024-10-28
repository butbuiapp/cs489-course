package edu.miu.cs.cs489appsd.lab1a.productmgmtapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model.Product;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model.ProductList;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMgmtApp {

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("3128874119", "Banana", LocalDate.of(2023, 1, 24), 124, 0.55),
                new Product("2927458265", "Apple", LocalDate.of(2022, 12, 9), 18, 1.09),
                new Product("9189927460", "Carrot", LocalDate.of(2023, 3, 31), 89, 2.99)
        );

        printProducts(products);
    }

    private static void printProducts(List<Product> products) {
        // sort by name asc
        List<Product> sortedProducts = products.stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());

        // JSON Format
        System.out.println("Printed in JSON format:");
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            String jsonOutput = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sortedProducts);
            System.out.println(jsonOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // XML Format
        System.out.println("\nPrinted in XML format:");
        try {
            XmlMapper xmlMapper = new XmlMapper();
            // LocalDate support
            xmlMapper.registerModule(new JavaTimeModule());
            xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);

            ProductList productList = new ProductList(sortedProducts);

            String xmlOutput = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(productList);
            System.out.println(xmlOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // CSV Format (using stream API)
        System.out.println("\nPrinted in CSV format:");
//        String csvOutput = sortedProducts.stream()
//                .map(p -> String.join(",", p.getProductId(), p.getName(), p.getDateSupplied().toString(),
//                        String.valueOf(p.getQuantityInStock()), String.valueOf(p.getUnitPrice())))
//                .collect(Collectors.joining("\n"));
//        System.out.println(csvOutput);

        try {
            CsvMapper csvMapper = new CsvMapper();
            csvMapper.registerModule(new JavaTimeModule());
            csvMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            CsvSchema csvSchema = CsvSchema.builder()
                    .addColumn("productId")
                    .addColumn("name")
                    .addColumn("dateSupplied")
                    .addColumn("quantityInStock")
                    .addColumn("unitPrice")
                    .build();
            String csvOutput = csvMapper.writer(csvSchema).writeValueAsString(sortedProducts);
            System.out.println(csvOutput);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
