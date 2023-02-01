import React, { useEffect, useState } from 'react';

const reviewMoreStarView = (data: any) => {
  const reviewStarArr = [0, 1, 2, 3, 4];
  const [rating, setRating] = useState(data.starLiked);
  return (
    <>
      <div className="ml-[12px] flex flex-row">
        {reviewStarArr.map((star, index) => {
          index += 1;
          return (
            <>
              <span key={star}>
                <svg
                  key={star}
                  className={
                    index <= rating ? 'mt-[5.23px] fill-[#FFB21D]' : 'mt-[5.23px] fill-[#CDCDCD] '
                  }
                  width="19"
                  height="17"
                  viewBox="0 0 19 17"
                  fill="none"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    fill="correct"
                    d="M14.9944 10.2055C14.7656 10.4222 14.6605 10.7356 14.7126 11.043L15.4978 15.291C15.5641 15.651 15.4086 16.0154 15.1004 16.2235C14.7983 16.4393 14.3964 16.4652 14.0669 16.2925L10.1549 14.2981C10.0189 14.2273 9.86784 14.1893 9.71326 14.1849H9.47389C9.39087 14.197 9.3096 14.2229 9.23541 14.2627L5.32249 16.2666C5.12905 16.3616 4.91 16.3953 4.69536 16.3616C4.17246 16.2649 3.82357 15.7779 3.90925 15.2642L4.69536 11.0162C4.74748 10.7063 4.64237 10.3911 4.4136 10.171L1.22408 7.14903C0.957333 6.89605 0.864588 6.51615 0.986481 6.17337C1.10484 5.83146 1.40692 5.58194 1.77171 5.52582L6.1616 4.9033C6.49548 4.86963 6.78873 4.67104 6.93889 4.37748L8.87326 0.500777C8.91919 0.414436 8.97838 0.335003 9.04992 0.267657L9.12941 0.207218C9.17093 0.162321 9.21863 0.125194 9.27162 0.094975L9.3679 0.0604386L9.51806 0H9.88992C10.222 0.0336729 10.5144 0.22794 10.6672 0.518045L12.6272 4.37748C12.7685 4.65982 13.0432 4.85581 13.3603 4.9033L17.7502 5.52582C18.1212 5.57762 18.4312 5.82801 18.554 6.17337C18.6697 6.5196 18.5699 6.8995 18.2978 7.14903L14.9944 10.2055Z"
                  />
                </svg>
              </span>
            </>
          );
        })}
      </div>
    </>
  );
};

export default reviewMoreStarView;