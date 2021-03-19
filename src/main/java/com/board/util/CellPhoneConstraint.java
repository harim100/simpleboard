package com.board.util;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = CellPhoneValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CellPhoneConstraint {
	String message() default "핸드폰번호를 확인해주세요.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
