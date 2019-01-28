package org.launchcode.salessamurai.models.data;

import org.launchcode.salessamurai.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public interface ProductDAO extends CrudRepository<Product, Integer> {

    //Need a list of all the products for a certain user "products"
    public ArrayList<Product> products = new ArrayList<>();
    //products = dao.findall()

    //find by user_id
    List<Product> findByUserId(int id);

    ArrayList<Product> findByName(String value);
}

