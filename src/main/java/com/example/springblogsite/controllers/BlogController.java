package com.example.springblogsite.controllers;

import com.example.springblogsite.model.Post;
import com.example.springblogsite.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

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

    @GetMapping("/blog/add")
    public String addBlog(Model model){
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,@RequestParam String anons,@RequestParam String full_text, Model model){
        Post post=new Post(title,anons,full_text);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String getBlog(@PathVariable("id") Long id,Model model){
        Optional<Post> post=postRepository.findById(id);
        if (post.isPresent()){
            model.addAttribute("post",post);
        }
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String editBlog(@PathVariable("id") Long id,Model model){
        Optional<Post> post=postRepository.findById(id);
        if (post.isPresent()){
            model.addAttribute("post",post);
        }
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String updateBlog(@PathVariable("id") Long id,@RequestParam String title,@RequestParam String anons,
                             @RequestParam String full_text,Model model){
        Post post=postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);

        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String deletePost(@PathVariable("id") Long id,Model model){
        Post post=postRepository.findById(id).orElseThrow();
        postRepository.delete(post);

        return "redirect:/blog";
    }
}