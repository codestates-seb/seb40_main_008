import React from 'react';
import BaseNavbar from '../../components/BaseNavBar/BaseNavbar';
import styles from './ChargeCoin.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCoins } from '@fortawesome/free-solid-svg-icons';
import OrangeButton from '../../components/Buttons/orangeButton';
import ChargeButton from './ChargeButton';
import Link from 'next/link';
import verifyLogin from "../../utils/VerifyLogin";

const ChargeCoin = async () => {

	const userInfo = await verifyLogin();

	return (
		<>
			<BaseNavbar />
			<div className={styles.TitleWrapper}>
				<h1 style={{ padding: '10px' }}>코인 충전</h1>
				<h2>보유 코인 {userInfo?.totalCoin}원</h2>
			</div>
			<div className={styles.line}></div>

			<div className={styles.ChargeWrapper}>
				<div className={styles.charge}>
					<div className={styles.coinwrap}>
						<FontAwesomeIcon
							icon={faCoins}
							width={24}
							className={styles.coinfont}
						/>
						<h2>5000원</h2>
					</div>
					<ChargeButton amount={'FIVE'} />
				</div>

				<div className={styles.charge}>
					<div className={styles.coinwrap}>
						<FontAwesomeIcon
							icon={faCoins}
							width={24}
							className={styles.coinfont}
						/>
						<h2>10000원</h2>
					</div>
					<ChargeButton amount={'TEN'} />
				</div>

				<div className={styles.charge}>
					<div className={styles.coinwrap}>
						<FontAwesomeIcon
							icon={faCoins}
							width={24}
							className={styles.coinfont}
						/>
						<h2>20000원</h2>
					</div>
					<ChargeButton amount={'TWENTY'} />
				</div>
				<div className={styles.charge}>
					<div className={styles.coinwrap}>
						<FontAwesomeIcon
							icon={faCoins}
							width={24}
							className={styles.coinfont}
						/>
						<h2>50000원</h2>
					</div>
					<ChargeButton amount={'FIFTY'} />
				</div>
			</div>

			<Link href='/mypage/coinhistory'><OrangeButton name={'충전 내역보기'}></OrangeButton></Link>
		</>
	);
};
export default ChargeCoin;
