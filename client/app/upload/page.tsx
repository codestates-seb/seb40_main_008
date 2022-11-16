"use client";
import styles from "./upload.module.css";
import React from "react";
import { useSession } from "next-auth/react";
import SignInButton from "../../components/Buttons/SignInButton";
import BaseNavbar from "../../components/BaseNavBar/BaseNavbar";

const UploadPage = () => {
  const session = useSession();
  console.log(session);
  if (session.status === "loading") return <p>loading</p>;
  if (session.status === "unauthenticated")
    return (
      <>
        <p> you are not authorized</p> <SignInButton isSignIn={false} />
      </>
    );
  if (!session) return <p>null</p>;
  if (session.status === "authenticated")
    return (
      <div className="main">
        <BaseNavbar />
        <section className={styles.uploadpage}>
          <form className={styles.form1}>
            <p className={styles.title}>클래스명</p>
            <input className={styles.classnameinput}></input>
            <p className={styles.title}>카테고리</p>
            <form>
              <select id="fruit" name="fruit" className={styles.select}>
                <option value="">-- 선택하세요 --</option>
                <optgroup label="봄" className={styles.label}>
                  <option value="strawberry">딸기</option>
                  <option value="banana">바나나</option>
                </optgroup>
                <optgroup label="여름" className={styles.label}>
                  <option value="mango">망고</option>
                  <option value="melon">멜론</option>
                  <option value="grape">포도</option>
                  <option value="watermelon">수박</option>
                </optgroup>
                <optgroup label="가을" className={styles.label}>
                  <option value="apple">사과</option>
                  <option value="pear">배</option>
                </optgroup>
                <optgroup label="겨울" className={styles.label}>
                  <option value="mandarine">귤</option>
                </optgroup>
              </select>
            </form>
            <p className={styles.title}>강의 소개</p>
            <textarea className={styles.introduceClass}></textarea>
            <p className={styles.title}>강사 소개</p>
            <textarea className={styles.introduceInstructor}></textarea>

            <div className={styles.filebox}>
              <p className={styles.title}>클래스 썸네일 업로드</p>
              <label htmlFor="ex_file">+</label>
              <input type="file" id="ex_file" />
            </div>
            <div className={styles.uploadimg}></div>
            <button className={styles.openclassbtn}> 강좌 개설하기</button>
          </form>
        </section>
      </div>
    );
};

export default UploadPage;
