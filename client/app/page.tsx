import HomeNavBar from "../components/HomeNavBar/HomeNavBar";
import TabNavigator from "../components/TabNavigator/TabNavigator";
import { CarouselInfo } from "../types/homeScreen/carousel";
import { Content } from "../types/homeScreen/mainVideoContents";
import HomeCarouselSection from "./HomeCarouselSection";
import HomeClassesSection from "./HomeClassesSection";
import styles from "./styles/page.module.css";
import CarouselImageWithText from "../components/Carousel/CarouselImageWithText";

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
          <HomeCarouselSection>
            {carouselList.map((e) => (
              <CarouselImageWithText
                key={e.id}
                title={e.title}
                subtitle={e.subtitle}
                src={e.src}
              />
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
