package com.mindtree.cms.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.cms.controller.BlogCommentsFo;
import com.mindtree.cms.controller.BlogFo;
import com.mindtree.cms.service.BlogService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsApplicationTests {
	
	@Autowired
	BlogService blogService;


	@Test
	public void findAll(){
		List<BlogFo> list=blogService.findAll();
		assertEquals(list.size(),0);
		
	}

	@Test
	public void findAllBlogFo() {
		BlogFo blogFo= blogService.findAllBlogFo(15);
		assertNull(blogFo);
	}
	
	 @Test(expected = Exception.class)
	public void  saveBlogComments() throws Exception{
		 BlogCommentsFo blogCommentsFo=new BlogCommentsFo();
		 blogCommentsFo.setBlogId(50);
		 blogCommentsFo.setUserName("test");
		 blogCommentsFo.setComment("test");
		 blogService.saveBlogComments(blogCommentsFo);
		
	}
	



}
