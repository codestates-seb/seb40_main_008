import React from 'react';
import { BookmarkInfo } from '../../../../../types/videoPage/video';
import styles from './VideoPage.module.css';
interface Props {
	memoInfo: BookmarkInfo[];
}

const MemoPanel = ({ memoInfo }: Props) => {
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
						0: 00
					</div>
					<p>{memo.memo}</p>
				</div>
			))}
		</section>
	);
};

export default MemoPanel;
