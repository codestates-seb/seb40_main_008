import { getCookie } from "cookies-next";

export async function patchWish(contentId: number, wish: boolean) {
  const token = getCookie("accessToken");
  console.log("sdsadasd", token);

  const requestOptions = {
    method: "POST",
    headers: {
      Authorization: `Bearer ${token}`,
    },
    // BUG: payload not being accepted
    body: JSON.stringify({ wish: String(true) }),
  };
  try {
    const response = await fetch(
      `https://pioneroroom.com/auth/${contentId}/wish`,
      requestOptions
    );
    const data = await response.json();
    return data.status;
  } catch (err) {
    console.error(err);
    return undefined;
  }
}
