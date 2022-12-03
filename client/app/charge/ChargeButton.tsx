'use client';
import { getCookie } from 'cookies-next';
import { useRouter } from 'next/navigation';
import React from 'react';
import styles from './ChargeCoin.module.css';
interface Props {
	amount: 'FIVE' | 'TEN' | 'TWENTY' | 'FIFTY';
}

const ChargeButton = ({ amount }: Props) => {
	const router = useRouter();
	const handleCharge = async () => {
		let formData = new FormData();
		formData.append('chargeAmount', amount);

		const token = getCookie('accessToken');
		const res = await fetch('https://pioneroroom.com/auth/coincharge/ready', {
			method: 'POST',
			headers: {
				Authorization: `Bearer ${token}`,
			},
			body: formData,
		});
		const { data } = await res.json();
		console.log(
			'🚀 ~ file: ChargeButton.tsx:24 ~ handleCharge ~ data ',
			data
		);
		if (window.innerWidth > 768) {
			window.location = data.next_redirect_pc_url;
		} else {
			window.location = data.next_redirect_mobile_url;
		}
	};
	return (
		<button className={styles.chargeBtn} onClick={handleCharge}>
			충전하기
		</button>
	);
};

export default ChargeButton;
