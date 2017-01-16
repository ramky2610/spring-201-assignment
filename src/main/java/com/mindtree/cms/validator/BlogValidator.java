package com.mindtree.cms.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mindtree.cms.controller.BlogFo;


public class BlogValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		// TODO Auto-generated method stub
		return BlogFo.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		BlogFo blogFo = (BlogFo) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file", "file.required");
		if (blogFo.getTitle().length() < 3 || blogFo.getTitle().length() > 20) {
			errors.rejectValue("title", "customer.title.range.invalid");

		}
		if (blogFo.getTitle().length() < 3 || blogFo.getTitle().length() > 500) {
			errors.rejectValue("description", "customer.description.range.invalid");

		}
		if (blogFo.getFile()==null || blogFo.getFile().isEmpty()) {
			errors.rejectValue("file", "file.required");

		}


	}

}
