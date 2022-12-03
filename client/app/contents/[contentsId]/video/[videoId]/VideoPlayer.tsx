'use client';
import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import ReactPlayer from 'react-player';
import { OnProgressProps } from 'react-player/base';
import { useHasWindow } from '../../../../../utils/hooks/useHasWindow';
import styles from './VideoPage.module.css';
interface VideoPlayerProps {
	url: string;
	videoRef: any;
	setTime: Dispatch<SetStateAction<string>>;
}

const VideoPlayer = ({ url, videoRef, setTime }: VideoPlayerProps) => {
	const hasWindow = useHasWindow();

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

	const handleProgress = (e: OnProgressProps) => {
		const { playedSeconds } = e;
		// format playedSeconds to mm:ss format
		const minutes = Math.floor(playedSeconds / 60);
		const seconds = Math.floor(playedSeconds % 60);
		const secondsString = seconds < 10 ? `0${seconds}` : seconds;
		const minutesString = minutes < 10 ? `0${minutes}` : minutes;
		setTime(`${minutesString}:${secondsString}`);
	};

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
			progressInterval={1000}
			onProgress={handleProgress}
		/>
	);
};

export default VideoPlayer;