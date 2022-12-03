'use client';
import { getCookie } from 'cookies-next';
import React from 'react';
import styles from './ChargeCoin.module.css';
interface Props {
	amount: 'FIVE' | 'TEN' | 'TWENTY' | 'FIFTY';
}

const ChargeButton = ({ amount }: Props) => {
	const handleCharge = async () => {
		const token = getCookie('accessToken');

		const res = await fetch('https://pioneroroom.com/auth/coincharge/ready', {
			method: 'POST',
			headers: {
				Authorization: `Bearer ${token}`,
			},
			body: JSON.stringify({
				chargeAmount: amount,
			}),
		});
		console.log('🚀 ~ file: ChargeButton.tsx:24 ~ handleCharge ~ res', res);
	};

	return (
		<button className={styles.chargeBtn} onClick={handleCharge}>
			충전하기
		</button>
	);
};

export default ChargeButton;
