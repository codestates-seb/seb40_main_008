export interface IContent {
  contentsId: number;
  title: string;
  thumbnail: string;
  likesCount: number;
  categories: string;
  details: string;
  tutorDetail: string;
  curriculumInContent: CurriculumInContent;
}

export interface CurriculumInContent {
  curriculumInfo: CurriculumInfo[];
}

export interface CurriculumInfo {
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
