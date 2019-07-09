package com.cyq.bmall.controller;

import com.cyq.bmall.model.Buyer;
import com.cyq.bmall.model.RestResp;
import com.cyq.bmall.service.BuyerService;
import com.cyq.bmall.vo.BuyerVO;
import com.cyq.bmall.vo.DataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* @author: cyq
* @date: 7/5/2019
* @description:
*/
@RestController
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @RequestMapping(value= "/tables", method = RequestMethod.POST)
    public DataTable<Buyer> tables(@RequestBody BuyerVO buyerVO){
        return buyerService.tables(buyerVO);
    }

     /**
     *  买家详情
     * @param buyerId 买家id
     * @return 买家
     */
    @RequestMapping(value ="/{buyerId}", method = RequestMethod.GET)
    public Buyer getBuyerById(@PathVariable("buyerId") Long buyerId){
        return buyerService.get(buyerId);
    }

    /**
     * 添加买家
     */
    @RequestMapping(method = RequestMethod.POST)
    public RestResp add(@RequestBody Buyer buyer) {
        buyerService.add(buyer);
        return RestResp.ok("添加成功");
    }

    /**
     * 编辑买家
     */
    @RequestMapping(method = RequestMethod.PUT)
    public RestResp edit(@RequestBody Buyer buyer) {
        buyerService.edit(buyer);

        return RestResp.ok("编辑成功");
    }

    /**
     * 删除买家
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public RestResp delete(@PathVariable("id") Long id) {
        buyerService.delete(id);

        return RestResp.ok("删除成功");
    }

}
