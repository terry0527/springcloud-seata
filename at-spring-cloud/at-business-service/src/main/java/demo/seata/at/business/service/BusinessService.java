package demo.seata.at.business.service;


import demo.seata.common.dto.BusinessDTO;

public interface BusinessService {

    String handleBusiness(BusinessDTO businessDTO);
}
