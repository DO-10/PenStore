package com.example.penstore.service;

import com.example.penstore.dao.CommentMapper;
import com.example.penstore.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

//    @Transactional
//    public void addComment(Comment comment) {
//        // 后端自动生成ID已通过切面处理
////        System.out.println(comment.getUserId());
////        System.out.println(comment.getStar());
////        System.out.println(comment.getContent());
//        comment.setCommentAt(LocalDateTime.now());
//        if ("2".equals(comment.getPop())) {
//            commentMapper.updateReplyStatus(comment.getParentId());
//        }
//        commentMapper.insertComment(comment);
//    }
//
//    public List<Comment> getCommentsByGoodsId(String goodsId) {
//        return commentMapper.selectByGoodsId(goodsId);
//    }
//
//    public List<Comment> getRepliesByParentId(String parentId) {
//        return commentMapper.selectRepliesByParentId(parentId);
//    }
// 添加评论或回复
@Transactional
public void addComment(Comment comment) {
    comment.setComment_at(LocalDateTime.now());
    if ("2".equals(comment.getPop())) {
        commentMapper.updateReplyStatus(comment.getParentId());
    }
    commentMapper.insertComment(comment);
}

    // 获取商品评论及回复（嵌套结构）
    public List<Comment> getNestedComments(String goodsId) {
        // 获取所有主评论
        List<Comment> mainComments = commentMapper.selectByGoodsId(goodsId);
        System.out.println(mainComments.get(0).getUsername());
        System.out.println(mainComments.get(0).getComment_at());
        System.out.println(mainComments.get(0).getId());
        System.out.println(mainComments.get(0).getUser_id());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 遍历 mainComments 列表
        for (Comment comment : mainComments) {
            // 获取 comment_at 属性并转换为 String
            LocalDateTime commentAt = comment.getComment_at();
            if (commentAt != null) { // 确保 comment_at 不为 null
                String formattedTime = commentAt.format(formatter);
                // 将转换后的时间赋值给 time 属性
                comment.setTime(formattedTime);
            }

        }


        // 为每条主评论查询回复
        mainComments.forEach(comment -> {
            List<Comment> replies = commentMapper.selectRepliesByParentId(comment.getId());

            for (Comment reply : replies) {

                // 获取 comment_at 属性并转换为 String
                LocalDateTime commentAt = reply.getComment_at();
                if (commentAt != null) { // 确保 comment_at 不为 null
                    String formattedTime = commentAt.format(formatter);
                    // 将转换后的时间赋值给 time 属性
                    reply.setTime(formattedTime);
                }

            }

            comment.setReplies(replies);
        });

        return mainComments;
    }
}
