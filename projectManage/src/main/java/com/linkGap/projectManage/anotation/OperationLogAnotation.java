package com.linkGap.projectManage.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解操作日志
 * @author renhengli
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface OperationLogAnotation {
	//模块名  
    String moduleName() default "";  
    //操作内容  
    String operation() default "";  
}
