'use client';
import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import ReactPlayer from 'react-player';
import { useHasWindow } from '../../../../../utils/hooks/useHasWindow';
import styles from './VideoPage.module.css';
interface VideoPlayerProps {
	url: string;
	videoRef: any;
	setTime: Dispatch<SetStateAction<string>>;
}

const VideoPlayer = ({ url, videoRef, setTime }: VideoPlayerProps) => {
	const hasWindow = useHasWindow();
	return (
		// <video src={url}></video>

		<ReactPlayer
			// ref={videoRef}
			url={url}
			controls={true}
			playing={true}
			width="100%"
			height="360px"
			// progressInterval={1000}
			// onProgress={(e) => console.log(e)}
		/>
	);
	if (!hasWindow) {
		return (
			<div
				style={{
					border: '1px solid white',
					width: '100%',
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
			// progressInterval={1000}
			// onProgress={(e) => console.log(e)}
		/>
	);
};

export default VideoPlayer;
