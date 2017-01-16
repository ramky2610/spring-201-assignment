/**
 * 
 */
package com.mindtree.cms.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.cms.controller.BlogCommentsFo;
import com.mindtree.cms.controller.BlogFo;
import com.mindtree.cms.dao.BlogDao;
import com.mindtree.cms.model.BlogComments;
import com.mindtree.cms.model.BlogContent;

/**
 * @author M1029673
 *
 */
@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogDao blogDao;

	@Transactional
	public void saveBlog(BlogFo blogFo) throws IOException {
		BlogContent blogContent = new BlogContent();
		blogContent.setName(blogFo.getFile().getOriginalFilename());
		blogContent.setTittle(blogFo.getFile().getOriginalFilename().substring(0,
				blogFo.getFile().getOriginalFilename().indexOf(".")));
		blogContent.setContent(blogFo.getDescription());
		blogContent.setName(blogFo.getFile().getOriginalFilename());
		blogContent.setFile(blogFo.getFile().getBytes());
		blogContent.setUserName("Admin");
		blogContent.setType(blogFo.getFile().getContentType());
		blogDao.save(blogContent);

	}

	public List<BlogFo> findAll() {
		List<BlogFo> blogFos = new ArrayList();
		try {
			List<BlogContent> blogContents = blogDao.findAll();
			for (BlogContent blogContent : blogContents) {
				BlogFo blogFo = new BlogFo();
				blogFo.setTitle(blogContent.getTittle());
				blogFo.setDescription(blogContent.getContent());
				byte[] encodeBase64 = Base64.encodeBase64(blogContent.getFile());
				String imgBase64encoded = new String(encodeBase64, "UTF-8");
				blogFo.setImageSrc(imgBase64encoded);
				List<BlogCommentsFo> commentsFos = new ArrayList();
				for (BlogComments blogComments : blogContent.getBlogComments()) {
					BlogCommentsFo blogCommentsFo = new BlogCommentsFo();
					blogCommentsFo.setComment(blogComments.getComment());
					blogCommentsFo.setUserName(blogComments.getUserName());
					commentsFos.add(blogCommentsFo);
				}
				blogFo.setBlogCommentsFo(commentsFos);
				blogFos.add(blogFo);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return blogFos;
	}

	public List<BlogCommentsFo> findAllBlogComments(int blogId) {
		List<BlogCommentsFo> commentsFos = new ArrayList();
		List<BlogFo> blogFos = new ArrayList();
		try {
			List<BlogComments> comments = blogDao.findAllBlogComments(blogId);

			for (BlogComments blogComments : comments) {
				BlogCommentsFo blogCommentsFo = new BlogCommentsFo();
				blogCommentsFo.setComment(blogComments.getComment());
				blogCommentsFo.setUserName(blogComments.getUserName());
				blogCommentsFo.setBlogId(blogComments.getBlogContent().getBlogId());
				commentsFos.add(blogCommentsFo);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return commentsFos;
	}

	@Transactional
	public void saveBlogComments(BlogFo blogFo) throws IOException {
		BlogContent blogContent = new BlogContent();
		blogContent.setName(blogFo.getFile().getOriginalFilename());
		blogContent.setTittle(blogFo.getFile().getOriginalFilename().substring(0,
				blogFo.getFile().getOriginalFilename().indexOf(".")));
		blogContent.setContent(blogFo.getComments());
		blogContent.setName(blogFo.getFile().getOriginalFilename());
		blogContent.setFile(blogFo.getFile().getBytes());
		blogContent.setUserName("test");
		blogContent.setType(blogFo.getFile().getContentType());
		blogDao.save(blogContent);

	}

	@Transactional
	public void saveBlogComments(BlogCommentsFo blogCommentsFo) throws Exception{
		BlogComments blogComments = new BlogComments();
		BlogContent blogContent=new BlogContent();
		blogContent.setBlogId(blogCommentsFo.getBlogId());
		blogComments.setBlogContent(blogContent);
		blogComments.setUserName(blogCommentsFo.getUserName());
		blogComments.setComment(blogCommentsFo.getComment());
		blogDao.saveBlogComments(blogComments);
	}

	public BlogFo findAllBlogFo(int blogId) {
		BlogFo blogFo = null;
		try {
			BlogContent comments = blogDao.findAllBlogContent(blogId);
			List<BlogContent> blogContents = blogDao.findAll();
			if (comments != null) {
				blogFo = new BlogFo();
				blogFo.setTitle(comments.getTittle());
				blogFo.setBlogId(comments.getBlogId());
				blogFo.setCount(blogContents.size());
				blogFo.setDescription(comments.getContent());
				byte[] encodeBase64 = Base64.encodeBase64(comments.getFile());
				String imgBase64encoded = new String(encodeBase64, "UTF-8");
				blogFo.setImageSrc(imgBase64encoded);
				List<BlogCommentsFo> commentsFos = new ArrayList<BlogCommentsFo>();
				for (BlogComments blogComments : comments.getBlogComments()) {
					BlogCommentsFo blogCommentsFo = new BlogCommentsFo();
					blogCommentsFo.setComment(blogComments.getComment());
					blogCommentsFo.setUserName(blogComments.getUserName());
					blogCommentsFo.setBlogId(comments.getBlogId());
					commentsFos.add(blogCommentsFo);
					blogFo.setBlogCommentsFo(commentsFos);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return blogFo;
	}
	
}
