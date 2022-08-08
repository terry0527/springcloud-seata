package demo.seata.saga.business.controller;

import cn.dmego.seata.common.dto.BusinessDTO;
import demo.seata.saga.business.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BusinessController
 *
 */
@RestController
@RequestMapping("/saga")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @RequestMapping("/buy")
    public String handlerBusiness(@RequestBody BusinessDTO businessDTO) {
        return businessService.handlerBusiness(businessDTO);
    }
}
