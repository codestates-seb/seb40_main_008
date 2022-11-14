"use client";
import React, { useState, useEffect } from "react";
import Link from "next/link";
import Image from "next/image";
import styles from "./HomeNavBar.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars } from "@fortawesome/free-solid-svg-icons";

const HomeNavBar = () => {
  const [isLogin, setIsLogin] = useState(false);

  const [show, setShow] = useState(true);
  const [lastScrollY, setLastScrollY] = useState(0);

  const controlNavbar = () => {
    if (typeof window !== "undefined") {
      if (window.scrollY > lastScrollY) {
        // if scroll down hide the navbar
        setShow(false);
      } else {
        // if scroll up show the navbar
        setShow(true);
      }

      // remember current page location to use in the next move
      setLastScrollY(window.scrollY);
    }
  };

  useEffect(() => {
    if (typeof window !== "undefined") {
      window.addEventListener("scroll", controlNavbar);

      // cleanup function
      return () => {
        window.removeEventListener("scroll", controlNavbar);
      };
    }
  }, [lastScrollY]);
  const scrollTopBtn = () => {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  return (
    <nav className={show ? styles.nav : styles.change_nav}>
      <div className="div1">
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
