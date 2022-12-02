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

interface VideoPageSectionProps {
	data: IVideoPage;
	contentsId: string;
	uploadClassId: string;
}

const VideoPageSection = ({
	data,
	contentsId,
	uploadClassId,
}: VideoPageSectionProps) => {
	const playerRef = useRef<ReactPlayer>(null);
	const [time, setTime] = useState('00:00');
	return (
		<>
			<VideoPlayer url={data.video} videoRef={playerRef} setTime={setTime} />
			<CustomTab
				tabs={['커리큘럼', '수업 자료', '댓글', '메모하기']}
				contents={getPanels(data, contentsId, uploadClassId, time)}
			/>
		</>
	);
};

function getPanels(
	data: IVideoPage,
	contentsId: string,
	uploadClassId: string,
	time: string
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
		/>,
	];
}

export default VideoPageSection;
