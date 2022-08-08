package demo.seata.tcc.business.service;


import demo.seata.common.dto.BusinessDTO;

/**
 * @description: 业务服务
 **/
public interface BusinessService {

    String handleBusiness(BusinessDTO businessDTO);
}
