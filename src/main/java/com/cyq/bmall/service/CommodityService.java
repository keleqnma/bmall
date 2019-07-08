package com.cyq.bmall.service;


import com.cyq.bmall.model.Commodity;
import com.cyq.bmall.vo.CommodityVO;
import com.cyq.bmall.vo.DataTable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
/**
* @author: cyq
* @date: 7/5/2019
* @description:
*/

public interface CommodityService { 

        /**
        * 获得自己拥有的商品
	    *
        * @return
        */
        List<Commodity> commodities();

        /**
         * 获得所有商品
         *
         * @return
         */
        List<Commodity> allCommodity();

        /**
         * 添加商品
         *
         * @param commodity 添加的商品
         */
        public void add(Commodity commodity);

        /**
         * 删除商品
         *
         * @param id
         */
        public void delete(Long id);

        /**
         * 修改商品
         *
         * @param commodity 修改的商品
         */
        public void edit(Commodity commodity);

        /**
         * 获得一个商品
         *
         * @param id 获得的商品id
         * @return
         */
        public Commodity get(Long id);

        DataTable<Commodity> tables(CommodityVO commodityVO);


}
