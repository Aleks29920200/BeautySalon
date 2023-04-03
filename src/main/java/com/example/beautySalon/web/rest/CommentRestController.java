package com.example.beautySalon.web.rest;

import com.example.beautySalon.domain.dto.binding.CommentDto;
import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import com.example.beautySalon.domain.dto.view.CommentView;
import com.example.beautySalon.domain.entity.Comment;
import com.example.beautySalon.domain.entity.User;
import com.example.beautySalon.services.CommentService;
import com.example.beautySalon.services.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.beautySalon.domain.entity.RoleName.*;

@RestController
public class CommentRestController {
    private CommentService commentService;
    private UserServiceImpl service;
    private ModelMapper mapper;

    @Autowired
    public CommentRestController(CommentService commentService, UserServiceImpl service, ModelMapper mapper) {
        this.commentService = commentService;
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/api/{serviceId}/comments")
    public ResponseEntity<List<CommentView>> getCommentsRoutes(@PathVariable("serviceId") Long serviceId, Principal principal) throws ObjectNotFoundException {
        User user = null;
        try {
            user = mapper.map(service.findUserByUsername(principal.getName()),User.class);
        } catch (RuntimeException e) {
            //IGNORE
        }
        var comments = commentService.getCommentsByRoute(serviceId)
                .stream().map(createCommentViewForUser(principal,user))
                .collect(Collectors.toList());
        return ResponseEntity.ok(comments);
    }

    private Function<Comment, CommentView> createCommentViewForUser(Principal principal, User user) {
        return c -> {
            boolean canEdit = principal != null &&
                    (isUser(user) || user.getId() == c.getAuthor().getId());
            return mapToCommentView(c, canEdit);
        };
    }

    private CommentView mapToCommentView(Comment c) {
        return mapToCommentView(c, false);
    }

    private CommentView mapToCommentView(Comment c, boolean canEdit) {
        return new CommentView(c.getId(), c.getText(), c.getAuthor().getFirstName(),
                c.getCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")), canEdit);
    }

    @GetMapping("/api/{serviceId}/comments/{commentId}")
    private ResponseEntity<CommentView> getComment(@PathVariable("commentId") Long commentId, @PathVariable Long serviceId) {
        try {
            return ResponseEntity.ok(mapToCommentView(commentService.getComment(commentId)));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/api/{serviceId}/comments", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CommentView> createComment(@AuthenticationPrincipal UserDetails userDetails,
                                                     @RequestBody CommentDto commentDto,
                                                     @PathVariable("serviceId") Long serviceId) throws ObjectNotFoundException {
        Comment comment = commentService.createComment(commentDto,
                serviceId, mapper.map(service.findUserByUsername(userDetails.getUsername()), User.class));

        CommentView commentView = mapToCommentView(comment);

        return ResponseEntity.created(URI.create(String.format("/api/%s/comments/%d", serviceId, comment.getId())))
                .body(commentView);
    }

    @DeleteMapping("/api/{serviceId}/comments/{commentId}")
    public ResponseEntity<CommentView> deleteComment(@PathVariable("commentId") Long commentId,
                                                     @AuthenticationPrincipal UserDetails principal) {
        User user = mapper.map(service.findUserByUsername(principal.getUsername()), User.class);
        try {
            return deleteCommentInternal(commentId, user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<CommentView> deleteCommentInternal(Long commentId, User user) {
        Comment comment = commentService.getComment(commentId);

        if(isUser(user) || mapper.map(user, com.example.beautySalon.domain.entity.User.class).getId() == comment.getAuthor().getId()) {
            Comment deleted = commentService.deleteComment(commentId);
            return ResponseEntity.ok(mapToCommentView(deleted));
        }
        return ResponseEntity.status(403).build();
    }

    private boolean isUser(User user) {
        return user.getAuthorities().stream().anyMatch(r -> r.getAuthority() == USER.toString());
    }
}