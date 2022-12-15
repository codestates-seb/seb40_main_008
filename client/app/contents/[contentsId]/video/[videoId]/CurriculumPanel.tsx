import Link from 'next/link';
import React from 'react';
import { CurriculumInfo } from '../../../../../types/videoPage/video';
import styles from './VideoPage.module.css';

interface CurriculumProps {
	curriculumInfo: CurriculumInfo[];
	contentsId: string;
}

const VideoPageCurriculumPanel = ({
	curriculumInfo,
	contentsId,
}: CurriculumProps) => {
	if (!curriculumInfo) return null;
	return (
		<section className={styles.container}>
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
									<Link
										href={`/contents/${contentsId}/video/${el.uploadClassId}`}
									>
										{el.title}
									</Link>
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
