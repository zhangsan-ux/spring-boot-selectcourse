package com.personal.course.util;

import com.personal.core.enums.ResultEnum;
import com.personal.core.exception.BusinessException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;
/**
 * 说明
 *
 * @author cgc 6828
 * @date 2020/4/20 19:31
 */
public class ValidatorUtil {
    /**
     * 验证对象
     *
     * @param object 待验证的对象
     * @param groups 待验证的对象组
     * @param <T>
     * @return
     */
    public static <T> String validate(T object, Class<?>... groups) {
        StringBuilder str = new StringBuilder();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintValidatorSet = validator.validate(object, groups);
        if (constraintValidatorSet != null && !constraintValidatorSet.isEmpty()) {
            for (Iterator<ConstraintViolation<Object>> iterator = constraintValidatorSet.iterator(); iterator.hasNext(); ) {
                ConstraintViolation<Object> constraintViolation = iterator.next();
                String message = constraintViolation.getMessage();
                str.append(constraintViolation.getPropertyPath().toString()).append(":").append(message);
            }
        }
        return str.toString();
    }

    /**
     * 验证对象
     *
     * @param object 待验证的对象
     * @param groups 待验证的对象组
     * @param <T>
     * @throws BusinessException 业务异常
     */
    public static <T> void validateObject(T object, Class<?>... groups) throws BusinessException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintValidatorSet = validator.validate(object, groups);
        if (constraintValidatorSet != null && !constraintValidatorSet.isEmpty()) {
            for (ConstraintViolation<Object> constraintViolation : constraintValidatorSet) {
                String message = constraintViolation.getMessage();
                throw new BusinessException(ResultEnum.PARAMETER_ERROR.getCode(), message);
            }
        }
    }
}
