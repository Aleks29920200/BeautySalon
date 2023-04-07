package com.example.beautySalon.services;

import com.example.beautySalon.domain.dto.binding.CommentDto;
import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import com.example.beautySalon.domain.entity.Comment;
import com.example.beautySalon.repositories.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private ServiceImpl service;
    private UserServiceImpl userService;
    private ModelMapper mapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, ServiceImpl service, UserServiceImpl userService, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.service = service;
        this.userService = userService;
        this.mapper = mapper;
    }

    public List<Comment> getComments() throws ObjectNotFoundException {
        return commentRepository.findAll();
    }

    public Comment createComment(CommentDto commentDto, Long user) throws ObjectNotFoundException {
        Comment comment = new Comment();
        comment.setCreated(LocalDateTime.now());
        comment.setAuthor(this.userService.findUserById(user));
        comment.setText(commentDto.getText());
        comment.setApproved(true);
        commentRepository.save(comment);
        return comment;
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found!"));
    }

    public Comment deleteComment(Long id) {
        Comment comment = getComment(id);
        commentRepository.delete(comment);
        return comment;
    }


}

