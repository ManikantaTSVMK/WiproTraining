package com.sample.service;

import com.sample.model.Comment;
import java.util.List;

public interface CommentService {
    void addComment(Comment comment);
    void deleteComment(Long id);
    Comment getCommentById(Long id);
    List<Comment> getCommentsByTaskId(Long taskId);
    List<Comment> getCommentsByUserId(Long userId);
}
