'use client';
import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import ReactPlayer from 'react-player';
// import { OnProgressProps } from 'react-player/base';
import { useHasWindow } from '../../../../../utils/hooks/useHasWindow';
import styles from './VideoPage.module.css';
import { Player, ControlBar, PlayToggle } from 'video-react';

interface ProgressEvent {
	played: number;
	playedSeconds: number;
	loaded: number;
	loadedSeconds: number;
}

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

	const handleProgress = (e: ProgressEvent) => {
		const { playedSeconds } = e;
		console.log('🚀 ~ file: VideoPlayer.tsx:40 ~ handleProgress ~ e', e);
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
			onProgress={handleProgress}
			controls={true}
			playing={true}
			width="100%"
			height="360px"
		/>
	);
};

export default VideoPlayer;
