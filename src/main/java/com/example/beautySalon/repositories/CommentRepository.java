package com.example.beautySalon.repositories;

import com.example.beautySalon.domain.entity.Comment;
import com.example.beautySalon.domain.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<List<Comment>> findAllByService(Service service);
}