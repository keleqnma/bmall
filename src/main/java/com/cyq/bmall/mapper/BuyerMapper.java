package com.cyq.bmall.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cyq.bmall.model.Buyer;
import org.apache.ibatis.annotations.Mapper;

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
}
