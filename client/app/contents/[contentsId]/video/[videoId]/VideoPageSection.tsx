'use client';
import React, { useRef } from 'react';
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
}

const VideoPageSection = ({ data }: VideoPageSectionProps) => {
	console.log(
		'🚀 ~ file: VideoPageSection.tsx:13 ~ VideoPageSection ~ data',
		data.curriculumInfo
	);
	const playerRef = useRef<ReactPlayer>(null);
	return (
		<>
			<h2>Hi</h2>
			<VideoPlayer url={data.video} videoRef={playerRef} />
			<CustomTab
				tabs={['커리큘럼', '수업 자료', '댓글', '메모하기']}
				contents={getPanels(data)}
			/>
		</>
	);
};

function getPanels(data: IVideoPage) {
	return [
		<CurriculumPanel curriculumInfo={data.curriculumInfo} key={1} />,
		<HandOutsPanel key={2} />,
		<CommentsPanel key={3} />,
		<MemoPanel key={4} />,
	];
}

export default VideoPageSection;
