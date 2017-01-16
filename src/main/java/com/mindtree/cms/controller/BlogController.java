/**
 * 
 */
package com.mindtree.cms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.cms.service.BlogService;

/**
 * @author M1032230
 *
 */
@Controller
public class BlogController {

	@Autowired
	BlogService blogService;
	
	@RequestMapping(value = "/")
	public String loginPage(ModelMap model) {
		model.addAttribute("loginFo", new LoginFo());
		return "userSubmit";

	}

	

	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	public String userLogin(ModelMap model) {
		model.addAttribute("loginFo", new LoginFo());
		return "userSubmit";
	}

	@RequestMapping(value = "/viewBlog", method = RequestMethod.GET)
	public String viewBlog(Model model, @Valid @ModelAttribute LoginFo loginFo, BindingResult result,
			@RequestParam(value = "guestUserName", required = false) String guestUserName,
			@RequestParam(value = "blogId", defaultValue = "1", required = false) int blogId) {
		if (result.hasErrors()) {
			return "userSubmit";
		}
		BlogFo blogFo = blogService.findAllBlogFo(blogId);
			model.addAttribute("blogFo", blogFo);
			model.addAttribute("blogCommentsFo", new BlogCommentsFo());
			model.addAttribute("guestUserName", loginFo.getGuestUserName());
			if (blogFo != null) {
			model.addAttribute("blogId", blogFo.getBlogId());
		}
		return "viewblog";

	}

	@RequestMapping(value = "/saveBlogComments", method = RequestMethod.POST)
	public @ResponseBody List<BlogCommentsFo> saveBlogComments(@RequestBody BlogCommentsFo blogCommentsFo,
			HttpServletRequest request, HttpServletResponse response) {
		List<BlogCommentsFo>  blogCommentsFos=null;
		try{
		if(blogCommentsFo.getComment()!=null && !blogCommentsFo.getComment().isEmpty())
		blogService.saveBlogComments(blogCommentsFo);
		 blogCommentsFos=blogService.findAllBlogComments(blogCommentsFo.getBlogId());
		}
		catch(Exception ex){
			
		}
		return blogCommentsFos;

	}

	@RequestMapping(value = "/viewBlogForAdmin", method = RequestMethod.GET)
	public String viewBlogForAdmin(Model model, @ModelAttribute LoginFo loginFo,
			@RequestParam(value = "guestUserName", required = false) String guestUserName,
			@RequestParam(value = "blogId", required = false) int blogId) {
		BlogFo blogFo = blogService.findAllBlogFo(blogId);
		if (blogFo != null) {
			model.addAttribute("blogFo", blogFo);
			model.addAttribute("blogCommentsFo", new BlogCommentsFo());
			model.addAttribute("guestUserName", loginFo.getGuestUserName());
			model.addAttribute("blogId", blogFo.getBlogId());
		}
		return "viewblogadmin";

	}
}
