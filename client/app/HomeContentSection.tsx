import Image from 'next/image';
import React from 'react';
import { Content } from '../types/rootScreen/mainVideoContents';
import styles from './HomeContentSection.module.css';

interface HomeContentProps {
	contentList: Content[];
}

const HomeContentSection = ({ contentList }: HomeContentProps) => {
	return (
		<div className={styles.container}>
			{contentList.map((e) => (
				<div key={e.id} className={styles.video}>
					<Image
						src={e.thumbnail}
						alt="thumbnail"
						placeholder="blur"
						blurDataURL={e.thumbnail}
					/>
				</div>
			))}
		</div>
	);
};

export default HomeContentSection;
