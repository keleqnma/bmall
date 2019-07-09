package com.cyq.bmall.service.impl;

import com.cyq.bmall.mapper.BuyerMapper;
import com.cyq.bmall.mapper.ResourceMapper;
import com.cyq.bmall.mapper.UserMapper;
import com.cyq.bmall.model.Buyer;
import com.cyq.bmall.service.BuyerService;
import com.cyq.bmall.util.SessionUtil;
import com.cyq.bmall.util.id.IdUtil;
import com.cyq.bmall.vo.BuyerVO;
import com.cyq.bmall.vo.DataTable;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CYQ
 * @date $[DATE]
 * @description
 */
@Service("buyerService")
@Transactional
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private BuyerMapper buyerMapper;

   @Override
    public List<Buyer> allBuyer(){
       return buyerMapper.getBuyerList(new HashMap<>());
   }

    @Override
    public void add(Buyer buyer) {
        buyer.setId(IdUtil.generateId());
        buyerMapper.save(buyer);
    }

    @Override
    public Buyer get(Long buyerId) {
        Buyer buyer = buyerMapper.getById(buyerId);
        return buyer;
    }

    @Override
    public void edit(Buyer buyer) {
        buyerMapper.update(buyer);
    }

    @Override
    public void delete(Long id) {
        buyerMapper.deleteById(id);
    }

    @Override
    public DataTable<Buyer> tables(BuyerVO buyerVO) {
        PageHelper.offsetPage(buyerVO.getStart(), buyerVO.getLength());

        List<Buyer> commodities = buyerMapper.getBuyerList(new HashMap<>());

        DataTable<Buyer> tables = new DataTable<>();
        tables.setRecordsTotal(((Page) commodities).getTotal());
        tables.setRecordsFiltered(tables.getRecordsTotal());
        tables.setDraw(buyerVO.getDraw());
        tables.setData(commodities);
        return tables;
    }
}
