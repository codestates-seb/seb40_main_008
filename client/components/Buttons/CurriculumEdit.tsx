import { useRouter } from 'next/navigation';
import { fetchEditChapter } from '../../api/fetchDelete';
import OrangeButton from './orangeButton';

const CurriculumEdit = (props: {
	url: string;
	thumbnail: any;
	chapterOrder: string;
	title: string;
	Id: any;
	contentId: any;
}) => {
	const router = useRouter();

	const handleEditClick = async (
		url: string,
		thumbnail: string,
		chapterOrder: string,
		title: string,
		Id: any,
		contentId: any,
		refresh: () => void
	) => {
		try {
			console.log('🚀 ~ file: CurriculumEdit.tsx:38 ~ Id', Id);

			const status = await fetchEditChapter(
				url,
				thumbnail,
				chapterOrder,
				title,
				Id
			);
			if (status !== 200) throw new Error('status is not good');
			refresh();
			router.push(`/contents/${contentId}`);
		} catch (err) {
			console.error(err);
		}
	};

	return (
		<>
			<OrangeButton
				handleClick={() =>
					handleEditClick(
						props.url,
						props.thumbnail,
						props.chapterOrder,
						props.title,
						props.Id,
						props.contentId,
						router.refresh
					)
				}
				name={'수정하기'}
			/>
		</>
	);
};

export default CurriculumEdit;
