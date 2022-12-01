import Image from 'next/image';
import React from 'react';
import { ReviewInfo } from '../../../../../types/videoPage/video';
import styles from './VideoPage.module.css';
interface Props {
	reviews: ReviewInfo[];
}

const CommentsPanel = ({ reviews }: Props) => {
	return (
		<section
			style={{
				padding: '20px',
			}}
		>
			{reviews.map((review) => (
				<div
					key={review.reviewId}
					style={{
						display: 'flex',
						flexDirection: 'row',
						justifyContent: 'flex-start',
						alignItems: 'center',
						height: '80px',
					}}
				>
					<Image
						src={'/img/myimg.png'}
						alt="Picture of the author"
						width={42}
						height={42}
					/>
					<div
						style={{
							display: 'flex',
							flexDirection: 'column',
							justifyContent: 'center',
							alignItems: 'flex-start',
							height: '80px',
							width: '200px',
							marginLeft: '10px',
						}}
					>
						<div
							style={{
								display: 'flex',
								flexDirection: 'row',
								justifyContent: 'flex-start',
								alignItems: 'flex-start',
							}}
						>
							<p
								style={{
									marginRight: '8px',
								}}
							>
								{'username'}
							</p>
							<p>{formatTime(review.createdAt)}</p>
						</div>
						<p>{review.comments}</p>
					</div>
				</div>
			))}
		</section>
	);
};

function formatTime(time: string) {
	const date = new Date(time);
	const year = date.getFullYear();
	const month = date.getMonth();
	const day = date.getDate();
	const hour = date.getHours();
	const minutes = date.getMinutes();
	return `${year}-${month}-${day}`;
}

export default CommentsPanel;
