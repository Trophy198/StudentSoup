import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFire } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';

interface State {
  value1: string;
  value2: number;
}

const PCBoard = (props: any) => {
  const {
    currentPage,
    category,
    bestBoardItems,
    hotBoardItems,
    currentPosts,
    setSorted,
    memberId,
  } = props;

  const handleClickSorted = (e: React.MouseEvent<HTMLDivElement, MouseEvent>) => {
    const target = e.target as HTMLDivElement;
    setSorted(target.getAttribute('data-value'));
  };

  const navigate = useNavigate();

  const handleClickDetail = (e: any) => {
    e.stopPropagation();
    const value = e.target.id;
    const propsState: State = { value1: value, value2: memberId };
    navigate('/board/detail', { state: propsState });
  };

  return (
    <table className="board-table">
      <thead>
        <tr>
          <th className="board-title">제목</th>
          <th className="post-information">
            <div className="board-writer">작성자</div>
            <div className="board-write-date" data-value="2" onClick={handleClickSorted}>
              작성일
            </div>
            <div className="board-view-count" data-value="3" onClick={handleClickSorted}>
              조회수
            </div>
            <div className="board-like-count" data-value="1" onClick={handleClickSorted}>
              좋아요
            </div>
          </th>
        </tr>
      </thead>
      <tbody>
        {currentPosts
          .filter((post: any) => post.authentication === 'Y')
          .map((post: any) => {
            return (
              <tr id={post.boardId} key={post.boardId} className="authentication-wrap">
                <td id={post.boardId} onClick={handleClickDetail} className="board-title">
                  [{post.tag}]&nbsp;
                  <span id={post.boardId} className="authentication-post">
                    {post.title}
                  </span>
                  <span className="board-review-count">{post.reviewCount}</span>
                </td>
                <td className="post-information">
                  <div className="board-writer">{post.nickname}</div>
                  <div className="board-write-date">{post.writeDate}</div>
                  <div className="board-view-count">{post.view}</div>
                  <div className="board-like-count">{post.likedCount}</div>
                </td>
              </tr>
            );
          })}
        {currentPage === 1 && category === 'ALL'
          ? bestBoardItems.map((post: any) => {
              return (
                <tr id={post.boardId} key={post.boardId} className="best-post">
                  <td id={post.boardId} onClick={handleClickDetail} className="board-title">
                    <span className="best-cell">BEST</span>[{post.tag}]&nbsp;
                    <span id={post.boardId}>{post.title}</span>
                    <span className="board-review-count">{post.reviewCount}</span>
                  </td>
                  <td className="post-information">
                    <div className="board-writer">{post.nickname}</div>
                    <div className="board-write-date">{post.writeDate}</div>
                    <div className="board-view-count">{post.view}</div>
                    <div className="board-like-count">{post.likedCount}</div>
                  </td>
                </tr>
              );
            })
          : null}
        {currentPage === 1 && category === 'ALL'
          ? hotBoardItems.map((post: any) => {
              return (
                <tr id={post.boardId} key={post.boardId} className="best-post">
                  <td id={post.boardId} onClick={handleClickDetail} className="board-title">
                    <span className="best-cell">
                      HOT &nbsp;
                      <FontAwesomeIcon icon={faFire} />
                    </span>
                    [{post.tag}]&nbsp;
                    <span id={post.boardId}>{post.title}</span>
                    <span className="board-review-count">{post.reviewCount}</span>
                  </td>
                  <td className="post-information">
                    <div className="board-writer">{post.nickname}</div>
                    <div className="board-write-date">{post.writeDate}</div>
                    <div className="board-view-count">{post.view}</div>
                    <div className="board-like-count">{post.likedCount}</div>
                  </td>
                </tr>
              );
            })
          : null}
        {!!currentPosts &&
          currentPosts
            .filter((post: any) => post.authentication === 'N')
            .map((post: any) => {
              return (
                <tr id={post.boardId} key={post.boardId} className="board-wrap">
                  <td id={post.boardId} onClick={handleClickDetail} className="board-title">
                    [{post.tag}]&nbsp;
                    <span id={post.boardId}>{post.title}</span>
                    <span className="board-review-count">{post.reviewCount}</span>
                  </td>
                  <td className="post-information">
                    <div className="board-writer">{post.nickname}</div>
                    <div className="board-write-date">{post.writeDate}</div>
                    <div className="board-view-count">{post.view}</div>
                    <div className="board-like-count">{post.likedCount}</div>
                  </td>
                </tr>
              );
            })}
      </tbody>
    </table>
  );
};

export default PCBoard;
