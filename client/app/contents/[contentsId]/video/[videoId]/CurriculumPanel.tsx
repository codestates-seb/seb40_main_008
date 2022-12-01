import React from 'react';
import { CurriculumInfo } from '../../../../../types/videoPage/video';

interface CurriculumProps {
	curriculumInfo: CurriculumInfo[];
}

const VideoPageCurriculumPanel = ({ curriculumInfo }: CurriculumProps) => {
	console.log(
		'🚀 ~ file: CurriculumPanel.tsx:9 ~ VideoPageCurriculumPanel ~ curriculumInfo',
		curriculumInfo
	);
	return (
		<section>
			{curriculumInfo.map((el, idx) => {
				return (
					<div key={idx}>
						<h4>{el.title}</h4>
						<ul>
							{el.uploadClassList.map((el) => (
								<li key={el.uploadClassId}>{el.title}</li>
							))}
						</ul>
					</div>
				);
			})}
		</section>
	);
};

export default VideoPageCurriculumPanel;
