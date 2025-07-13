package com.utilwed.web.service.post;



import java.util.List;

import com.utilwed.web.Entity.community.Post;
import com.utilwed.web.repository.PostRepository;

public class PostService {

	private PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public int savePost(Post post) {
		return postRepository.savePost(post);
	}
	
	public List<Post> getPostList(int categoryId, String field, String query, int page){
		return postRepository.getPostList(categoryId, field, query, page);
	}
	
	public int getPostCount(String field, String query, int categoryId) {
		return postRepository.getPostCount(categoryId, field, query);
	}
	
	public Post getPost(int categoryId, int postId) {
		return postRepository.getPost(categoryId, postId);
	}

	public boolean updatePost(Post post) {
		return postRepository.updatePost(post);
	}

	public int deletePost(int postId) {
		return postRepository.deletePost(postId);
	}
	
}
