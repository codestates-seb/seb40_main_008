"use client";
import styles from "./upload.module.css";
import React, { useEffect, useMemo, useRef, useState } from "react";
import SignInButton from "../../components/Buttons/SignInButton";
import BaseNavbar from "../../components/BaseNavBar/BaseNavbar";
import Image from "next/image";
import { getCookie } from "cookies-next";
import { useRouter, useSearchParams } from "next/navigation";

import { UploadClassType, UploadImage } from "../../types/uploadclass";

const formData = new FormData();

const UploadPage = () => {
  const token = getCookie("accessToken");
  const router = useRouter();
  const searchParams = useSearchParams();
  const query = searchParams.get("slug");
  const thumbnail = searchParams.get("thumbnail");
  const contentsId = searchParams.get("contentsId");
  const Categories = searchParams.get("Categories");
  const title = searchParams.get("title");
  const details = searchParams.get("details");
  const tutorDetail = searchParams.get("tutorDetail");
  const price = searchParams.get("price");

  const session = {
    status: "authenticated",
  };

  const queryContents = {
    thumbnail: thumbnail,
    categories: Categories,
    title: title,
    details: details,
    tutorDetail: tutorDetail,
    price: price,
  };

  const img = {
    file: null,
    thumbnail: thumbnail,
    type: null,
  };

  const fileInput = useRef<HTMLInputElement>(null);

  const [values, setValues] = useState<UploadClassType>(queryContents);
  const [imageFile, setImageFile] = useState<UploadImage | null>(img);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValues({
      ...values,
      [e.target.name]: e.target.value,
    });
  };

  const handleTextChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setValues({
      ...values,
      [e.target.name]: e.target.value,
    });
  };

  const handleOptionChange = (e: React.FormEvent<HTMLSelectElement>) => {
    setValues({
      ...values,
      categories: e.currentTarget.value,
    });
  };

  const handleSubmit = (
    e: React.FormEvent<HTMLFormElement>,
    formData: FormData
  ) => {
    e.preventDefault();

    if (
      values.categories === null ||
      values.details === null ||
      values.price === null ||
      values.title === null ||
      values.thumbnail === null ||
      values.tutorDetail === null
    ) {
      alert("모든 값을 입력해주세요");
      return;
    }
    if (formData.get("thumbnail") === null) {
      alert("썸네일을 바꿔주세요");
    }

    formData.append("categories", values.categories);
    formData.append("details", values.details);
    formData.append("price", values.price);
    formData.append("tutorDetail", values.tutorDetail);
    formData.append("title", values.title);

    if (query !== "edit") {
      fetch("https://pioneroroom.com/auth/uploadcontents", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
        },
        body: formData,
      }).then((res) => {
        formData.delete("categories");
        formData.delete("details");
        formData.delete("price");
        formData.delete("tutorDetail");
        formData.delete("title");
        formData.delete("thumbnail");
        console.log(res);
        if (res.ok) {
          router.push(`/mypage/uploadclass`);
        }
      });
    } else {
      fetch(`https://pioneroroom.com/auth/contents/${contentsId}`, {
        method: "PATCH",
        headers: {
          Authorization: `Bearer ${token}`,
        },
        body: formData,
      }).then((res) => {
        formData.delete("categories");
        formData.delete("details");
        formData.delete("price");
        formData.delete("tutorDetail");
        formData.delete("title");
        formData.delete("thumbnail");
        if (res.ok) {
          router.push(`/mypage/uploadclass`);
        }
      });
    }
  };

  const handleClickFileInput = () => {
    fileInput.current?.click();
  };

  const uploadfile = (e: React.ChangeEvent<HTMLInputElement>) => {
    formData.delete("thumbnail");
    const fileList = e.target.files;
    if (fileList && fileList[0]) {
      const url = URL.createObjectURL(fileList[0]);

      formData.append("thumbnail", fileList[0]);

      setImageFile({
        file: fileList[0],
        thumbnail: url,
        type: fileList[0].type.slice(0, 5),
      });

      setValues({
        ...values,
        thumbnail: fileList[0],
      });
    }
  };

  const showImage = useMemo(() => {
    if (!imageFile && imageFile == null) {
      return <p>비어있는 프로필</p>;
    }

    return (
      <Image
        className={styles.thumbnail}
        src={imageFile.thumbnail ?? "/"}
        alt={imageFile.type ?? "/"}
        sizes="(max-width: 768px) 100vw,
				(max-width: 1200px) 50vw,
				33vw"
        width={290}
        height={190}
        onClick={handleClickFileInput}
        style={{ objectFit: "contain", borderRadius: "4px" }}
      />
    );
  }, [imageFile]);

  // if (session.status === "loading") return <p>loading</p>;
  if (session.status === "unauthenticated")
    return (
      <>
        <p> you are not authorized</p> <SignInButton isSignIn={false} />
      </>
    );
  if (!session) return <p>null</p>;

  if (session.status === "authenticated")
    return (
      <>
        <BaseNavbar name={"강좌 개설하기"} page={"back"} />
        <section className={styles.uploadpage}>
          <form
            onSubmit={(e) => handleSubmit(e, formData)}
            className={styles.form}
          >
            <p className={styles.classtitle}>클래스명</p>
            <input
              type="text"
              name="title"
              value={values.title}
              onChange={handleChange}
              className={styles.classnameinput}
            ></input>
            <p className={styles.title}>카테고리</p>
            <select
              id="categories"
              value={values.categories}
              name="categories"
              onChange={handleOptionChange}
              className={styles.select}
            >
              <option value="choice">-- 선택하세요 --</option>
              <optgroup className={styles.label}>
                <option value="드로잉">드로잉</option>
                <option value="성공 마인드">성공 마인드</option>
                <option value="베이킹/디저트">베이킹</option>
                <option value="금융/재테크">금융,재테크</option>
                <option value="창업/부업">창업,부업</option>
                <option value="프로그래밍">프로그래밍</option>
                <option value="영어">영어</option>
                <option value="공예">공예</option>
                <option value="요리/음료">요리/음료</option>
                <option value="마켓팅">마켓팅</option>
                <option value="라이프 스타일">라이프 스타일</option>
                <option value="사진/영상">사진/영상</option>
                <option value="음악">음악</option>
              </optgroup>
            </select>

            <p className={styles.title}>강의 가격</p>
            <input
              type="number"
              name="price"
              value={values.price}
              onChange={handleChange}
              className={styles.classPrice}
            />
            {values.price % 1000 === 0 ? null : (
              <div className={styles.alertMessage}>
                1,000원 단위로 입력 해주세요.
              </div>
            )}
            {values.price <= 50000 ? null : (
              <div className={styles.alertMessage}>
                50,000원 이하로 입력 해주세요.
              </div>
            )}

            <p className={styles.title}>강의 소개</p>
            <textarea
              name="details"
              onChange={handleTextChange}
              value={values.details}
              className={styles.introduceClass}
            ></textarea>
            <p className={styles.title}>강사 소개</p>
            <textarea
              name="tutorDetail"
              onChange={handleTextChange}
              value={values.tutorDetail}
              className={styles.introduceInstructor}
            ></textarea>

            <div className={styles.filebox}>
              <p className={styles.title}>클래스 썸네일</p>
              <input
                type="file"
                accept="image/png, image/jpg, image/jpeg"
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
              <button type="submit" className={styles.openclassbtn}>
                수정하기
              </button>
            ) : (
              <button type="submit" className={styles.openclassbtn}>
                강좌 개설하기
              </button>
            )}
          </form>
        </section>
      </>
    );
};

export default UploadPage;
