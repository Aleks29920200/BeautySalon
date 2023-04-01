package com.example.beautySalon.services;

import com.example.beautySalon.domain.dto.binding.CommentDto;
import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import com.example.beautySalon.domain.entity.Comment;
import com.example.beautySalon.domain.entity.User;
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
    private ModelMapper mapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, ServiceImpl service, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.service = service;
        this.mapper = mapper;
    }

    public List<Comment> getCommentsByRoute(Long serviceId) throws ObjectNotFoundException {
        return commentRepository.findAllByService(mapper.map(this.service.findServiceById(serviceId), com.example.beautySalon.domain.entity.Service.class)).get();
    }

    public Comment createComment(CommentDto commentDto, Long routeId, User author) throws ObjectNotFoundException {
        Comment comment = new Comment();
        comment.setCreated(LocalDateTime.now());
        comment.setService(mapper.map(service.findServiceById(routeId), com.example.beautySalon.domain.entity.Service.class));
        comment.setAuthor(author);
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

