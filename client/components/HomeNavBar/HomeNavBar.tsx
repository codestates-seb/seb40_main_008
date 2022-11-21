"use client";
import React, { useState, useEffect } from "react";
import Link from "next/link";
import Image from "next/image";
import { UseScrollBar } from "../../hooks/\bScrollBar/UseScrollBar";
import styles from "./HomeNavBar.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars } from "@fortawesome/free-solid-svg-icons";

const HomeNavBar = () => {
  const [isLogin, setIsLogin] = useState(false);

  const { show } = UseScrollBar();

  const scrollTopBtn = () => {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  return (
    <nav className={`${show ? styles.nav : styles.change_nav} ${styles.tab}`}>
      <div className="logo">
        <FontAwesomeIcon icon={faBars} className={styles.font} />
        <button className={styles.logo} onClick={scrollTopBtn}>
          class4989
        </button>
      </div>
      <div>
        {isLogin ? (
          <Link href={"/mypage"}>
            <Image
              className="myimg"
              alt="myimg"
              src="/img/myimg.png"
              width={40}
              height={40}
            />
          </Link>
        ) : (
          <Link className={styles.login} href={"/login"}>
            Login
          </Link>
        )}
      </div>
    </nav>
  );
};

export default HomeNavBar;
