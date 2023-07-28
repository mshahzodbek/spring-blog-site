package com.example.springblogsite.controllers;

import com.example.springblogsite.model.Post;
import com.example.springblogsite.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blog(Model model){
        Iterable<Post> posts=postRepository.findAll();
        model.addAttribute("posts",posts);
        return "blog";
    }

    @PostMapping("/blog/add")
    public String addBlog(Model model){
        return "blog-add";
    }
}
