import Image from 'next/image';
import React from 'react';
import { ReviewInfo } from '../../../../../types/videoPage/video';
import styles from './VideoPage.module.css';
interface Props {
	reviews: ReviewInfo[];
}
/**
 *  {
      "usersId": 2,
      "reviewId": 1,
      "starRate": 3,
      "comments": "이것은 새로운 리뷰이다",
      "createdAt": "2022-12-01T11:07:48.577521+09:00",
      "modifiedAt": "2022-12-01T11:07:51.366907+09:00"
    }
 */

const CommentsPanel = ({ reviews }: Props) => {
	console.log(
		'🚀 ~ file: CommentsPanel.tsx:20 ~ CommentsPanel ~ reviews',
		reviews
	);
	return (
		<section className={styles.container}>
			{reviews.map((review) => (
				<div
					key={review.reviewId}
					style={{
						display: 'flex',
						flexDirection: 'row',
						justifyContent: 'flex-start',
						alignItems: 'flex-start',
						border: '1px solid white',
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
							justifyContent: 'flex-start',
							alignItems: 'flex-start',
							border: '1px dotted red',
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
								border: '1px dotted purple',
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
