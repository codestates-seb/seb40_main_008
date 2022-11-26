"use client";

import BaseNavbar from "../../../components/BaseNavBar/BaseNavbar";
import styles from "./uploadChapter.module.css";
import Image from "next/image";
import { useRouter } from "next/router";
import {
  initialChapter,
  UploadChapterType,
  UploadImage,
} from "../../../types/uploadclass";
import { useMemo, useRef, useState } from "react";
import OrangeButton from "../../../components/Buttons/orangeButton";
import { useSearchParams } from "next/navigation";

const UploadChapterPage = () => {
  const searchParams = useSearchParams();
  const query = searchParams.get("slug");
  console.log(query);

  const fileInput = useRef<HTMLInputElement>(null);
  const [values, setValues] = useState<UploadChapterType>(initialChapter);
  const [imageFile, setImageFile] = useState<UploadImage | null>(null);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValues({
      ...values,
      [e.target.name]: e.target.value,
    });
  };

  const handleOptionChange = (e: React.FormEvent<HTMLSelectElement>) => {
    setValues({
      ...values,
      chapterOrder: e.currentTarget.value,
    });
    console.log(e.currentTarget);
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

      setImageFile({
        file: fileList[0],
        thumbnail: url,
        type: fileList[0].type.slice(0, 5),
      });

      setValues({
        ...values,
        thumbnail: fileList[0],
      });
      console.log("fileList", fileList);
      console.log("fileList[0]", fileList[0]);
      console.log("URL", URL);
      console.log("url", url);
    }
  };

  const showImage = useMemo(() => {
    if (!imageFile && imageFile == null) {
      return <p>비어있는 프로필</p>;
    }

    return (
      <Image
        className={styles.thumbnail}
        src={imageFile.thumbnail}
        alt={imageFile.type}
        width={300}
        height={220}
        onClick={handleClickFileInput}
        style={{ objectFit: "contain", borderRadius: "4px" }}
      />
    );
  }, [imageFile]);

  return (
    <>
      <BaseNavbar />
      <section className={styles.uploadpage}>
        <form onSubmit={handleSubmit} className={styles.form}>
          <p className={styles.title}>챕터순서</p>

          <select
            id="chapterOrder"
            name="chapterOrder"
            onChange={handleOptionChange}
            className={styles.select}
          >
            <option>-- 선택하세요 --</option>
            <optgroup>
              <option value="chapter 1">Chapter 1</option>
            </optgroup>
          </select>

          <p className={styles.classtitle}>챕터 명</p>
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

          <div className={styles.uploadimg}>{showImage}</div>

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
export default UploadChapterPage;
