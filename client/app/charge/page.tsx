import React from "react";
import BaseNavbar from "../../components/BaseNavBar/BaseNavbar";
import styles from "./ChargeCoin.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCoins } from "@fortawesome/free-solid-svg-icons";
import OrangeButton from "../../components/Buttons/orangeButton";

const ChargeCoin = () => {
  return (
    <>
      <BaseNavbar />
      <div className={styles.TitleWrapper}>
        <div style={{ marginBottom: "20px", fontSize: "26px", fontWeight: "bold" }}>코인 충전</div>
        <div style={{ fontSize: "18px", fontWeight: "bold" }}>보유 코인 원</div>
      </div>

      <div className={styles.ChargeWrapper}>
        <div className={styles.charge}>
          <div className={styles.coinwrap}>
            <FontAwesomeIcon icon={faCoins} width={24} className={styles.coinfont} />
            <div className={styles.price}>5,000원</div>
          </div>
          <button className={styles.chargeBtn}> 충전하기 </button>
        </div>

        <div className={styles.charge}>
          <div className={styles.coinwrap}>
            <FontAwesomeIcon icon={faCoins} width={24} className={styles.coinfont} />
            <div className={styles.price}>10,000원</div>
          </div>
          <button className={styles.chargeBtn}> 충전하기 </button>
        </div>

        <div className={styles.charge}>
          <div className={styles.coinwrap}>
            <FontAwesomeIcon icon={faCoins} width={24} className={styles.coinfont} />
            <div className={styles.price}>20,000원</div>
          </div>
          <button className={styles.chargeBtn}> 충전하기 </button>
        </div>
        <div className={styles.charge}>
          <div className={styles.coinwrap}>
            <FontAwesomeIcon icon={faCoins} width={24} className={styles.coinfont} />
            <div className={styles.price}>50,000원</div>
          </div>
          <button className={styles.chargeBtn}> 충전하기 </button>
        </div>
      </div>

      <OrangeButton name={"충전 내역보기"}></OrangeButton>
    </>
  );
};
export default ChargeCoin;
