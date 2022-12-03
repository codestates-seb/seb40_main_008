'use client';
import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import ReactPlayer from 'react-player';
import { OnProgressProps } from 'react-player/base';
import { useHasWindow } from '../../../../../utils/hooks/useHasWindow';
import styles from './VideoPage.module.css';
import { Player, ControlBar } from 'video-react';
import './video.css';
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
		const minutes = Math.floor(playedSeconds / 60);
		const seconds = Math.floor(playedSeconds % 60);
		const secondsString = seconds < 10 ? `0${seconds}` : seconds;
		const minutesString = minutes < 10 ? `0${minutes}` : minutes;
		setTime(`${minutesString}:${secondsString}`);
	};

	return (
		<div
			style={{
				border: '1px solid white',
				width: '100%',
				height: '360px',
			}}
		>
			<Player
				playsInline={true}
				ref={videoRef}
				fluid={false}
				height={'100%'}
				autoPlay={true}
				muted={false}
				preload="auto"
				src={url}
				width={'100%'}
			>
				<ControlBar autoHide={false} />
			</Player>
		</div>
	);

	// return (
	// 	<ReactPlayer
	// 		ref={videoRef}
	// 		url={url}
	// 		muted={true}
	// 		controls={true}
	// 		playing={true}
	// 		className={styles.videoPlayer}
	// 		width="100%"
	// 		height="360px"
	// 		progressInterval={1000}
	// 		onProgress={handleProgress}
	// 	/>
	// );
};

export default VideoPlayer;
