package com.lxc.elevateapi.common;

import lombok.Data;

import java.io.Serializable;

/**
 *  定义一个专门传id的  requestDto
 * @author mortal
 * @date 2024/5/17 10:49
 */
@Data
public class IdRequest implements Serializable {
	private static final long serialVersionUID = 1929849405500752385L;
	private Long id;

}
