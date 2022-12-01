'use client';
import React from 'react';
import { IVideoPage } from '../../../../../types/videoPage/video';
import ReactPlayer from 'react-player';

interface VideoPageSectionProps {
	data: IVideoPage;
}

const VideoPageSection = ({ data }: VideoPageSectionProps) => {
	console.log(data);
	console.log('videoPageSection');
	return (
		<>
			<h2>Hi</h2>
			<ReactPlayer url={data.video} />
			{/* <ReactPlayer
				url={`https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4`}
			/> */}
			{/* <video src={data.video} /> */}
		</>
	);
};

export default VideoPageSection;
