import { type } from "os";

export interface UploadClassType {
  classname: string;
  categoryOption: string;
  classPrice: number;
  introduceClass: string;
  introduceInstructor: string;
  thumbnail: File | null;
}

export const initialClass = {
  classname: "",
  categoryOption: "",
  classPrice: 0,
  introduceClass: "",
  introduceInstructor: "",
  thumbnail: null,
};

export interface UploadImage {
  file: File;
  thumbnail: string;
  type: string;
}
