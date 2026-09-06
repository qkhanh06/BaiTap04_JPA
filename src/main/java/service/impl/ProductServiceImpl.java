package service.impl;

import java.util.List;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import model.Product;
import service.ProductService;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao =
            new ProductDaoImpl();

    @Override
    public void insert(Product product) {
        productDao.insert(product);
    }

    @Override
    public void edit(Product product) {
        Product oldProduct =
                productDao.findById(product.getProductId());

        if (oldProduct == null) {
            return;
        }

        oldProduct.setProductName(product.getProductName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setStatus(product.getStatus());
        oldProduct.setCategory(product.getCategory());

        if (product.getImages() != null
                && !product.getImages().isBlank()) {
            oldProduct.setImages(product.getImages());
        }

        productDao.update(oldProduct);
    }

    @Override
    public void delete(int id) {
        try {
            productDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public List<Product> findAll(int page, int pagesize) {
        return productDao.findAll(page, pagesize);
    }

    @Override
    public List<Product> findLatest(int limit) {
        return productDao.findLatest(limit);
    }

    @Override
    public int count() {
        return productDao.count();
    }
}
