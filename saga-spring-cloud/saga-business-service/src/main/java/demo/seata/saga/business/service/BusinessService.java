package demo.seata.saga.business.service;

import demo.seata.common.dto.BusinessDTO;

/**
 * BusinessService
 *
 */
public interface BusinessService {

    String handlerBusiness(BusinessDTO businessDTO);
}
