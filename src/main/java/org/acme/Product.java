package org.acme;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;

@Entity
@Table(name = "PRODUCT")
@IdClass(Product.ProductPrimaryKey.class)
public class Product extends PanacheEntityBase {

    private static final Logger LOG = LoggerFactory.getLogger(Product.class.getName());

    public static class ProductPrimaryKey implements Serializable {
        public String userName;
        public String shoppingName;
        public String productName;
    }

    @Id
    @Column(length = 20)
    public String userName;

    @Id
    @Column(length = 40)
    public String shoppingName;

    @Id
    @Column(length = 40)
    public String productName;

    public int quantity;

    public static List<Product> listByUserAndShopping(String userName, String shoppingName, boolean useSqlPrefix) {

        String query = "";

        if (useSqlPrefix) {
            query = "SELECT P FROM Product P WHERE ";
        }

        query = query + "userName = :userName AND shoppingName = :shoppingName ";

        LOG.debug("PanacheEntityBase String query=[" + query + "]");

        return list(query, Sort.by("productName"),
                Parameters.with("userName", userName).and("shoppingName", shoppingName));

    }

    public static Product getByPrimaryKey(String userName, String shoppingName, String productName,
            boolean useSqlPrefix) {

        String query = "";

        if (useSqlPrefix) {
            query = "SELECT P FROM Product P WHERE ";
        }

        query = query + "userName = :userName AND shoppingName = :shoppingName AND productName = :productName";

        LOG.debug("PanacheEntityBase String query=[" + query + "]");

        return find(query,
                Parameters.with("userName", userName).and("shoppingName", shoppingName).and("productName", productName))
                        .firstResult();

    }

    public static int updateByUserAndShopping(String userName, String shoppingName, int quantity,
            boolean useSqlPrefix) {

        String query = "";

        if (useSqlPrefix) {
            query = "UPDATE Product P SET ";
        }

        query = query + "quantity = :quantity WHERE userName = :userName AND shoppingName = :shoppingName";

        LOG.debug("PanacheEntityBase String query=[" + query + "]");

        return update(query,
                Parameters.with("userName", userName).and("shoppingName", shoppingName).and("quantity", quantity));

    }

    public static long deleteByUserAndShopping(String userName, String shoppingName, boolean useSqlPrefix) {

        String query = "";

        if (useSqlPrefix) {
            query = "DELETE FROM Product P WHERE ";
        }

        query = query + "userName = :userName AND shoppingName = :shoppingName";

        LOG.debug("PanacheEntityBase String query=[" + query + "]");

        return delete(query,
                Parameters.with("userName", userName).and("shoppingName", shoppingName));

    }

}
