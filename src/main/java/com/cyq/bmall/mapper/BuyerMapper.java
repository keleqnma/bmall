package com.cyq.bmall.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cyq.bmall.model.Buyer;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author CYQ
 * @date $[DATE]
 * @description
 */
@Mapper
public interface BuyerMapper extends BaseMapper<Buyer> {
    /**
     * 获取买家列表
     *
     * @param params 参数
     * @return
     */
    List<Buyer> getBuyerList(Map<String, Object> params);

    /**
     * 增加买家
     *
     * @param buyer
     */
    void save(Buyer buyer);

    /**
     * 级联删除
     *
     * @param id 主键
     */
    void deleteById(Long id);

    /**
     * 编辑买家
     *
     * @param buyer 买家
     */
    void update(Buyer buyer);

    /**
     * 通过ID获得买家
     *
     * @param id ID
     * @return 买家
     */
    Buyer getById(Long id);

    //某个时间段内下单金额前十名的用户
    ArrayList<String> bestBuyerNames(Map<String, Object> params);

    //某个时间段内下单金额前十名的用户下单的金额
    ArrayList<Float> bestBuyerValues(Map<String, Object> params);
}
