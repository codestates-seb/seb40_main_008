"use client";
import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBookmark } from "@fortawesome/free-solid-svg-icons";
import styles from "../content/ContentInfo.module.css";

interface ContentCardWishProps {
  contentId: number;
}

export const ContentCardWishBtn = ({ contentId }: ContentCardWishProps) => {
  const [wish, setWish] = useState(false);

  const handleWishCheck = () => {
    setWish(!wish);
    console.log("wish", wish);
  };

  useEffect(() => {
    fetch(`https://pioneroroom.com/auth/${contentId}/wish`, {
      method: "POST",
      headers: {
        Authorization:
          "Bearer " +
          "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoidG1kZGxmMjVAbmF2ZXIuY29tIiwic3ViIjoidG1kZGxmMjVAbmF2ZXIuY29tIiwiaWF0IjoxNjY5NzE2MjY3LCJleHAiOjE2Njk3MTgwNjd9.EVhZJKXoCJBprhFi_w1rfVRqnaQCPV6g0MoK_lhBYhN2-T92vWX36aMQW2Gp9aYf8MazHgKXt9GUmDXdwD92UQ",
      },
      body: JSON.stringify({ wish: wish }),
    })
      .then((res) => {
        res.json();
      })
      .then((data) => {
        console.log("성공", data);
      })
      .catch((error) => {
        setWish(!wish);
      });
  }, [wish]);

  return (
    <>
      <button onClick={handleWishCheck} className={styles.zzimbtn}>
        <FontAwesomeIcon
          icon={faBookmark}
          className={`${wish ? styles.icon : styles.clickicon}`}
        />
        북마크
      </button>
    </>
  );
};
