package com.example.sns_project.service;

import com.example.sns_project.domain.dto.comment.CommentDeleteResponse;
import com.example.sns_project.domain.dto.comment.CommentRequest;
import com.example.sns_project.domain.dto.comment.CommentResponse;
import com.example.sns_project.domain.entity.Comment;
import com.example.sns_project.domain.entity.Post;
import com.example.sns_project.domain.entity.User;
import com.example.sns_project.exception.AppException;
import com.example.sns_project.exception.ErrorCode;
import com.example.sns_project.repository.CommentRepository;
import com.example.sns_project.repository.PostRepository;
import com.example.sns_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentResponse addComment(CommentRequest commentRequestDto,String userName,Long postId){
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new AppException(ErrorCode.USERNAME_NOT_FOUND, userName + "이 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND,"post가 존재하지 않습니다."));

        Comment comment = commentRepository.save(commentRequestDto.toEntity(user,post));
        CommentResponse commentResponse = CommentResponse.fromEntity(comment);

        return commentResponse;

    }

    public CommentDeleteResponse deleteComment(Long postsId,Long commentsId){
        Post post = postRepository.findById(postsId)
                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND, postsId + "가 없습니다."));

        Comment comment = commentRepository.findById(commentsId)
                .orElseThrow(()->new AppException(ErrorCode.COMMENT_NOT_FOUND,commentsId+"가 없습니다."));

        commentRepository.delete(comment);
        CommentDeleteResponse commentDeleteResponse = CommentDeleteResponse
                .builder()
                .message("댓글 삭제 완료")
                .commentId(comment.getId())
                .build();

        return commentDeleteResponse;
    }

    public CommentResponse editComment(CommentRequest dto,Long postsId, Long commentsId){

        Post post = postRepository.findById(postsId)
                .orElseThrow(()->new AppException(ErrorCode.POST_NOT_FOUND,postsId+"가 없습니다."));
        Comment comment = commentRepository.findById(commentsId)
                .orElseThrow(()->new AppException(ErrorCode.COMMENT_NOT_FOUND,commentsId+"가 없습니다."));
        comment.setComment(dto.getComment());
        commentRepository.save(comment);
        CommentResponse commentResponse = CommentResponse.fromEntity(comment);
        return commentResponse;

    }

    public Page<CommentResponse> getAllComments(Pageable pageable,Long postId){

        Page<Comment> comments =commentRepository.findByPostId(postId,pageable);

        Page<CommentResponse> commentResponses = comments.map(comment -> CommentResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .userName(comment.getUser().getUserName())
                .postId(comment.getPost().getId())
                .createdAt(comment.getCreatedAt())
                .lastModifiedAt(comment.getLastModifiedAt())
                .build());
        return commentResponses;
    }
}
