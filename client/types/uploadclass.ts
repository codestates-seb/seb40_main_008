import { type } from "os";

export interface UploadClassType {
  title: string;
  categories: string;
  details: string;
  tutorDetail: string;
  thumbnail: any;
  price: number;
}

export const initialClass = {
  title: "",
  categories: "",
  details: "",
  tutorDetail: "",
  thumbnail: null,
  price: 0,
};

export interface UploadImage {
  file: any;
  thumbnail: any;
  type: string | null;
}

export interface UploadChapterType {
  thumbnail: string | null;
  chapterOrder: string | null;
  title: string | null;
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
