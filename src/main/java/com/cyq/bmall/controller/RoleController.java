package com.cyq.bmall.controller;

import com.cyq.bmall.model.RestResp;
import com.cyq.bmall.model.Role;
import com.cyq.bmall.service.RoleService;
import com.cyq.bmall.vo.DataTable;
import com.cyq.bmall.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色控制器
 *
 * @author yangfan
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    /**
     * 添加角色
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public RestResp add(@RequestBody Role role) {
        roleService.add(role);
        return RestResp.ok("添加成功！");
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public RestResp edit(@RequestBody Role role) {
        roleService.edit(role);
        return RestResp.ok("更新成功！");
    }

    @RequestMapping(value = "/tables", method = RequestMethod.POST)
    public DataTable<Role> tables(@RequestBody RoleVO roleVO) {
        return roleService.tables(roleVO);
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public List<Role> tree() {
        return roleService.tree();
    }

    /**
     * get role by id
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
    public Role getById(@PathVariable("roleId") Long roleId) {

        return roleService.get(roleId);
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/{roleId}", method = RequestMethod.DELETE)
    public RestResp delete(@PathVariable("roleId") Long roleId) {
        roleService.delete(roleId);

        return RestResp.ok("删除成功！");
    }


    /**
     * 授权
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/grant", method = RequestMethod.POST)
    public RestResp grant(@RequestBody Role role) {

        roleService.grant(role);

        return RestResp.ok("授权成功！");
    }


}
