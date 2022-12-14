export interface IContent {
	contentsId: number;
	title: string;
	thumbnail: string;
	likesCount: number;
	categories: string;
	details: string;
	grade: number;
	price: number;
	liked: boolean;
	role: 'creator' | 'Unpaid_customer' | 'Paid_customer';
	wished: boolean;
	tutorName: string;
	tutorDetail: string;
}

export type ContentsWithCurriculum = {
	contentInfo: IContent;
	curriculumInfo: ICurriculumContent[];
};

export interface Curriculum {
	curriculumInfo?: ICurriculumContent[];
}
export interface ICurriculumContent {
	chapterId: number;
	chapterOrder: string;
	title: string;
	thumbnail: string;
	uploadClassList: UploadClassList[];
}

export interface UploadClassList {
	uploadClassId: number;
	title: string;
}
