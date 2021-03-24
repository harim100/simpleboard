package com.board.frm.util;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = IdDuplicationValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IdDuplicationConstraint {
	String message() default "중복된 아이디 입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
