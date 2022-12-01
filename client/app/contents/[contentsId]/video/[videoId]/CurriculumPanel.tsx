import React from 'react';
import { CurriculumInfo } from '../../../../../types/videoPage/video';
import styles from './VideoPage.module.css';

interface CurriculumProps {
	curriculumInfo: CurriculumInfo[];
}

const VideoPageCurriculumPanel = ({ curriculumInfo }: CurriculumProps) => {
	console.log(
		'🚀 ~ file: CurriculumPanel.tsx:9 ~ VideoPageCurriculumPanel ~ curriculumInfo',
		curriculumInfo
	);
	return (
		<section className={styles.Container}>
			{curriculumInfo.map((el, idx) => {
				return (
					<div className={styles.curriculumSection} key={idx}>
						<h4>{el.title}</h4>
						<ul
							style={{
								paddingLeft: '1rem',
								marginBottom: '24px',
							}}
						>
							{el.uploadClassList.map((el) => (
								<li
									style={{
										listStyleType: 'none',
										marginTop: '24px',
										fontWeight: 'bold',
									}}
									className={styles.curriChapter}
									key={el.uploadClassId}
								>
									{el.title}
								</li>
							))}
						</ul>
					</div>
				);
			})}
		</section>
	);
};

export default VideoPageCurriculumPanel;
