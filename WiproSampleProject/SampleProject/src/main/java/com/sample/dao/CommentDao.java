package com.sample.dao;

import com.sample.model.Comment;
import java.util.List;

public interface CommentDao {
    void saveComment(Comment comment);
    void deleteComment(Long id);
    Comment getCommentById(Long id);
    List<Comment> getCommentsByTaskId(Long taskId);
    List<Comment> getCommentsByUserId(Long userId);
}
