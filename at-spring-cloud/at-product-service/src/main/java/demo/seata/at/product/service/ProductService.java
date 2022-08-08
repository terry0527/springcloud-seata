package demo.seata.at.product.service;


public interface ProductService {

    /**
     * 扣减库存
     *
     * @param productId 商品 ID
     * @param count    扣减数量
     * @throws Exception 扣减失败时抛出异常
     */
    boolean reduceStock(Long productId, Integer count) throws Exception;

    Integer getPriceById(Long productId);
}
