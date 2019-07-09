package com.cyq.bmall.service;


import com.cyq.bmall.model.Buyer;
import com.cyq.bmall.vo.BuyerVO;
import com.cyq.bmall.vo.DataTable;

import java.util.List;

/**
* @author: cyq
* @date: 7/5/2019
* @description:
*/

public interface BuyerService {

        /**
         * 获得所有买家
         *
         * @return
         */
        List<Buyer> allBuyer();

        /**
         * 添加买家
         *
         * @param buyer 添加的买家
         */
        public void add(Buyer buyer);

        /**
         * 删除买家
         *
         * @param id
         */
        public void delete(Long id);

        /**
         * 修改买家
         *
         * @param buyer 修改的买家
         */
        public void edit(Buyer buyer);

        /**
         * 获得一个买家
         *
         * @param id 获得的买家id
         * @return
         */
        public Buyer get(Long id);

        DataTable<Buyer> tables(BuyerVO buyerVO);


}
