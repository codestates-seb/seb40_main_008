'use client';
import React, { useEffect, useState } from 'react';
import ReactPlayer from 'react-player';
import styles from './VideoPage.module.css';
interface VideoPlayerProps {
	url: string;
	videoRef: any;
}

const VideoPlayer = ({ url, videoRef }: VideoPlayerProps) => {
	const [hasWindow, setHasWindow] = useState(false);
	useEffect(() => {
		if (typeof window !== 'undefined') {
			setHasWindow(true);
		}
	}, []);
	console.log('height', videoRef.current);

	if (!hasWindow) {
		return (
			<div
				style={{
					border: '1px solid white',
					width: '100%',
					// height: '100%',
					height: '360px',
				}}
			></div>
		);
	}
	return (
		<ReactPlayer
			ref={videoRef}
			url={url}
			muted={true}
			controls={true}
			playing={true}
			className={styles.videoPlayer}
			width="100%"
			height="360px"
		/>
	);
};

export default VideoPlayer;
