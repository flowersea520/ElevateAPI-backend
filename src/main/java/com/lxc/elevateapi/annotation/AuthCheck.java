package com.lxc.elevateapi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限校验
 *
 * @author lxc
 */
//该注解是应用在方法上的。
@Target(ElementType.METHOD)
//表示这个注解会在运行时保留，这样可以在运行时通过反射来获取并处理这个注解的信息。
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

/**
 * 注解中的属性并不一定都是带小括号的，有些属性可以不带小括号。
 * 具体来说，只有在注解的属性需要参数或有默认值时，才需要在属性后面加上小括号并指定相应的参数。
 * 属性的定义  形式通常是带有小括号的，这是为了表示这些属性是方法，
 */

	/**
	 * 有任何一个角色
	 *
	 * @return
	 */
//	需要加default的话，该注解的 属性一定要带（）
//	这里的 括号 并不表示方法的执行，而是用于 声明这个属性可以被 设置值
//	在使用时可以 像 方法 一样 进行调用。
	String[] anyRole() default "";

	/**
	 * 必须有某个角色
	 *
	 * @return
	 */
	String mustRole() default "";

}

