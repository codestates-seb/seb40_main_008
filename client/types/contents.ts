export interface IContent {
  contentsId: number;
  title: string;
  thumbnail: string;
  likesCount: number;
  categories: string;
  details: string;
  grade: number;
  tutorname: string;
  tutorDetail: string;
}

export interface Curriculum {
  curriculumInfo: ICurriculumContent[];
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
