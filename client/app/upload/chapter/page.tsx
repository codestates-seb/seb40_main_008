"use client";
import BaseNavbar from "../../../components/BaseNavBar/BaseNavbar";
import styles from "./uploadChapter.module.css";
import Image from "next/image";
import {
  initialChapter,
  UploadChapterType,
  UploadImage,
} from "../../../types/uploadclass";
import { useMemo, useRef, useState } from "react";
import OrangeButton from "../../../components/Buttons/orangeButton";
import { useSearchParams } from "next/navigation";
import { ICurriculumContent } from "../../../types/contents";
import { fetchEditChapter } from "../../../utils/api/fetchDelete";

const UploadChapterPage = () => {
  const searchParams = useSearchParams();
  const query = searchParams.get("slug");
  const thumbnail = searchParams.get("thumbnail");
  const chapterOrder = searchParams.get("chapterOrder");
  const title = searchParams.get("title");

  console.log("썸네일", thumbnail);

  const queryChapter = {
    thumbnail,
    chapterOrder,
    title,
  };

  const img = {
    file: null,
    thumbnail: thumbnail,
    type: null,
  };

  const fileInput = useRef<HTMLInputElement>(null);

  const [values, setValues] = useState<UploadChapterType>(queryChapter);

  const [imageFile, setImageFile] = useState<UploadImage | null>(img);

  // const handleChapterEditClick = async ({
  //   chapterId,
  //   chapterOrder,
  //   thumbnail,
  //   title,

  // }: ICurriculumContent) => {
  //   try {
  //     const status = await fetchEditChapter(
  //       thumbnail,
  //       chapterOrder,
  //       title,
  //       chapterId
  //     );
  //   } catch (err) {
  //     console.error(err);
  //   }
  // };

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
        thumbnail: url,
      });
      console.log("fileList", fileList);
      console.log("fileList[0]", fileList[0]);
      console.log("URL", URL);
      console.log("url", url);
    }
  };

  const showImage = useMemo(() => {
    if (!imageFile && imageFile == null) {
      return (
        <p style={{ backgroundColor: "black", border: "1px solid red" }}>
          비어있는 프로필
        </p>
      );
    }

    return (
      <Image
        className={styles.thumbnail}
        src={imageFile.thumbnail ?? "/"}
        alt={"img"}
        width={350}
        height={200}
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
            value={values.chapterOrder ?? ""}
            onChange={handleOptionChange}
            className={styles.select}
          >
            <option>-- 선택하세요 --</option>
            <optgroup>
              <option value="chapter 1">Chapter 1</option>
              <option value="chapter 1">Chapter 2</option>
              <option value="chapter 1">Chapter 3</option>
              <option value="chapter 1">Chapter 4</option>
              <option value="chapter 1">Chapter 5</option>
            </optgroup>
          </select>

          <p className={styles.classtitle}>챕터 명</p>
          <input
            type="text"
            name="title"
            value={values.title ?? ""}
            onChange={handleChange}
            className={styles.chapternameinput}
          ></input>

          <div className={styles.filebox}>
            <p className={styles.title}>챕터 썸네일</p>
            <input
              type="file"
              accept="image/jpg, image/jpeg, image/png string"
              name="thumbnail"
              ref={fileInput}
              id="ex_file"
              // value={values.thumbnail ?? ""}
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
            <OrangeButton name={"수정하기"} />
          ) : (
            <OrangeButton type={"submit"} name={"올리기"} />
          )}
        </form>
      </section>
    </>
  );
};
export default UploadChapterPage;
