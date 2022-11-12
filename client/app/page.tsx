import React from "react";
import QuestionCard from "../components/Card/QuestionCard";
import Carousel from "../components/Carousel/Carousel";
import HomeNavBar from "../components/HomeNavBar/HomeNavBar";
import { ICarousel } from "../types/carousel";

const getGoogleSession = async () => {};

const getQuestionList = async () => {
  const response = await fetch("https://pioneroroom.com/questionlist");
  const data = await response.json();
  return data;
};

const getCarouselImages = async () => {
  const response = await fetch(
    "https://rajmdhzzh0zidy3bkmrny2liadj1.requestly.me/AmateurAmaranthThrush"
  );
  const json = await response.json();
  const imageArr: ICarousel[] = json.data;
  return imageArr;
};

const page = async ({ Question }: any) => {
  // const imageArr = await getCarouselImages();
  const data = await getQuestionList();

  return (
    <div>
      <HomeNavBar />
      {/* <Carousel carousel={imageArr} /> */}
      {data.data.map((e: any) => {
        return <QuestionCard key={e.questionId} question={e} />;
      })}
    </div>
  );
};

export default page;
