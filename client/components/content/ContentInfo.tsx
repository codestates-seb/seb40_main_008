import styles from './ContentInfo.module.css';
import OrangeButton from '../Buttons/orangeButton';
import { IContent, ICurriculumContent } from '../../types/contents';
import Image from 'next/image';
import { ContentCardWishBtn } from '../Buttons/ContentCardWishBtn';
import { ContentCardFavoriteBtn } from '../Buttons/ContentCardFavoriteBtn';
import Link from 'next/link';
import PurchaseButton from './PurchaseButton';
import StaticStars from './StaticStars';

interface ContentInfoProps {
	contentInfo: IContent;
	uploadclassId: number | null;
}

const ContentInfo = ({ contentInfo, uploadclassId }: ContentInfoProps) => {
	const id = contentInfo?.contentsId;

	const getRoleButton = (role: string) => {
		if (role === 'creator') {
			return (
				<Link
					href={{
						pathname: '/upload/chapter',
						query: {
							contentId: id,
						},
					}}
				>
					<OrangeButton name={'챕터올리기'} />
				</Link>
			);
		} else if (role === 'Unpaid_customer') {
			return (
				<PurchaseButton
					contentId={contentInfo.contentsId}
					contentInfo={contentInfo}
				/>
			);
		} else if (role === 'Paid_customer') {
			return (
				<Link
					href={
						uploadclassId !== null
							? `/contents/${contentInfo.contentsId}/video/${uploadclassId}/`
							: {}
					}
				>
					<OrangeButton
						name={
							uploadclassId !== null ? ` 재생하기` : '강의 업로드 예정'
						}
					/>
				</Link>
			);
		}
		return <div>cannot find</div>;
	};

	const getIconButton = (role: string) => {
		if (role === 'Paid_customer') {
			return <ContentCardFavoriteBtn contentId={contentInfo?.contentsId} />;
		} else if (role === 'Unpaid_customer') {
			return <ContentCardWishBtn contentId={contentInfo?.contentsId} />;
		}
	};

	return (
		<div>
			<div className={styles.thumbnail}>
				<Image
					src={contentInfo?.thumbnail}
					alt={contentInfo?.title}
					fill={true}
					style={{ objectFit: 'cover' }}
				/>
			</div>
			<div className={styles.InfoWrapper}>
				<div className={styles.Info}>
					{contentInfo?.categories} &nbsp; {contentInfo?.tutorName}
					{getIconButton(contentInfo?.role)}
				</div>

				<div className={styles.classWrapper}>
					<div className={styles.classtitle}>{contentInfo?.title}</div>
					<span style={{ fontSize: '20px' }}>₩ {contentInfo?.price}</span>
				</div>
				<StaticStars grade={contentInfo?.grade || 0} />

				<div className={styles.btn}>{getRoleButton(contentInfo?.role)}</div>
			</div>
			<hr className={styles.line}></hr>
		</div>
	);
};

export default ContentInfo;
