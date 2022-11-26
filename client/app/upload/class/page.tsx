"use client";
import styles from "./uploadClass.module.css";
import { useRef, useState } from "react";

import OrangeButton from "../../../components/Buttons/orangeButton";
import BaseNavbar from "../../../components/BaseNavBar/BaseNavbar";
import { initialLecture, UploadLectureType } from "../../../types/uploadclass";
import { useSearchParams } from "next/navigation";

const UploadClassPage = () => {
  const searchParams = useSearchParams();
  const query = searchParams.get("slug");
  console.log(query);
  const fileInput = useRef<HTMLInputElement>(null);

  const [values, setValues] = useState<UploadLectureType>(initialLecture);
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValues({
      ...values,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    alert(JSON.stringify(values, null, 2));
  };

  const handleClickFileInput = () => {
    fileInput.current?.click();
  };

  const uploadfile = (e: React.ChangeEvent<HTMLInputElement>) => {
    const fileList = e.target.files;

    if (fileList && fileList[0]) {
      const url = URL.createObjectURL(fileList[0]);

      setValues({
        ...values,
        docsFile: fileList[0],
      });
      console.log("fileList", fileList);
      console.log("fileList[0]", fileList[0]);
      console.log("URL", URL);
      console.log("url", url);
    }
  };

  return (
    <>
      <BaseNavbar />
      <section className={styles.uploadpage}>
        <form onSubmit={handleSubmit} className={styles.form}>
          <p className={styles.title}>강의명</p>

          <p className={styles.classtitle}>강의 업로드</p>
          <input
            type="text"
            name="title"
            onChange={handleChange}
            className={styles.chapternameinput}
          ></input>

          <div className={styles.filebox}>
            <p className={styles.title}>챕터 썸네일</p>
            <input
              type="file"
              accept="image/jpg, image/jpeg, image/png"
              name="thumbnail"
              ref={fileInput}
              id="ex_file"
              style={{ display: "none" }}
              onChange={uploadfile}
            />
            <button
              className={styles.uploadbtn}
              type="button"
              onClick={handleClickFileInput}
            >
              업로드
            </button>
          </div>

          {query == "edit" ? (
            <OrangeButton type={"submit"} name={"수정하기"} />
          ) : (
            <OrangeButton type={"submit"} name={"올리기"} />
          )}
        </form>
      </section>
    </>
  );
};
export default UploadClassPage;
