package com.lxc.elevateapi.service.impl;

import java.util.Date;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxc.elevateapi.common.ErrorCode;
import com.lxc.elevateapi.exception.BusinessException;
import com.lxc.elevateapi.mapper.InterfaceInfoMapper;
import com.lxc.elevateapi.model.entity.InterfaceInfo;

import com.lxc.elevateapi.service.InterfaceInfoService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author lxc
 * @description 针对表【interface_info(接口信息)】的数据库操作Service实现
 * @createDate 2024-05-13 15:29:57
 */
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
		implements InterfaceInfoService {

	@Override
	public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
		Long id = interfaceInfo.getId();
		String name = interfaceInfo.getName();
		String description = interfaceInfo.getDescription();
		String url = interfaceInfo.getUrl();
		String requestHeader = interfaceInfo.getRequestHeader();
		String responseHeader = interfaceInfo.getResponseHeader();
		Integer status = interfaceInfo.getStatus();
		String method = interfaceInfo.getMethod();
		Long userId = interfaceInfo.getUserId();
		Date createTime = interfaceInfo.getCreateTime();
		Date updateTime = interfaceInfo.getUpdateTime();
		Integer isDeleted = interfaceInfo.getIsDeleted();

		if (interfaceInfo == null) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}

		// 创建时，所有参数必须非空
		if (add) {
			// 这里判断校验，是对前端会填的字段进行校验
			if (StringUtils.isAnyBlank(name, description, url, requestHeader, responseHeader, method
			)) {
				throw new BusinessException(ErrorCode.PARAMS_ERROR);
			}
		}
		if (StringUtils.isNotBlank(description) && description.length() > 819) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口描述过长");
		}
		if (StringUtils.isNotBlank(name) && name.length() > 50) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口名称过长");
		}
	}

}




