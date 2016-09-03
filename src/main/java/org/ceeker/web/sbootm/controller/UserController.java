package org.ceeker.web.sbootm.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.ceeker.web.sbootm.domain.User;
import org.ceeker.web.sbootm.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户控制器
 * 
 * @author zhangxiaoling01
 *
 */
@Api("操作用户信息")
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

	@Resource
	UserService userService;

	@ApiOperation(value = "所有用户", notes = "全部用户信息")
	@RequestMapping(value="all",method=RequestMethod.GET)
	public User all() {
		return userService.getByUserName("");
	}

	@RequestMapping(value="{id}",method=RequestMethod.GET)
	@ApiOperation(value = "获取用户", notes = "根据id获取用户信息")
	@ApiImplicitParam(name = "id", value = "用户id", required = true,dataType="Integer")
	public User get(@PathVariable int id) {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Map<String, String[]> map = req.getParameterMap();
		int a = 1 / 0;
		log.info("request params=" + map.get("id"));
		return userService.getById(id);
	}

	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true,dataType="User")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public User save(@Valid @RequestBody User user, BindingResult result) {
		if (result.hasFieldErrors()) {
			log.info("request params valid errors ={}", result.getAllErrors());
		}
		return user;
	}

	@ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true,dataType="Long")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        return "success";
    }
	
	@ApiOperation(value = "更新用户", notes = "根据user参数更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String update(@PathVariable Long id, @RequestBody User user) {
        return "success";
    }
	
	
	

}
