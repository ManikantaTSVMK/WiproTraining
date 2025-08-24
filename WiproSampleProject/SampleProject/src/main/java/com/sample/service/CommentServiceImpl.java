package com.sample.service;

import com.sample.dao.CommentDao;
import com.sample.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public void addComment(Comment comment) {
        commentDao.saveComment(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentDao.deleteComment(id);
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentDao.getCommentById(id);
    }

    @Override
    public List<Comment> getCommentsByTaskId(Long taskId) {
        return commentDao.getCommentsByTaskId(taskId);
    }

    @Override
    public List<Comment> getCommentsByUserId(Long userId) {
        return commentDao.getCommentsByUserId(userId);
    }
}
