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
			const data = await res.json();
			if (data.status === 200) {
				alert('결제가 완료되었습니다.');
				router.push(`/contents/${contentId}`);
			}
			if (data.status >= 400 && data.status < 500) {
				alert('결제에 실패하였습니다.');
				router.push(`/contents/${contentId}`);
			}
		} catch (e) {
			console.error(e);
		}
	};

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
