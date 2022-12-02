"use client";
import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCartShopping } from "@fortawesome/free-solid-svg-icons";
import styles from "../content/ContentInfo.module.css";
import { getCookie } from "cookies-next";
interface ContentCardWishProps {
  contentId: number;
}

export const ContentCardWishBtn = (props: ContentCardWishProps) => {
  const { contentId } = props;
  const token = getCookie("accessToken");
  const [wish, setWish] = useState(false);

  const handleWishCheck = () => {
    setWish((prev) => !prev);
  };

  useEffect(() => {
    fetch(`https://pioneroroom.com/auth/${contentId}/wish`, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => {
        res.json();
      })
      .then((data) => {
        console.log("성공", data);
      })
      .catch((error) => { });
  }, [wish]);

  return (
    <>
      <button onClick={handleWishCheck} className={styles.zzimbtn}>
        <FontAwesomeIcon
          color="red"
          width={24}
          icon={faCartShopping}
          className={`${wish ? styles.icon : styles.clickicon}`}
        />
        찜하기
      </button>
    </>
  );
};
