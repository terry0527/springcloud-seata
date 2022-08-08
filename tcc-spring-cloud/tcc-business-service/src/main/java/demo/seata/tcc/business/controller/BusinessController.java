package demo.seata.tcc.business.controller;

import demo.seata.common.dto.BusinessDTO;
import demo.seata.tcc.business.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 业务服务
 **/
@RestController
@RequestMapping("/tcc")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @RequestMapping("/buy")
    public String handleBusiness(@RequestBody BusinessDTO businessDTO){
        return businessService.handleBusiness(businessDTO);
    }
}
