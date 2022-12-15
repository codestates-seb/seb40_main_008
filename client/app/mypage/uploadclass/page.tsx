import React from "react";
import BaseNavbar from "../../../components/BaseNavBar/BaseNavbar";
import UploadClassSection from "../../../components/Card/UploadClassSection";
import { ICategorySearchResult } from "../../../types/category_search/categorySearchType";
import verifyLogin from "../../../utils/VerifyLogin";
import { redirect } from "next/navigation";
import { cookies } from "next/headers";

const getMyUploadClasses = async (): Promise<Array<ICategorySearchResult>> => {
  const token = cookies().get("accessToken")?.value;

  const response = await fetch(
    `https://pioneroroom.com/auth/mypage/myuploadclass`,
    {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }
  );
  const { contentsList } = await response.json();
  return contentsList;
};

const MyUploadClassPage = async () => {
  const isLogin = await verifyLogin();

  if (!isLogin) redirect(`/login`);

  const myUploadclass = await getMyUploadClasses();

  return (
    <>
      <BaseNavbar />
      <UploadClassSection contentsList={myUploadclass} />
    </>
  );
};

export default MyUploadClassPage;
