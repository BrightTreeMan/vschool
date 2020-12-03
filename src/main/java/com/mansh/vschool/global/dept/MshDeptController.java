package com.mansh.vschool.global.dept;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mansh.vschool.custom.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
*
* @author mansh
* @since 2020-11-26
*/
@RestController
@RequestMapping("/dept")
@Api(tags = "部门管理接口")
public class MshDeptController {
    @Resource
    private MshDeptService deptService;

    @ApiOperation("新增部门")
    @PostMapping("/deptAdd")
    public ResultData<Integer> deptAdd(HttpServletRequest request, MshDeptEntity dept){
        return deptService.deptAdd(request,dept);
    }

    @ApiOperation("删除部门")
    @ApiImplicitParam(name = "deptId",value = "部门ID",required = true,example = "1",dataTypeClass = Integer.class)
    @DeleteMapping("/deptDel/{deptId}")
    public ResultData<Integer> deptDel(HttpServletRequest request,@PathVariable("deptId") String deptId){
        return deptService.remove(request,deptId);
    }

    @ApiOperation("修改部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId",value = "部门ID",required = true,example = "1",dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "deptName",value = "部门名称",required = true,example = "名称",dataTypeClass = String.class),
            @ApiImplicitParam(name = "deptParentId",value = "父部门ID",required = true,example = "1",dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "listOrder",value = "排序ID",required = true,example = "1",dataTypeClass = Integer.class)
    })
    @PutMapping("/deptEdit/{deptId}/{deptName}/{deptParentId}/{listOrder}")
    public ResultData<Integer> deptEdit(HttpServletRequest request,@PathVariable("deptId") int deptId, @PathVariable("deptName") String deptName, @PathVariable("deptParentId") int deptParentId, @PathVariable("listOrder") int listOrder){
        return deptService.deptEdit(request,deptId,deptName,deptParentId,listOrder);
    }

    @ApiOperation("分页查询部门列表")
    @PostMapping("/deptPage")
    public ResultData<IPage> deptPage(MshDeptSearch deptSearch){
        Page page = new Page(deptSearch.getCurrent(),deptSearch.getSize());
        return deptService.deptPage(page,deptSearch);
    }

    @ApiOperation("获取部门列表-选择")
    @GetMapping("/deptList")
    public ResultData<List<MshDeptEntity>> deptList(){
        return deptService.deptList();
    }

    @ApiOperation("检测主键 0重复 1不重复")
    @ApiImplicitParam(name = "deptId",value = "部门ID",required = true,example = "1",dataTypeClass = Integer.class)
    @GetMapping("/deptCheck/{deptId}")
    public ResultData<String> deptCheck(@PathVariable("deptId") int deptId){
        return deptService.deptCheck(deptId);
    }

    @ApiOperation("获取子部门排序")
    @ApiImplicitParam(name = "deptId" ,value= "部门ID" ,required = true,example = "1",dataTypeClass = Integer.class)
    @GetMapping("/deptOrder/{deptId}")
    public ResultData<Integer> deptOrder(@PathVariable("deptId")int deptId){
        return deptService.deptOrder(deptId);
    }
}
