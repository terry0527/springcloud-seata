package demo.seata.saga.product.service;


/**
 * ProductService
 *
 */
public interface ProductService {

    Boolean reduceStock(Long productId, Integer count) throws Exception;

    Boolean compensateStock(Long productId, Integer count) throws Exception;

    Integer getPriceById(Long productId);
}
