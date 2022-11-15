import Image from 'next/image';
import { Content } from '../types/rootScreen/mainVideoContents';
import { titleLengthFormatter } from '../utils/helper/titleLengthFormatter';
import styles from './HomeContentSection.module.css';

interface HomeContentProps {
	contentList: Content[];
}

const HomeContentSection = ({ contentList }: HomeContentProps) => {
	return (
		<div className={styles.gridContainer}>
			{contentList.map((e) => (
				<div key={e.id} className={styles.content}>
					<div key={e.id} className={styles.video}>
						<Image
							src={e.thumbnail}
							alt="home lecture content thumbnail"
							placeholder="blur"
							blurDataURL="../public/images/blur.png"
							fill={true}
							style={{ objectFit: 'cover', borderRadius: '10px' }}
						/>
					</div>
					<div className={styles.infoContainer}>
						<p className={styles.title}>{titleLengthFormatter(e.title)}</p>
						<div className={styles.semiInfoContainer}>
							<p className={styles.category}>{e.categories}</p>
							<p className={styles.username}>{e.users.username}</p>
						</div>
					</div>
				</div>
			))}
		</div>
	);
};

export default HomeContentSection;
