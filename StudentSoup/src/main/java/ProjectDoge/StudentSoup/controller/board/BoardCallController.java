package ProjectDoge.StudentSoup.controller.board;

import ProjectDoge.StudentSoup.dto.board.BoardCallDto;
import ProjectDoge.StudentSoup.dto.board.BoardDto;
import ProjectDoge.StudentSoup.dto.board.BoardMainDto;
import ProjectDoge.StudentSoup.exception.page.PagingLimitEqualsZeroException;
import ProjectDoge.StudentSoup.service.board.BoardCallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardCallController {
    private final BoardCallService boardCallService;


    /**
     * @param category
     * @param sorted  0 normal(업데이트 순), 1(좋아요 5개 이상), 2(좋아요 순)
     * @param boardCallDto schoolId memberId departmentId
     * @return
     */
    @PostMapping("/boards")
    public Page<BoardMainDto> callBoards(@RequestParam String category,
                                           @RequestParam int sorted,
                                           @RequestBody BoardCallDto boardCallDto,
                                           @PageableDefault(size = 15) Pageable pageable){
        log.info("category [{}], sorted [{}] schoolId[{}] departmentId [{}] memberId [{}] offset[{}] size [{}]",
                category,
                sorted,
                boardCallDto.getSchoolId(),
                boardCallDto.getDepartmentId(),
                boardCallDto.getMemberId(),
                pageable.getOffset(),
                pageable.getPageSize());
        checkPagingSize(pageable.getPageSize());
        return boardCallService.getBoardSortedCall(boardCallDto, category, sorted, pageable);

    }

    @PostMapping("/board/{boardId}/{memberId}")
    public BoardDto clickBoard(@PathVariable Long boardId,@PathVariable Long memberId){
        return  boardCallService.getBoardDetail(boardId,memberId);
    }
    private void checkPagingSize(Integer limit) {
        if (limit == 0) {
            throw new PagingLimitEqualsZeroException("limit 의 개수는 1 이상이여야 합니다.");
        }
    }
}
