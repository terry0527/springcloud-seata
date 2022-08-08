package demo.seata.saga.business.service;

import cn.dmego.seata.common.dto.BusinessDTO;

/**
 * BusinessService
 *
 */
public interface BusinessService {

    String handlerBusiness(BusinessDTO businessDTO);
}
