import Image from 'next/image';
import React from 'react';
import { ReviewInfo } from '../../../../../types/videoPage/video';
import VideoComment from './VideoComment';
interface Props {
	reviews: ReviewInfo[];
	uploadClassId: string;
}

const CommentsPanel = ({ reviews, uploadClassId }: Props) => {

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
						src={review?.profileImage}
						alt="Picture of the author"
						sizes="(max-width: 768px) 100vw,
						(max-width: 1200px) 50vw,
						33vw"
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
								{review.userName}
							</p>
							<p>{formatTime(review.createdAt)}</p>
						</div>
						<p>{review.comments}</p>
					</div>
				</div>
			))}
			<VideoComment uploadClassId={uploadClassId} />
		</section>
	);
};

function formatTime(time: string) {
	const date = new Date(time);
	const year = date.getFullYear();
	const month = date.getMonth() + 1;
	const day = date.getDate();
	const hour = date.getHours();
	const minutes = date.getMinutes();
	return `${year}-${month}-${day}`;
}

export default CommentsPanel;
