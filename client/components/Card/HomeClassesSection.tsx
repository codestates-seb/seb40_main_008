import Image from 'next/image';
import Link from 'next/link';
import { ICategorySearchResult } from '../../types/category_search/categorySearchType';
import { titleLengthFormatter } from '../../utils/helper/titleLengthFormatter';
import styles from './HomeClassesSection.module.css';

interface HomeContentProps {
	contentsList: ICategorySearchResult[];
}

const HomeClassesSection = ({ contentsList }: HomeContentProps) => {
	return (
		<div className={styles.gridContainer}>
			{contentsList.map((e) => (
				<Link key={e.contentsId} href={`/contents/${e.contentsId}`}>
					<div key={e.contentsId} className={styles.content}>
						<div key={e.contentsId} className={styles.thumbnailContainer}>
							<Image
								src={e.thumbnail}
								alt="home lecture content thumbnail"
								placeholder="blur"
								blurDataURL="../public/images/blur.png"
								fill={true}
								sizes="(max-width: 768px) 100vw,
								(max-width: 1200px) 50vw,
								33vw"
								style={{ objectFit: 'cover', borderRadius: '4px' }}
							/>
						</div>
						<div className={styles.infoContainer}>
							<span className={styles.title}>
								{titleLengthFormatter(e.title)}
							</span>
							<div className={styles.semiInfoContainer}>
								<span className={styles.category}>{e.categories}</span>
								<span className={styles.username}>
									{e.users.userName}
								</span>
							</div>
						</div>
					</div>
				</Link>
			))}
		</div>
	);
};

export default HomeClassesSection;
