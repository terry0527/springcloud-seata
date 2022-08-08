package demo.seata.common.dto;

/**
 * @description: 账户服务DTO
 **/
public class AccountDTO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 扣减金额
     */
    private Integer amount;

    public AccountDTO() {
    }

    public AccountDTO(Long userId, Integer amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
