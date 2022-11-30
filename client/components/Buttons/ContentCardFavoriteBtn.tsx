import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from "@fortawesome/free-solid-svg-icons";
import styles from "../content/ContentInfo.module.css";
import { cookies } from "next/headers";
interface ContentCardFavoriteProps {
  contentId: number;
  likecount: number;
}

export const ContentCardFavoriteBtn = ({
  contentId,
  likecount,
}: ContentCardFavoriteProps) => {
  const [like, setLike] = useState(false);
  const token = cookies().get("accessToken")?.value ?? "";

  const handleWishCheck = () => {
    setLike(!like);
    console.log("like", like);
  };

  useEffect(() => {
    fetch(`https://pioneroroom.com/auth/${contentId}/likes`, {
      method: "PATCH",
      headers: {
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({ liked: like }),
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
