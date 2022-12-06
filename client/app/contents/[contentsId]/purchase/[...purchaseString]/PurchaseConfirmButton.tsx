'use client';
import { getCookie } from 'cookies-next';
import { useRouter } from 'next/navigation';
import React from 'react';
import OrangeButton from '../../../../../components/Buttons/orangeButton';

interface Props {
	contentId: string;
	remainingAmount: string;
}

const PurchaseConfirmButton = ({ contentId, remainingAmount }: Props) => {
	const router = useRouter();
	const handlePurchase = async () => {
		const token = getCookie('accessToken');
		try {
			const res = await fetch(
				`https://pioneroroom.com/auth/${contentId}?payed=true`,
				{
					method: 'POST',
					headers: {
						Authorization: `Bearer ${token}`,
					},
				}
			);
			console.log(
				'🚀 ~ file: PurchaseConfirmButton.tsx:26 ~ handlePurchase ~ res',
				res
			);
			if (res.ok) {
				alert('결제가 완료되었습니다.');
				router.push(`/contents/${contentId}`);
			}
			const data = await res.json();
			console.log(
				'🚀 ~ file: PurchaseConfirmButton.tsx:27 ~ handlePurchase ~ data',
				data
			);
			if (data.status >= 400 && data.status < 500) {
				alert('결제에 실패하였습니다.');
				router.push(`/contents/${contentId}`);
			}
		} catch (e) {
			console.error(e);
		}
	};
	router.prefetch(`/contents/${contentId}`);
	router.prefetch(`/contents`);

	const handleToChargeCoin = () => {
		router.push('/charge');
	};

	return (
		<>
			{remainingAmount[0] === '+' ? (
				<OrangeButton name="구매하기" handleClick={handlePurchase} />
			) : (
				<OrangeButton
					name="코인 충전하기"
					handleClick={handleToChargeCoin}
				/>
			)}
		</>
	);
};

export default PurchaseConfirmButton;
