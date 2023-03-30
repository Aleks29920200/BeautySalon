package com.example.beautySalon.web.rest;

import com.example.beautySalon.domain.dto.binding.CommentCreationDto;
import com.example.beautySalon.domain.dto.error.ObjectNotFoundException;
import com.example.beautySalon.domain.dto.view.CommentDisplayView;
import com.example.beautySalon.domain.dto.view.UserViewDto;
import com.example.beautySalon.domain.entity.Comment;
import com.example.beautySalon.services.CommentService;
import com.example.beautySalon.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class CommentRestController {
    private CommentService commentService;
    private UserService userService;
    private ModelMapper mapper;

    @Autowired
    public CommentRestController(CommentService commentService, UserService userService, ModelMapper mapper) {
        this.commentService = commentService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/api/{routeId}/comments")
    public ResponseEntity<List<CommentDisplayView>> getCommentsRoutes(@PathVariable("routeId") Long routeId, Principal principal) throws ObjectNotFoundException {
      UserViewDto user = null;
        try {
            user=userService.findUserByUsername(principal.getName());
        } catch (RuntimeException e) {
            //IGNORE
        }
        var comments = commentService.getCommentsByRoute(routeId)
                .stream().map(createCommentViewForUser(principal,user))
                .collect(Collectors.toList());
        return ResponseEntity.ok(comments);
    }

    private Function<Comment, CommentDisplayView> createCommentViewForUser(Principal principal, UserViewDto user) {
        return c -> {
            boolean canEdit = principal != null &&
                    (isAdminOrModerator(mapper.map(user, User.class)) || user.getId() == c.getAuthor().getId());
            return mapToCommentView(c, canEdit);
        };
    }

    private CommentDisplayView mapToCommentView(Comment c) {
        return mapToCommentView(c, false);
    }

    private CommentDisplayView mapToCommentView(Comment c, boolean canEdit) {
        return new CommentDisplayView(c.getId(), c.getText(), c.getAuthor().getLastName()+" "+c.getAuthor().getFirstName(),
                c.getCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
    }

    @GetMapping("/api/{serviceId}/comments/{commentId}")
    private ResponseEntity<CommentDisplayView> getComment(@PathVariable("commentId") Long commentId, @PathVariable Long serviceId) {
        try {
            return ResponseEntity.ok(mapToCommentView(commentService.getComment((commentId))));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/api/{serviceId}/comments", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CommentDisplayView> createComment(@AuthenticationPrincipal UserDetails userDetails,
                                                     @RequestBody CommentCreationDto commentDto,
                                                     @PathVariable("serviceId") Long serviceId) throws ObjectNotFoundException {
        Comment comment = commentService.createComment(commentDto,
                serviceId, userService.findUserByUsername(userDetails.getUsername()));

        CommentDisplayView commentView = mapToCommentView(comment);

        return ResponseEntity.created(URI.create(String.format("/api/%s/comments/%d", serviceId, comment.getId())))
                .body(commentView);
    }

    @DeleteMapping("/api/{serviceId}/comments/{commentId}")
    public ResponseEntity<CommentDisplayView> deleteComment(@PathVariable("commentId") Long commentId,
                                                            @AuthenticationPrincipal UserDetails principal, @PathVariable Long serviceId) {
        UserViewDto user = userService.findUserByUsername(principal.getUsername());
        try {
            return deleteCommentInternal(commentId, user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<CommentDisplayView> deleteCommentInternal(Long commentId, UserViewDto user) {
        Comment comment = commentService.getComment(commentId);

        if(isAdminOrModerator(mapper.map(user,User.class)) || user.getId() == comment.getAuthor().getId()) {
            Comment deleted = commentService.deleteComment(commentId);
            return ResponseEntity.ok(mapToCommentView(deleted));
        }
        return ResponseEntity.status(403).build();
    }

    private boolean isAdminOrModerator(User user) {
        return user.getAuthorities().stream().anyMatch(r -> Objects.equals(r.getAuthority(), "MODERATOR") || Objects.equals(r.getAuthority(), "ADMIN"));
    }
}