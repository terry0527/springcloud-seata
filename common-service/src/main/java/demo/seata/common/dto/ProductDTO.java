package demo.seata.common.dto;

/**
 * @description: 仓库服务DTO
 **/
public class ProductDTO {

    /**
     * 商品编号
     */
    private Long productId;

    /**
     * 扣减数量
     */
    private Integer count;

    public ProductDTO() {
    }

    public ProductDTO(Long productId, Integer count) {
        this.productId = productId;
        this.count = count;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
