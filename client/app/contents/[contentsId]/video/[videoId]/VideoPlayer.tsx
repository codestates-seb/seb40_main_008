'use client';
import React from 'react';
import ReactPlayer from 'react-player';
import styles from './VideoPage.module.css';
interface VideoPlayerProps {
	url: string;
}

const VideoPlayer = ({ url }: VideoPlayerProps) => {
	return (
		<ReactPlayer
			url={url}
			muted={true}
			controls={true}
			playing={true}
			className={styles.videoPlayer}
			width="100%"
			height="100%"
		/>
	);
};

export default VideoPlayer;
