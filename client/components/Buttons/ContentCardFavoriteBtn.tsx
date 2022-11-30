"use client";
import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from "@fortawesome/free-solid-svg-icons";
import styles from "../content/ContentInfo.module.css";
import { cookies } from "next/headers";
interface ContentCardFavoriteProps {
  contentId: number;
}

export const ContentCardFavoriteBtn = ({
  contentId,
}: ContentCardFavoriteProps) => {
  const [like, setLike] = useState(false);

  const handleWishCheck = () => {
    setLike(!like);
    // console.log("like", like);
  };

  useEffect(() => {
    fetch(`https://pioneroroom.com/auth/${contentId}/likes`, {
      method: "POST",
      headers: {
        Authorization: `Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoibWluZ2lqODAzMEBnbWFpbC5jb20iLCJzdWIiOiJtaW5naWo4MDMwQGdtYWlsLmNvbSIsImlhdCI6MTY2OTgwNTUxMiwiZXhwIjoxNjY5ODE2MzEyfQ.JijkEWxw3ldhe_QNfuZzO_jXzX_6z0INT3MYjCZVehu8kBcj2PZH7AQ9C9431QjSD_dzqoaAZuFtBLiyOe9FZw`,
      },
      body: JSON.stringify({ liked: true }),
    })
      .then((res) => {
        res.json();
      })
      .then((data) => {
        console.log("성공", data);
      })
      .catch((error) => {
        setLike(!like);
      });
  }, [like]);

  return (
    <>
      <button onClick={handleWishCheck} className={styles.zzimbtn}>
        <FontAwesomeIcon
          icon={faHeart}
          className={`${like ? styles.icon : styles.clickicon}`}
        />
        북마크
      </button>
    </>
  );
};
