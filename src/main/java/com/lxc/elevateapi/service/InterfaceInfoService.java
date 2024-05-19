package com.lxc.elevateapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxc.elevateapi.model.entity.InterfaceInfo;
import com.lxc.elevateapi.model.entity.Post;

/**
* @author lxc
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-05-13 15:29:57
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
	/**
	 * 校验
	 *
	 * @param post
	 * @param add 是否为创建校验
	 */
	void validInterfaceInfo(InterfaceInfo post, boolean add);
}
