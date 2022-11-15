import Image from 'next/image';
import { Content } from '../types/rootScreen/mainVideoContents';
import { titleLengthFormatter } from '../utils/helper/titleLengthFormatter';
import styles from './HomeSuggestionSection.module.css';

interface HomeContentProps {
	contentList: Content[];
}

const HomeSuggestionSection = ({ contentList }: HomeContentProps) => {
	return (
		<div className={styles.gridContainer}>
			{contentList.map((e) => (
				<div key={e.id} className={styles.content}>
					<div key={e.id} className={styles.thumbnailContainer}>
						<Image
							src={e.thumbnail}
							alt="home lecture content thumbnail"
							placeholder="blur"
							blurDataURL="../public/images/blur.png"
							fill={true}
							style={{ objectFit: 'cover', borderRadius: '4px' }}
						/>
					</div>
					<div className={styles.infoContainer}>
						<span className={styles.title}>{titleLengthFormatter(e.title)}</span>
						<div className={styles.semiInfoContainer}>
							<span className={styles.category}>{e.categories}</span>
							<span className={styles.username}>{e.users.username}</span>
						</div>
					</div>
				</div>
			))}
		</div>
	);
};

export default HomeSuggestionSection;
