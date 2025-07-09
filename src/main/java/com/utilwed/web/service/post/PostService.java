package com.utilwed.web.service.post;

import com.utilwed.web.Entity.post.Post;
import com.utilwed.web.repository.PostRepository;

public class PostService {

	private PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public int savePost(Post post) {
		return postRepository.savePost(post);
	}
	
	
	
}
