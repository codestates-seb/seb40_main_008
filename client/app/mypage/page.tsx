import React from "react";
import BaseNavbar from "../../components/BaseNavBar/BaseNavbar";
import styles from "./Mypage.module.css";
import Link from "next/link";
import Image from "next/image";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCoins,
  faPenToSquare,
  faPencil,
} from "@fortawesome/free-solid-svg-icons";

const MyPage = () => {
  return (
    <>
      <BaseNavbar />
      <div className={styles.myinfo}>
        <Link href={"/editmypage"}>
          <Image
            className={styles.myimg}
            alt="myimg"
            src="/img/myimg.png"
            width={70}
            height={70}
          />
          닉네임 &#62;
        </Link>
        <h3 className={styles.id}>아이디</h3>

        <div className={styles.Wrapper}>
          <FontAwesomeIcon icon={faCoins} className={styles.coinfont} />
          <h3 className={styles.mycoin}>코인갯수</h3>
        </div>
        <hr className={styles.line}></hr>
      </div>

      <div className={styles.mycorner}>
        <div className={styles.CourseWrapper}>
          <FontAwesomeIcon icon={faCoins} className={styles.fontimg} />
          <h2 className={styles.font}>코인 충전하기</h2>
        </div>
        <div className={styles.CourseWrapper}>
          <FontAwesomeIcon icon={faPencil} className={styles.fontimg} />
          <h2 className={styles.font}>내가 올린 클래스</h2>
        </div>
        <div className={styles.CourseWrapper}>
          <FontAwesomeIcon icon={faPenToSquare} className={styles.fontimg} />
          <h2 className={styles.font}>강좌 개설하기</h2>
        </div>
        <button className={styles.logoutBtn}>로그아웃</button>
      </div>
    </>
  );
};

export default MyPage;
