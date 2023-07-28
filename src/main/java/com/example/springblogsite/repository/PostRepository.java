package com.example.springblogsite.repository;

import com.example.springblogsite.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post,Long> {
}
