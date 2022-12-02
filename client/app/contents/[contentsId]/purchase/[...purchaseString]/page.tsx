import React from 'react';
import BaseNavbar from '../../../../../components/BaseNavBar/BaseNavbar';
import OrangeButton from '../../../../../components/Buttons/orangeButton';
import PageHeader from '../../../../../components/Text/PageHeader';
import verifyLogin from '../../../../../utils/VerifyLogin';
import UpperTextContainer from './UpperTextContainer';

interface Props {
	searchParams: {
		contentId: string;
		price: string;
		title: string;
		thumbnail: string;
		tutorName: string;
	};
}

const PurchaseStringPage = async ({ searchParams }: Props) => {
	const { contentId, price, title, thumbnail, tutorName } = searchParams;
	const userInfo = await verifyLogin();
	return (
		<>
			<BaseNavbar />
			<PageHeader
				text="구매 내역"
				style={{
					marginTop: '100px',
				}}
			/>
			<div
				style={{
					position: 'relative',
					display: 'flex',
					justifyContent: 'flex-start',
					alignItems: 'center',
					flexDirection: 'column',
					height: '80vh',
					border: '1px solid red',
				}}
			>
				<div
					style={{
						display: 'flex',
						justifyContent: 'center',
						alignItems: 'center',
						flexDirection: 'column',
						border: '1px solid white',
						width: '92%',
						// height: '80vh',
					}}
				>
					<UpperTextContainer
						tutorName={tutorName}
						title={title}
						thumbnail={thumbnail}
					/>
					<div
						style={{
							marginTop: '40px',
							width: '92%',
							height: '2px',
							backgroundColor: 'white',
						}}
					></div>
					<div
						style={{
							display: 'flex',
							justifyContent: 'space-between',
							alignItems: 'center',
							width: '92%',
							height: '100px',
						}}
					>
						<h3>총 금액</h3>
						<h3>{price}원</h3>
					</div>
					<div
						style={{
							display: 'flex',
							justifyContent: 'space-between',
							alignItems: 'center',
							width: '92%',
							height: '100px',
						}}
					>
						<h3>보유 코인</h3>
						<h3>{userInfo?.totalCoin || '출력 불가'}</h3>
					</div>
					<div
						style={{
							display: 'flex',
							justifyContent: 'space-between',
							alignItems: 'center',
							width: '92%',
							height: '100px',
						}}
					>
						<h2>구매 후 잔액</h2>
						<h2>{getRemainingCoin(userInfo?.totalCoin, price)}</h2>
					</div>
				</div>
				<div
					style={{
						position: 'absolute',
						bottom: '0',
						border: '1px solid white',
						width: '92%',
					}}
				>
					<div
						style={{
							display: 'flex',
							justifyContent: 'flex-end',
							alignItems: 'center',
						}}
					>
						<p
							style={{
								color: '#aaaaaa',
								marginBottom: '8px',
							}}
						>
							잔액이 마이너스일 경우, 충전하기
						</p>
					</div>
					<OrangeButton name="구매하기" />
				</div>
			</div>
		</>
	);
};

function getRemainingCoin(totalCoin: number | undefined, price: string) {
	if (!totalCoin) return '출력 불가';
	const amount = totalCoin - parseInt(price);
	if (amount < 0) return `${amount}`;
	return `+${amount}원`;
}

export default PurchaseStringPage;
