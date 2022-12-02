import { type } from 'os';

export interface UploadClassType {
	title: string;
	categories: string;
	details: string;
	tutorDetail: string;
	thumbnail: any;
	price: string;
}

export const initialClass = {
	title: '',
	categories: '',
	details: '',
	tutorDetail: '',
	thumbnail: null,
	price: '',
};

export interface UploadImage {
	file: any;
	thumbnail: string;
	type: string | null;
}

export interface UploadChapterType {
	thumbnail: any;
	chapterOrder: any;
	title: any;
}

export const initialChapter = {
	thumbnail: null,
	chapterOrder: '',
	title: '',
};

export interface UploadLectureType {
	videoFile: File | null;
	title: string;
	docsFile: File | null;
	details: string;
}

export const initialLecture = {
	videoFile: null,
	title: '',
	docsFile: null,
	details: '',
};
