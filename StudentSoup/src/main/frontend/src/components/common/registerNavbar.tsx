import { useHistory } from 'react-router-dom';
import Reddit from '../../img/Reddit.svg';

const RegisterNavbar = () => {
  const history = useHistory();

  return (
    <div className="w-full h-[88px] items-center sticky flex justify-between border-b-[1px] border-[#FF611D]">
      <img src={Reddit} alt="" className="w-[162px] h-[72px]"
        onClick={() => {
          history.push('/');
        }} />
    </div>
  );
};

export default RegisterNavbar;
