import HomeNavBar from "../components/HomeNavBar/HomeNavBar";
import TabNavigator from "../components/TabNavigator/TabNavigator";
import { CarouselInfo } from "../types/homeScreen/carousel";
import { Content } from "../types/homeScreen/mainVideoContents";
import HomeCarouselSection from "./HomeCarouselSection";
import HomeClassesSection from "./HomeClassesSection";
import styles from "./styles/page.module.css";
import Image from "next/image";
import HomeCarouselTitle from "./HomeCarouselTitle";
import { Erica_One } from "@next/font/google";
import { relative } from "path";
import { HomeCarouselList } from "../components/Carousel/HomeCarouselList";

const getClassesContents = async (): Promise<Array<Content>> => {
  try {
    const response = await fetch(
      "https://run.mocky.io/v3/3990c908-5af6-4850-9501-fa41adb80109",
      {
        next: {
          revalidate: 60,
        },
      }
    );
    const { contentsList } = await response.json();
    return contentsList;
  } catch (error) {
    console.error(error);
    return [];
  }
};

const getCarouselInfo = async (): Promise<Array<CarouselInfo>> => {
  try {
    const response = await fetch(
      "https://run.mocky.io/v3/dd10a02d-8e88-483a-a270-c284b4d4e81d"
    );
    const { carouselInfo } = await response.json();
    console.log("C", carouselInfo.length);
    return carouselInfo;
  } catch (error) {
    alert(error);
    return [];
  }
};

const page = async () => {
  const contentsList = await getClassesContents();
  const carouselList = await getCarouselInfo();
  return (
    <>
      <div className={styles.contentbody}>
        <HomeNavBar />
        <div className={styles.carousel}>
          <HomeCarouselSection caroselList={carouselList}>
            {carouselList.map((e) => (
              <div
                key={e.id}
                style={{ position: "relative", border: "1px solid orange" }}
              >
                <Image
                  key={e.id}
                  src={e.src}
                  alt="img"
                  //fill = true 를 쓰려면 상위 div가 relative
                  width={412}
                  height={462}
                  style={{ objectFit: "cover" }}
                />
                <div
                  style={{
                    height: "200px",
                    width: "200px",
                    border: "1px solid red",
                    position: "absolute",
                    zIndex: 1000,
                    bottom: 0,
                  }}
                >
                  <h2 style={{ color: "black" }}>{e.title}</h2>
                </div>
              </div>
            ))}
          </HomeCarouselSection>
        </div>
        <HomeClassesSection contentList={contentsList} />
        <TabNavigator activeLink={"home"} />
      </div>
    </>
  );
};

export default page;
