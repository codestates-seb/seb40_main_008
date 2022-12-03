import { type } from "os";

export interface UploadClassType {
  title: any;
  categories: any;
  details: any;
  tutorDetail: any;
  thumbnail: any;
  price: any;
}

export const initialClass = {
  title: "",
  categories: "",
  details: "",
  tutorDetail: "",
  thumbnail: null,
  price: "",
};

export interface UploadImage {
  file: any;
  thumbnail: any;
  type: string | null;
}

export interface UploadChapterType {
  thumbnail: any;
  chapterOrder: any;
  title: any;
}

export const initialChapter = {
  thumbnail: null,
  chapterOrder: "",
  title: "",
};

export interface UploadLectureType {
  videoFile: File | null;
  title: string;
  docsFile: File | null;
  details: string;
}

export const initialLecture = {
  videoFile: null,
  title: "",
  docsFile: null,
  details: "",
};
