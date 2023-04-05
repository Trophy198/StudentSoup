import MainNavbar from '../common/MainNavbar';
import './boardDetail.scss';
import left from '../../img/left.svg';
import review_white from '../../img/review_white.svg';
import heart_white from '../../img/heart_white.svg';
import Circle_human from '../../img/circle_human.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEllipsis, faHeart } from '@fortawesome/free-solid-svg-icons';

const BoardDetail = () => {
  return (
    <div>
      <MainNavbar />
      <div className="board-detail-main">
        <div className="board-detail-top-div">
          <div className="board-detail-top">
            <div className="board-detail-top-left">
              <img src={left} alt="" />
              <span>게시판 이름</span>
            </div>
            <div className="board-detail-top-right">
              <button className="board-detail-write-div">
                <img src={review_white} alt="" />
                <p>글쓰기</p>
              </button>
            </div>
          </div>
        </div>
        <div className="board-detail-mid-div">
          <div className="board-detail-mid-title-div">
            <div className="board-detail-mid-title">
              <span>제목</span>
              <p>0</p>
            </div>
            <div className="board-detail-mid-title-info">
              <p>작성자 | 작성일 | 조회수 | 좋아요</p>
            </div>
            <div className="board-detail-underline" />
          </div>
          <div className="board-detail-content-div">
            <div className="board-detail-content">글 내용</div>
            <div className="board-detail-like-button-div">
              <button className="board-detail-like-button">
                <img src={heart_white} alt="" />
                <p>좋아요 20</p>
              </button>
            </div>
          </div>
        </div>
        <div className="board-detail-bottom-div">
          <div className="board-detail-bottom-review">
            <div className="board-detail-bottom-review-count">
              <p>댓글 수</p>
            </div>
            <div className="board-detail-bottom-function">
              <span className="board-detail-bottom-modify">수정</span>
              <span className="board-detail-bottom-report">신고</span>
            </div>
          </div>
          <div className="board-detail-bottom-review-write-div">
            <div className="board-detail-bottom-review-write">
              <input type="text" placeholder="댓글을 입력해주세요." />
              <button>작성</button>
            </div>
          </div>
          <div className="board-detail-bottom-best-review-div">
            <div className="board-detail-bottom-best-review">
              <div className="board-detail-bottom-best-review-left">
                <img src={Circle_human} alt="" />
                <div className="board-detail-bottom-best-review-best-div">
                  <span className='board-detail-bottom-best-text'>BEST</span>
                  <div className="board-detail-bottom-best-review-name-div">
                    <span>
                      유저네임 <p>작성날짜</p>
                    </span>
                    <p>댓글내용</p>
                  </div>
                </div>
              </div>
              <div className="board-detail-bottom-best-review-right">
                <FontAwesomeIcon icon={faEllipsis} className="board-detail-function-icon" />
                <div className="board-detail-bottom-best-review-right-heart">
                  <FontAwesomeIcon icon={faHeart} className="board-detail-function-heart-icon" />
                  <p>14</p>
                </div>
              </div>
            </div>
            <div className="board-detail-underline" />
          </div>
        </div>
      </div>
    </div>
  );
};

export default BoardDetail;
