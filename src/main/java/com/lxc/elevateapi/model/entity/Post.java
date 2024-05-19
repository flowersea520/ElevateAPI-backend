package com.lxc.elevateapi.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子
 *
 * @TableName post
 */
@TableName(value = "post")
@Data
public class Post implements Serializable {
	/**
	 * id
	 */
	private Long id;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 性别（0-男, 1-女）
	 */
	private Integer gender;

	/**
	 * 学历
	 */
	private String education;

	/**
	 * 地点
	 */
	private String place;

	/**
	 * 职业
	 */

	private String job;

	/**
	 * 联系方式
	 */

	private String contact;

	/**
	 * 感情经历
	 */

	private String loveExp;

	/**
	 * 内容（个人介绍）
	 */

	private String content;

	/**
	 * 照片地址
	 */

	private String photo;

	/**
	 * 状态（0-待审核, 1-通过, 2-拒绝）
	 */

	private Integer reviewStatus;

	/**
	 * 审核信息
	 */

	private String reviewmEssage;

	/**
	 * 浏览数
	 */

	private Integer viewNum;

	/**
	 * 点赞数
	 */

	private Integer thumbNum;

	/**
	 * 创建用户 id
	 */

	private Long userId;

	/**
	 * 创建时间
	 */

	private Date createTime;

	/**
	 * 更新时间
	 */

	private Date updateTime;

	/**
	 * 是否删除
	 */
	@TableLogic
	private Integer isDelete;
	//这样的设置告诉MyBatis-Plus框架忽略这个字段与数据库表的映射。通常情况下，
// 数据库表是不需要存储serialVersionUID这样的字段的，因为它是用于序列化和版本控制的，而不是实际的数据库存储数据。
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
}