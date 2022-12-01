'use client';
import React from 'react';
import ReactPlayer from 'react-player';
import styles from './VideoPage.module.css';
interface VideoPlayerProps {
	url: string;
	videoRef: any;
}

const VideoPlayer = ({ url, videoRef }: VideoPlayerProps) => {
	return (
		<ReactPlayer
			ref={videoRef}
			url={url}
			muted={true}
			controls={true}
			playing={true}
			className={styles.videoPlayer}
			width="100%"
		/>
	);
};

export default VideoPlayer;
