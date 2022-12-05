import { AnyTxtRecord } from "dns";
import React from "react";
import { IChargeCoin } from "../../../types/chargeCoin";
import styles from "./coinhistorySection.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCoins } from "@fortawesome/free-solid-svg-icons";
interface CoinHistorySectionProps {
  data: IChargeCoin;
}

const CoinHistorySection = ({ data }: CoinHistorySectionProps) => {
  console.log("Adaseeae", data.coinChargeDetails);

  const getCoinHow = (
    paySuccess: any,
    refund: any,
    chargeamount: number,
    cancelamount: number,
    canceled_at: any,
    approvedAt: any
  ) => {
    if (paySuccess === true && refund === false) {
      return (
        <div className={styles.CoinContents}>
          <span>코인 충전</span>
          <span>+{chargeamount}</span>
          <span> {approvedAt.slice(0, 10)}</span>
        </div>
      );
    } else if (paySuccess === null && refund === true) {
      return (
        <div className={styles.CoinContents}>
          <span>강좌 구매</span>
          <span>-{cancelamount}</span>
          <span> {canceled_at.slice(0, 10)}</span>
        </div>
      );
    } else if (paySuccess === true && refund === null) {
      return (
        <div className={styles.CoinContents}>
          <span>판매 수익</span>
          <span>+{chargeamount}</span>
          <span> {approvedAt.slice(0, 10)}</span>
        </div>
      );
    }
  };

  return (
    <>
      <section className={styles.coinHistoryWrapper}>
        <div className={styles.title}>
          <h1 className={styles.h1}>코인내역</h1>
          <div className={styles.h3}>
            <FontAwesomeIcon
              icon={faCoins}
              width={24}
              className={styles.coinfont}
            />
            {data.totalCoin}
          </div>
        </div>

        <div className={styles.coinBoard}>
          <div className={styles.boardtitle}>
            <p> 내역</p>
            <p> 금액</p>
            <p> 날짜</p>
          </div>
        </div>

        {data.coinChargeDetails.length === 0 ? (
          <div className={styles.noclassWrapper}>
            내가올린 클래스가 없습니다.
          </div>
        ) : (
          data.coinChargeDetails.map((e, idx) => (
            <div key={idx} className={styles.board}>
              {getCoinHow(
                e.paySuccess,
                e.refund,
                e.chargeAmount,
                e.cancelAmount,
                e.canceled_at,
                e.approvedAt
              )}
              <div className={styles.line} />
            </div>
          ))
        )}
      </section>
    </>
  );
};

export default CoinHistorySection;
