import React from "react";
import styles from "./ContentInfo.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBookmark } from "@fortawesome/free-solid-svg-icons";
import OrangeButton from "../Buttons/orangeButton";
const ContentInfo = () => {
  return (
    <>
      <div className={styles.thumbnail}></div>

      <div className={styles.InfoWrapper}>
        <div className={styles.Info}>
          <div>
            <h3>카테고리&nbsp; 강사이름</h3>
          </div>
          <span>
            <FontAwesomeIcon icon={faBookmark} className={styles.font} />
            찜하기
          </span>
        </div>

        <div className={styles.classWrapper}>
          <div className={styles.classtitle}>
            <h2>강의 제목</h2>
          </div>
          <h2>5000원</h2>
        </div>
        <h3>별점 </h3>
        <div className={styles.btn}>
          <OrangeButton name={"강의 구매하기"} />
        </div>
      </div>
      <hr className={styles.line}></hr>
    </>
  );
};

export default ContentInfo;
