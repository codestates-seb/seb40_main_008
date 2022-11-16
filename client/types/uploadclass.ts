import { type } from "os";

export interface UploadClassType {
  classname: string;
  categoryOption: string;
  introduceClass: string;
  introduceInstructor: string;
  thumbnail: File;
}

export const initialClass = {
  classname: "",
  categoryOption: "",
  introduceClass: "",
  introduceInstructor: "",
  thumbnail: null,
};

export interface UploadImage {
  file: File;
  thumbnail: string;
  type: string;
}
