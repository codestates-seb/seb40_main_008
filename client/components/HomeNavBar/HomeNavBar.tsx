"use client";
import React, { useState, useEffect } from "react";
import Link from "next/link";
import Image from "next/image";
import { UseScrollBar } from "../../hooks/\bScrollBar/UseScrollBar";
import styles from "./HomeNavBar.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars } from "@fortawesome/free-solid-svg-icons";
import { useSession } from "next-auth/react";

const HomeNavBar = () => {
  const session = useSession();
  const { show } = UseScrollBar();
  // console.log(window.scrollY);

  const scrollTopBtn = () => {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  return (
    <>
      {window.scrollY < 60 ? (
        <nav className={styles.firstNav}>
          <div className={styles.logowrapper}>
            <FontAwesomeIcon icon={faBars} className={styles.font} />
            <button className={styles.logo} onClick={scrollTopBtn}>
              class4989
            </button>
          </div>
          <div>
            {session.status === "authenticated" ? (
              <Link href={"/mypage"}>
                <Image
                  className="myimg"
                  alt="myimg"
                  src="/img/myimg.png"
                  width={35}
                  height={35}
                />
              </Link>
            ) : (
              <Link className={styles.login} href={"/login"}>
                Login
              </Link>
            )}
          </div>
        </nav>
      ) : (
        <nav className={`${show ? styles.nav : styles.change_nav} `}>
          <div className="logo">
            <FontAwesomeIcon icon={faBars} className={styles.font} />
            <button className={styles.logo} onClick={scrollTopBtn}>
              class4989
            </button>
          </div>
          <div>
            {session.status === "authenticated" ? (
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
      )}
    </>
  );
};

export default HomeNavBar;
