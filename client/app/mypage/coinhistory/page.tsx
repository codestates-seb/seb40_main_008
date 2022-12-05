import { cookies } from "next/headers";
import React from "react";
import BaseNavbar from "../../../components/BaseNavBar/BaseNavbar";
import CoinHistorySection from "./CoinHistorySection";

const getCoinCharge = async () => {
  const token = cookies().get("accessToken")?.value;
  try {
    const res = await fetch(`https://pioneroroom.com/auth/coincharge`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    const data = await res.json();
    return data;
  } catch (error) {
    console.error(error);
  }
};

const coinHistoryPage = async () => {
  const data = await getCoinCharge();
  return (
    <>
      <BaseNavbar page={"back"} />
      <CoinHistorySection data={data} />
    </>
  );
};

export default coinHistoryPage;
