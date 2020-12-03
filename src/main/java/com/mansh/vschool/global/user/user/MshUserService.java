package com.mansh.vschool.global.user.user;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mansh.vschool.custom.ParamConst;
import com.mansh.vschool.custom.ResultData;
import com.mansh.vschool.exception.MSHException;
import com.mansh.vschool.global.role.role.MshRoleEntity;
import com.mansh.vschool.global.role.role.MshRoleMapper;
import com.mansh.vschool.global.user.user_role.MshUserRoleEntity;
import com.mansh.vschool.global.user.user_role.MshUserRoleMapper;
import com.mansh.vschool.utils.CacheUtils;
import com.mansh.vschool.utils.CommonUtils;
import com.mansh.vschool.utils.JWTUtils;
import com.mansh.vschool.utils.LogUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
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
* @since 2020-10-12
*/
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MshUserService{
    @Resource
    private MshUserMapper userMapper;

    @Resource
    private MshUserRoleMapper userRoleMapper;

    @Resource
    private MshRoleMapper roleMapper;

    public ResultData<Integer> userAdd(HttpServletRequest request, MshUserEntity user) {
        String curUser = JWTUtils.get(CommonUtils.getToken(request),"userName");
        user.setAddDate(System.currentTimeMillis());
        user.setAddUserName(curUser);
        user.setModDate(System.currentTimeMillis());
        user.setModUserName(curUser);
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String encodPwd = new SimpleHash(Sha256Hash.ALGORITHM_NAME,user.getPassword(),salt, ParamConst.HASH_ITERATIONS).toString();
        user.setSalt(salt);
        user.setPassword(encodPwd);
        try {
            int result = userMapper.insert(user);
            String desc = "用户["+curUser+"],新增用户成功,被操作用户["+user.getUserName()+"]";
            LogUtils.addSysLog(request,"新增用户","成功",desc);
            CacheUtils.reload("user");
            return new ResultData<Integer>(result).setSuccess(true);
        } catch (Exception e) {
            String desc = "用户["+curUser+"],新增用户失败,被操作用户["+user.getUserName()+"]";
            LogUtils.addSysLog(request,"新增用户","失败",desc);
            throw new MSHException(desc);
        }
    }

    public ResultData<List<MshUserEntity>> userList(){
        List<MshUserEntity> list = userMapper.selectList(null);
        return new ResultData(list).setSuccess(true);
    }

    public ResultData<IPage> userPage(IPage page, MshUserSearch userSearch) {
        IPage pageResult = userMapper.userPage(page, userSearch);
        for(int i=0;i<pageResult.getRecords().size();i++){
            MshUserEntity user = (MshUserEntity) pageResult.getRecords().get(i);
            QueryWrapper query = new QueryWrapper();
            query.eq("user_id",user.getUserId());
            List<MshUserRoleEntity> list =  userRoleMapper.selectList(query);
            int[] userRoleId = new int[list.size()];
            for(int j=0;j<list.size();j++){
                userRoleId[j] = list.get(j).getRoleId();
            }
            user.setUserRoleId(userRoleId);
        }
        return new ResultData<>(pageResult).setSuccess(true);
    }

    public ResultData<String> userCheck(String userName) {
        QueryWrapper query = new QueryWrapper();
        query.eq("user_name",userName);
        MshUserEntity user = userMapper.selectOne(query);
        String data = "1";
        if(user != null){
            data = "0";
        }
        return new ResultData<>(data).setSuccess(true);
    }

    public ResultData<Integer> resetPwd(HttpServletRequest request,int userId) {
        String curUser = JWTUtils.get(CommonUtils.getToken(request),"userName");
        MshUserEntity user = userMapper.selectById(userId);
        String encodPwd = new SimpleHash(Sha256Hash.ALGORITHM_NAME,"111111",user.getSalt(),ParamConst.HASH_ITERATIONS).toString();
        user.setPassword(encodPwd);
        user.setModDate(System.currentTimeMillis());
        user.setModUserName(curUser);
        try {
            int result = userMapper.updateById(user);
            String desc = "用户["+curUser+"],重置密码成功,被操作用户["+getUserNameById(userId)+"]";
            LogUtils.addSysLog(request,"重置密码","成功",desc);
            return new ResultData<>(result).setSuccess(true);
        } catch (Exception e) {
            String desc = "用户["+curUser+"],重置密码失败,被操作用户["+getUserNameById(userId)+"]";
            LogUtils.addSysLog(request,"重置密码","失败",desc);
            throw new MSHException(desc);
        }
    }

    public ResultData<Integer> userLock(HttpServletRequest request,int userId,String state) {
        String curUser = JWTUtils.get(CommonUtils.getToken(request),"userName");
        MshUserEntity mshUser = new MshUserEntity();
        mshUser.setUserId(userId);
        mshUser.setState("0".equals(state)?"1":"0");
        try {
            int result = userMapper.updateById(mshUser);
            if("0".equals(state)){
                String desc = "用户["+curUser+"],冻结用户成功,被操作用户["+getUserNameById(userId)+"]";
                LogUtils.addSysLog(request,"冻结用户","成功",desc);
            }else{
                String desc = "用户["+curUser+"],解冻用户成功,被操作用户["+getUserNameById(userId)+"]";
                LogUtils.addSysLog(request,"解冻用户","成功",desc);
            }
            return new ResultData<>(result).setSuccess(true);

        } catch (Exception e) {
            String desc = "";
            if("0".equals(state)){
                desc = "用户["+curUser+"],冻结用户失败,被操作用户["+getUserNameById(userId)+"]";
                LogUtils.addSysLog(request,"冻结用户","失败",desc);
            }else{
                desc = "用户["+curUser+"],解冻用户失败,被操作用户["+getUserNameById(userId)+"]";
                LogUtils.addSysLog(request,"解冻用户","失败",desc);
            }
            throw new MSHException(desc);
        }
    }

    public ResultData<List<MshRoleEntity>> userRoleList() {
        return new ResultData<>(roleMapper.selectList(null)).setSuccess(true);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public ResultData<Integer> userRole(HttpServletRequest request,int userId,int[] userRoleId){
        String curUser = JWTUtils.get(CommonUtils.getToken(request),"userName");
        String userName = getUserNameById(userId);
        try {
            QueryWrapper query = new QueryWrapper();
            query.eq("user_id",userId);
            int result = userRoleMapper.delete(query);
            for(int i=0;i<userRoleId.length;i++){
                MshUserRoleEntity userRole = new MshUserRoleEntity();
                userRole.setRoleId(userRoleId[i]);
                userRole.setUserId(userId);
                userRoleMapper.insert(userRole);
            }
            String desc = "用户["+curUser+"],分配角色成功,被操作用户["+userName+"]";
            LogUtils.addSysLog(request,"分配角色","成功",desc);
            return new ResultData<>(result).setSuccess(true);
        } catch (Exception e) {
            String desc = "用户["+curUser+"],分配角色失败,被操作用户["+userName+"]";
            LogUtils.addSysLog(request,"分配角色","失败",desc);
            throw new MSHException(desc);
        }
    }

    public ResultData userEdit(HttpServletRequest request,int userId,int deptId,String nickName,String phone) {
        String curUser = JWTUtils.get(CommonUtils.getToken(request),"userName");
        MshUserEntity user = new MshUserEntity();
        user.setUserId(userId);
        user.setDeptId(deptId);
        user.setNickName(nickName);
        user.setPhone(phone);
        user.setModDate(System.currentTimeMillis());
        user.setModUserName(curUser);
        try {
            int result = userMapper.updateById(user);
            String desc = "用户["+curUser+"],修改用户成功,被操作用户["+getUserNameById(userId)+"]";
            LogUtils.addSysLog(request,"修改用户","成功",desc);
            return new ResultData(result).setSuccess(true);
        } catch (Exception e) {
            String desc = "用户["+curUser+"],修改用户失败,被操作用户["+getUserNameById(userId)+"]";
            LogUtils.addSysLog(request,"修改用户","失败",desc);
            throw new MSHException(desc);
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public ResultData<Integer> remove(HttpServletRequest request,int userId){
        String curUser = JWTUtils.get(CommonUtils.getToken(request),"userName");
        String userName = getUserNameById(userId);
        try {
            QueryWrapper query = new QueryWrapper();
            query.eq("user_id",userId);
            userRoleMapper.delete(query);
            userMapper.delete(query);
            String desc = "用户["+curUser+"],删除用户成功,被操作用户["+userName+"]";
            LogUtils.addSysLog(request,"删除用户","成功",desc);
            return new ResultData<>().setSuccess(true);
        } catch (Exception e) {
            String desc = "用户["+curUser+"],删除用户失败,被操作用户["+userName+"]";
            LogUtils.addSysLog(request,"删除用户","失败",desc);
            throw new MSHException(desc);
        }
    }

    public ResultData<Integer> changePwd(HttpServletRequest request,String userName,String password,String oldPassword) {
        int result = 1;
        JSONObject user = CacheUtils.user().getJSONObject(userName);
        String eqPwd = new SimpleHash(Sha256Hash.ALGORITHM_NAME,oldPassword,user.getStr("salt"),ParamConst.HASH_ITERATIONS).toString();
        if(StrUtil.equals(eqPwd,user.getStr("password"))){
            String encodPwd = new SimpleHash(Sha256Hash.ALGORITHM_NAME,password,user.getStr("salt"),ParamConst.HASH_ITERATIONS).toString();
            MshUserEntity userEntity = userMapper.selectById(user.getInt("userId"));
            userEntity.setPassword(encodPwd);
            userMapper.updateById(userEntity);
            String desc = "用户["+userName+"],修改密码成功";
            LogUtils.addSysLog(request,"修改密码","成功",desc);
            result = 0;
        }
        return new ResultData<>(result).setSuccess(true);
    }

    private String getUserNameById(int userId){
        MshUserEntity user = userMapper.selectById(userId);
        return user==null?"":user.getUserName();
    }

}