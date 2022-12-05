import React from "react";
import BaseNavbar from "../../../components/BaseNavBar/BaseNavbar";
import styles from "./coinhistory.module.css";
const coinHistory = () => {
  return (
    <>
      <BaseNavbar page={"back"} />
      <section className={styles.coinHistoryWrapper}>
        <div className={styles.title}>
          <h1 className={styles.h1}>코인내역</h1>
          <h3 className={styles.h3}>보유 코인 200원</h3>
        </div>

        <div className={styles.coinBoard}>
          <div className={styles.boardtitle}>
            <p> 내역</p>
            <p> 금액</p>
            <p> 날짜</p>
          </div>

          <div className={styles.board}>
            <div className={styles.CoinContents}>
              <span>코안충전</span>
              <span>5000원</span>
              <span> 2022년 10.12</span>
            </div>
            <div className={styles.line} />
          </div>
        </div>
      </section>
    </>
  );
};

export default coinHistory;
