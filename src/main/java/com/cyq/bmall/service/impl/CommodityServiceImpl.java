package com.cyq.bmall.service.impl;

import com.cyq.bmall.mapper.*;
import com.cyq.bmall.mapper.CommodityMapper;
import com.cyq.bmall.model.Menu;
import com.cyq.bmall.model.Commodity;
import com.cyq.bmall.model.Commodity;
import com.cyq.bmall.service.CommodityService;
import com.cyq.bmall.util.AppConst;
import com.cyq.bmall.util.SessionUtil;
import com.cyq.bmall.util.id.IdUtil;
import com.cyq.bmall.vo.DataTable;
import com.cyq.bmall.vo.CommodityVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author CYQ
 * @date $[DATE]
 * @description
 */
@Service("commodityService")
@Transactional
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Commodity commodity) {
        commodity.setId(IdUtil.generateId());
        commodityMapper.save(commodity);
    }

    @Override
    public Commodity get(Long commodityId) {
        Commodity commodity = commodityMapper.getById(commodityId);
        return commodity;
    }

    @Override
    public void edit(Commodity commodity) {
        commodityMapper.update(commodity);
    }

    @Override
    public void delete(Long id) {
        // PID的外键为级联删除
        commodityMapper.deleteById(id);
    }


    @Override
    public List<Commodity> commodities() {

        Map<String, Object> params = new HashMap<>();
        Long currUid = SessionUtil.getCurrUid();
        if (currUid != null) {
            params.put("userId", currUid);// 查自己有权限的商品
        }
        return commodityMapper.getCommodityList(params);
    }

    @Override
    public List<Commodity> allCommodity() {
        return commodityMapper.getCommodityList(new HashMap<>());
    }

    @Override
    public DataTable<Commodity> tables(CommodityVO commodityVO) {

        Map<String, Object> params = new HashMap<>();
        Long currUid = SessionUtil.getCurrUid();
        if (currUid != null && !userMapper.getUserRoleIds(currUid).contains(AppConst.USER_TYPE_ADMIN.longValue() ) ) {
            params.put("userId", currUid);// 查自己有权限的商品
        }

        PageHelper.offsetPage(commodityVO.getStart(), commodityVO.getLength());
        List<Commodity> commodities = commodityMapper.getCommodityList(params);

        //for(Commodity commodity:commodities){
        //    commodity.setCategoryName(categoryMapper.getCategoryCommodities(commodity.getId()).getName());
        //}
        DataTable<Commodity> tables = new DataTable<>();
        tables.setRecordsTotal(((Page) commodities).getTotal());
        tables.setRecordsFiltered(tables.getRecordsTotal());
        tables.setDraw(commodityVO.getDraw());
        tables.setData(commodities);
        return tables;
    }
}
