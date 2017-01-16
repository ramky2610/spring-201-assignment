/**
 * 
 */
package com.mindtree.cms.service;

import java.io.IOException;
import java.util.List;

import com.mindtree.cms.controller.BlogCommentsFo;
import com.mindtree.cms.controller.BlogFo;

/**
 * @author M1029673
 *
 */
public interface BlogService {
	public void saveBlog(BlogFo blogFo) throws IOException;

	public List<BlogFo> findAll();

	public List<BlogCommentsFo> findAllBlogComments(int blogId);

	public void saveBlogComments(BlogCommentsFo blogCommentsFo) throws Exception;

	public BlogFo findAllBlogFo(int blogId);
	
	

}
