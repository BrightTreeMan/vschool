package com.mansh.vschool.global.dept;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mansh.vschool.custom.ResultData;
import com.mansh.vschool.exception.MSHException;
import com.mansh.vschool.utils.CommonUtils;
import com.mansh.vschool.utils.JWTUtils;
import com.mansh.vschool.utils.LogUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
*
* @author mansh
* @since 2020-11-26
*/
@Service
public class MshDeptService{
    @Resource
    private MshDeptMapper deptMapper;

    public ResultData<Integer> deptAdd(HttpServletRequest request, MshDeptEntity dept) {
        String curUser = JWTUtils.get(CommonUtils.getToken(request),"userName");
        dept.setAddDate(System.currentTimeMillis());
        dept.setAddUserName(curUser);
        dept.setModDate(System.currentTimeMillis());
        dept.setModUserName(curUser);
        try {
            int result = deptMapper.insert(dept);
            String desc = "新增部门成功";
            LogUtils.addSysLog(request,"新增部门","成功",desc);
            return new ResultData<Integer>(result).setSuccess(true);
        } catch (Exception e) {
            String desc = "新增部门失败";
            LogUtils.addSysLog(request,"新增部门","失败",desc);
            throw new MSHException("新增部门失败");
        }
    }

    public ResultData<Integer> remove(HttpServletRequest request,String deptId){
        try {
            QueryWrapper query = new QueryWrapper();
            query.eq("dept_id",deptId);
            int result = deptMapper.delete(query);
            String desc = "删除部门成功";
            LogUtils.addSysLog(request,"删除部门","成功",desc);
            return new ResultData<Integer>(result).setSuccess(true);
        } catch (Exception e) {
            String desc = "删除部门失败";
            LogUtils.addSysLog(request,"删除部门","失败",desc);
            throw new MSHException(desc);
        }
    }

    public ResultData<Integer> deptEdit(HttpServletRequest request,int deptId,String deptName,int deptParentId,int listOrder) {
        String curUser = JWTUtils.get(CommonUtils.getToken(request),"userName");
        MshDeptEntity dept = new MshDeptEntity();
        dept.setDeptId(deptId);
        dept.setDeptName(deptName);
        dept.setParentDeptId(deptParentId);
        dept.setListOrder(listOrder);
        dept.setModDate(System.currentTimeMillis());
        dept.setModUserName(curUser);

        try {
            int result = deptMapper.updateById(dept);
            String desc = "修改部门成功";
            LogUtils.addSysLog(request,"修改部门","成功",desc);
            return new ResultData<Integer>(result).setSuccess(true);
        } catch (Exception e) {
            String desc = "修改部门失败";
            LogUtils.addSysLog(request,"修改部门","失败",desc);
            throw new MSHException(desc);
        }
    }

    public ResultData<IPage> deptPage(IPage page, MshDeptSearch deptSearch) {
        return new ResultData<IPage>(deptMapper.deptPage(page, deptSearch)).setSuccess(true);
    }

    public ResultData<List<MshDeptEntity>> deptList() {
        return new ResultData<List<MshDeptEntity>>(deptMapper.selectList(null)).setSuccess(true);
    }

    public ResultData<String> deptCheck(int deptId) {
        MshDeptEntity dept = deptMapper.selectById(deptId);
        String data = "1";
        if(dept != null){
            data = "0";
        }
        return new ResultData<>(data).setSuccess(true);
    }

    public ResultData<Integer> deptOrder(int deptId) {
        return new ResultData<>(deptMapper.deptOrder(deptId)).setSuccess(true);
    }
}