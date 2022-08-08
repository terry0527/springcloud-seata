package demo.seata.tcc.in.controller;



import demo.seata.tcc.in.service.IInAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 收钱方
 **/
@RestController
@RequestMapping("/inAccount")
public class InAccountController {

    @Autowired
    IInAccountService inAccountService;

    @PostMapping(value = "/try")
    public boolean inTry(@RequestParam("inId") String inId, @RequestParam("amount") String amount){
        return inAccountService.inTry(inId, amount);
    }

    @PostMapping(value = "/try2")
    public boolean inTry2(@RequestParam("inId") String inId, @RequestParam("amount") String amount){
        return inAccountService.inTry2(inId, amount);
    }

    @PostMapping(value = "/reset/{number}")
    public boolean reset(@PathVariable("number") int number){
        return inAccountService.reset(number);
    }

}
