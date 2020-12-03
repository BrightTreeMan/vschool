package com.mansh.vschool.global.role.role;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mansh.vschool.custom.ResultData;
import com.mansh.vschool.exception.MSHException;
import com.mansh.vschool.global.role.role_menu.MshRoleMenuEntity;
import com.mansh.vschool.global.role.role_menu.MshRoleMenuMapper;
import com.mansh.vschool.utils.CommonUtils;
import com.mansh.vschool.utils.JWTUtils;
import com.mansh.vschool.utils.LogUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
*
* @author mansh
* @since 2020-11-26
*/
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MshRoleService{
    @Resource
    private MshRoleMapper roleMapper;

    @Resource
    private MshRoleMenuMapper roleMenuMapper;

    public ResultData<IPage> rolePage(IPage page, MshRoleSearch roleSearch) {
        Page pageResult = roleMapper.rolePage(page,roleSearch);
        for(int i=0;i<pageResult.getRecords().size();i++){
            MshRoleEntity role = (MshRoleEntity) pageResult.getRecords().get(i);
            QueryWrapper query = new QueryWrapper();
            query.eq("role_id",role.getRoleId());
            List<MshRoleMenuEntity> list =  roleMenuMapper.selectList(query);
            int[] roleMenuId = new int[list.size()];
            for(int j=0;j<list.size();j++){
                roleMenuId[j] = list.get(j).getMenuId();
            }
            role.setRoleMenuId(roleMenuId);
        }
        return new ResultData<>(pageResult).setSuccess(true);
    }

    @Transactional
    public ResultData<Integer> roleAdd(HttpServletRequest request, MshRoleEntity role) {
        String curUser = JWTUtils.get(CommonUtils.getToken(request),"userName");
        role.setAddDate(System.currentTimeMillis());
        role.setAddUserName(curUser);
        role.setModDate(System.currentTimeMillis());
        role.setModUserName(curUser);
        try {
            //插入role表
            roleMapper.insert(role);
            //插入role_menu表
            for(int i=0;i<role.getRoleMenuId().length;i++){
                MshRoleMenuEntity roleMenu = new MshRoleMenuEntity();
                roleMenu.setMenuId(role.getRoleMenuId()[i]);
                roleMenu.setRoleId(role.getRoleId());
                roleMenuMapper.insert(roleMenu);
            }
            String desc = "用户["+curUser+"],新增角色成功,被操作角色["+role.getRoleName()+"]";
            LogUtils.addSysLog(request,"新增角色","成功",desc);
            return new ResultData<>(1).setSuccess(true);
        } catch (Exception e) {
            String desc = "用户["+curUser+"],新增角色失败";
            LogUtils.addSysLog(request,"新增角色","失败",desc);
            throw new MSHException(desc);
        }
    }

    @Transactional
    public ResultData<Integer> roleEdit(HttpServletRequest request,int roleId,String roleName,String roleDesc,int[] roleMenuId) {
        String curUser = JWTUtils.get(CommonUtils.getToken(request),"userName");
        MshRoleEntity role = new MshRoleEntity();
        role.setModDate(System.currentTimeMillis());
        role.setModUserName(curUser);
        role.setRoleId(roleId);
        role.setRoleDesc(roleDesc);
        role.setRoleName(roleName);
        try {
            roleMapper.updateById(role);
            QueryWrapper query = new QueryWrapper();
            query.eq("role_id",roleId);
            roleMenuMapper.delete(query);
            for(int i=0;i<roleMenuId.length;i++){
                MshRoleMenuEntity roleMenu = new MshRoleMenuEntity();
                roleMenu.setMenuId(roleMenuId[i]);
                roleMenu.setRoleId(roleId);
                roleMenuMapper.insert(roleMenu);
            }
            String desc = "用户["+curUser+"],修改角色成功,被操作角色ID["+roleId+"]";
            LogUtils.addSysLog(request,"修改角色","成功",desc);
            return new ResultData<>(1).setSuccess(true);
        } catch (Exception e) {
            String desc = "用户["+curUser+"],修改角色失败,被操作角色ID["+roleId+"]";
            LogUtils.addSysLog(request,"修改角色","失败",desc);
            throw new MSHException(desc);
        }
    }

    @Transactional
    public ResultData<Integer> remove(HttpServletRequest request,int roleId) {
        String curUser = JWTUtils.get(CommonUtils.getToken(request),"userName");
        try {
            QueryWrapper query = new QueryWrapper();
            query.eq("role_id",roleId);
            roleMenuMapper.delete(query);
            roleMapper.deleteById(roleId);
            String desc = "用户["+curUser+"],删除角色成功,被操作角色ID["+roleId+"]";
            LogUtils.addSysLog(request,"删除角色","成功",desc);
            return new ResultData<>(1).setSuccess(true);
        } catch (Exception e) {
            String desc = "用户["+curUser+"],删除角色成功,被操作角色ID["+roleId+"]";
            LogUtils.addSysLog(request,"删除角色","失败",desc);
            throw new MSHException(desc);
        }
    }
}