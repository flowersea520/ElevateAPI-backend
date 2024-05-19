package com.lxc.elevateapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.lxc.elevateapi.annotation.AuthCheck;
import com.lxc.elevateapi.common.*;
import com.lxc.elevateapi.constant.CommonConstant;
import com.lxc.elevateapi.exception.BusinessException;
import com.lxc.elevateapi.model.dto.interfaceinfo.InterfaceInfoAddRequest;
import com.lxc.elevateapi.model.dto.interfaceinfo.InterfaceInfoInvokeRequest;
import com.lxc.elevateapi.model.dto.interfaceinfo.InterfaceInfoQueryRequest;
import com.lxc.elevateapi.model.dto.interfaceinfo.InterfaceInfoUpdateRequest;
import com.lxc.elevateapi.model.entity.InterfaceInfo;
import com.lxc.elevateapi.model.entity.User;
import com.lxc.elevateapi.model.enums.InterfaceInfoStatusEnum;
import com.lxc.elevateapi.service.InterfaceInfoService;
import com.lxc.elevateapi.service.UserService;
import com.lxc.lxcapiclientsdk.client.LxcApiClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 接口管理
 *
 * @author lxc
 */
@RestController
@RequestMapping("/interfaceInfo")
@Slf4j
public class InterfaceInfoController {

	@Resource
	private InterfaceInfoService interfaceInfoService;

	@Resource
	private UserService userService;

	@Resource
	private LxcApiClient lxcApiClient;

	// region 增删改查

	/**
	 * 创建
	 *
	 * @param interfaceInfoAddRequest
	 * @param request
	 * @return
	 */
	@PostMapping("/add")
	public BaseResponse<Long> addInterfaceInfo(@RequestBody InterfaceInfoAddRequest interfaceInfoAddRequest, HttpServletRequest request) {
		if (interfaceInfoAddRequest == null) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		InterfaceInfo interfaceInfo = new InterfaceInfo();
		BeanUtils.copyProperties(interfaceInfoAddRequest, interfaceInfo);
		// 校验
		interfaceInfoService.validInterfaceInfo(interfaceInfo, true);
		User loginUser = userService.getLoginUser(request);
		interfaceInfo.setUserId(loginUser.getId());
		boolean result = interfaceInfoService.save(interfaceInfo);
		if (!result) {
			throw new BusinessException(ErrorCode.OPERATION_ERROR);
		}
		long newInterfaceInfoId = interfaceInfo.getId();
		return ResultUtils.success(newInterfaceInfoId);
	}

	/**
	 * 删除
	 *
	 * @param deleteRequest
	 * @param request
	 * @return
	 */
	@PostMapping("/delete")
	public BaseResponse<Boolean> deleteInterfaceInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
		if (deleteRequest == null || deleteRequest.getId() <= 0) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		User user = userService.getLoginUser(request);
		long id = deleteRequest.getId();
		// 判断是否存在
		InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
		if (oldInterfaceInfo == null) {
			throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
		}
		// 仅本人或管理员可删除
		if (!oldInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
			throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
		}
		boolean b = interfaceInfoService.removeById(id);
		return ResultUtils.success(b);
	}

	/**
	 * 更新
	 *
	 * @param interfaceInfoUpdateRequest
	 * @param request
	 * @return
	 */
	@PostMapping("/update")
	public BaseResponse<Boolean> updateInterfaceInfo(@RequestBody InterfaceInfoUpdateRequest interfaceInfoUpdateRequest,
													 HttpServletRequest request) {
		if (interfaceInfoUpdateRequest == null || interfaceInfoUpdateRequest.getId() <= 0) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		InterfaceInfo interfaceInfo = new InterfaceInfo();
		BeanUtils.copyProperties(interfaceInfoUpdateRequest, interfaceInfo);
		// 参数校验
		interfaceInfoService.validInterfaceInfo(interfaceInfo, false);
		User user = userService.getLoginUser(request);
		long id = interfaceInfoUpdateRequest.getId();
		// 判断是否存在
		InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
		if (oldInterfaceInfo == null) {
			throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
		}
		// 仅本人或管理员可修改
		if (!oldInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
			throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
		}
		boolean result = interfaceInfoService.updateById(interfaceInfo);
		return ResultUtils.success(result);
	}

	/**
	 * 根据 id 获取
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/get")
	public BaseResponse<InterfaceInfo> getInterfaceInfoById(long id) {
		if (id <= 0) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);
		return ResultUtils.success(interfaceInfo);
	}

	/**
	 * 获取列表（仅管理员可使用）
	 *
	 * @param interfaceInfoQueryRequest
	 * @return
	 */
	@AuthCheck(mustRole = "admin")
	@GetMapping("/list")
	public BaseResponse<List<InterfaceInfo>> listInterfaceInfo(InterfaceInfoQueryRequest interfaceInfoQueryRequest) {
		InterfaceInfo interfaceInfoQuery = new InterfaceInfo();
		if (interfaceInfoQueryRequest != null) {
			BeanUtils.copyProperties(interfaceInfoQueryRequest, interfaceInfoQuery);
		}
		QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>(interfaceInfoQuery);
		List<InterfaceInfo> interfaceInfoList = interfaceInfoService.list(queryWrapper);
		return ResultUtils.success(interfaceInfoList);
	}

	/**
	 * 分页获取列表
	 *
	 * @param interfaceInfoQueryRequest
	 * @param request
	 * @return
	 */
	@GetMapping("/list/page")
	public BaseResponse<Page<InterfaceInfo>> listInterfaceInfoByPage(InterfaceInfoQueryRequest interfaceInfoQueryRequest, HttpServletRequest request) {
//        == null 检查: 检查对象是否本身是 null，适用于前端没有传递任何对象的情况。
		if (interfaceInfoQueryRequest == null) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		InterfaceInfo interfaceInfoQuery = new InterfaceInfo();
		BeanUtils.copyProperties(interfaceInfoQueryRequest, interfaceInfoQuery);
		long current = interfaceInfoQueryRequest.getCurrent();
		long size = interfaceInfoQueryRequest.getPageSize();
		String sortField = interfaceInfoQueryRequest.getSortField();
		String sortOrder = interfaceInfoQueryRequest.getSortOrder();
		String description = interfaceInfoQuery.getDescription();
		// content 需支持模糊搜索
		interfaceInfoQuery.setDescription(null);
		// 限制爬虫
		if (size > 50) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>(interfaceInfoQuery);
		queryWrapper.like(StringUtils.isNotBlank(description), "description", description);
		// 设置查询字段的排序条件
// 如果 sortField 不为空，则按照 sortOrder 的顺序进行排序
		queryWrapper.orderBy(
//	第一个参数：表示是否应用排序。如果为 true，则应用排序；如果为 false，则不应用排序。
//第二个参数：表示排序方式。如果为 true，表示升序排序；如果为 false，表示降序排序。
//第三个参数：指定排序的字段名。
				StringUtils.isNotBlank(sortField),  // 判断是否存在排序字段
				sortOrder.equals(CommonConstant.SORT_ORDER_ASC),  // 判断排序方式是否为升序
				sortField  // 指定排序的字段名
		);
		Page<InterfaceInfo> interfaceInfoPage = interfaceInfoService.page(new Page<>(current, size), queryWrapper);
		return ResultUtils.success(interfaceInfoPage);
	}

	// endregion


	/**
	 * 发布接口
	 *
	 * @param idRequest
	 * @param request
	 * @return
	 */
	@PostMapping("/online")
//    调用自定义的切面AOP 注解， 校验是否为管理员
//     @AuthCheck(mustRole = "admin") 注解，要求调用此方法时必须要有 "admin" 的角色权限。（如果和数据库中的不符合就会抛异常)
	@AuthCheck(mustRole = "admin")
	// 如果注解抛异常，这个onlineInterfaceInfo方法是不执行的
	public BaseResponse<Boolean> onlineInterfaceInfo(@RequestBody IdRequest idRequest,
													 HttpServletRequest request) {
		if (idRequest == null || idRequest.getId() <= 0) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		// 拿到 要下线接口的 id
		Long id = idRequest.getId();
		// 根据前端传过来的id，查询接口表，这个接口是否存在
		InterfaceInfo oldInterfaceInofo = interfaceInfoService.getById(id);
		if (oldInterfaceInofo == null) {
			throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
		}

		// 判断该接口是否存在
		com.lxc.lxcapiclientsdk.model.entity.User user = new com.lxc.lxcapiclientsdk.model.entity.User();
		user.setUsername("lxcTest");
		String username = lxcApiClient.getUserNameByPost(user);
		if (StringUtils.isBlank(username)) {
			throw new BusinessException(ErrorCode.SYSTEM_ERROR, "接口验证失败");
		}

		// 根据用户传过来的id，我们修改对应的数据库，记得修改其status   0表示下线接口，1表示上线
		InterfaceInfo interfaceInfo = new InterfaceInfo();
		interfaceInfo.setId(id);
//        修改其status   0表示下线接口，1表示上线
		// 像这种多种情况，就创建一个枚举值对象
		interfaceInfo.setStatus(InterfaceInfoStatusEnum.ONLINE.getValue());

		boolean result = interfaceInfoService.updateById(interfaceInfo);
		return ResultUtils.success(result);
	}


	/**
	 * 下线接口
	 *
	 * @param idRequest
	 * @param request
	 * @return
	 */
	@PostMapping("/offline")
	//    调用自定义的切面AOP 注解， 校验是否为管理员
//     @AuthCheck(mustRole = "admin") 注解，要求调用此方法时必须要有 "admin" 的角色权限。（如果和数据库中的不符合就会抛异常)
	@AuthCheck(mustRole = "admin")
	public BaseResponse<Boolean> offlineInterfaceInfo(@RequestBody IdRequest idRequest,
													  HttpServletRequest request) {
		if (idRequest == null || idRequest.getId() <= 0) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		// 拿到 要下线接口的 id
		Long id = idRequest.getId();
		// 根据前端传过来的id，查询接口表，这个接口是否存在
		InterfaceInfo oldInterfaceInofo = interfaceInfoService.getById(id);
		if (oldInterfaceInofo == null) {
			throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
		}

		// 判断该接口是否存在
		com.lxc.lxcapiclientsdk.model.entity.User user = new com.lxc.lxcapiclientsdk.model.entity.User();
		user.setUsername("lxcTest");
		String username = lxcApiClient.getUserNameByPost(user);
		if (StringUtils.isBlank(username)) {
			throw new BusinessException(ErrorCode.SYSTEM_ERROR, "接口验证失败");
		}

		// 根据用户传过来的id，我们修改对应的数据库，记得修改其status   0表示下线接口，1表示上线
		InterfaceInfo interfaceInfo = new InterfaceInfo();
		interfaceInfo.setId(id);
//        修改其status   0表示下线接口，1表示上线
		// 像这种多种情况，就创建一个枚举值对象
		interfaceInfo.setStatus(InterfaceInfoStatusEnum.OFFLINE.getValue());

		boolean result = interfaceInfoService.updateById(interfaceInfo);
		return ResultUtils.success(result);
	}


	/**
	 *  接口调用 （先经过我们的backend后端，然后调用自定义的sdk发送请求的客户端）
	 * @param interfaceInfoInvokeRequest
	 * @param request 获取登录用户的
	 * @return
	 */

	@PostMapping("/invoke")
	public BaseResponse<Object> invokeInterfaceInfo(@RequestBody InterfaceInfoInvokeRequest interfaceInfoInvokeRequest,
													  HttpServletRequest request) {
		if (interfaceInfoInvokeRequest == null || interfaceInfoInvokeRequest.getId() <= 0) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		// 拿到 要调用接口的 id 和 请求参数
		Long id = interfaceInfoInvokeRequest.getId();
//		这个userParams就是 json字符串，所以用String类型，后面将其转化为 Java对象
		String userRequestParams = interfaceInfoInvokeRequest.getUserRequestParams();
		// 根据前端传过来的id，查询接口表，这个接口是否存在
		InterfaceInfo oldInterfaceInofo = interfaceInfoService.getById(id);
		if (oldInterfaceInofo == null) {
			throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
		}

		if (oldInterfaceInofo.getStatus() == InterfaceInfoStatusEnum.OFFLINE.getValue()) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口已关闭");
		}

		// 获取登录用户请求 ak，sk
		User loginUser = userService.getLoginUser(request);
		String accessKey = loginUser.getAccessKey();
		String secretKey = loginUser.getSecretKey();
//		这里没有用注入的sdk，而是new了这个对象（要不然总是走yml配置的ak和sk）
		LxcApiClient tempLxcClient = new LxcApiClient(accessKey, secretKey);

		// 引入 gson，将json字符串，反序列化Java对象
		Gson gson = new Gson();
		// 记住，请求参数是：{"username":"lxc"}，这个username一定要和那个 client里面的实体类user的属性名一致
		// 要不然映射不成功，一直为null
		com.lxc.lxcapiclientsdk.model.entity.User user = gson.fromJson(userRequestParams, com.lxc.lxcapiclientsdk.model.entity.User.class);


		System.out.println("Deserialized user: " + user.getUsername());  // Debugging statement

		// 调用自定义的sdk（这个sdk用于发送接口的客户端），传入前端发送过来的  实体对象，
		String userNameByPost = tempLxcClient.getUserNameByPost(user);

		return ResultUtils.success(userNameByPost);
	}
}
