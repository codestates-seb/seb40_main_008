import { type } from "os";

export interface UploadClassType {
  title: string;
  categories: string;
  details: string;
  tutorDetail: string;
  thumbnail: File | null;
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
  file: File;
  thumbnail: string;
  type: string;
}

export interface UploadChapterType {
  thumbnail: File | null;
  chapterOrder: string;
  title: string;
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
