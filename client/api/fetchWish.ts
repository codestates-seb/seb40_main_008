import { getCookie } from "cookies-next";
export async function patchWish(contentId: number, wish: boolean) {
  const token = getCookie();

  console.log("sadasasd", token);
  const requestOptions = {
    method: "PATCH",
    headers: {
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({ wish: wish }),
  };
  try {
    const response = await fetch(
      `https://pioneroroom.com/auth/${contentId}/wish`,
      requestOptions
    );
    const data = await response.json();
    console.log("data", data);
    return data.status;
  } catch (err) {
    console.error(err);
  }
}
