'use client';
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { getCookie } from 'cookies-next';
import React, { useState } from 'react';
import ReactPlayer from 'react-player';
import { BookmarkInfo } from '../../../../../types/videoPage/video';
import styles from './VideoPage.module.css';
interface Props {
	uploadClassId: string;
	memoInfo: BookmarkInfo[];
	time: string;
	playerRef: React.RefObject<ReactPlayer>;
}

const MemoPanel = ({ uploadClassId, memoInfo, time, playerRef }: Props) => {
	const [memo, setMemo] = useState('');

	const handleSubmit = (time: string) => {
		const token = getCookie('accessToken');
		console.log('🚀 ~ file: MemoPanel.tsx:19 ~ handleSubmit ~ token', token);
		fetch(`https://pioneroroom.com/auth/bookmark/${uploadClassId}`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
				Authorization: `Bearer ${token}`,
			},
			body: JSON.stringify({
				memo: memo,
				timeLine: time,
			}),
		});
	};

	const handleSkipToTimeStamp = () => {
		if (playerRef.current) {
			console.log(
				'🚀 ~ file: MemoPanel.tsx:50 ~ handleSkipToTimeStamp ~ time',
				time
			);
			// format hh:mm:ss to seconds
			const timeArr = time.split(':');
			const seconds =
				parseInt(timeArr[0]) * 3600 +
				parseInt(timeArr[1]) * 60 +
				parseInt(timeArr[2]);
			console.log(
				'🚀 ~ file: MemoPanel.tsx:40 ~ handleSkipToTimeStamp ~ seconds',
				seconds
			);
			// playerRef.current.seekTo(seconds);
		}
	};

	return (
		<section className={styles.container}>
			{memoInfo.map((memo) => (
				<div
					key={memo.bookmarkId}
					style={{
						display: 'flex',
						flexDirection: 'row',
						justifyContent: 'flex-start',
						alignItems: 'flex-start',
						border: '1px solid white',
						marginBottom: '16px',
					}}
				>
					<div
						style={{
							marginRight: '16px',
						}}
					>
						{/* time stamp, on clicked, goes to specific time of video */}
						{/* <button onClick={handleSkipToTimeStamp}>{time}</button> */}
						<button onClick={handleSkipToTimeStamp}>00:00:01</button>
					</div>
					<p>{memo.memo}</p>
				</div>
			))}
			<input
				style={{
					width: '80%',
					height: '30px',
					border: '1px solid white',
					borderRadius: '5px',
					backgroundColor: '#d9d9d926',
					color: 'white',
					paddingLeft: '10px',
					marginRight: '10px',
				}}
				type="text"
				value={memo}
				onChange={(e) => setMemo(e.target.value)}
			/>
			<FontAwesomeIcon
				onClick={() => handleSubmit(time)}
				size={'lg'}
				icon={faPaperPlane}
				width={24}
			/>
		</section>
	);
};

export default MemoPanel;
