package org.acme;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

@Path("user/felipe/shoppings/grocery/products")
public class ProductResource {

    @GET    
    public List<Product> getByUserAndShopping(
        @Parameter(
            description = "Set if the PanacheEntityBase will or not use the SQL Prefix as SELECT S FROM Product P WHERE",
            required = true) 
        @QueryParam("useSqlPrefix") boolean useSqlPrefix) {        

        return Product.listByUserAndShopping("felipe", "grocery", useSqlPrefix);
        
    }

    @Path("apple")
    @GET
    public Product getByPrimaryKey(
        @Parameter(
            description = "Set if the PanacheEntityBase will or not use the SQL Prefix as SELECT S FROM Product P WHERE",
            required = true) 
        @QueryParam("useSqlPrefix") boolean useSqlPrefix) {        

        return Product.getByPrimaryKey("felipe", "grocery", "apple", useSqlPrefix);
        
    }

    @PUT
    @Operation(summary = "Updates all quantities of one user and shopping")
    @Transactional
    public List<Product> updateByUserAndShopping(
        @Parameter(
            description = "Set if the PanacheEntityBase will or not use the SQL Prefix as UPDATE Product P SET",
            required = true) 
        @QueryParam("useSqlPrefix") boolean useSqlPrefix,
        @Parameter(
            description = "New quantity",
            required = true)
        @QueryParam("quantity") int quantity)
        
        {

        Product.updateByUserAndShopping("felipe", "grocery", quantity, useSqlPrefix);

        return Product.listByUserAndShopping("felipe", "grocery", useSqlPrefix);
        
    }

    @DELETE
    @Operation(summary = "Delete all rows of one user and shopping")
    @Transactional
    public List<Product> deleteByUserAndShopping(
        @Parameter(
            description = "Set if the PanacheEntityBase will or not use the SQL Prefix as DELETE FROM Product P WHERE",
            required = true) 
        @QueryParam("useSqlPrefix") boolean useSqlPrefix)
        
        {

        Product.deleteByUserAndShopping("felipe", "grocery", useSqlPrefix);

        return Product.listAll();
        
    }
    
}
