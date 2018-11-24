package com.drww.controller.customer;

import com.drww.entity.Customer;
import com.drww.service.customer.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * className:information
 * discriptoin:
 * author:丁启斌
 * createTime:2018-11-24 14:44
 */
@Controller
@RequestMapping(value = "customer")
public class information {
    @Autowired
    private InformationService informationService;

    @ResponseBody
    @RequestMapping(value = "getTelephoneByUserName")
    public Object getTelephoneByUserName(HttpSession session){
        Object customerName = session.getAttribute("CustomerName");
        Customer customer = informationService.getTelephoneByUserName(customerName.toString());
        Map map = new HashMap();
        if (customer.getTelephone()!=null&&customer.getTelephone()!=""){
            map.put("telephone",customer.getTelephone());
        }else{
            map.put("telephone","0");
        }
        return map;
    }

}
