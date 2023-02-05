package ProjectDoge.StudentSoup.dto.boardreply;

import ProjectDoge.StudentSoup.entity.board.BoardReply;
import ProjectDoge.StudentSoup.entity.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardReplyDto {

    private Long boardReplyId;
    private String content;
    private int likeCount;
    private String nickname;
    private String writeDate;
    private String memberProfileImageName;
    private int seq;
    private int depth;
    private int level;
    private String active;
    private boolean like;

    public BoardReplyDto createBoardReplyDto(BoardReply boardReply, Boolean like) {
        this.boardReplyId = boardReply.getReplyId();
        this.content = boardReply.getContent();
        this.likeCount = boardReply.getLikedCount();
        this.nickname = boardReply.getMember().getNickname();
        this.writeDate = boardReply.getWriteDate();
        this.memberProfileImageName = setProfileImageFileName(boardReply.getMember());
        this.seq = boardReply.getSeq();
        this.depth = boardReply.getDepth();
        this.level = boardReply.getLevel();
        this.active = boardReply.getActive();
        this.like = like;
        return this;
    }

    private String setProfileImageFileName(Member member){
        if(member.getImageFile() != null){
            return member.getImageFile().getFileName();
        }
        return null;
    }

}