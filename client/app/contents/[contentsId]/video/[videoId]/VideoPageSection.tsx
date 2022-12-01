'use client';
import React from 'react';
import { IVideoPage } from '../../../../../types/videoPage/video';
import VideoPlayer from './VideoPlayer';

interface VideoPageSectionProps {
	data: IVideoPage;
}

const VideoPageSection = ({ data }: VideoPageSectionProps) => {
	return (
		<>
			<h2>Hi</h2>
			<VideoPlayer url={data.video} />
		</>
	);
};

export default VideoPageSection;
