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

  const [file, setFile] = useState<any>();
  const [docfile, setDocFile] = useState<any>();
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

  const uploadVideofile = (e: React.ChangeEvent<HTMLInputElement>) => {
    const fileList = e.target.files;

    if (fileList && fileList[0]) {
      setFile(fileList[0].name);

      setValues({
        ...values,
        docsFile: fileList[0],
      });

      console.log("fileList[0]1", fileList[0].name);
    }
  };

  const uploadDocsfile = (e: React.ChangeEvent<HTMLInputElement>) => {
    const fileList = e.target.files;

    if (fileList && fileList[0]) {
      setDocFile(fileList[0].name);

      setValues({
        ...values,
        docsFile: fileList[0],
      });

      console.log("fileList[0]2", fileList[0].name);
    }
  };

  return (
    <>
      <BaseNavbar />
      <section className={styles.uploadpage}>
        <form onSubmit={handleSubmit} className={styles.form}>
          <h1 className={styles.title}>강의</h1>

          <p className={styles.classtitle}>강의명</p>
          <input
            type="text"
            name="title"
            onChange={handleChange}
            className={styles.chapternameinput}
          ></input>

          <h3 className={styles.title}>강의 업로드</h3>
          <div className={styles.filebox}>
            <input
              className={styles.uploadname}
              value={file}
              placeholder="첨부파일"
            />
            <label htmlFor="file">파일찾기</label>
            <input
              accept="video/*"
              name="videoFile"
              onChange={uploadVideofile}
              type="file"
              id="file"
            ></input>
          </div>

          <h1 className={styles.lecturetitle}>수업자료</h1>

          <p className={styles.classtitle}>내용</p>
          <input
            type="text"
            name="details"
            onChange={handleChange}
            className={styles.lectureinput}
          ></input>

          <div className={styles.filebox}>
            <input
              className={styles.uploadname}
              value={docfile}
              placeholder="첨부파일"
            />
            <label htmlFor="file2">파일찾기</label>
            <input
              accept=".pdf, .text/plain"
              name="docsFile"
              onChange={uploadDocsfile}
              type="file"
              id="file2"
            ></input>
          </div>

          <h5 className={styles.ex}>텍스트/PDF 업로드/사진(x)</h5>

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
