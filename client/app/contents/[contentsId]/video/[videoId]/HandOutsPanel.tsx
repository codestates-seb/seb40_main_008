import React from 'react';
import { DocsInfo } from '../../../../../types/videoPage/video';
import styles from './VideoPage.module.css';
interface Props {
	handOutInfo: DocsInfo;
}

const HandOutsPanel = ({ handOutInfo }: Props) => {
	return (
		<section className={styles.container}>
			<p>{handOutInfo.name}</p>
			<p>{handOutInfo.details}</p>
			{/* <p>{handOutInfo.docsId}</p> */}
		</section>
	);
};

export default HandOutsPanel;
