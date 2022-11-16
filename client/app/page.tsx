import React from "react";
import QuestionCard from "../components/Card/QuestionCard";
import Carousel from "../components/Carousel/Carousel";
import HomeNavBar from "../components/HomeNavBar/HomeNavBar";
import { ICarousel } from "../types/carousel";
import TabNavigator from "../components/TabNavigator/TabNavigator";

const getGoogleSession = async () => { };

const getQuestionList = async () => {
  const response = await fetch(`https://pioneroroom.com/questionlist`);
  const data = await response.json();
  return data;
};

const page = async ({ Question }: any) => {
  // const imageArr = await getCarouselImages();
  const data = await getQuestionList();

  return (
    <div className="main">
      <HomeNavBar />
      {/* <Carousel carousel={imageArr} /> */}
      <div className="contentbody">
        {data.data.map((e: any) => {
          return <QuestionCard key={e.questionId} question={e} />;
        })}
      </div>

      <TabNavigator activeLink={""} />
    </div>
  );
};

export default page;
