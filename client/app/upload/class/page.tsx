'use client';
import styles from './uploadClass.module.css';
import { useState } from 'react';
import OrangeButton from '../../../components/Buttons/orangeButton';
import BaseNavbar from '../../../components/BaseNavBar/BaseNavbar';
import { initialLecture, UploadLectureType } from '../../../types/uploadclass';
import { useSearchParams } from 'next/navigation';
import { getCookie } from 'cookies-next';
import { useRouter } from 'next/navigation';

const formData = new FormData();

const UploadClassPage = () => {
	const token = getCookie('accessToken');
	const searchParams = useSearchParams();
	const query = searchParams.get('slug');
	const chapterId = searchParams.get('chapterId');
	const contentsId = searchParams.get('contentsId');
	const router = useRouter();
	console.log(query);

	const [file, setFile] = useState<any>();
	const [docfile, setDocFile] = useState<any>();

	const [values, setValues] = useState<UploadLectureType>(initialLecture);

	const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		setValues({
			...values,
			[e.target.name]: e.target.value,
		});
	};
	const handleTextChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
		setValues({
			...values,
			[e.target.name]: e.target.value,
		});
	};

	const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		alert(JSON.stringify(values, null, 2));
		formData.append('title', values.title);
		formData.append('details', values.details);

		console.log('formData:video:: ', formData.getAll('videoFile'));
		console.log('formData:docs:: ', formData.getAll('docsFile'));
		console.log('formData:title:: ', formData.getAll('title'));

		fetch(`https://pioneroroom.com/auth/chapter/lecture/${chapterId}`, {
			method: 'POST',
			headers: {
				Authorization: `Bearer ${token}`,
			},
			body: formData,
		}).then((res) => {
			if (res.ok) {
				formData.delete('title');
				formData.delete('details');
				formData.delete('videoFile');
				formData.delete('docsFile');
				router.push(`/contents/${contentsId}`);
			}
		});
	};

	router.prefetch(`/contents/${contentsId}`);

	const uploadVideofile = (e: React.ChangeEvent<HTMLInputElement>) => {
		const fileList = e.target.files;

		if (fileList && fileList[0]) {
			setFile(fileList[0].name);
			formData.append('videoFile', fileList[0]);
			setValues({
				...values,
				videoFile: fileList[0],
			});

			console.log('fileList[0]1', fileList[0].name);
		}
	};

	const uploadDocsfile = (e: React.ChangeEvent<HTMLInputElement>) => {
		const fileList = e.target.files;

		if (fileList && fileList[0]) {
			setDocFile(fileList[0].name);
			formData.append('docsFile', fileList[0]);
			setValues({
				...values,
				docsFile: fileList[0],
			});

			console.log('fileList[0]2', fileList[0].name);
		}
	};

	return (
		<>
			<BaseNavbar />
			<section className={styles.uploadpage}>
				<form onSubmit={handleSubmit} className={styles.form}>
					<h1 className={styles.title}>강의</h1>

					<p className={styles.classtitle}>강의명</p>
					<input
						type="text"
						name="title"
						onChange={handleChange}
						className={styles.chapternameinput}
					></input>

					<h3 className={styles.title}>강의 업로드</h3>
					<div className={styles.filebox}>
						<input
							className={styles.uploadname}
							value={file}
							placeholder="첨부파일"
						/>
						<label htmlFor="file">파일찾기</label>
						<input
							accept="video/*"
							name="videoFile"
							onChange={uploadVideofile}
							type="file"
							id="file"
						></input>
					</div>

					<h1 className={styles.lecturetitle}>수업자료</h1>

					<p className={styles.classtitle}>내용</p>
					<textarea
						name="details"
						onChange={handleTextChange}
						className={styles.lectureinput}
					></textarea>

					<div className={styles.filebox}>
						<input
							className={styles.uploadname}
							value={docfile}
							placeholder="첨부파일"
						/>
						<label htmlFor="file2">파일찾기</label>
						<input
							accept=".pdf, .text/plain"
							name="docsFile"
							onChange={uploadDocsfile}
							type="file"
							id="file2"
						></input>
					</div>
					{query == 'edit' ? (
						<OrangeButton type={'submit'} name={'수정하기'} />
					) : (
						<OrangeButton type={'submit'} name={'올리기'} />
					)}
				</form>
			</section>
		</>
	);
};

export default UploadClassPage;
