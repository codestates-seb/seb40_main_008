"use client";
import React, {
  useMemo,
  Children,
  useLayoutEffect,
  useState,
  useRef,
  useEffect,
  useCallback,
} from "react";
import { CarouselInfo } from "../types/homeScreen/carousel";
import styles from "./styles/HomeCarouselSection.module.css";

interface HomeCarouselSectionProps {
  children: any;
  carouselList: Array<CarouselInfo>;
}

const HomeCarouselSection = ({
  children,
  carouselList,
}: HomeCarouselSectionProps) => {
  console.log(children);
  const containerRef = useRef<HTMLUListElement>(null);
  const intervalRef = useRef<MutableRefObject>(null);
  const [current, setCurrent] = useState(1);
  const [translateX, setTranslateX] = useState(0);

  const actionHandler = useCallback(
    (mode) => {
      if (containerRef.current) {
        containerRef.current.style.transitionDuration = "1000ms";
      }
      if (mode === "prev") {
        if (current <= 1) {
          setTranslateX(0);
          setCurrent(children.length);
        } else {
          if (containerRef.current) {
            setTranslateX(containerRef.current.clientWidth * (current - 1));
            setCurrent((prev) => --prev);
          }
        }
      } else if (mode === "next") {
        if (current >= children.length) {
          if (containerRef.current) {
            setTranslateX(
              containerRef.current.clientWidth * (children.length + 1)
            );
            setCurrent(1);
          }
        } else {
          if (containerRef.current) {
            setTranslateX(containerRef.current.clientWidth * (current + 1));
            setCurrent((prev) => ++prev);
          }
        }
      }
    },
    [current, children]
  );

  //this is for infinite scroll smooth effect
  useEffect(() => {
    const transitionEnd = () => {
      if (current <= 1 && containerRef.current) {
        containerRef.current.style.transitionDuration = "0ms";
        setTranslateX(containerRef.current.clientWidth * current);
      }
      if (current >= children.length) {
        if (containerRef.current) {
          containerRef.current.style.transitionDuration = "0ms";
          setTranslateX(containerRef.current.clientWidth * children.length);
        }
      }
    };
    document.addEventListener("transitionend", transitionEnd);

    return () => {
      document.removeEventListener("transitionend", transitionEnd);
    };
  }, [current, children]);

  //for autoplay
  useEffect(() => {
    if (intervalRef.current) {
      clearInterval(intervalRef.current);
    }
    intervalRef.current = setInterval(() => {
      actionHandler("next");
    }, 3000);
    return () => {
      if (intervalRef.current) {
        clearInterval(intervalRef.current);
      }
    };
  }, [actionHandler]);

  const slides = useMemo(() => {
    if (children.length > 1) {
      let items = children.map((child: any, index: number) => (
        <li key={index} className={styles.Slide}>
          {child}
        </li>
      ));

      return [
        <li key={children.length + 1} className={styles.Slide}>
          {children[children.length - 1]}
        </li>,
        ...items,
        <li key={children.length + 2} className={styles.Slide}>
          {children[0]}
        </li>,
      ];
    }
    return <li className={styles.Slide}>{children[0]}</li>;
    //return null;
  }, [children]);

  useLayoutEffect(() => {
    if (containerRef.current) {
      setTranslateX(containerRef.current.clientWidth * current);
    }
  }, []);

  return (
    <section className={styles.carouselSection}>
      <ul
        ref={containerRef}
        className={styles.Container}
        style={{
          transform: `translate3d(${-translateX}px,0,0)`,
        }}
      >
        {slides}
      </ul>
      <div className={styles.titlecontainer}>
        asdas
        {carouselList &&
          carouselList.map((e) => (
            <>
              <p className={styles.title} key={e.id}>
                {e.title}dsa
              </p>
              <p className={styles.subtitile} key={e.id}>
                {e.subtitle}
              </p>
            </>
          ))}
      </div>

      <button
        onClick={() => actionHandler("prev")}
        className={`${styles.Btn} ${styles.BtnLeft}`}
      >
        {"<"}
      </button>
      <button
        onClick={() => actionHandler("next")}
        className={`${styles.Btn} ${styles.BtnRight}`}
      >
        {">"}
      </button>
    </section>
  );
};

export default HomeCarouselSection;
