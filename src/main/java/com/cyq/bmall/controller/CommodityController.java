package com.cyq.bmall.controller;

import com.cyq.bmall.model.Menu;
import com.cyq.bmall.model.Commodity;
import com.cyq.bmall.model.RestResp;
import com.cyq.bmall.service.CommodityService;
import com.cyq.bmall.vo.CommodityVO;
import com.cyq.bmall.vo.DataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author: cyq
* @date: 7/5/2019
* @description:
*/
@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @RequestMapping(value= "/tables", method = RequestMethod.POST)
    public DataTable<Commodity> tables(@RequestBody CommodityVO commodityVO){
        return commodityService.tables(commodityVO);
    }

     /**
     *  商品详情
     * @param commodityId 商品id
     * @return 商品
     */
    @RequestMapping(value ="/{commodityId}", method = RequestMethod.GET)
    public Commodity getCommodityById(@PathVariable("commodityId") Long commodityId){
        return commodityService.get(commodityId);
    }

    /**
     * 添加商品
     */
    @RequestMapping(method = RequestMethod.POST)
    public RestResp add(@RequestBody Commodity commodity) {
        commodityService.add(commodity);
        return RestResp.ok("添加成功");
    }

    /**
     * 编辑商品
     */
    @RequestMapping(method = RequestMethod.PUT)
    public RestResp edit(@RequestBody Commodity commodity) {
        commodityService.edit(commodity);

        return RestResp.ok("编辑成功");
    }

    /**
     * 删除商品
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public RestResp delete(@PathVariable("id") Long id) {
        commodityService.delete(id);

        return RestResp.ok("删除成功");
    }

}
