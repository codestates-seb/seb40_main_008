import React from "react";
import BaseNavbar from "../../components/BaseNavBar/BaseNavbar";
import styles from "./ChargeCoin.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCoins } from "@fortawesome/free-solid-svg-icons";
import Button from "../../components/Buttons/Button";

const ChargeCoin = () => {
  return (
    <>
      <BaseNavbar />
      <div className={styles.TitleWrapper}>
        <h1 style={{ padding: "10px" }}>코인 충전</h1>
        <h2>보유 코인 원</h2>
      </div>
      <div className={styles.line}></div>

      <div className={styles.ChargeWrapper}>
        <div className={styles.charge}>
          <FontAwesomeIcon icon={faCoins} className={styles.coinfont} />
          <h2>5000원</h2>
          <button className={styles.chargeBtn}> $ 충전하기 </button>
        </div>
        <div className={styles.charge}>
          <FontAwesomeIcon icon={faCoins} className={styles.coinfont} />
          <h2>10000원</h2>
          <button className={styles.chargeBtn}> $ 충전하기 </button>
        </div>
        <div className={styles.charge}>
          <FontAwesomeIcon icon={faCoins} className={styles.coinfont} />
          <h2>20000원</h2>
          <button className={styles.chargeBtn}> $ 충전하기 </button>
        </div>
        <div className={styles.charge}>
          <FontAwesomeIcon icon={faCoins} className={styles.coinfont} />
          <h2>50000원</h2>
          <button className={styles.chargeBtn}> $ 충전하기 </button>
        </div>
      </div>

      <Button name={"충전 내역보기"}></Button>
    </>
  );
};
export default ChargeCoin;
