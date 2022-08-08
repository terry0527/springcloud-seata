package demo.seata.at.business.controller;


import demo.seata.at.business.service.BusinessService;
import demo.seata.common.dto.BusinessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: BusinessController
 **/
@RestController
@RequestMapping("/at")
public class BusinessController {
    
    @Autowired
    BusinessService businessService;

    @RequestMapping("/buy")
    public String handleBusiness(@RequestBody BusinessDTO businessDTO){
        return businessService.handleBusiness(businessDTO);
    }
}
