package com.lxc.elevateapi.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 接口状态枚举
 * 修改其status   0表示下线接口，1表示上线
 *
 * @author lxc
 */
public enum InterfaceInfoStatusEnum {

	ONLINE("上线", 1),
	OFFLINE("下线", 0);

	private final String text;

	private final int value;

	InterfaceInfoStatusEnum(String text, int value) {
		this.text = text;
		this.value = value;
	}

	/**
	 * 获取值列表
	 *
	 * @return
	 */
	public static List<Integer> getValues() {
		return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
	}

	public int getValue() {
		return value;
	}

	public String getText() {
		return text;
	}
}
