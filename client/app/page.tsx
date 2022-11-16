import HomeNavBar from "../components/HomeNavBar/HomeNavBar";
import TabNavigator from "../components/TabNavigator/TabNavigator";
import { CarouselInfo } from "../types/homeScreen/carousel";
import { Content } from "../types/homeScreen/mainVideoContents";
import HomeCarouselSection from "./HomeCarouselSection";
import HomeClassesSection from "./HomeClassesSection";

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
//dadsadas
//rkrkrkrk
//dadsadsadasdasdsa
//adsadsadasdsadasdaasdas
const getCarouselInfo = async (): Promise<Array<CarouselInfo>> => {
  try {
    const response = await fetch(
      "https://run.mocky.io/v3/dd10a02d-8e88-483a-a270-c284b4d4e81d"
    );
    const { carouselInfo } = await response.json();
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
      <HomeNavBar />
      <HomeCarouselSection carouselList={carouselList} />
      <HomeClassesSection contentList={contentsList} />
      <TabNavigator activeLink={"home"} />
    </>
  );
};

export default page;
