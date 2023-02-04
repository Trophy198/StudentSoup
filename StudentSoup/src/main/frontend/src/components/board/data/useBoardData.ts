import axios from 'axios';
import { useState } from 'react';
import { BoardListType } from '../content/boardListComponent';

interface RequestType {
  column: string; // 검색 카테고리
  value: string; // 검색 내용

  category: string; // 게시판 카테고리
  sorted: number; // 정렬 0~4
  page: number; // 게시판 페이지
  size: number; // 게시판 크기 (default=12)
}

interface DataResType {
  boards: { content: BoardListType[] };
  bestBoards?: BoardListType[];
  hotBoards?: BoardListType[];
}

const useBoardData = () => {
  const getBoardList = (request: RequestType, callback: (data: DataResType) => void) => {
    const { category, sorted, page, size = 12, column, value } = request;

    const schoolId = Number(sessionStorage.getItem('schoolId')) ?? 0;
    const memberId = Number(sessionStorage.getItem('memberId')) ?? 0;
    const departmentId = Number(sessionStorage.getItem('departmentId')) ?? 0;

    const body = {
      schoolId,
      memberId,
      departmentId,
    };

    axios
      .post(
        `/boards?category=${category}&sorted=${sorted}&page=${page}&size=${size}&column=${column}&value=${value}`,
        body,
      )
      .then(res => {
        callback(res.data);
      })
      .catch(err => {
        console.error(err);
      });
  };

  return { getBoardList };
};

export default useBoardData;
