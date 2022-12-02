"use client";
import React from "react";
import { delMyClass, editMyClass } from "../../utils/api/classEditDel";
import Curriculumdelete from "./Curriculumdelete";
import styles from "./DelEditbtn.module.css";

const DelEditbtn = (props: { id: number }) => {
  return (
    <>
      <button className={styles.btn} onClick={() => editMyClass(props.id)}>
        수정
      </button>
      <Curriculumdelete
        url={`https://pioneroroom.com/auth/contents/`}
        Id={props.id}
      />
    </>
  );
};

export default DelEditbtn;
