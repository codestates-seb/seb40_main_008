'use client';
import React, { useEffect, useRef, useState } from 'react';
import { IVideoPage } from '../../../../../types/videoPage/video';
import VideoPlayer from './VideoPlayer';
import ReactPlayer from 'react-player';
import { CustomTab } from '../../../../../components/Tab/CustomTab';
import CurriculumPanel from './CurriculumPanel';
import HandOutsPanel from './HandOutsPanel';
import CommentsPanel from './CommentsPanel';
import MemoPanel from './MemoPanel';
import Link from 'next/link';

interface VideoPageSectionProps {
	data: IVideoPage;
	contentsId: string;
	uploadClassId: string;
	status: string;
}

const VideoPageSection = ({
	data,
	contentsId,
	uploadClassId,
	status,
}: VideoPageSectionProps) => {
	const playerRef = useRef<ReactPlayer>(null);
	const [time, setTime] = useState('00:00');
	return (
		<>
			{status === 'Unpaid_customer' ? (
				<section
					style={{
						display: 'flex',
						justifyContent: 'flex-start',
						paddingTop: '250px',
						alignItems: 'center',
						height: '100vh',
						flexDirection: 'column',
					}}
				>
					<h2>강의를 먼저 구매해주셔야 합니다</h2>
					<Link
						style={{
							marginTop: '25px',
						}}
						href={`/contents/${contentsId}`}
					>
						뒤로가기
					</Link>
				</section>
			) : (
				<>
					<VideoPlayer
						url={data.video}
						videoRef={playerRef}
						setTime={setTime}
					/>
					<CustomTab
						tabs={['커리큘럼', '수업 자료', '댓글', '메모하기']}
						contents={getPanels(
							data,
							contentsId,
							uploadClassId,
							time,
							playerRef
						)}
					/>
				</>
			)}
		</>
	);
	return (
		<>
			<CustomTab
				tabs={['커리큘럼', '수업 자료', '댓글', '메모하기']}
				contents={getPanels(
					data,
					contentsId,
					uploadClassId,
					time,
					playerRef
				)}
			/>
		</>
	);
};

function getPanels(
	data: IVideoPage,
	contentsId: string,
	uploadClassId: string,
	time: string,
	playerRef: React.RefObject<ReactPlayer>
) {
	return [
		<CurriculumPanel
			curriculumInfo={data.curriculumInfo}
			key={1}
			contentsId={contentsId}
		/>,
		<HandOutsPanel handOutInfo={data.docsInfo} key={2} />,
		<CommentsPanel
			reviews={data.reviewInfo}
			key={3}
			uploadClassId={uploadClassId}
		/>,
		<MemoPanel
			memoInfo={data.bookmarkInfo}
			key={4}
			time={time}
			uploadClassId={uploadClassId}
			playerRef={playerRef}
		/>,
	];
}

export default VideoPageSection;
