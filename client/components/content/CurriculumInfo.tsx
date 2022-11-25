import React from "react";
import styles from "./CurriculumInfo.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPencil } from "@fortawesome/free-solid-svg-icons";
const CurriculumInfo = () => {
  return (
    <div className={styles.Wrapper}>
      <div className={styles.thumbnail}>챕터썸네일</div>
      <div className={styles.chapterWrapper}>
        <div className={styles.chapterTitle}>
          <h4>CHAPTER 1</h4>
          <div className={styles.chapter}>
            <h3>챕터명</h3>
            <span>
              <button className={styles.btn}>수정</button>
              <button className={styles.btn}>삭제</button>
            </span>
          </div>

          <div className={styles.class}>
            <div>
              <h4>강의명1</h4>
              <h5>영상길이</h5>
            </div>
            <span>
              <button className={styles.btn}>수정</button>
              <button className={styles.btn}>삭제</button>
            </span>
          </div>

          <div className={styles.addbtnWrapper}>
            <button className={styles.addbtn}>
              <FontAwesomeIcon icon={faPencil} className={styles.fontimg} />
              강의 추가하기
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CurriculumInfo;
