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
