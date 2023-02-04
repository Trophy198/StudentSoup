import axios from 'axios';
import { ChangeEvent, useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import Reddit from '../../img/Reddit.svg';
import Board from '../../img/board.jpg';
import Restaurant from '../../img/restaurant.jpg';
import Faq from '../../img/faq.jpg';
import Logout from '../../img/logout.jpg';

const RestaurantNavbar = () => {
  const history = useHistory();

  const IMAGE_FILE_ID = String(sessionStorage.getItem('fileName'));
  const userId = sessionStorage.getItem('id');

  const [isLogin, setIsLogin] = useState<boolean>(false);
  const [searchSchool, setSearchSchool] = useState<any[]>();
  const [posts, setPosts] = useState<any[]>();
  const [inputSchool, setInputSchool] = useState<string>();
  const [listSchool, setListSchool] = useState<string>('');

  useEffect(() => {
    if (userId === null) {
      setIsLogin(false);
    } else {
      setIsLogin(true);
    }
  }, []);

  const getSchool = () => {
    axios
      .get('/home')
      .then(res => {
        setPosts(res.data);
      })
      .catch(err => {
        console.error(err);
      });
  };

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    setInputSchool(value);
    if (value.length === 0 || value === '' || value === null || value === undefined) {
      setSearchSchool(undefined);
      return;
    }
    const resultArray = posts?.filter(post => post.schoolName.includes(e.target.value));
    const compareResult = posts?.filter(post => post.schoolName.includes(e.target.value));
    setSearchSchool(resultArray);
    setListSchool(compareResult?.shift().schoolName);
  };

  const handleClick = (e: any) => {
    setInputSchool(e.target.innerText);
    const resultArray = posts?.filter(post => post.schoolName.includes(e.target.innerText));
    const compareResult = posts?.filter(post => post.schoolName.includes(e.target.value));
    setSearchSchool(resultArray);
    setListSchool(compareResult?.shift().schoolName);
  };

  const handlePushRestaurant = () => {
    if (inputSchool === '' || inputSchool === undefined) {
      alert('학교를 검색해주세요');
    } else if (inputSchool === listSchool) {
      history.push('/restaurant', inputSchool);
    } else {
      alert('학교 정보가 올바르지 않습니다.');
    }
  };

  const handleOnKeyPress = (e: any) => {
    if (e.key === 'Enter') {
      handlePushRestaurant();
    }
  };

  const handleLogin = (e: any) => {
    const value = e.target.innerText;
    if (value === 'LOGOUT') {
      if (sessionStorage.getItem('saved') === String(true)) {
        sessionStorage.removeItem('email');
        sessionStorage.removeItem('nickname');
        sessionStorage.removeItem('departmentId');
        sessionStorage.removeItem('departmentName');
        sessionStorage.removeItem('fileName');
        sessionStorage.removeItem('memberId');
        sessionStorage.removeItem('schoolId');
        sessionStorage.removeItem('schoolName');
        sessionStorage.removeItem('registrationDate');
        history.push('/');
      } else {
        sessionStorage.clear();
        history.push('/');
      }
    } else if (value === 'LOGIN') {
      history.push('/login');
    }
  };

  const handleMypage = () => {
    if (userId === null) {
      alert('로그인 후 이용가능합니다.');
    } else {
      history.push('/mypage');
    }
  };

  return (
    <div className="w-full h-[80px] items-center sticky flex justify-between border-b-[1px] border-[#FF611D] z-[2] shadow-lg">
      <div className="flex items-center gap-x-[32px]">
        <img
          src={Reddit}
          alt=""
          className="w-[162px] h-[72px] cursor-pointer"
          onClick={() => {
            history.push('/');
          }}
        />
        <div className="w-[466px] h-[44px] px-[23px] flex items-center gap-x-3 border-none rounded-[5px] bg-[#E8E8E8]">
          <svg
            width="25"
            height="24"
            viewBox="0 0 25 24"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M10.2681 3.15693C8.42418 3.15693 6.65583 3.88941 5.35202 5.19322C4.04821 6.49704 3.31573 8.26539 3.31573 10.1093C3.31573 11.9531 4.04821 13.7215 5.35202 15.0253C6.65583 16.3291 8.42418 17.0616 10.2681 17.0616C12.1119 17.0616 13.8803 16.3291 15.1841 15.0253C16.4879 13.7215 17.2204 11.9531 17.2204 10.1093C17.2204 8.26539 16.4879 6.49704 15.1841 5.19322C13.8803 3.88941 12.1119 3.15693 10.2681 3.15693ZM0.998291 10.1093C0.998502 8.63404 1.35079 7.18018 2.02589 5.8685C2.70098 4.55683 3.67939 3.42521 4.87978 2.56771C6.08017 1.7102 7.46788 1.15158 8.92759 0.938253C10.3873 0.724929 11.8768 0.863069 13.2724 1.34119C14.668 1.81932 15.9293 2.62361 16.9516 3.68724C17.9738 4.75087 18.7274 6.04311 19.1498 7.45657C19.5721 8.87003 19.651 10.3639 19.38 11.814C19.1089 13.2641 18.4956 14.6285 17.5912 15.7939L23.8332 22.036C24.0443 22.2545 24.1611 22.5472 24.1584 22.851C24.1558 23.1548 24.0339 23.4454 23.8191 23.6603C23.6042 23.8751 23.3136 23.997 23.0098 23.9996C22.706 24.0023 22.4133 23.8855 22.1948 23.6744L15.9527 17.4324C14.5825 18.4962 12.9412 19.1542 11.2156 19.3315C9.48998 19.5088 7.74922 19.1984 6.19127 18.4354C4.63332 17.6725 3.3207 16.4878 2.40269 15.0158C1.48467 13.5439 0.998098 11.844 0.998291 10.1093Z"
              fill="#767676"
            />
          </svg>
          <input
            onChange={handleChange}
            onKeyDown={handleOnKeyPress}
            type="text"
            value={inputSchool}
            placeholder="학교 명을 입력하세요"
            className="w-full text-[#717171] bg-transparent outline-0"
          ></input>
          <button onClick={handlePushRestaurant} className="hidden">
            검색
          </button>
        </div>
      </div>
      <div className="flex items-center mr-[32px] m-5">
        <div className="flex justify-center items-center w-[100px] cursor-pointer">
          <img src={Board} alt="" className="mr-[13.6px] w-[14.4px] h-[16px]" />
          <span
            className="text-[16px] fw-400 leading-[19px] text-[#353535] mr-[16px]"
            onClick={() => {
              history.push('/board');
            }}
          >
            BOARD
          </span>
        </div>
        <span className="w-[1px] h-[30.5px] bg-[#B1B1B1] mr-[16px]"></span>
        <div className="flex justify-center items-center w-[150px] cursor-pointer">
          <img src={Restaurant} alt="" className="mr-[10px] w-[16px] h-[16px]" />
          <span
            className="text-[16px] fw-400 leading-[19px] text-[#353535] mr-[16px]"
            onClick={() => {
              history.push('/restaurant');
            }}
          >
            RESTAURANT
          </span>
        </div>
        <span className="w-[1px] h-[30.5px] bg-[#B1B1B1] mr-[19px]"></span>
        <div
          onClick={handleLogin}
          className="flex justify-center items-center w-[110px] cursor-pointer"
        >
          <img src={Logout} alt="" className="mr-[6px] w-[16px] h-[16px]" />
          {isLogin ? (
            <span className="text-[16px] fw-400 leading-[19px] text-[#353535] mr-[30px]">
              LOGOUT
            </span>
          ) : (
            <span className="text-[16px] fw-400 leading-[19px] text-[#353535] mr-[30px]">
              LOGIN
            </span>
          )}
        </div>
        <div className="flex flex-col items-center cursor-pointer">
          <img
            src={IMAGE_FILE_ID}
            className='relative top-[34px] bg-cover w-[40px] h-[40px] bg-[url("./img/circle_human.png")] rounded-full mb-[70px]'
            onClick={handleMypage}
          />
        </div>
      </div>
    </div>
  );
};

export default RestaurantNavbar;
