package com.cyq.bmall.service.impl;

import com.cyq.bmall.mapper.ResourceMapper;
import com.cyq.bmall.mapper.RoleMapper;
import com.cyq.bmall.mapper.UserMapper;
import com.cyq.bmall.model.Role;
import com.cyq.bmall.service.RoleService;
import com.cyq.bmall.util.SessionUtil;
import com.cyq.bmall.util.id.IdUtil;
import com.cyq.bmall.vo.DataTable;
import com.cyq.bmall.vo.RoleVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public void add(Role role) {
        role.setId(IdUtil.generateId());
        roleMapper.save(role);

        // 刚刚添加的角色，赋予给当前的用户
        userMapper.saveRoles(SessionUtil.getCurrUid(), Collections.singletonList(role.getId()));
    }

    @Override
    public Role get(Long roleId) {
        Role role = roleMapper.getById(roleId);
        List<Long> resourceIds = resourceMapper.getRoleResourceIds(roleId);
        List<String> resourceNames = resourceMapper.getRoleResourceNames(roleId);

        role.setResourceIds(resourceIds);
        role.setResourceNames(resourceNames);
        return role;
    }

    @Override
    public void edit(Role role) {
        roleMapper.update(role);
    }


    @Override
    public List<Role> treeGrid() {
        Map<String, Object> params = new HashMap<>();
        Long currUid = SessionUtil.getCurrUid();
        if (currUid != null) {
            params.put("userId", currUid);// 查自己有权限的角色
        }

        List<Role> roles = roleMapper.getRoleList(params);

        roles.forEach(role -> {
            List<Long> resourceIds = resourceMapper.getRoleResourceIds(role.getId());
            List<String> resourceNames = resourceMapper.getRoleResourceNames(role.getId());

            role.setResourceIds(resourceIds);
            role.setResourceNames(resourceNames);
        });
        return roles;
    }

    @Override
    public void delete(Long id) {
        // PID的外键为级联删除
        roleMapper.deleteById(id);
    }


    @Override
    public List<Role> roles() {

        Map<String, Object> params = new HashMap<>();
        Long currUid = SessionUtil.getCurrUid();
        if (currUid != null) {
            params.put("userId", currUid);// 查自己有权限的角色
        }


        return roleMapper.getRoleList(params);
    }

    @Override
    public List<Role> allRole() {
        return roleMapper.getRoleList(new HashMap<>());
    }

    @Override
    public void grant(Role role) {
        roleMapper.deleteRoleResources(role.getId());

        if (!role.getResourceIds().isEmpty()) {
            roleMapper.saveRoleResources(role.getId(), role.getResourceIds());
        }
    }

    @Override
    public DataTable<Role> tables(RoleVO roleVO) {
        PageHelper.offsetPage(roleVO.getStart(), roleVO.getLength());

        List<Role> roles = roleMapper.getRoleList(new HashMap<>());
        DataTable<Role> tables = new DataTable<>();
        tables.setRecordsTotal(((Page) roles).getTotal());
        tables.setRecordsFiltered(tables.getRecordsTotal());
        tables.setDraw(roleVO.getDraw());
        tables.setData(roles);
        return tables;
    }

    @Override
    public List<Role> tree() {

        Map<String, Object> params = new HashMap<>();

        List<Role> roles = roleMapper.getRoleList(params);



        return roles;
    }

}
