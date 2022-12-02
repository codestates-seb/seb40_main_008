'use client';
import BaseNavbar from '../../../components/BaseNavBar/BaseNavbar';
import styles from './uploadChapter.module.css';
import Image from 'next/image';
import { UploadChapterType, UploadImage } from '../../../types/uploadclass';
import { useEffect, useMemo, useRef, useState } from 'react';
import OrangeButton from '../../../components/Buttons/orangeButton';
import { redirect, useSearchParams } from 'next/navigation';
import { getCookie } from 'cookies-next';
import { ICurriculumContent } from '../../../types/contents';
import { useRouter } from 'next/navigation';
import CurriculumEdit from '../../../components/Buttons/CurriculumEdit';

const formData = new FormData();
let isFirst = true;

interface Props {
	params: {
		chapterId: string;
	};
}

const UploadChapterPage = () => {
	const token = getCookie('accessToken');
	const searchParams = useSearchParams();
	const query = searchParams.get('slug');
	let thumbnail = searchParams.get('thumbnail') as string;
	const chapterOrder = searchParams.get('chapterOrder');
	const title = searchParams.get('title');
	const contentId = searchParams.get('contentId');
	const chapterId = searchParams.get('chapterId');
	console.log(
		'🚀 ~ file: page.tsx:32 ~ UploadChapterPage ~ chapterId',
		chapterId
	);
	const router = useRouter();
	console.log('썸네일', thumbnail);
	const queryChapter = {
		thumbnail: thumbnail,
		chapterOrder: chapterOrder,
		title: title,
	};
	console.log('🚀 ~ file: page.tsx:15 ~ formData', formData);

	const img = {
		file: null,
		thumbnail: thumbnail,
		type: null,
	};

	const fileInputRef = useRef<HTMLInputElement>(null);

	const [values, setValues] = useState<UploadChapterType>(queryChapter);

	const [imageFile, setImageFile] = useState<UploadImage>(img);
	const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		setValues({
			...values,
			[e.target.name]: e.target.value,
		});
	};

	const handleOptionChange = (e: React.FormEvent<HTMLSelectElement>) => {
		setValues({
			...values,
			chapterOrder: e.currentTarget.value,
		});
		console.log(e.currentTarget);
	};

	const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		alert(JSON.stringify(values, null, 2));

		formData.append('chapterOrder', values.chapterOrder);
		formData.append('title', values.title);

		fetch(`https://pioneroroom.com/auth/contents/chapter/${contentId}`, {
			method: 'POST',
			headers: {
				Authorization: `Bearer ${token}`,
			},
			body: formData,
		}).then((res) => {
			if (res.ok) {
				formData.delete('chapterOrder');
				formData.delete('title');
				formData.delete('thumbnail');
				router.push(`/contents/${contentId}`);
			}
		});
	};

	useEffect(() => {
		console.log('🚀 ~ file: page.tsx:95 ~ useEffect ~ imageFile', imageFile);
	}, [imageFile]);

	const handleClickFileInput = () => {
		fileInputRef.current?.click();
	};

	const uploadfile = (e: React.ChangeEvent<HTMLInputElement>) => {
		const fileList = e.target.files;
		console.log('🚀 ~ file: page.tsx:89 ~ uploadfile ~ fileList', fileList);
		const fileInfo = fileList?.[0];
		formData.delete('thumbnail');
		setFileInfoData(fileInfo);
	};

	// how to make File from url

	const setFileInfoData = (fileInfo: File | undefined) => {
		console.log(
			'🚀 ~ file: page.tsx:99 ~ setFileInfoData ~ fileInfo',
			fileInfo
		);
		if (!fileInfo) return;

		const url = URL.createObjectURL(fileInfo);
		formData.append('thumbnail', fileInfo);

		setImageFile({
			file: fileInfo,
			thumbnail: url,
			type: fileInfo.type,
		});

		setValues({
			...values,
			thumbnail: fileInfo,
		});
	};

	if (thumbnail && isFirst) {
		fetch(thumbnail, {
			mode: 'no-cors',
		}).then(async (response) => {
			const contentType = response.headers.get('Content-Type');
			const blob = await response.blob();
			const file = new File([blob], 'EditInitialFile', {
				type: 'image/png',
			});
			console.log('🚀 ~ file: page.tsx:123 ~ file', file);
			setFileInfoData(file);
			isFirst = false;
		});
	}

	const showImage = useMemo(() => {
		if (!imageFile && imageFile == null) {
			return (
				<p style={{ backgroundColor: 'black', border: '1px solid red' }}>
					비어있는 프로필
				</p>
			);
		}
		imageFile.thumbnail;
		console.log(
			'🚀 ~ file: page.tsx:158 ~ showImage ~ imageFile.thumbnail',
			imageFile.thumbnail
		);
		if (imageFile.thumbnail[0] === 'h') {
			return (
				<Image
					className={styles.thumbnail}
					src={imageFile.thumbnail ?? '/'}
					alt={'img'}
					width={350}
					height={200}
					onClick={handleClickFileInput}
					style={{ objectFit: 'contain', borderRadius: '4px' }}
				/>
			);
		}
	}, [imageFile]);

	return (
		<>
			<BaseNavbar />
			<section className={styles.uploadpage}>
				<form onSubmit={handleSubmit} className={styles.form}>
					<p className={styles.title}>챕터순서</p>

					<select
						id="chapterOrder"
						name="chapterOrder"
						value={values.chapterOrder ?? ''}
						onChange={handleOptionChange}
						className={styles.select}
					>
						<option>-- 선택하세요 --</option>
						<optgroup>
							<option value="chapter 1">Chapter 1</option>
							<option value="chapter 2">Chapter 2</option>
							<option value="chapter 3">Chapter 3</option>
							<option value="chapter 4">Chapter 4</option>
							<option value="chapter 5">Chapter 5</option>
						</optgroup>
					</select>

					<p className={styles.classtitle}>챕터 명</p>
					<input
						type="text"
						name="title"
						// value={values.title ?? ''}
						onChange={handleChange}
						className={styles.chapternameinput}
					></input>

					<div className={styles.filebox}>
						<p className={styles.title}>챕터 썸네일</p>
						<input
							type="file"
							accept="image/png"
							name="thumbnail"
							ref={fileInputRef}
							id="ex_file"
							src={thumbnail}
							// value={values.thumbnail ?? ""}
							style={{ display: 'none' }}
							onChange={uploadfile}
						/>
						<button
							className={styles.uploadbtn}
							type="button"
							onClick={handleClickFileInput}
						>
							업로드
						</button>
					</div>

					<div className={styles.uploadimg}>{showImage}</div>

					{query == 'edit' ? (
						// <OrangeButton type={"submit"} name={"수정하기"} />
						<CurriculumEdit
							url={'http://localhost:8080/auth/contents/chapter/'}
							thumbnail={imageFile?.file}
							chapterOrder={values.chapterOrder}
							title={values.title}
							Id={chapterId}
							contentId={contentId}
						/>
					) : (
						<OrangeButton type={'submit'} name={'올리기'} />
					)}
				</form>
			</section>
		</>
	);
};

export default UploadChapterPage;
